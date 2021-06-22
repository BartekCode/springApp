package portfolio.portfolio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import portfolio.portfolio.logic.UserService;
import portfolio.portfolio.model.User;
import portfolio.portfolio.model.UserRole;
import portfolio.portfolio.registration.RegistrationRequest;
import portfolio.portfolio.registration.RegistrationService;

import javax.validation.Validator;
import java.util.Set;

@SpringBootApplication
public class PortfolioApplication {



    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = SpringApplication.run(PortfolioApplication.class, args);
        UserService userService = ctx.getBean(UserService.class);
        RegistrationService bean = ctx.getBean(RegistrationService.class);



//        User byEmail = userService.findByEmail("bartek@wp.pl");
//        String string = byEmail.toString();
//        System.out.println(string);

//        userService.createNewPassword("balon@wp.pl", "darek");
//        System.out.println("Pass changed");
    }



    public Validator validator(){
        return new LocalValidatorFactoryBean();
    }

}
