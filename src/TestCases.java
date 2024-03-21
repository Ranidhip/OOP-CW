import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class TestCases{

    @Test
    public void testAddProduct() {
        WestminsterShoppingManager manager = new WestminsterShoppingManager();
        Electronics electronics = new Electronics("P101", "iPhone 12", 10, 999.99, "Apple", 1);

        manager.addProducts(electronics);

        assertEquals(1, manager.getProductList().size());
        assertEquals(electronics, manager.getProductList().get(0));
    }

    @Test
    public void testDeleteProduct() {
        WestminsterShoppingManager manager = new WestminsterShoppingManager();
        Electronics electronics = new Electronics("P101", "iPhone 12", 10, 999.99, "Apple", 1);
        manager.addProducts(electronics);

        manager.deleteProduct("P101");

        assertEquals(0, manager.getProductList().size());
    }

    @Test
    public void testSaveAndLoad() {
        WestminsterShoppingManager manager = new WestminsterShoppingManager();

        manager.addProducts(new Electronics("P101", "iPhone 12", 10, 999.99, "Apple", 1));
        manager.addProducts(new Clothing("P102", "Blue T-Shirt", 5, 29.99, "M", "Blue"));

        manager.saveFile();
        manager.getProductList().clear();
        manager.loadData();

        assertEquals(2, manager.getProductList().size());
        assertTrue(manager.getProductList().get(0) instanceof Electronics);
        assertTrue(manager.getProductList().get(1) instanceof Clothing);
    }

    @Test
    public void testUser() {
        User user = new User("john123", "pass123");
        user.addUserToHistory();

        ArrayList<String> users = User.loadPastUsersList();
        assertTrue(users.contains("john123"));
    }

    @Test
    public void testShoppingCartAddProduct() {
        ShoppingCart cart = new ShoppingCart();
        Product product = new Electronics("P101", "iPhone", 1, 1000, "Apple", 1);

        cart.addProduct(product);

        assertEquals(1, cart.getShoppingCartProducts().size());
        assertEquals(product, cart.getShoppingCartProducts().get(0));
    }

    @Test
    public void testCalculateTotalCost() {
        ShoppingCart cart = new ShoppingCart();
        cart.addProduct(new Electronics("P101", "iPhone", 1, 1000, "Apple", 1));
        cart.addProduct(new Clothing("P102", "Shirt", 1, 50, "M", "Blue"));

        assertEquals(1050, cart.calculateTotalCost(), 0.0);
    }
}
