import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AuditTrail {
    private List<String> logs;

    public AuditTrail() {
        logs = new ArrayList<>();
    }

    public void logAccessAttempt(String cardID, String location, boolean success) {
        String log = "Access Attempt - CardID: " + cardID + ", Location: " + location + ", Time: " + LocalDateTime.now() + ", Success: " + success;
        logs.add(log);
    }

    public void logCardCreation(String cardID, List<String> permissions, LocalDateTime expiryTime) {
        String log = "Card Creation - CardID: " + cardID + ", Permissions: " + permissions + ", ExpiryTime: " + expiryTime + ", Time: " + LocalDateTime.now();
        logs.add(log);
    }

    public void logCardModification(String cardID, List<String> newPermissions, LocalDateTime newExpiryTime) {
        String log = "Card Modification - CardID: " + cardID + ", NewPermissions: " + newPermissions + ", NewExpiryTime: " + newExpiryTime + ", Time: " + LocalDateTime.now();
        logs.add(log);
    }

    public void logCardRevocation(String cardID) {
        String log = "Card Revocation - CardID: " + cardID + ", Time: " + LocalDateTime.now();
        logs.add(log);
    }

    public List<String> getLogs() {
        return logs;
    }
}
