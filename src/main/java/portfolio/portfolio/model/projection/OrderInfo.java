package portfolio.portfolio.model.projection;

import java.time.LocalDateTime;
import java.util.List;

public class OrderInfo {

    private int id;
    private LocalDateTime orderDate;
    private double amount;

    private String customerFirstName;
    private String customerLastName;
    private String userEmail;

    private List<OrderDetailInfo> details;

    public OrderInfo() {
    }

    public OrderInfo(int id, LocalDateTime orderDate, double amount, String userFirstName, String userLastName, String userEmail, List<OrderDetailInfo> details) {
        this.id = id;
        this.orderDate = orderDate;
        this.amount = amount;
        this.customerFirstName = userFirstName;
        this.customerLastName = userLastName;
        this.userEmail = userEmail;
        this.details = details;
    }

    public OrderInfo(int id, LocalDateTime orderDate, double amount, String customerFirstName, String customerLastName, String userEmail) {
        this.id = id;
        this.orderDate = orderDate;
        this.amount = amount;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.userEmail = userEmail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public List<OrderDetailInfo> getDetails() {
        return details;
    }

    public void setDetails(List<OrderDetailInfo> details) {
        this.details = details;
    }

}
