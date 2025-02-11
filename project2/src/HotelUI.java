import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HotelUI {
    private HotelControl hotelControl;

    public HotelUI(HotelControl hotelControl) {
        this.hotelControl = hotelControl;
    }

    public void start() {
        JFrame frame = new JFrame("Hotel Management System");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ใช้ CardLayout สำหรับสลับระหว่างหน้า
        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel(cardLayout);

        // หน้า Home
        showHomeScreen(cardPanel, cardLayout);

        frame.add(cardPanel);
        frame.setVisible(true);
    }

    private void showHomeScreen(JPanel cardPanel, CardLayout cardLayout) {
        JPanel homePanel = new JPanel();
        homePanel.setLayout(new FlowLayout());

        JButton userLoginButton = new JButton("User Login");
        JButton adminLoginButton = new JButton("Admin Login");

        homePanel.add(userLoginButton);
        homePanel.add(adminLoginButton);

        cardPanel.add(homePanel, "Home");

        // กดปุ่ม User Login
        userLoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showUserLogin(cardPanel, cardLayout);
            }
        });

        // กดปุ่ม Admin Login
        adminLoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAdminLogin(cardPanel, cardLayout);
            }
        });

        cardLayout.show(cardPanel, "Home");
    }

    private void showUserLogin(JPanel cardPanel, CardLayout cardLayout) {
        JPanel userLoginPanel = new JPanel();
        userLoginPanel.setLayout(new FlowLayout());

        JLabel roomIdLabel = new JLabel("Room ID:");
        JTextField roomIdField = new JTextField(10);
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(10);
        JButton loginButton = new JButton("Login");
        JButton backButton = new JButton("Back");

        userLoginPanel.add(roomIdLabel);
        userLoginPanel.add(roomIdField);
        userLoginPanel.add(passwordLabel);
        userLoginPanel.add(passwordField);
        userLoginPanel.add(loginButton);
        userLoginPanel.add(backButton);

        cardPanel.add(userLoginPanel, "User Login");

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int roomId = Integer.parseInt(roomIdField.getText());
                String password = new String(passwordField.getPassword());

                if (hotelControl.validateRoom(roomId, password)) {
                    JOptionPane.showMessageDialog(null, "User Login Successful.");
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Room ID or Password.");
                }
            }
        });

        // ปุ่มย้อนกลับ
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Home");
            }
        });

        cardLayout.show(cardPanel, "User Login");
    }

    private void showAdminLogin(JPanel cardPanel, CardLayout cardLayout) {
        JPanel adminLoginPanel = new JPanel();
        adminLoginPanel.setLayout(new FlowLayout());

        JLabel adminIDLabel = new JLabel("Admin ID:");
        JTextField adminIDField = new JTextField(15);
        JLabel passwordLabel = new JLabel("Admin Password:");
        JPasswordField passwordField = new JPasswordField(15);
        JButton loginButton = new JButton("Login");
        JButton backButton = new JButton("Back");

        adminLoginPanel.add(adminIDLabel);
        adminLoginPanel.add(adminIDField);
        adminLoginPanel.add(passwordLabel);
        adminLoginPanel.add(passwordField);
        adminLoginPanel.add(loginButton);
        adminLoginPanel.add(backButton);

        cardPanel.add(adminLoginPanel, "Admin Login");

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String adminID = adminIDField.getText();
                String password = new String(passwordField.getPassword());

                if (adminID.equals("admin123") && password.equals("adminpassword")) {
                    JOptionPane.showMessageDialog(null, "Admin Login Successful.");
                    showAdminDashboard(cardPanel, cardLayout); // พาไปหน้า Admin Dashboard
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Admin Credentials.");
                }
            }
        });

        // ปุ่มย้อนกลับ
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Home");
            }
        });

        cardLayout.show(cardPanel, "Admin Login");
    }

    private void showAdminDashboard(JPanel cardPanel, CardLayout cardLayout) {
        JPanel adminDashboardPanel = new JPanel();
        adminDashboardPanel.setLayout(new FlowLayout());

        JButton showRoomButton = new JButton("Show Room Info");
        JButton changeRoomPasswordButton = new JButton("Change Room Password");
        JButton backButton = new JButton("Back");

        adminDashboardPanel.add(showRoomButton);
        adminDashboardPanel.add(changeRoomPasswordButton);
        adminDashboardPanel.add(backButton);

        cardPanel.add(adminDashboardPanel, "Admin Dashboard");

        // เมื่อคลิกปุ่ม Show Room Info
        showRoomButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showRoomInfo(cardPanel, cardLayout);
            }
        });

        // เมื่อคลิกปุ่ม Change Room Password
        changeRoomPasswordButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                changeRoomPassword(cardPanel, cardLayout);
            }
        });

        // ปุ่มย้อนกลับ
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Admin Login");
            }
        });

        cardLayout.show(cardPanel, "Admin Dashboard");
    }

    private void showRoomInfo(JPanel cardPanel, CardLayout cardLayout) {
        JPanel showRoomPanel = new JPanel();
        showRoomPanel.setLayout(new FlowLayout());

        StringBuilder roomInfo = new StringBuilder();
        for (int roomId = 101; roomId <= 105; roomId++) {
            if (hotelControl.isRoomAvailable(roomId)) {
                roomInfo.append("Room " + roomId + " is available.\n");
            } else {
                roomInfo.append("Room " + roomId + " is occupied.\n");
            }
        }

        JTextArea roomInfoTextArea = new JTextArea(10, 30);
        roomInfoTextArea.setText(roomInfo.toString());
        roomInfoTextArea.setEditable(false);

        JButton backButton = new JButton("Back");

        showRoomPanel.add(new JScrollPane(roomInfoTextArea));
        showRoomPanel.add(backButton);

        cardPanel.add(showRoomPanel, "Show Room Info");

        // ปุ่มย้อนกลับ
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Admin Dashboard");
            }
        });

        cardLayout.show(cardPanel, "Show Room Info");
    }

    private void changeRoomPassword(JPanel cardPanel, CardLayout cardLayout) {
        JPanel changePasswordPanel = new JPanel();
        changePasswordPanel.setLayout(new FlowLayout());

        JLabel roomIdLabel = new JLabel("Room ID:");
        JTextField roomIdField = new JTextField(10);
        JLabel newPasswordLabel = new JLabel("New Password:");
        JPasswordField newPasswordField = new JPasswordField(10);

        JButton setPasswordButton = new JButton("Set Password");
        JButton backButton = new JButton("Back");

        changePasswordPanel.add(roomIdLabel);
        changePasswordPanel.add(roomIdField);
        changePasswordPanel.add(newPasswordLabel);
        changePasswordPanel.add(newPasswordField);
        changePasswordPanel.add(setPasswordButton);
        changePasswordPanel.add(backButton);

        cardPanel.add(changePasswordPanel, "Change Room Password");

        // เมื่อคลิกปุ่ม Back
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Admin Dashboard");
            }
        });

        // เมื่อคลิกปุ่ม Set Password
        setPasswordButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int roomId = Integer.parseInt(roomIdField.getText());
                String newPassword = new String(newPasswordField.getPassword());

                boolean success = hotelControl.setRoomPassword(roomId, newPassword);
                if (success) {
                    JOptionPane.showMessageDialog(null, "Password for Room " + roomId + " has been updated.");
                } else {
                    JOptionPane.showMessageDialog(null, "Room not found.");
                }
            }
        });

        cardLayout.show(cardPanel, "Change Room Password");
    }
}
