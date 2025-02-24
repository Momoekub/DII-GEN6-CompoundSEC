public class Room {
    private int roomId;
    private boolean available;
    private String password;
    private String userName; // New field to store the user who booked the room

    public Room(int roomId, boolean available, String password, String userName) {
        this.roomId = roomId;
        this.available = available;
        this.password = password;
        this.userName = userName;
    }

    public int getRoomId() {
        return roomId;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}