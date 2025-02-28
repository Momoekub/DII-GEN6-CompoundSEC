import java.time.LocalDateTime;
import java.util.List;

public class AccessCard {
    private String cardID;
    private String multiDoorCode;
    private LocalDateTime expiryTime;
    private List<String> permissions;

    public AccessCard(String cardID, String multiDoorCode, List<String> permissions, LocalDateTime expiryTime) {
        this.cardID = cardID;
        this.multiDoorCode = multiDoorCode;
        this.permissions = permissions;
        this.expiryTime = expiryTime;
    }

    public String getCardID() {
        return cardID;
    }

    public String getMultiDoorCode() {
        return multiDoorCode;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public LocalDateTime getExpiryTime() {
        return expiryTime;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public void setExpiryTime(LocalDateTime expiryTime) {
        this.expiryTime = expiryTime;
    }
}

