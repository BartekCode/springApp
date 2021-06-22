package portfolio.portfolio.logic.Utils;

import portfolio.portfolio.model.projection.CartInfo;

import javax.servlet.http.HttpServletRequest;

public class Utils {

    //products int Cart stored in session
    public static CartInfo getCartInSession(HttpServletRequest request){
        CartInfo cartInfo = (CartInfo) request.getSession().getAttribute("cart");
        //if null create one
        if (cartInfo == null){
            cartInfo = new CartInfo();
            //store to seession
            request.getSession().setAttribute("cart", cartInfo);
        }
        return cartInfo;
    }
    //remove cart from the session
    public static void removeCartInSession(HttpServletRequest request){
        request.getSession().removeAttribute("cart");
    }
}
