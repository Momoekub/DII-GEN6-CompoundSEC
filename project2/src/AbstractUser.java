import java.util.Map;

public abstract class AbstractUser {
    protected Hotel hotel;

    public AbstractUser(Hotel hotel) {
        this.hotel = hotel;
    }

    public abstract boolean performSpecialAction();

    public void displayRoomStatus() {
        System.out.println("Room Status:");
        Map<Integer, Room> rooms = hotel.getRooms();
        for (Map.Entry<Integer, Room> entry : rooms.entrySet()) {
            Room room = entry.getValue();
            String status = room.isAvailable() ? "Available" : "Occupied by " + room.getUserName();
            System.out.println("Room " + room.getRoomId() + ": " + status);
        }
    }
}