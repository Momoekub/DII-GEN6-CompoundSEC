import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    public static void main(String[] args) {
        // Create Hotel and HotelControl instances
        Hotel hotel = new Hotel();
        HotelControl hotelControl = new HotelControl(hotel);

        // Create JFrame window
        JFrame frame = new JFrame("Hotel Management System");

        // Add window closing event listener
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int option = JOptionPane.showConfirmDialog(frame,
                        "Do you really want to exit?",
                        "Exit Confirmation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);

                if (option == JOptionPane.YES_OPTION) {
                    System.exit(0); // Exit the program
                }
            }
        });

        // Prompt user for mode selection (User or Admin)
        String mode;
        while (true) {
            mode = JOptionPane.showInputDialog("Enter 'user' for User Mode or 'admin' for Admin Mode:");

            // If the user clicks "Cancel" or closes the window
            if (mode == null) {
                JOptionPane.showMessageDialog(null, "Program exited.", "Goodbye", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0); // Exit the program
            }

            mode = mode.trim().toLowerCase(); // Trim spaces and convert to lowercase

            if (mode.equals("user")) {
                HotelUI hotelUI = new HotelUI(hotelControl);
                hotelUI.startUserMode(); // Start User Mode
                break;
            } else if (mode.equals("admin")) {
                HotelUI hotelUI = new HotelUI(hotelControl);
                hotelUI.startAdminMode(); // Start Admin Mode
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Invalid mode! Please enter 'user' or 'admin'.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}