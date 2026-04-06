package com.inventory;

import com.inventory.dao.ProductDAO;
import com.inventory.model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.plaf.FontUIResource;

public class Main {
    private ProductDAO productDAO;
    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;
    
    private JTextField nameField;
    private JTextField quantityField;
    private JTextField priceField;
    private JTextField idField;

    public Main() {
        productDAO = new ProductDAO();
        initializeUI();
        
        // Connect to the database in the background to prevent freezing the UI when the app starts.
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() {
                System.out.println("Connecting to database...");
                productDAO.createTableIfNotExists();
                return null;
            }

            @Override
            protected void done() {
                System.out.println("Database connected!");
                refreshTable();
            }
        }.execute();
    }

    private void initializeUI() {
        frame = new JFrame("Inventory Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        // Top Panel for Inputs
        JPanel inputPanel = new JPanel(new GridLayout(2, 4, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Product Details"));

        inputPanel.add(new JLabel("ID (For Update/Delete):"));
        idField = new JTextField();
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Quantity:"));
        quantityField = new JTextField();
        inputPanel.add(quantityField);

        inputPanel.add(new JLabel("Price:"));
        priceField = new JTextField();
        inputPanel.add(priceField);

        frame.add(inputPanel, BorderLayout.NORTH);

        // Center Panel for Table
        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Quantity", "Price"}, 0);
        table = new JTable(tableModel);
        table.setRowHeight(25);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Bottom Panel for Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton addButton = new JButton("Add Product");
        JButton updateButton = new JButton("Update Product");
        JButton deleteButton = new JButton("Delete Product");
        JButton refreshButton = new JButton("Refresh List");

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);

        frame.getRootPane().setDefaultButton(addButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Button Actions
        addButton.addActionListener(e -> addProduct());
        updateButton.addActionListener(e -> updateProduct());
        deleteButton.addActionListener(e -> deleteProduct());
        refreshButton.addActionListener(e -> refreshTable());

        // Removed initial automatic refreshTable() from here, as it will run after the DB connects in the background.
        
        frame.setVisible(true);
    }

    private void refreshTable() {
        tableModel.setRowCount(0); // Clear table
        List<Product> products = productDAO.getAllProducts();
        for (Product p : products) {
            tableModel.addRow(new Object[]{p.getId(), p.getName(), p.getQuantity(), p.getPrice()});
        }
    }

    private void addProduct() {
        try {
            String name = nameField.getText();
            int qty = Integer.parseInt(quantityField.getText());
            double price = Double.parseDouble(priceField.getText());

            productDAO.addProduct(new Product(name, qty, price));
            JOptionPane.showMessageDialog(frame, "Product added successfully!");
            clearFields();
            refreshTable();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Please enter valid numbers for Quantity and Price.");
        }
    }

    private void updateProduct() {
        try {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            int qty = Integer.parseInt(quantityField.getText());
            double price = Double.parseDouble(priceField.getText());

            productDAO.updateProduct(new Product(id, name, qty, price));
            JOptionPane.showMessageDialog(frame, "Product updated successfully!");
            clearFields();
            refreshTable();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Please enter valid numbers for ID, Quantity and Price.");
        }
    }

    private void deleteProduct() {
        try {
            int id = Integer.parseInt(idField.getText());
            productDAO.deleteProduct(id);
            JOptionPane.showMessageDialog(frame, "Product deleted successfully!");
            clearFields();
            refreshTable();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid ID.");
        }
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        quantityField.setText("");
        priceField.setText("");
    }

    public static void setUIFont(FontUIResource f) {
        java.util.Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, f);
            }
        }
    }

    public static void main(String[] args) {
        setUIFont(new FontUIResource("SansSerif", Font.PLAIN, 16));
        SwingUtilities.invokeLater(() -> new LoginScreen());
    }
}
