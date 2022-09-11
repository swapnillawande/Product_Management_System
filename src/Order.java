import java.time.LocalDate;
import java.util.Map;

public class Order {
    private String orderId;
    private LocalDate orderDate;
    private String orderStatus;
    private Map productList;
    private Customer customer;

    public Order(String orderId, LocalDate orderDate, String orderStatus, Map productList,Customer customer) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.productList = productList;
        this.customer = customer;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Map getProductList() {
        return productList;
    }

    public void setProductList(Map productList) {
        this.productList = productList;
    }
}
