import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShoppingCartGUI extends JFrame {
    JPanel top = new JPanel();
    JPanel bottom = new JPanel();
    JLabel label1,label2,label3,label4,totalLbl,fDiscountLbl,sCategoryLbl,fTotalLbl,lbl1,lbl2,lbl3,lbl4,lbl5;
    JTable table;
    JButton checkout;
    DefaultTableModel tableModel;
    JScrollPane tableScrollPane;
    User user;
    private ShoppingCart shoppingCart;


    ShoppingCartGUI(User user, ShoppingCart shoppingCart){
        this.user = user;
        this.shoppingCart = shoppingCart;

        //Setting properties for panels
        top.setPreferredSize(new Dimension(800,300));
        bottom.setPreferredSize(new Dimension(300,500));
        bottom.setLayout(new GridLayout(5,3));

        //Creating top panel components
        String[] columns ={"Product","Quantity","Price"};
        tableModel = new DefaultTableModel(columns,0);
        table = new JTable(tableModel);
        tableScrollPane = new JScrollPane(table);
        tableScrollPane.setPreferredSize(new Dimension(500,300));

        //Creating bottom panel components
        lbl1 = new JLabel("");
        lbl2 = new JLabel("");
        lbl3 = new JLabel("");
        lbl4 = new JLabel("");
        lbl5 = new JLabel("");
        label1 = new JLabel("Total: ");
        label2 = new JLabel("Fist Purchase (10%): ");
        label3 = new JLabel("Three items Discount (20%): ");
        label4 = new JLabel("Final Total: ");
        totalLbl = new JLabel("0");
        fDiscountLbl = new JLabel("0");
        sCategoryLbl = new JLabel("0");
        fTotalLbl = new JLabel("0");
        checkout = new JButton("Checkout");
        checkout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableModel.setRowCount(0);
                totalLbl.setText("0");
                fDiscountLbl.setText("0");
                sCategoryLbl.setText("0");
                fTotalLbl.setText("0");
                user.addUserToHistory();
            }
        });

        //Adding Components
        top.add(tableScrollPane);
        bottom.add(lbl1);
        bottom.add(label1);
        bottom.add(totalLbl);
        bottom.add(lbl2);
        bottom.add(label2);
        bottom.add(fDiscountLbl);
        bottom.add(lbl3);
        bottom.add(label3);
        bottom.add(sCategoryLbl);
        bottom.add(lbl4);
        bottom.add(label4);
        bottom.add(fTotalLbl);
        bottom.add(lbl5);
        bottom.add(checkout);

        add(top, BorderLayout.NORTH);
        add(bottom, BorderLayout.CENTER);

        //Setting frame properties
        setTitle("Shopping Cart");
        setSize(600,600);
        setVisible(true);
    }

    public void addToCart(String productInfo, int quantity, double price) {
        Object[] row = {productInfo, quantity, price};
        tableModel.addRow(row);
    }

    public void calculateTotal() {
        totalLbl.setText(String.valueOf(shoppingCart.calculateTotalCost()));
    }

    public void discount10Percent() {
        if (User.loadPastUsersList().contains(user.getUsername())) {
            fDiscountLbl.setText("0");
        } else {
            fDiscountLbl.setText(String.valueOf(shoppingCart.calculateTotalCost() * 0.1));
        }
    }

    public void discount20Percent() {
        if (shoppingCart.containSame3Products()) {
            sCategoryLbl.setText(String.valueOf(shoppingCart.calculateTotalCost() * 0.2));
        } else {
            sCategoryLbl.setText("0");
        }
    }

    public void calculateFinalTotal() {
        double totalCost = shoppingCart.calculateTotalCost();
        double fDiscount = Double.parseDouble(fDiscountLbl.getText());
        double sCategory = Double.parseDouble(sCategoryLbl.getText());
        double finalTotal = totalCost - fDiscount - sCategory;
        fTotalLbl.setText(String.valueOf(finalTotal));
    }
}
