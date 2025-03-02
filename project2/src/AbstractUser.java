import java.util.Map;

public abstract class AbstractUser {
    protected Hotel hotel;
    protected LoggingFacade loggingFacade;

    public AbstractUser(Hotel hotel, LoggingFacade loggingFacade) {
        this.hotel = hotel;
        this.loggingFacade = loggingFacade;
    }

    public abstract boolean performSpecialAction();

    public void displayRoomStatus() {
        loggingFacade.log("Displaying room status.");
        System.out.println("Room Status:");
        Map<Integer, Room> rooms = hotel.getRooms();
        for (Map.Entry<Integer, Room> entry : rooms.entrySet()) {
            Room room = entry.getValue();
            String status = room.isAvailable() ? "Available" : "Occupied by " + room.getUserName();
            System.out.println("Room " + room.getRoomId() + ": " + status);
        }
    }
}