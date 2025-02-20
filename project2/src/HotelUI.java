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
    // ฟังก์ชันเริ่มต้นสำหรับ Admin Mode
    public void startAdminMode() {
        JFrame frame = new JFrame("Admin Mode - Hotel Management System");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ใช้ CardLayout สำหรับสลับระหว่างหน้า
        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel(cardLayout);

        // หน้า Home สำหรับ Admin
        showAdminHomeScreen(cardPanel, cardLayout);

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

    // หน้า Home สำหรับ Admin
    private void showAdminHomeScreen(JPanel cardPanel, CardLayout cardLayout) {
        JPanel homePanel = new JPanel();
        homePanel.setLayout(new FlowLayout());

        JButton adminLoginButton = new JButton("Admin Login");

        homePanel.add(adminLoginButton);

        cardPanel.add(homePanel, "Admin Home");

        // เมื่อกดปุ่ม Admin Login
        adminLoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAdminLogin(cardPanel, cardLayout);
            }
        });

        cardLayout.show(cardPanel, "Admin Home");
    }

    // หน้า User Login
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
        for (int roomId = 101; roomId <= 105; roomId++) {
            if (hotelControl.isRoomAvailable(roomId)) {
                roomInfo.append("Room " + roomId + " is available.\n");
            } else {
                roomInfo.append("Room " + roomId + " is occupied.\n");
            }
        }

        // แสดงข้อมูลสถานะห้องใน Dialog
        JOptionPane.showMessageDialog(null, roomInfo.toString(), "Room Information", JOptionPane.INFORMATION_MESSAGE);
    }

    // หน้า Admin Login
    // หน้า Admin Login
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
                    JOptionPane.showMessageDialog(null, "Invalid Admin Credentials. Please try again.");
                }
            }
        });

        // ปุ่มย้อนกลับ
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Admin Home"); // กลับไปที่หน้า Home ของ Admin
            }
        });

        cardLayout.show(cardPanel, "Admin Login");
    }

    // หน้า Admin Dashboard
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

    // แสดงข้อมูลสถานะห้องใน Admin Dashboard
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

    // ฟังก์ชันสำหรับการเปลี่ยนรหัสห้อง
    private void changeRoomPassword(JPanel cardPanel, CardLayout cardLayout) {
        JPanel changePasswordPanel = new JPanel();
        changePasswordPanel.setLayout(new FlowLayout());

        JLabel roomIdLabel = new JLabel("Room ID:");
        JTextField roomIdField = new JTextField(10);
        JLabel newPasswordLabel = new JLabel("New Password:");
        JPasswordField newPasswordField = new JPasswordField(10);
        JButton changeButton = new JButton("Change Password");
        JButton backButton = new JButton("Back");

        changePasswordPanel.add(roomIdLabel);
        changePasswordPanel.add(roomIdField);
        changePasswordPanel.add(newPasswordLabel);
        changePasswordPanel.add(newPasswordField);
        changePasswordPanel.add(changeButton);
        changePasswordPanel.add(backButton);

        cardPanel.add(changePasswordPanel, "Change Room Password");

        changeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int roomId = Integer.parseInt(roomIdField.getText());
                String newPassword = new String(newPasswordField.getPassword());

                hotelControl.changeRoomPassword(roomId, newPassword);
                JOptionPane.showMessageDialog(null, "Password changed successfully.");
            }
        });

        // ปุ่มย้อนกลับ
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Admin Dashboard");
            }
        });

        cardLayout.show(cardPanel, "Change Room Password");
    }
}
