public class HotelControl {
    private RoomManagement roomManagement;

    public HotelControl(RoomManagement roomManagement) {
        this.roomManagement = roomManagement;
    }

    public boolean validateRoom(int roomId, String password) {
        return roomManagement.validateRoom(roomId, password);
    }

    public boolean setRoomPassword(int roomId, String password) {
        return roomManagement.setRoomPassword(roomId, password);
    }

    public boolean isRoomAvailable(int roomId) {
        return roomManagement.isRoomAvailable(roomId);  // ใช้เมธอดจาก Hotel
    }
}
