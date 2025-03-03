public class User extends AbstractUser {
    public User(Hotel hotel, LoggingFacade loggingFacade) {
        super(hotel, loggingFacade);
    }

    @Override
    public boolean performSpecialAction() {
        loggingFacade.log("User performed a special action.");
        return true;
    }
}