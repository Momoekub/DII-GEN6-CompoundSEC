import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccessControlSystem {
    private Map<String, AccessCard> cards;
    private AuditTrail auditTrail;
    private LoggingFacade loggingFacade;

    public AccessControlSystem(LoggingFacade loggingFacade) {
        this.cards = new HashMap<>();
        this.auditTrail = new AuditTrail();
        this.loggingFacade = loggingFacade;
    }

    public void generateCard(String cardID, String multiDoorCode, List<String> permissions, LocalDateTime expiryTime) {
        AccessCard card = new AccessCard(cardID, multiDoorCode, permissions, expiryTime);
        cards.put(cardID, card);
        auditTrail.logCardCreation(cardID, permissions, expiryTime);
        loggingFacade.log("Generated card with ID: " + cardID);
    }

    public AuditTrail getAuditTrail() {
        return auditTrail;
    }
}