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
}


