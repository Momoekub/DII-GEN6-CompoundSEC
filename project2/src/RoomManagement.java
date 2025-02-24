public interface RoomManagement {
    boolean validateRoom(int roomId, String password);
    boolean setRoomPassword(int roomId, String password, String userName);
    boolean isRoomAvailable(int roomId);
    boolean resetRoom(int roomId);  // Reset room to available
}