public class Admin extends AbstractUser {
    public Admin(Hotel hotel, LoggingFacade loggingFacade) {
        super(hotel, loggingFacade);
    }

    @Override
    public boolean performSpecialAction() {
        loggingFacade.log("Admin performed a special action.");
        return true;
    }

    public boolean loginAsAdmin(String adminId, String adminPassword) {
        boolean success = "a".equals(adminId) && "a".equals(adminPassword);
        loggingFacade.log("Admin login attempt with ID: " + adminId + " - " + (success ? "Success" : "Failure"));
        return success;
    }
}