/**
 * Concrete subclass of Product representing electronic products.
 */
class Electronics extends Product{
    private String brand;
    private int warrantyPeriod;
    public Electronics(String productId,String productName,int availableItems,double price, String brand, int warrantyPeriod){
        super(productId,productName,availableItems,price);
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
    }

    public String getBrand() {
        return brand;
    }

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    /**
     * Displays detailed information about the electronics product.
     */
    @Override
    public void displayInfo(){
        System.out.println("Product Type: Electronics");
        System.out.println("Product ID: " + getProductID());
        System.out.println("Product Name: " + getProductName());
        System.out.println("Available Items: " + getAvailableItems());
        System.out.println("Price: " + getPrice());
        System.out.println("Brand: " + brand);
        System.out.println("Warranty Period: " + warrantyPeriod);
    }

    /**
     * Returns the type of the product.
     * @return The type "Electronics."
     */
    @Override
    public String productType() {
        return "Electronics";
    }
}
