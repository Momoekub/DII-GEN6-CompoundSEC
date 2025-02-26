import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class HotelUI {
    private HotelControl hotelControl;
    private AccessControlSystem accessControlSystem;
    private LoginStrategy loginStrategy;
    private Map<Integer, String> registrationTimes = new HashMap<>();
    private Map<Integer, String> resetTimes = new HashMap<>();

    public HotelUI(HotelControl hotelControl, AccessControlSystem accessControlSystem) {
        this.hotelControl = hotelControl;
        this.accessControlSystem = accessControlSystem;
    }

    public void setLoginStrategy(LoginStrategy loginStrategy) {
        this.loginStrategy = loginStrategy;
    }

    public void startLoginScreen() {
        JFrame frame = new JFrame("Hotel Management System");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String mode;
        while (true) {
            mode = JOptionPane.showInputDialog(frame, "Enter 'user' for User Mode or 'admin' for Admin Mode:");

            if (mode == null) {
                JOptionPane.showMessageDialog(null, "Program exited.", "Goodbye", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }

            mode = mode.trim().toLowerCase();

            if (mode.equals("user")) {
                setLoginStrategy(new UserLoginStrategy(hotelControl, this));
                break;
            } else if (mode.equals("admin")) {
                setLoginStrategy(new AdminLoginStrategy(hotelControl, this));
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Invalid mode! Please enter 'user' or 'admin'.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        loginStrategy.login();
    }

    public void startUserMode() {
        JFrame frame = new JFrame("User Mode - Hotel Management System");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel(cardLayout);

        showUserHomeScreen(cardPanel, cardLayout, frame);

        frame.add(cardPanel);
        frame.setVisible(true);
    }

    public void startAdminMode() {
        JFrame frame = new JFrame("Admin Mode - Hotel Management System");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel(cardLayout);

        showAdminLogin(cardPanel, cardLayout, frame);

        frame.add(cardPanel);
        frame.setVisible(true);
    }

    private void showUserHomeScreen(JPanel cardPanel, CardLayout cardLayout, JFrame frame) {
        JPanel homePanel = new JPanel();
        homePanel.setLayout(new FlowLayout());

        JButton userLoginButton = new JButton("User Login");
        JButton infoButton = new JButton("Room Info");

        homePanel.add(userLoginButton);
        homePanel.add(infoButton);

        cardPanel.add(homePanel, "User Home");

        userLoginButton.addActionListener(e -> showUserLogin(cardPanel, cardLayout, frame));
        infoButton.addActionListener(e -> showRoomInfoDialog());

        cardLayout.show(cardPanel, "User Home");
    }

    private void showUserLogin(JPanel cardPanel, CardLayout cardLayout, JFrame frame) {
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

        loginButton.addActionListener(e -> {
            try {
                int roomId = Integer.parseInt(roomIdField.getText());
                String password = new String(passwordField.getPassword());

                if (hotelControl.validateRoom(roomId, password)) {
                    JOptionPane.showMessageDialog(null, "User Login Successful. Welcome!");
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Room ID or Password. Please try again.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid Room ID.");
            }
        });

        backButton.addActionListener(e -> {
            frame.dispose();
            startLoginScreen();
        });

        cardLayout.show(cardPanel, "User Login");
    }

    private void showRoomInfoDialog() {
        String[] floors = {"Floor 1", "Floor 2", "Floor 3"};
        String selectedFloor = (String) JOptionPane.showInputDialog(null, "Select a floor:",
                "Floor Selection", JOptionPane.QUESTION_MESSAGE, null, floors, floors[0]);

        if (selectedFloor != null) {
            StringBuilder roomInfo = new StringBuilder();

            if (selectedFloor.equals("Floor 1")) {
                roomInfo.append("\nFloor 1\n");
                for (int roomId = 101; roomId <= 110; roomId++) {
                    if (hotelControl.isRoomAvailable(roomId)) {
                        roomInfo.append("Room ").append(roomId).append(" is available.\n");
                    } else {
                        roomInfo.append("Room ").append(roomId).append(" is occupied.\n");
                    }
                }
            } else if (selectedFloor.equals("Floor 2")) {
                roomInfo.append("\nFloor 2\n");
                for (int roomId = 201; roomId <= 210; roomId++) {
                    if (hotelControl.isRoomAvailable(roomId)) {
                        roomInfo.append("Room ").append(roomId).append(" is available.\n");
                    } else {
                        roomInfo.append("Room ").append(roomId).append(" is occupied.\n");
                    }
                }
            } else if (selectedFloor.equals("Floor 3")) {
                roomInfo.append("\nFloor 3\n");
                for (int roomId = 301; roomId <= 310; roomId++) {
                    if (hotelControl.isRoomAvailable(roomId)) {
                        roomInfo.append("Room ").append(roomId).append(" is available.\n");
                    } else {
                        roomInfo.append("Room ").append(roomId).append(" is occupied.\n");
                    }
                }
            }

            JOptionPane.showMessageDialog(null, roomInfo.toString(), "Room Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void showAdminLogin(JPanel cardPanel, CardLayout cardLayout, JFrame frame) {
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

        loginButton.addActionListener(e -> {
            String adminId = adminIdField.getText();
            String adminPassword = new String(adminPasswordField.getPassword());

            Admin admin = new Admin(hotelControl.getHotel());

            if (admin.loginAsAdmin(adminId, adminPassword)) {
                JOptionPane.showMessageDialog(null, "Admin Login Successful!");
                showAdminDashboard(cardPanel, cardLayout, frame);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Admin ID or Password. Please try again.");
            }
        });

        resetButton.addActionListener(e -> {
            adminIdField.setText("");
            adminPasswordField.setText("");
        });

        cardLayout.show(cardPanel, "Admin Login");
    }

    private void showAdminDashboard(JPanel cardPanel, CardLayout cardLayout, JFrame frame) {
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
        JButton viewTimesButton = new JButton("View Times");
        JButton advancedAdminButton = new JButton("Advanced Admin");
        JButton backButton = new JButton("Back");

        adminDashboardPanel.add(hotelUserNameLabel);
        adminDashboardPanel.add(hotelUserNameField);
        adminDashboardPanel.add(roomIdLabel);
        adminDashboardPanel.add(roomIdField);
        adminDashboardPanel.add(roomPasswordLabel);
        adminDashboardPanel.add(roomPasswordField);
        adminDashboardPanel.add(setPasswordButton);
        adminDashboardPanel.add(showRoomInfoButton);
        adminDashboardPanel.add(viewTimesButton);
        adminDashboardPanel.add(advancedAdminButton);
        adminDashboardPanel.add(backButton);

        cardPanel.add(adminDashboardPanel, "Admin Dashboard");

        setPasswordButton.addActionListener(e -> {
            try {
                int roomId = Integer.parseInt(roomIdField.getText());
                String roomPassword = new String(roomPasswordField.getPassword());
                String userName = hotelUserNameField.getText();
                if (roomPassword.equals("reset")) {
                    hotelControl.resetRoomAvailability(roomId);
                    String resetTime = LocalDateTime.now().toString();
                    resetTimes.put(roomId, resetTime);
                    JOptionPane.showMessageDialog(null, "Room " + roomId + " has been reset to available.");
                } else if (roomPassword.isEmpty() || userName.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter both Room Password and Hotel User Name.");
                } else if (hotelControl.setRoomPassword(roomId, roomPassword, userName)) {
                    String registrationTime = LocalDateTime.now().toString();
                    registrationTimes.put(roomId, registrationTime);
                    JOptionPane.showMessageDialog(null, "Password for room " + roomId + " has been set successfully.");
                } else {
                    JOptionPane.showMessageDialog(null, "Room " + roomId + " not found.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid Room ID.");
            }
        });

        showRoomInfoButton.addActionListener(e -> showAdminRoomInfoDialog());
        viewTimesButton.addActionListener(e -> showTimesDialog());

        advancedAdminButton.addActionListener(e -> {
            String adminCode = JOptionPane.showInputDialog("Enter the admin code:");
            if ("123".equals(adminCode)) {
                showAdvancedAdminDialog();
            } else {
                JOptionPane.showMessageDialog(null, "Invalid admin code.");
            }
        });

        backButton.addActionListener(e -> {
            frame.dispose();
            startLoginScreen();
        });

        cardLayout.show(cardPanel, "Admin Dashboard");
    }

    private void showAdminRoomInfoDialog() {
        StringBuilder roomInfo = new StringBuilder();
        Hotel hotel = hotelControl.getHotel();

        roomInfo.append("Floor 1\n");
        for (int roomId = 101; roomId <= 110; roomId++) {
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

        roomInfo.append("\nFloor 2\n");
        for (int roomId = 201; roomId <= 210; roomId++) {
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

        roomInfo.append("\nFloor 3\n");
        for (int roomId = 301; roomId <= 310; roomId++) {
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

        JOptionPane.showMessageDialog(null, roomInfo.toString(), "Room Information", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showTimesDialog() {
        StringBuilder timesInfo = new StringBuilder();
        timesInfo.append("Registration and Reset Times\n");

        for (Map.Entry<Integer, String> entry : registrationTimes.entrySet()) {
            int roomId = entry.getKey();
            String registrationTime = entry.getValue();
            String resetTime = resetTimes.get(roomId);
            timesInfo.append("Room ").append(roomId).append(":\n");
            timesInfo.append("Registration Time: ").append(registrationTime).append("\n");
            if (resetTime != null) {
                timesInfo.append("Reset Time: ").append(resetTime).append("\n");
            } else {
                timesInfo.append("Reset Time: Not yet reset\n");
            }
        }

        JOptionPane.showMessageDialog(null, timesInfo.toString(), "Times Information", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showAdvancedAdminDialog() {
        JOptionPane.showMessageDialog(null, "Welcome to the advanced admin level!", "Advanced Admin", JOptionPane.INFORMATION_MESSAGE);
        // Here, you can add additional functionalities and dialogs for the advanced admin level
    }
}