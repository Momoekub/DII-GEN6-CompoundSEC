public class Room {
    private int roomId;
    private boolean available;
    private String password;

    public Room(int roomId, boolean available, String password) {
        this.roomId = roomId;
        this.available = available;
        this.password = password;
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
}
