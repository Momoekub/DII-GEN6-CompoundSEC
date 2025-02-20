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

    // เพิ่มฟังก์ชันสำหรับการเปลี่ยนรหัสห้อง
    public void changeRoomPassword(int roomId, String newPassword) {
        if (roomManagement.setRoomPassword(roomId, newPassword)) {
            System.out.println("Password for room " + roomId + " has been changed.");
        } else {
            System.out.println("Room " + roomId + " not found.");
        }
    }
}
