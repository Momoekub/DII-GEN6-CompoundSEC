import java.util.HashMap;
import java.util.Map;

public class Hotel {
    private Map<Integer, Room> rooms;
    private LoggingFacade loggingFacade;

    public Hotel(LoggingFacade loggingFacade) {
        this.rooms = new HashMap<>();
        this.loggingFacade = loggingFacade;
        // Initialize rooms (for simplicity, I'll add some rooms)
        for (int i = 101; i <= 110; i++) {
            rooms.put(i, new Room(i));
        }
        for (int i = 201; i <= 210; i++) {
            rooms.put(i, new Room(i));
        }
        for (int i = 301; i <= 310; i++) {
            rooms.put(i, new Room(i));
        }
        loggingFacade.log("Hotel initialized with rooms.");
    }

    public Map<Integer, Room> getRooms() {
        return rooms;
    }

    public Room getRoom(int roomId) {
        return rooms.get(roomId);
    }

    public boolean validateRoom(int roomId, String password) {
        Room room = getRoom(roomId);
        return room != null && !room.isAvailable() && password.equals(room.getPassword());
    }
}