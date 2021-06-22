package portfolio.portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import portfolio.portfolio.logic.OrderService;
import portfolio.portfolio.logic.UserService;
import portfolio.portfolio.logic.Utils.Utils;
import portfolio.portfolio.model.Order;
import portfolio.portfolio.model.Role;
import portfolio.portfolio.model.User;
import portfolio.portfolio.model.projection.CartInfo;
import portfolio.portfolio.repository.OrderRepository;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequestMapping("/order")
public class OrderController {
    OrderRepository cartRepository;
    OrderService cartService;
    UserService userService;


    public OrderController(OrderRepository cartRepository, OrderService cartService, UserService userService) {
        this.cartRepository = cartRepository;
        this.cartService = cartService;
        this.userService = userService;
    }

    @ResponseBody
    @PostMapping("/{id}")
    ResponseEntity<Order> createCart(@PathVariable int id, Order order){
        cartService.createOrderForUser(id,order);
        return ResponseEntity.ok().build();
    }



    @GetMapping("/confirm")
    @PreAuthorize("hasAuthority('USER')")
    public String confirmOrder(HttpServletRequest request, Model model, Principal principal){
        User loggedUser = userService.findByEmail(principal.getName());
        CartInfo cartInSession = Utils.getCartInSession(request);
        double toPay = cartInSession.getAmountTotal();
        model.addAttribute("toPay", toPay);
        model.addAttribute("loggedUser", loggedUser);

        return "orderConfirmation";
    }
}
