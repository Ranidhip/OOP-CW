import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.io.PrintWriter;
import java.util.Scanner;

class WestminsterShoppingManager implements ShoppingManager{
    public static List<Product> productList;
    private static final int MAX_PRODUCT = 50;
    private static final String FILE_NAME = "product_data.txt";

    /**
     * Constructor for WestminsterShoppingManager class, initializes the product list.
     */
    public WestminsterShoppingManager() {
        this.productList = new ArrayList<>();
    }

    /**
     * Getter method for retrieving the product list.
     * @return List of products in the system.
     */
    public List<Product> getProductList() {
        return productList;
    }

    /**
     * Add a new product to Online shopping system based on user input
     * The method prompts the user for product details such as type,ID,
     * name,availability,price and additional specification(brand,warranty period, size,colour)
     * Check the uniqueness of the product ID before adding to the product list.
     */
    @Override
    public void addProducts(Product product) {
        if (productList.size() < MAX_PRODUCT ) {
            productList.add(product);
            System.out.println("\n!!! Product added successfully.");
        } else {
            System.out.println("\n!!! Storage limit reached.");
        }
    }

    /**
     * Delete a product from the Online Shopping system based on user input.
     * The method prompts the user for the product ID to be deleted.
     * Then it searches for the product with the specific ID and removes it from the product list.
     */
    @Override
    public void deleteProduct(String productId) {
        Product productToDelete = null;
        for(Product product : productList){
            if(product.getProductID().equals(productId)){
                productToDelete = product;
                break;
            }
        }
        if (productToDelete != null) {
            productToDelete.displayInfo();
            productList.remove(productToDelete);
            System.out.println("!!! Product deleted successfully.");
        }else {
            System.out.println("\n!!! Product with ID " + productId + " not found. Please enter the valid product ID.");
        }
        if (productList.isEmpty()) {
            System.out.println("!!! Product list is empty.");
        } else {
            System.out.println("!!! Total number of products left: " + productList.size());
        }
    }

    /**
     * Displays the list of products in the Westminster Online Shopping system.
     * If the product list is empty, a message indicating the absence of products is printed.
     * Otherwise, it prints detailed information for each product in the list.
     */
    @Override
    public void printProducts(){
        if (productList.isEmpty()) {
            System.out.println("!!! Product list is empty.\n");
        } else {
            System.out.println("\nTotal available products in the product list right now: ");
            for (Product product : productList) {
                product.displayInfo();
                System.out.println();
            }
        }
    }

    /**
     * Save the product details to a text file
     */
    @Override
    public void saveFile() {
        try{
            // Declare the file to save the data
            PrintWriter writer = new PrintWriter(FILE_NAME);
            writer.println("Product data of Westminster online shopping system.\n");
            for(Product product : productList){
                if(product instanceof Electronics){
                    writer.println("Electronics/" +
                            product.getProductID()+"/"+
                            product.getProductName() +"/"+
                            product.getPrice() +"/"+
                            product.getAvailableItems()+"/"+
                            ((Electronics)product).getBrand()+"/"+
                            ((Electronics) product).getWarrantyPeriod());

                }else if(product instanceof Clothing){
                    writer.println("Clothing/" +
                            product.getProductName()+"/"+
                            product.getProductID()+"/"+
                            ((Clothing) product).getColor()+"/"+
                            ((Clothing) product).getSize()+"/"+
                            product.getPrice()+"/"+
                            product.getAvailableItems());
                }
            }
            System.out.println("\n!!! Product data saved successfully\n");
            writer.close();
        }catch (Exception e){
            System.out.println("!!! Something went wrong.");
        }
    }

    /**
     * Read the saved file and load the details to the program
     */
    @Override
    public void loadData() {
        productList.clear();
        try {
            // Adding the specific file
            File file = new File(FILE_NAME);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                String[] productDetails = line.split("/");
                if (productDetails.length == 7) {
                    if (productDetails[0].equals("Electronics")) {
                        String productID = productDetails[1];
                        String productName = productDetails[2];
                        double price = Double.parseDouble(productDetails[3]);
                        int availableAmount = Integer.parseInt(productDetails[4]);
                        String brand = productDetails[5];
                        int warrantyPeriod = Integer.parseInt(productDetails[6]);

                        // Create a new Electronic product and add to the system product list
                        Product electronicProduct = new Electronics(productID, productName, availableAmount, price, brand, warrantyPeriod);
                        productList.add(electronicProduct);

                    } else if (productDetails[0].equals("Clothing")){
                        String productID = productDetails[2];
                        String productName = productDetails[1];
                        double price = Double.parseDouble(productDetails[5]);
                        int availableAmount = Integer.parseInt(productDetails[6]);
                        String color = productDetails[3];
                        String size = productDetails[4];

                        // Create a new Clothing product and add to the system product list
                        Product clothingProduct = new Clothing(productID, productName, availableAmount, price, size, color);
                        productList.add(clothingProduct);
                    }
                }
            }
            System.out.println("!!! Product details successfully loaded.");
        }
        catch (IOException e) {
            System.out.println("!!! Something went wrong.");
        }

    }


}