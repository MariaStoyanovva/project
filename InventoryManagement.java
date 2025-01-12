import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class Product {
    private String name;
    private double price;
    private int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String displayDetails() {
        return  name + "   -   $" + price + "   -   " + quantity;
    }
}

class Inventory {
    private ArrayList<Product> products;

    public Inventory() {
        products = new ArrayList<>();
    }

    public String addProduct(String name, double price, int quantity) {
        if (products.size() >= 3) {
            return "No more space available.";
        }
        products.add(new Product(name, price, quantity));
        return "Product ADDED successfully!";
    }

    public String updateProduct(String name, double price, int quantity) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                product.setPrice(price);
                product.setQuantity(quantity);
                return "Product UPDATED successfully!";
            }
        }
        return "Product NOT FOUND.";
    }

    public String displayAllProducts() {
        if (products.isEmpty()) {
            return "NO PRODUCTS in the inventory.";
        }
        StringBuilder details = new StringBuilder();
        for (Product product : products) {
            details.append(product.displayDetails()).append("\n");
        }
        return details.toString();
    }
}

public class InventoryManagement {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();

        JFrame frame = new JFrame("Inventory Management System");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // main panel 
        JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        Font font = new Font("Arial", Font.PLAIN, 18);

        // input panels
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(font);
        JTextField nameField = new JTextField(15);  //Setting width to 15 characters
        nameField.setFont(font);
        namePanel.add(nameLabel);
        namePanel.add(nameField);

        JPanel pricePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setFont(font);
        JTextField priceField = new JTextField(15);
        priceField.setFont(font);
        pricePanel.add(priceLabel);
        pricePanel.add(priceField);

        JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setFont(font);
        JTextField quantityField = new JTextField(15);
        quantityField.setFont(font);
        quantityPanel.add(quantityLabel);
        quantityPanel.add(quantityField);

        // buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton addButton = new JButton("Add Product");
        addButton.setFont(font);
        JButton updateButton = new JButton("Update Product");
        updateButton.setFont(font);
        JButton displayButton = new JButton("Display Products");
        displayButton.setFont(font);
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(displayButton);

        // display area
        JTextArea displayArea = new JTextArea(10, 30);
        displayArea.setEditable(false);
        displayArea.setFont(font);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        // Add components to main panel
        mainPanel.add(namePanel);
        mainPanel.add(pricePanel);
        mainPanel.add(quantityPanel);
        mainPanel.add(buttonPanel);
        mainPanel.add(scrollPane);

        frame.add(mainPanel);
        frame.setVisible(true);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                try {
                    double price = Double.parseDouble(priceField.getText());
                    int quantity = Integer.parseInt(quantityField.getText());
                    if (price < 0 || quantity < 0) {
                        displayArea.setText("Price and quantity must be >=0.");
                    } else {
                        displayArea.setText(inventory.addProduct(name, price, quantity));
                    }
                } catch (NumberFormatException ex) {
                    displayArea.setText("Invalid input.");
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                try {
                    double price = Double.parseDouble(priceField.getText());
                    int quantity = Integer.parseInt(quantityField.getText());
                    if (price < 0 || quantity < 0) {
                        displayArea.setText("Price and quantity must be >=0.");
                    } else {
                        displayArea.setText(inventory.updateProduct(name, price, quantity));
                    }
                } catch (NumberFormatException ex) {
                    displayArea.setText("Invalid input.");
                }
            }
        });

        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayArea.setText(inventory.displayAllProducts());
            }
        });
    }
}
