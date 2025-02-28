public class Room {
    private int roomId;
    private String password;
    private String userName;
    private boolean isAvailable;

    public Room(int roomId) {
        this.roomId = roomId;
        this.isAvailable = true;
    }

    public int getRoomId() {
        return roomId;
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

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}