import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a shopping cart in the online shopping system.
 */
class ShoppingCart {
    private List<Product> products;

    public ShoppingCart() {
        this.products = new ArrayList<>();
    }

    /**
     * Adds a product to the shopping cart.
     * @param product The product to be added.
     */
    public void addProduct(Product product) {
        products.add(product);
    }

    /**
     * Calculates the total cost of all products in the shopping cart.
     * @return The total cost of products in the shopping cart.
     */
    public double calculateTotalCost() {
        double totalCost = 0;
        for (Product p : products) {
            totalCost += p.getPrice();
        }
        return totalCost;
    }

    public List<Product> getShoppingCartProducts() {
        return products;
    }

    public boolean containSame3Products() {
        int electronicCount = 0;
        int clothingCount = 0;
        for (Product p : products) {
            if (p instanceof Electronics) {
                electronicCount ++;
            }
            else {
                clothingCount ++;
            }
        }
        if (clothingCount >= 3 || electronicCount >= 3) {
            return true;
        } else {
            return false;
        }
    }

}
