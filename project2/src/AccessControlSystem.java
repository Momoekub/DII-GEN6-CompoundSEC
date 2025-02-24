import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccessControlSystem {
    private Map<String, AccessCard> cards;
    private AuditTrail auditTrail;

    public AccessControlSystem() {
        cards = new HashMap<>();
        auditTrail = new AuditTrail();
    }

    public void generateCard(String cardID, String multiDoorCode, List<String> permissions, LocalDateTime expiryTime) {
        AccessCard card = new AccessCard(cardID, multiDoorCode, permissions, expiryTime);
        cards.put(cardID, card);
        auditTrail.logCardCreation(cardID, permissions, expiryTime);
    }

    public void modifyCard(String cardID, List<String> newPermissions, LocalDateTime newExpiryTime) {
        AccessCard card = cards.get(cardID);
        if (card != null) {
            card.setPermissions(newPermissions);
            card.setExpiryTime(newExpiryTime);
            auditTrail.logCardModification(cardID, newPermissions, newExpiryTime);
        }
    }

    public void revokeCard(String cardID) {
        cards.remove(cardID);
        auditTrail.logCardRevocation(cardID);
    }

    public boolean checkAccess(String cardID, String location) {
        AccessCard card = cards.get(cardID);
        if (card != null && card.getPermissions().contains(location) && card.getExpiryTime().isAfter(LocalDateTime.now())) {
            auditTrail.logAccessAttempt(cardID, location, true);
            return true;
        }
        auditTrail.logAccessAttempt(cardID, location, false);
        return false;
    }
}