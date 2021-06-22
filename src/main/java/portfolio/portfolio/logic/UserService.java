package portfolio.portfolio.logic;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import portfolio.portfolio.model.Role;
import portfolio.portfolio.model.User;
import portfolio.portfolio.registration.token.ConfirmationToken;
import portfolio.portfolio.registration.token.ConfirmationTokenService;
import portfolio.portfolio.adapter.SqlUserRepository;


import java.time.LocalDateTime;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG =
            "user with email %s not found";
    private static final String DEFAULT_ROLE = "ROLE_USER";

    private final SqlUserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Autowired
    public UserService(SqlUserRepository userRepository, PasswordEncoder passwordEncoder, ConfirmationTokenService confirmationTokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.confirmationTokenService = confirmationTokenService;
    }

    public String addUser(User user) {
        user.setRole(Role.USER);
        String passwordHash = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordHash);
        userRepository.save(user);

        //Confirmation token
        String token = UUID.randomUUID().toString(); //tworzymy randomowy token
        ConfirmationToken confToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );
        confirmationTokenService.saveConfirmationToken(confToken);

        //TODO SEND EMAIL
        return token;
    }
    public String reSendToken(User user){
        String token = UUID.randomUUID().toString(); //tworzymy randomowy token
        ConfirmationToken confToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );
        confirmationTokenService.saveConfirmationToken(confToken);
        return token;
    }

    public Set<User> findUsersByRole(Role role) {
        Set<User> allByRoles = userRepository.findAllByRole(Role.USER.toString());
        return allByRoles;
    }

    public User findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user;
    }

//    public int enableUser(String email) {
//        return userRepository.enableAppUser(email);
//    }
    public void enableUserr(String email){
        User byEmail = userRepository.findByEmail(email);
        byEmail.setEnabled(true);
    }


    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return userRepository.findByEmail(email);
//                .orElseThrow(() ->
//                        new UsernameNotFoundException(
//                                String.format(USER_NOT_FOUND_MSG, email)));
    }

    public void createNewPassword(String email, String newPassword) throws NullPointerException {
        Random random = new Random();
        User userByEmail = findByEmail(email);
        if (userByEmail != null) {
            String passwordHash = passwordEncoder.encode(newPassword);
            userByEmail.setPassword(passwordHash);
            System.out.println(userByEmail.getPassword());
            userRepository.save(userByEmail);
        }else {
            System.out.println("nie ma uzytkownika z takim mailem");
        }
    }

    public User findById(Integer userId){
      return   userRepository.findById(userId).orElse(null);
    }
}






