import java.time.LocalDateTime;

public class Room {
    private int roomId;
    private boolean available;
    private String userName;
    private String password;
    private LocalDateTime passwordSetTime;

    // Constructor that takes roomId as an argument
    public Room(int roomId) {
        this.roomId = roomId;
        this.available = true;
    }

    // Getters and setters for all fields

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getPasswordSetTime() {
        return passwordSetTime;
    }

    public void setPasswordSetTime(LocalDateTime passwordSetTime) {
        this.passwordSetTime = passwordSetTime;
    }
}