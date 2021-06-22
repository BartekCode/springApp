package portfolio.portfolio.logic;

import org.springframework.stereotype.Service;
import portfolio.portfolio.model.Product;

@Service
public class ProductValidator {


    public boolean validacja(Product product){
        String name = product.getName();
        String description = product.getDescription();
        if (name != null && description != null){
            return true;
        }
        else {
            return false;
        }

    }
    public boolean validateProduct(Product product){
        if (product.getName()!=null && product.getDescription()!=null && product.getPrice()>0){
            return true;
        }else {
            return false;
        }

    }


//    public boolean validacja(String name, String description){
//        if (name != null && description != null){
//            return true;
//        }
//        return false;
//    }
}
