package com.inventory;

import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginScreen {
    private JFrame frame;

    public LoginScreen() {
        // Setup modern dark look and feel using FlatLaf
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
            // Optional: customize FlatLaf properties for rounded corners
            UIManager.put("Button.arc", 15);
            UIManager.put("Component.arc", 15);
            UIManager.put("TextComponent.arc", 15);
        } catch (Exception ex) {
            System.err.println("Failed to initialize FlatDarkLaf");
        }
        
        initializeUI();
    }

    private void initializeUI() {
        frame = new JFrame("Inventory Management - Secure Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Slightly taller and wider for a more spacious dark mode look
        frame.setSize(450, 460);
        frame.setLocationRelativeTo(null); // Center on screen
        frame.setResizable(false);

        // Main panel - let FlatDarkLaf handle the background automatically!
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(35, 45, 35, 45));

        // Header Title
        JLabel titleLabel = new JLabel("INVENTORY SYSTEM", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titleLabel.setForeground(new Color(220, 220, 220)); 
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Subtitle
        JLabel subtitleLabel = new JLabel("Enter credentials to continue");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitleLabel.setForeground(new Color(150, 150, 150)); 
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Form Panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // Username Label
        JLabel userLabel = new JLabel("USERNAME");
        userLabel.setFont(new Font("Segoe UI", Font.BOLD, 11));
        userLabel.setForeground(new Color(160, 160, 160));
        gbc.gridx = 0; gbc.gridy = 0; gbc.insets = new Insets(10, 0, 5, 0);
        formPanel.add(userLabel, gbc);

        // Username Field
        JTextField userField = new JTextField();
        userField.setPreferredSize(new Dimension(300, 40));
        userField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        gbc.gridy = 1; gbc.insets = new Insets(0, 0, 15, 0);
        formPanel.add(userField, gbc);

        // Password Label
        JLabel passLabel = new JLabel("PASSWORD");
        passLabel.setFont(new Font("Segoe UI", Font.BOLD, 11));
        passLabel.setForeground(new Color(160, 160, 160));
        gbc.gridy = 2; gbc.insets = new Insets(0, 0, 5, 0);
        formPanel.add(passLabel, gbc);

        // Password Field
        JPasswordField passField = new JPasswordField();
        passField.setPreferredSize(new Dimension(300, 40));
        passField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        gbc.gridy = 3; gbc.insets = new Insets(0, 0, 25, 0);
        formPanel.add(passField, gbc);

        // Login Button
        JButton loginButton = new JButton("SIGN IN");
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        loginButton.setBackground(new Color(88, 101, 242)); // Discord/Indigo accent color
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.setPreferredSize(new Dimension(300, 45));
        gbc.gridy = 4; gbc.insets = new Insets(5, 0, 0, 0);
        formPanel.add(loginButton, gbc);

        // Hint Label for Credentials
        JLabel hintLabel = new JLabel("Use admin / admin");
        hintLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        hintLabel.setForeground(new Color(120, 120, 120)); 
        hintLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Login Action
        loginButton.addActionListener(e -> {
            String username = userField.getText();
            String password = new String(passField.getPassword());

            if ("admin".equals(username) && "admin".equals(password)) {
                frame.dispose(); // Close login window
                SwingUtilities.invokeLater(() -> new Main()); // Open main window
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Add everything to main panel with precise spacing
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        mainPanel.add(subtitleLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        mainPanel.add(formPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(hintLabel);
        mainPanel.add(Box.createVerticalStrut(15));

        // Enable "Enter" key to submit form
        frame.getRootPane().setDefaultButton(loginButton);

        frame.add(mainPanel);
        frame.setVisible(true);
    }
}
