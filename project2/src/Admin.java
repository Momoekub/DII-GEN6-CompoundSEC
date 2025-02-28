public class Admin extends AbstractUser {
    public Admin(Hotel hotel) {
        super(hotel);
    }

    @Override
    public boolean performSpecialAction() {
        return true;
    }

    public boolean loginAsAdmin(String adminId, String adminPassword) {
        return "admin123".equals(adminId) && "adminpassword".equals(adminPassword);
    }
}