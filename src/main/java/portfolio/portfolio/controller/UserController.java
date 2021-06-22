package portfolio.portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import portfolio.portfolio.logic.UserService;
import portfolio.portfolio.model.User;
import portfolio.portfolio.repository.UserRepository;

import java.security.Principal;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private  final UserRepository userRepository;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "registerForm";
    }

    @PostMapping("/register")
    public String addUser(@ModelAttribute @Validated User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registerForm";
        } else {
            userService.addUser(user);
            return "registerSuccess";
        }
    }

    @GetMapping("/test")
    public String test() {
        return "registerSuccess";
    }


    @GetMapping("/")
    public String showLoggedUser(Model model, Principal principal) {
        if (principal !=null) {
            User loggedUser = userService.findByEmail(principal.getName());
            model.addAttribute("loggedUser", loggedUser);
        }
        return "home";
    }

    @GetMapping("newPassword")
    public String newPassword(Principal principal){
        Random random = new Random();
        User byEmail = userService.findByEmail(principal.getName());
        byEmail.setPassword(random.ints(8,20)
        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        .toString());

        return "home";
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Integer id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
          return   ResponseEntity.ok().build();
        }  else {
             return    ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getUsers(){
        return  ResponseEntity.ok(userRepository.findAll());
    }


}

