package portfolio.portfolio.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import portfolio.portfolio.logic.UserService;
import portfolio.portfolio.model.User;
import portfolio.portfolio.registration.token.ConfirmationToken;
import portfolio.portfolio.registration.token.ConfirmationTokenRepository;
import portfolio.portfolio.registration.token.ConfirmationTokenService;

import java.time.LocalDateTime;

@Controller
@RequestMapping(path = "registration")
public class RegistrationController {


    private final ConfirmationTokenService confirmationTokenService;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final UserService userService;
    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(ConfirmationTokenService confirmationTokenService, ConfirmationTokenRepository confirmationTokenRepository, UserService userService, RegistrationService registrationService) {
        this.confirmationTokenService = confirmationTokenService;
        this.confirmationTokenRepository = confirmationTokenRepository;
        this.userService = userService;
        this.registrationService = registrationService;
    }

    @PostMapping("reg")
    public String register(@RequestBody RegistrationRequest request){
        return registrationService.req(request);
    }



    @GetMapping(path = "confirm")
    public String confirm(@RequestParam ("token")String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token).orElseThrow(() -> new IllegalStateException("token not found"));
        LocalDateTime expiredAt = confirmationToken.getExpiredAt();
        if  (confirmationToken.getConfirmedAt() != null) {
            return "alreadyConf";
        }else if (expiredAt.isBefore(LocalDateTime.now())) {
            return "reSend";
        } else  {
            registrationService.confirmToken(token);
            return "confirmed";
            }
        }

        @PostMapping("newToken")
        public String sendNewToken(@RequestParam( value = "email") String email){
            User user = userService.findByEmail(email);
            confirmationTokenService.deleteOldToken(user);
            RegistrationRequest request = userMap(user);
            registrationService.sendNewToken(request);
            return "home";
        }



    @GetMapping()
    public String register(Model model){
        model.addAttribute("user", new User());
//        model.addAttribute("request", new RegistrationRequest());
        return "registrationForm";
    }

    @PostMapping()
    public String addUser(@ModelAttribute @Validated User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registrationForm";
        } else {

            RegistrationRequest request = userMap(user);
            registrationService.req(request);

            return "registerSuccess";
        }
    }
        @DeleteMapping("/user/{id}")
        public ResponseEntity<ConfirmationToken> deleteToken(@PathVariable Integer id){
        confirmationTokenRepository.deleteByUser(userService.findById(id));
        return ResponseEntity.ok().build();
        }


   public RegistrationRequest userMap (User user){
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String email = user.getEmail();
        String password = user.getPassword();
        return new RegistrationRequest(firstName,lastName,email,password);
    }


}
