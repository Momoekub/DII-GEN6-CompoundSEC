public class Admin extends AbstractUser {
    public Admin(Hotel hotel) {
        super(hotel);
    }

    @Override
    public boolean performSpecialAction() {
        // ฟังก์ชันพิเศษสำหรับแอดมิน เช่น เปลี่ยนรหัสผ่านห้อง
        return true;
    }
}
