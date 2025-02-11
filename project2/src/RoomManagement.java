public interface RoomManagement {
    boolean validateRoom(int roomId, String password);
    boolean setRoomPassword(int roomId, String password);
    boolean isRoomAvailable(int roomId);  // เพิ่มฟังก์ชันนี้ในอินเทอร์เฟซ
}
