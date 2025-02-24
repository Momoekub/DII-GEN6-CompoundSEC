public class Admin extends AbstractUser {
    public Admin(Hotel hotel) {
        super(hotel);
    }

    @Override
    public boolean performSpecialAction() {
        // ฟังก์ชันพิเศษสำหรับแอดมิน เช่น เปลี่ยนรหัสผ่านห้อง
        return true;
    }

    public boolean loginAsAdmin(String adminId, String adminPassword) {
        return "admin123".equals(adminId) && "adminpassword".equals(adminPassword);
    }
}