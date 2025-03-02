import java.util.Map;

public class UserManagementFacade {
    private Hotel hotel;
    private LoggingFacade loggingFacade;

    public UserManagementFacade(Hotel hotel, LoggingFacade loggingFacade) {
        this.hotel = hotel;
        this.loggingFacade = loggingFacade;
    }

    public void displayRoomStatus() {
        loggingFacade.log("Displaying room status");
        for (Map.Entry<Integer, Room> entry : hotel.getRooms().entrySet()) {
            Room room = entry.getValue();
            String status = room.isAvailable() ? "Available" : "Occupied by " + room.getUserName();
            System.out.println("Room " + room.getRoomId() + ": " + status);
        }
    }
}