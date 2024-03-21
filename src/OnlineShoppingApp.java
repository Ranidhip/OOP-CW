import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class OnlineShoppingApp {

    // Creating instances of related classes
    public static WestminsterShoppingManager westminsterShoppingManager = new WestminsterShoppingManager();
    public static User user;


    public static void main(String[] args) {
        // Load product data at the start of the program
        westminsterShoppingManager.loadData();

        System.out.println("\n*******************************************************");
        System.out.println("*       WELCOME TO THE WESTMINSTER ONLINE SHOPPING    *");
        System.out.println("*******************************************************");

        Scanner scanner=new Scanner(System.in);
        String choice;

        // Display the menu and options
        do {
            System.out.println("\n\n* * * * * *  M E N U  * * * * * *");
            System.out.println("1. Add");
            System.out.println("2. Delete");
            System.out.println("3. Print");
            System.out.println("4. Save");
            System.out.println("5. GUI");
            System.out.println("6. Exit");

            System.out.print("\nEnter your choice: ");
            choice = scanner.nextLine();

            // Switch case for handling user choices
            switch (choice) {
                case "1":
                    // Add product option
                    addProductToTheSystem();
                    break;
                case "2":
                    // Delete product option
                    deleteProductFromTheSystem();
                    break;
                case "3":
                    //print products option
                    bubbleSort();
                    westminsterShoppingManager.printProducts();
                    break;
                case "4":
                    // Save option
                    System.out.println("\nSaving product data...");
                    westminsterShoppingManager.saveFile();
                    break;
                case "5":
                    loginSignup();
                    break;
                case "6":
                    // Exit option
                    System.out.println("Have a nice day...");
                    break;
                default:
                    // Invalid choice
                    System.out.println("!!! Invalid choice. Please enter a valid option.");
                    break;
                }
        } while (!choice.equals("6"));
        scanner.close();
    }

    /**
     * Ask user for the product information
     * Add product to the system product list
     */
    public static void addProductToTheSystem() {
        Scanner scanner = new Scanner(System.in);
        String productType;
        String productId;
        String productName;
        boolean isUnique;

        System.out.println("\n* * * * * *  A D D  * * * * * * *");

        // Ask for product type
        do {
            System.out.println("1. Electronics");
            System.out.println("2. Clothing");
            System.out.print("What product type do you want to add?: ");
            productType = scanner.nextLine();
        } while (!productType.equals("1") && !productType.equals("2"));

        // Ask for the product ID
        do {
            System.out.print("Product ID: " );
            productId= scanner.nextLine();
            if (productId.isEmpty()) {
                System.out.println("!!! Please enter something.");
            }
            // Check if the product ID is unique
            isUnique = true;
            for(Product product:westminsterShoppingManager.getProductList()){
                if(product.getProductID().equals(productId)){
                    System.out.println("!!! Product ID is not unique. Please enter a different product ID.");
                    isUnique = false;
                    break;
                }
            }
        } while (productId.isEmpty() || !isUnique);

        // Ask for Product Name
        System.out.print("Product Name: ");
        productName= scanner.nextLine();
        while (productName.trim().isEmpty()) {
            System.out.println("!!! Please enter a valid product name. Product name cannot be empty.");
            System.out.print("Product Name: ");
            productName = scanner.nextLine();
        }

        // Ask for available amount
        int availableItems = 0;
        boolean validItems = false;
        while (!validItems) {
            try {
                System.out.print("Available Items: ");
                availableItems = scanner.nextInt();
                if (availableItems >= 0) {
                    validItems = true;
                } else {
                    System.out.println("!!! Please enter a valid number of available items (non-negative).");
                }
            } catch (Exception e) {
                System.out.println("!!! Please enter a valid integer for available items.");
            }
            scanner.nextLine();
        }

        // Ask for the product price
        double price = 0;
        boolean validPrice = false;
        while (!validPrice) {
            try {
                System.out.print("Price: ");
                price = scanner.nextDouble();
                if (price >= 0) {
                    validPrice = true;
                } else {
                    System.out.println("!!! Please enter a valid price (non-negative).");
                }
            } catch (Exception e) {
                System.out.println("!!! Please enter a valid number for price.");
            }
            scanner.nextLine();
        }

        // Ask for Electronic product details
        if (productType.equals("1")) {
            // Ask for product brand
            System.out.print("Brand: ");
            String brand = scanner.nextLine();
            while (brand.trim().isEmpty()) {
                System.out.println("Please enter a valid brand. Brand cannot be empty");
                System.out.print("Brand: ");
                brand = scanner.nextLine();
            }

            // Ask for product warranty period
            int warrantyPeriod = 0;
            boolean validWarranty = false;
            while (!validWarranty) {
                try {
                    System.out.print("Warranty Period: ");
                    warrantyPeriod = scanner.nextInt();
                    if (warrantyPeriod >= 0) {
                        validWarranty = true;
                    } else {
                        System.out.println("!!! Please enter a valid warranty period (non-negative).");
                    }
                } catch (Exception e) {
                    System.out.println("!!! Please enter a valid integer for warranty period.");
                }
            }
            // Create a new Electronic object and add to the system
            Electronics electronics= new Electronics(productId, productName, availableItems, price, brand, warrantyPeriod);
            westminsterShoppingManager.addProducts(electronics);
        }

        // Ask Clothing product details
        else {
            // Ask for the product size
            System.out.print("Size: ");
            String size = scanner.nextLine();
            while (size.trim().isEmpty()) {
                System.out.println("!!! Please enter a valid size. Size cannot be empty.");
                size = scanner.nextLine();
            }

            // Ask for the product color
            System.out.print("Colour: ");
            String color = scanner.nextLine();
            while (color.trim().isEmpty()) {
                System.out.println("!!! Please enter a valid color. Color cannot be empty.");
                color = scanner.nextLine();
            }
            // Create a new Electronic object and add to the system
            Clothing clothing= new Clothing(productId,productName,availableItems,price,size,color);
            westminsterShoppingManager.addProducts(clothing);
        }
    }

    /**
     * Delete a product form the system
     * Ask user for the ID of the specific product
     */
    public static void deleteProductFromTheSystem(){
        System.out.println("\n* * * * * *  D E L E T E  * * * * * * *");

        Scanner scanner = new Scanner(System.in);

        // Displaying the list of product IDs in the system
        System.out.println("Product IDs in the system:");
        for (Product product : westminsterShoppingManager.getProductList()) {
            System.out.println("Product IDs: " + product.getProductID());
        }

        // Ask for product ID to delete
        System.out.print("\nEnter the product ID to delete the product: ");
        String productId = scanner.nextLine();
        westminsterShoppingManager.deleteProduct(productId);
    }

    /**
     * Sort the product list by product ID according to the alphabetical order
     * Uses the bubbleSort sorting algorithm
     * Display the ordered product list
     */
    public static void bubbleSort() {
        System.out.println("\n* * * * * *  Product List  * * * * * *");

        List<Product> productList = westminsterShoppingManager.getProductList();
        int n = productList.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (productList.get(j).getProductID().compareTo(productList.get(j + 1).getProductID()) > 0) {
                    Collections.swap(productList, j, j + 1);
                }
            }
        }
    }

    /**
     * Ask the user to Log in or Sign up
     * The GUI will display after a successful login or signup
     */
    public static void loginSignup() {
        Scanner scanner = new Scanner(System.in);
        String username;
        String password;
        ArrayList<String> userList = User.loadPastUsersList();
        System.out.println("\n* * * * * *  Login / Sign up * * * * * *");
        System.out.println("1. Login");
        System.out.println("2. Sign up");
        System.out.print("Enter your choice: ");
        String method = scanner.nextLine();

        switch (method) {
            case "1":   // Ask user to Login
                do {
                    System.out.println("\n* * * * * * *  L O G I N  * * * * * * *");
                    System.out.println("\nPlease enter your username and password to login.");
                    System.out.print("UserName: ");
                    username = scanner.nextLine();
                    System.out.print("Password: ");
                    password = scanner.nextLine();

                    if (!userList.contains(username)) {
                        System.out.println("!!! Username does not exist in the system. Enter a valid username. Try again.");
                    } else {
                        // Create a new User object after a successful login
                        user = new User(username, password);
                        new ShoppingCenter(westminsterShoppingManager, user);
                    }
                } while (!userList.contains(username));
                break;

            case "2":   // Ask user to Sign up
                System.out.println("\n* * * * * * *  S I G N  U P  * * * * * * *");
                System.out.println("Please enter a new username and password for sign up");
                System.out.print("UserName: ");
                username = scanner.nextLine();
                System.out.print("Password: ");
                password = scanner.nextLine();

                if (userList.contains(username)) {
                    System.out.println("Username exist in the system. Enter a unique username to signup. Try again.");
                } else {
                    // Create a new User object after a successful sign up
                    user = new User(username, password);
                    new ShoppingCenter(westminsterShoppingManager, user);
                }
                break;
            default:
                System.out.println("Please enter valid input.");
                break;
        }
    }
}
