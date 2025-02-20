import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // สร้าง Hotel และ HotelControl
        Hotel hotel = new Hotel();
        HotelControl hotelControl = new HotelControl(hotel);

        // ป้อนข้อมูลจากผู้ใช้เพื่อเลือกโหมด
        String mode = JOptionPane.showInputDialog("Enter 'user' for User Mode or 'admin' for Admin Mode:");

        // ตรวจสอบว่าผู้ใช้เลือกโหมดไหน
        if ("user".equalsIgnoreCase(mode)) {
            // หากเลือก User Mode
            HotelUI hotelUI = new HotelUI(hotelControl);
            hotelUI.startUserMode(); // เริ่มต้น UI สำหรับ User
        } else if ("admin".equalsIgnoreCase(mode)) {
            // หากเลือก Admin Mode
            HotelUI hotelUI = new HotelUI(hotelControl);
            hotelUI.startAdminMode(); // เริ่มต้น UI สำหรับ Admin
        } else {
            JOptionPane.showMessageDialog(null, "Invalid mode. Please enter 'user' or 'admin'.");
        }
    }
}
