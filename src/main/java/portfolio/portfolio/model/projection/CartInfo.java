package portfolio.portfolio.model.projection;

import java.util.ArrayList;
import java.util.List;

public class CartInfo {
    private int orderId;
    private CustomerInfo customerInfo;
    private final List<CartLineInfo> cartLines = new ArrayList<CartLineInfo>();


    public CartInfo() {
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }

    public List<CartLineInfo> getCartLines() {
        return cartLines;
    }

    private CartLineInfo findLineById(int id){
        for (CartLineInfo line: this.cartLines) {
            if (line.getProductInfo().getId()==id) {
                return line;
            }
        }
        return null;
    }

    public void addProduct(ProductInfo productInfo, int quantity){
        CartLineInfo lineById = this.findLineById(productInfo.getId());
        if (lineById==null){
            lineById= new CartLineInfo();
            lineById.setQuantity(0);
            lineById.setProductInfo(productInfo);
            this.cartLines.add(lineById);
        }
        int newQuantity = lineById.getQuantity() +quantity;
        if (newQuantity<=0){
            this.cartLines.remove(lineById);
        }else {
            lineById.setQuantity(newQuantity);
        }
    }
    public void updateProduct(Integer id, int quantity){//zwiekszamy liczebnosc produktu
        CartLineInfo lineById = this.findLineById(id);
        if (lineById!=null){
            if (quantity<=0){
                this.cartLines.remove(lineById);
            }else {
                lineById.setQuantity(quantity);
            }
        }
    }
    public void removeProduct(ProductInfo productInfo){ //usuwamy product
        CartLineInfo lineById = this.findLineById(productInfo.getId());
        if (lineById!=null){
            this.cartLines.remove(lineById);
        }
    }

    public int getQuantityTotal(){ //liczymy wszystkie sztuki
        int quantity=0;
        for (CartLineInfo line:this.cartLines) {
            quantity+=line.getQuantity();
        }
        return quantity;
    }
    public double getAmountTotal(){
        double total = 0;
        for (CartLineInfo line: this.cartLines) {
            total += line.getAmount();
        }
        return  total;
    }

    public void updateQuantity(CartInfo cartInfo){
        if (cartInfo!=null){
            List<CartLineInfo> cartLines = cartInfo.getCartLines();
            for (CartLineInfo line: cartLines) {
                this.updateProduct(line.getProductInfo().getId(), line.getQuantity());
            }
        }
    }
}
