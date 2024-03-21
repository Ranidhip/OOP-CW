class Clothing extends Product{
    private String size;
    private String color;
    public Clothing(String productId,String productName,int availableItems,double price,String size, String color){
        super(productId, productName, availableItems,price);
        this.size=size;
        this.color=color;
    }
    public String getColor() {
        return color;
    }

    public String getSize() {
        return size;
    }

    /**
     * Displays detailed information about the clothing product.
     */
    @Override
    public void displayInfo(){
        System.out.println("Product Type: Clothing");
        System.out.println("Product ID: " + getProductID());
        System.out.println("Product Name: " + getProductName());
        System.out.println("Available Items: " + getAvailableItems());
        System.out.println("Price: " + getPrice());
        System.out.println("Size: " + size);
        System.out.println("Colour: " + color);
    }

    /**
     * Returns the type of the product.
     * @return The type "Clothing."
     */
    @Override
    public String productType() {
        return "Clothing";
    }
}
