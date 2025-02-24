import java.util.HashMap;
import java.util.Map;

public class Hotel implements RoomManagement {
    private Map<Integer, Room> rooms = new HashMap<>();

    public Hotel() {
        // Initialize rooms for demonstration
        for (int i = 101; i <= 110; i++) {
            rooms.put(i, new Room(i, true, "", ""));
        }
        for (int i = 201; i <= 210; i++) {
            rooms.put(i, new Room(i, true, "", ""));
        }
        for (int i = 301; i <= 310; i++) {
            rooms.put(i, new Room(i, true, "", ""));
        }
    }

    @Override
    public boolean validateRoom(int roomId, String password) {
        Room room = rooms.get(roomId);
        return room != null && room.getPassword().equals(password);
    }

    @Override
    public boolean setRoomPassword(int roomId, String password, String userName) {
        Room room = rooms.get(roomId);
        if (room != null) {
            room.setPassword(password);
            room.setAvailable(false);
            room.setUserName(userName);
            return true;
        }
        return false;
    }

    @Override
    public boolean isRoomAvailable(int roomId) {
        Room room = rooms.get(roomId);
        return room != null && room.isAvailable();
    }

    @Override
    public boolean resetRoom(int roomId) {
        Room room = rooms.get(roomId);
        if (room != null) {
            room.setAvailable(true);
            room.setPassword("");
            room.setUserName("");
            return true;
        }
        return false;
    }

    public Map<Integer, Room> getRooms() {
        return rooms;
    }
}