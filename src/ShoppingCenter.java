import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCenter {

    private JFrame frame = new JFrame();
    private JPanel top = new JPanel();
    private JPanel center = new JPanel();
    private JPanel bottom = new JPanel();
    private JLabel label,title;
    private JComboBox<String> box;
    private JButton viewCart, addToCart, sort;
    private JTable table;
    private JScrollPane tableScrollPane;
    private JTextArea productInfo;
    private DefaultTableModel tableModel;
    private List<Product> systemProductList;
    private WestminsterShoppingManager shoppingManager;
    private Product selectedProduct;
    private ShoppingCart shoppingCart = new ShoppingCart();
    private ShoppingCartGUI shoppingCartGUI;

    ShoppingCenter(WestminsterShoppingManager shoppingManager, User user) {
        this.shoppingManager = shoppingManager;
        systemProductList = shoppingManager.getProductList();
        //Setting properties for panels
        top.setPreferredSize(new Dimension(800, 100));
        center.setPreferredSize(new Dimension(800, 300));
        bottom.setPreferredSize(new Dimension(800, 200));

        //Creating top panel components
        label = new JLabel("Select Product Category");
        String[] filters = {"All", "Electronics", "Clothing"};
        box = new JComboBox<>(filters);
        box.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterProducts();
            }
        });

        sort = new JButton("Sort Products");
        sort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortProductList();
            }
        });

        viewCart = new JButton("View Cart");
        viewCart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (shoppingCartGUI == null || !shoppingCartGUI.isVisible()) {
                    shoppingCartGUI = new ShoppingCartGUI(user, shoppingCart);
                } else {
                    shoppingCartGUI.toFront(); // Bring the existing instance to the front
                }
            }
        });

        //Creating center panel components(Product List)
        String[] cFilters ={"Product ID","Name", "Category", "Price($)","Info"};
        tableModel = new DefaultTableModel(cFilters,0);
        table = new JTable(tableModel);
        tableScrollPane = new JScrollPane(table);
        tableScrollPane.setPreferredSize(new Dimension(500,300));
        //Update the table according to the system product list
        for(Product p:systemProductList){
            addProductsToTable(p);
        }

        //Implementing selection of a product
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e){
                if(!e.getValueIsAdjusting()){
                    displayProductInfo();
                }
            }
        });

        //Creating bottom panel components
        title = new JLabel("Selected Product Details");
        productInfo = new JTextArea();
        addToCart = new JButton("Add to Shopping Cart");
        addToCart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addToShoppingCart();
            }
        });

        //Adding Components
        top.add(label);
        top.add(box);
        top.add(sort);
        top.add(viewCart);
        center.add(tableScrollPane);
        bottom.add(title);
        bottom.add(productInfo);
        bottom.add(addToCart);

        frame.add(top, BorderLayout.NORTH);
        frame.add(center, BorderLayout.CENTER);
        frame.add(bottom, BorderLayout.SOUTH);

        //Setting frame properties
        frame.setTitle("Westminster Shopping Center");
        frame.setSize(800, 600);
        frame.setVisible(true);
    }

    private void sortProductList(){
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING)); // Sort by column 0 (Product ID)

        RowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        sorter.setSortKeys(sortKeys);
        table.setRowSorter(sorter);
    }

    private void addProductsToTable(Product p){
        ArrayList<Object> tableRow = new ArrayList<>();

        tableRow.add(p.getProductID());

        if (p.getAvailableItems() <= 3) {
            tableRow.add("<html><font color='red'>" + p.getProductName() + "</font></html>");
            tableRow.add("<html><font color='red'>" + p.productType() + "</font></html>");
            tableRow.add("<html><font color='red'>" + p.getPrice() + "</font></html>");
            if (p instanceof Electronics) {
                tableRow.add("<html><font color='red'>" + ((Electronics) p).getWarrantyPeriod() +", "+((Electronics) p).getBrand() + "</font></html>");
            } else{
                tableRow.add("<html><font color='red'>" + ((Clothing)p).getSize()+", "+((Clothing) p).getColor() + "</font></html>");
            }
        } else {
            tableRow.add(p.getProductName());
            tableRow.add(p.productType());
            tableRow.add(p.getPrice());
            if(p instanceof Electronics){
                tableRow.add(((Electronics) p).getWarrantyPeriod() +", "+((Electronics) p).getBrand());
            }else{
                tableRow.add(((Clothing)p).getSize()+", "+((Clothing) p).getColor());
            }
        }
        tableModel.addRow(tableRow.toArray());
    }
    private void filterProducts(){
        tableModel.setRowCount(0);
        String selectedFilter = (String)box.getSelectedItem();
        for(Product p:systemProductList){
            if(selectedFilter.equals("All")
                    ||(selectedFilter.equals("Electronics") && p instanceof Electronics)
                    ||(selectedFilter.equals("Clothing") && p instanceof Clothing)){
                addProductsToTable(p);
            }
        }
    }
    private void displayProductInfo(){
        int selectedRow = table.getSelectedRow();

        if (selectedRow != -1) {
            String selectedProductId = (String)tableModel.getValueAt(selectedRow,0);
            for(Product p : systemProductList){
                if(selectedProductId.equals(p.getProductID())) {

                    selectedProduct = p;

                    if(p instanceof Electronics){ //"Product ID","Name", "Category", "Price($)","Info"
                        productInfo.setText("Product Id: " + p.getProductID() +
                                "\nCategory: " + p.productType() +
                                "\nName: " + p.getProductName() +
                                "\nPrice: " +p.getPrice() +
                                "\nBrand: " + ((Electronics) p).getBrand() +
                                "\nWarranty Period: " + ((Electronics) p).getWarrantyPeriod() +
                                "\nAvailable Items: " + p.getAvailableItems());
                    }else{
                        productInfo.setText("Product Id: " +p.getProductID() +
                                "\nCategory: " + p.productType() +
                                "\nName: " + p.getProductName() +
                                "\nColour: " +((Clothing)p).getColor() +
                                "\nSize: " + ((Clothing)p).getSize() +
                                "\nAvailable Items: " + p.getAvailableItems());
                    }
                }
            }
        }
    }

    private void addToShoppingCart() {
        String productDetails;
        int quantity = 0;
        double price;
        int availableAmount = selectedProduct.getAvailableItems();

        // Add the selected product to the shopping cart
        shoppingCart.addProduct(selectedProduct);

        // Display product details in the shopping cart table
        if (selectedProduct instanceof Electronics) {
            productDetails = selectedProduct.getProductID() + " \n" + selectedProduct.getProductName() + " \n" + ((Electronics) selectedProduct).getBrand() + " \n" + ((Electronics) selectedProduct).getWarrantyPeriod();
        } else {
            productDetails = selectedProduct.getProductID() + " \n" + selectedProduct.getProductName() + " \n" + ((Clothing) selectedProduct).getSize() + " \n" + ((Clothing) selectedProduct).getColor();
        }

        // Checking for the same product in shopping cart and update the product quantity
        for (Product product : shoppingCart.getShoppingCartProducts()) {
            if (product.equals(selectedProduct)) {
                quantity += 1;
            }
        }

        // Updating the available stock and increase the price
        if (availableAmount - 1 == 0) {
            WestminsterShoppingManager.productList.remove(selectedProduct);
        } else {
            selectedProduct.setAvailableItems(availableAmount - 1);
        }
        price = selectedProduct.getPrice() * quantity;

        // Checking for the same product row in shoppingCartTable
        boolean found = false;
        for (int i = 0; i < shoppingCartGUI.tableModel.getRowCount(); i++) {
            String existingProductInfo = (String) shoppingCartGUI.tableModel.getValueAt(i, 0);
            if (existingProductInfo.equals(productDetails)) {
                // Update the row in the table
                shoppingCartGUI.tableModel.setValueAt(quantity, i, 1);
                shoppingCartGUI.tableModel.setValueAt(price, i, 2);
                found = true;
                break;
            }
        }
        if (!found) {
            // If the product is not already in the shopping cart, add a new row
            shoppingCartGUI.addToCart(productDetails, quantity, price);
        }
        filterProducts();
        shoppingCartGUI.calculateTotal();
        shoppingCartGUI.discount10Percent();
        shoppingCartGUI.discount20Percent();
        shoppingCartGUI.calculateFinalTotal();
    }
}
