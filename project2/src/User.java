public class User extends AbstractUser {
    public User(Hotel hotel) {
        super(hotel);
    }

    @Override
    public boolean performSpecialAction() {
        // Perform user-specific actions here
        return true;
    }
}