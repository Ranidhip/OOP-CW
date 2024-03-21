/**
 * Abstract class representing a generic product in the online shopping system.
 * This class provides common attributes and methods that are shared by all types of products.
 */
abstract class Product {
    private String productID;
    private String productName;
    private int availableItems;
    private double price;

    /**
     * Constructor to initialize a product with basic details.
     *
     * @param productID       The unique identifier for the product.
     * @param productName     The name of the product.
     * @param availableItems  The quantity of the product available in stock.
     * @param price           The price of the product.
     */
    public Product(String productID,String productName,int availableItems,double price){
        this.productID = productID;
        this.productName = productName;
        this.availableItems = availableItems;
        this.price = price;

    }

    /**
     * Getter method to retrieve the product ID.
     *
     * @return The product ID.
     */
    public String getProductID() {
        return productID;
    }

    /**
     * Getter method to retrieve the product name.
     *
     * @return The product name.
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Getter method to retrieve the available quantity of the product.
     *
     * @return The available quantity of the product.
     */
    public int getAvailableItems() {
        return availableItems;
    }

    /**
     * Getter method to retrieve the price of the product.
     *
     * @return The price of the product.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Setter method to update the available quantity of the product.
     *
     * @param availableItems The new available quantity of the product.
     */
    public void setAvailableItems(int availableItems) {
        this.availableItems = availableItems;
    }


    /**
     * Abstract method to display detailed information about the product.
     * Subclasses must provide their own implementation.
     */
    public abstract void displayInfo();

    /**
     * Abstract method to retrieve the type of the product.
     * Subclasses must provide their own implementation.
     *
     * @return The type of the product.
     */
    public abstract String productType();


}
