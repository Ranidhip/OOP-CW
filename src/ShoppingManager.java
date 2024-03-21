/**
 * Interface defining the operations for managing products in an online shopping system.
 */
public interface ShoppingManager {

    /**
     * Adds a product to the online shopping system.
     * @param product The product to be added.
     */
    void addProducts(Product product);

    /**
     * Deletes a product from the online shopping system based on its ID.
     * @param productId The ID of the product to be deleted.
     */
    void deleteProduct(String productId);

    /**
     * Displays the list of products in the online shopping system.
     */
    void printProducts();
    void saveFile();
    void loadData();
}
