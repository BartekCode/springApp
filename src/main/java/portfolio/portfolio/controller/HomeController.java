package portfolio.portfolio.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import portfolio.portfolio.model.Role;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home(){
        return "home";
    }

    @RequestMapping("/logoutS")
    public String logout(){
        return "logout";
    }

}
