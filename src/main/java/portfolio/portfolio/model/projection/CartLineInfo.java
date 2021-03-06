package portfolio.portfolio.model.projection;

public class CartLineInfo {

    private ProductInfo productInfo;
    private  int quantity;



    public CartLineInfo() {
        this.quantity = 0;
    }

    public ProductInfo getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(ProductInfo productInfo) {
        this.productInfo = productInfo;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getAmount(){//suma ile zaplacimy
        return this.productInfo.getPrice()*this.quantity;
    }
}
