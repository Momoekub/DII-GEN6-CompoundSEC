import java.time.LocalDateTime;
import java.util.List;

public class AccessControlFacade {
    private AccessControlSystem accessControlSystem;
    private LoggingFacade loggingFacade;

    public AccessControlFacade(AccessControlSystem accessControlSystem, LoggingFacade loggingFacade) {
        this.accessControlSystem = accessControlSystem;
        this.loggingFacade = loggingFacade;
    }

    public void generateAccessCard(String cardID, String multiDoorCode, List<String> permissions, LocalDateTime expiryTime) {
        loggingFacade.log("Generating access card with ID " + cardID);
        accessControlSystem.generateCard(cardID, multiDoorCode, permissions, expiryTime);
    }

    public List<String> getAccessLogs() {
        loggingFacade.log("Retrieving access logs");
        return accessControlSystem.getAuditTrail().getLogs();
    }
}