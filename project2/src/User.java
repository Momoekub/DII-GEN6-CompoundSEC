public class User extends AbstractUser {
    public User(Hotel hotel) {
        super(hotel);
    }

    @Override
    public boolean performSpecialAction() {
        // ฟังก์ชันที่ใช้เฉพาะผู้ใช้งาน เช่น ดูห้องว่าง
        return true;
    }
}