public class Product {
    private String productId;
    private String productName;
    private int price;
    private String color;
    private String manufacturer;
    private int quantity;
    private Category category;


    public Product( String productName, int price, String color,  String manufacturer, int quantity ,Category category) {
//        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.color = color;

        this.manufacturer = manufacturer;
        this.quantity = quantity;

        this.category=category;
    }

    public Product(){

    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}
