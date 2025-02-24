import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HotelUI {
    private HotelControl hotelControl;

    public HotelUI(HotelControl hotelControl) {
        this.hotelControl = hotelControl;
    }

    // ฟังก์ชันเริ่มต้นสำหรับ User Mode
    public void startUserMode() {
        JFrame frame = new JFrame("User Mode - Hotel Management System");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ใช้ CardLayout สำหรับสลับระหว่างหน้า
        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel(cardLayout);

        // หน้า Home สำหรับ User
        showUserHomeScreen(cardPanel, cardLayout);

        // เพิ่มปุ่มกลับไปหน้า Login
        JButton backButton = new JButton("Back to Login");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // กลับไปหน้า Login
                String mode = JOptionPane.showInputDialog("Enter 'user' for User Mode or 'admin' for Admin Mode:");
                if ("user".equalsIgnoreCase(mode)) {
                    startUserMode();
                } else if ("admin".equalsIgnoreCase(mode)) {
                    startAdminMode();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid mode. Please enter 'user' or 'admin'.");
                }
            }
        });
        frame.add(backButton, BorderLayout.SOUTH); // เพิ่มปุ่มที่ด้านล่างของ frame

        frame.add(cardPanel);
        frame.setVisible(true);
    }

    // ฟังก์ชันเริ่มต้นสำหรับ Admin Mode
    public void startAdminMode() {
        JFrame frame = new JFrame("Admin Mode - Hotel Management System");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ใช้ CardLayout สำหรับสลับระหว่างหน้า
        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel(cardLayout);

        // หน้า Home สำหรับ Admin
        showAdminLogin(cardPanel, cardLayout);

        // เพิ่มปุ่มกลับไปหน้า Login
        JButton backButton = new JButton("Back to Login");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // กลับไปหน้า Login
                String mode = JOptionPane.showInputDialog("Enter 'user' for User Mode or 'admin' for Admin Mode:");
                if ("user".equalsIgnoreCase(mode)) {
                    startUserMode();
                } else if ("admin".equalsIgnoreCase(mode)) {
                    startAdminMode();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid mode. Please enter 'user' or 'admin'.");
                }
            }
        });
        frame.add(backButton, BorderLayout.SOUTH); // เพิ่มปุ่มที่ด้านล่างของ frame

        frame.add(cardPanel);
        frame.setVisible(true);
    }

    // หน้า Home สำหรับ User
    private void showUserHomeScreen(JPanel cardPanel, CardLayout cardLayout) {
        JPanel homePanel = new JPanel();
        homePanel.setLayout(new FlowLayout());

        JButton userLoginButton = new JButton("User Login");
        JButton infoButton = new JButton("Room Info");

        homePanel.add(userLoginButton);
        homePanel.add(infoButton); // เพิ่มปุ่ม Info

        cardPanel.add(homePanel, "User Home");

        // เมื่อกดปุ่ม User Login
        userLoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showUserLogin(cardPanel, cardLayout);
            }
        });

        // เมื่อกดปุ่ม Room Info
        infoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showRoomInfoDialog();
            }
        });

        cardLayout.show(cardPanel, "User Home");
    }

    // หน้า User Login
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

        // เมื่อกดปุ่ม User Login
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int roomId = Integer.parseInt(roomIdField.getText());
                    String password = new String(passwordField.getPassword());

                    if (hotelControl.validateRoom(roomId, password)) {
                        JOptionPane.showMessageDialog(null, "User Login Successful. Welcome!");
                        // หลังจาก login สำเร็จ เราสามารถทำอะไรต่อไปใน User Mode ได้
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid Room ID or Password. Please try again.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid Room ID.");
                }
            }
        });

        // ปุ่มย้อนกลับ
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "User Home"); // กลับไปที่หน้า Home ของ User
            }
        });

        cardLayout.show(cardPanel, "User Login");
    }

    // แสดงข้อมูลสถานะห้องใน Dialog สำหรับ User
    private void showRoomInfoDialog() {
        StringBuilder roomInfo = new StringBuilder();

        roomInfo.append("Floor 1\n");
        for (int roomId = 101; roomId <= 110; roomId++) {
            roomInfo.append("Room ").append(roomId).append(" is ");
            roomInfo.append(hotelControl.isRoomAvailable(roomId) ? "available.\n" : "occupied.\n");
        }

        roomInfo.append("\nFloor 2\n");
        for (int roomId = 201; roomId <= 210; roomId++) {
            roomInfo.append("Room ").append(roomId).append(" is ");
            roomInfo.append(hotelControl.isRoomAvailable(roomId) ? "available.\n" : "occupied.\n");
        }

        roomInfo.append("\nFloor 3\n");
        for (int roomId = 301; roomId <= 310; roomId++) {
            roomInfo.append("Room ").append(roomId).append(" is ");
            roomInfo.append(hotelControl.isRoomAvailable(roomId) ? "available.\n" : "occupied.\n");
        }

        JOptionPane.showMessageDialog(null, roomInfo.toString(), "Room Information", JOptionPane.INFORMATION_MESSAGE);
    }

    // หน้า Admin Login
    private void showAdminLogin(JPanel cardPanel, CardLayout cardLayout) {
        JPanel adminLoginPanel = new JPanel();
        adminLoginPanel.setLayout(new FlowLayout());

        JLabel adminIdLabel = new JLabel("Admin ID:");
        JTextField adminIdField = new JTextField(15);
        JLabel adminPasswordLabel = new JLabel("Admin Password:");
        JPasswordField adminPasswordField = new JPasswordField(15);
        JButton loginButton = new JButton("Login");
        JButton resetButton = new JButton("Reset");

        adminLoginPanel.add(adminIdLabel);
        adminLoginPanel.add(adminIdField);
        adminLoginPanel.add(adminPasswordLabel);
        adminLoginPanel.add(adminPasswordField);
        adminLoginPanel.add(loginButton);
        adminLoginPanel.add(resetButton);

        cardPanel.add(adminLoginPanel, "Admin Login");

        // เมื่อกดปุ่ม Login
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String adminId = adminIdField.getText();
                String adminPassword = new String(adminPasswordField.getPassword());

                Admin admin = new Admin(hotelControl.getHotel());

                if (admin.loginAsAdmin(adminId, adminPassword)) {
                    JOptionPane.showMessageDialog(null, "Admin Login Successful!");
                    showAdminDashboard(cardPanel, cardLayout); // ไปที่หน้าแดชบอร์ดของ Admin
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Admin ID or Password. Please try again.");
                }
            }
        });

        // เมื่อกดปุ่ม Reset
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                adminIdField.setText("");
                adminPasswordField.setText("");
            }
        });

        cardLayout.show(cardPanel, "Admin Login");
    }

    private void showAdminDashboard(JPanel cardPanel, CardLayout cardLayout) {
        JPanel adminDashboardPanel = new JPanel();
        adminDashboardPanel.setLayout(new FlowLayout());

        JLabel hotelUserNameLabel = new JLabel("Hotel User Name:");
        JTextField hotelUserNameField = new JTextField(15);
        JLabel roomIdLabel = new JLabel("Room ID:");
        JTextField roomIdField = new JTextField(10);
        JLabel roomPasswordLabel = new JLabel("Room Password:");
        JPasswordField roomPasswordField = new JPasswordField(10);
        JButton setPasswordButton = new JButton("Set Password");
        JButton showRoomInfoButton = new JButton("Show Room Info");

        adminDashboardPanel.add(hotelUserNameLabel);
        adminDashboardPanel.add(hotelUserNameField);
        adminDashboardPanel.add(roomIdLabel);
        adminDashboardPanel.add(roomIdField);
        adminDashboardPanel.add(roomPasswordLabel);
        adminDashboardPanel.add(roomPasswordField);
        adminDashboardPanel.add(setPasswordButton);
        adminDashboardPanel.add(showRoomInfoButton);

        cardPanel.add(adminDashboardPanel, "Admin Dashboard");

        // เมื่อกดปุ่ม Set Password
        setPasswordButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int roomId = Integer.parseInt(roomIdField.getText());
                    String roomPassword = new String(roomPasswordField.getPassword());
                    String userName = hotelUserNameField.getText();
                    if (hotelControl.setRoomPassword(roomId, roomPassword, userName)) {
                        JOptionPane.showMessageDialog(null, "Password for room " + roomId + " has been set successfully.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Room " + roomId + " not found.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid Room ID.");
                }
            }
        });

        // เมื่อกดปุ่ม Show Room Info
        showRoomInfoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAdminRoomInfoDialog();
            }
        });

        cardLayout.show(cardPanel, "Admin Dashboard");
    }

    private void showAdminRoomInfoDialog() {
        StringBuilder roomInfo = new StringBuilder();

        Hotel hotel = hotelControl.getHotel();

        for (int floor = 1; floor <= 3; floor++) {
            roomInfo.append("Floor ").append(floor).append("\n");
            for (int roomId = (floor - 1) * 100 + 101; roomId <= (floor - 1) * 100 + 110; roomId++) {
                Room room = hotel.getRooms().get(roomId);
                if (room != null) {
                    roomInfo.append("Room ").append(roomId).append(" is ");
                    if (room.isAvailable()) {
                        roomInfo.append("available.\n");
                    } else {
                        roomInfo.append("occupied by ").append(room.getUserName()).append(".\n");
                    }
                }
            }
            roomInfo.append("\n");
        }

        JOptionPane.showMessageDialog(null, roomInfo.toString(), "Room Information", JOptionPane.INFORMATION_MESSAGE);
    }
}