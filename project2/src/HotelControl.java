public class HotelControl {
    private RoomManagement roomManagement;

    public HotelControl(RoomManagement roomManagement) {
        this.roomManagement = roomManagement;
    }

    public boolean validateRoom(int roomId, String password) {
        return roomManagement.validateRoom(roomId, password);
    }

    public boolean setRoomPassword(int roomId, String password, String userName) {
        return roomManagement.setRoomPassword(roomId, password, userName);
    }

    public boolean isRoomAvailable(int roomId) {
        return roomManagement.isRoomAvailable(roomId);
    }

    // เพิ่มฟังก์ชันสำหรับการเปลี่ยนรหัสห้อง
    public void changeRoomPassword(int roomId, String newPassword, String userName) {
        if (roomManagement.setRoomPassword(roomId, newPassword, userName)) {
            System.out.println("Password for room " + roomId + " has been changed.");
        } else {
            System.out.println("Room " + roomId + " not found.");
        }
    }

    // เพิ่มฟังก์ชันเพื่อรีเซ็ตรห้องให้กลับไปเป็นห้องว่าง
    public void resetRoomAvailability(int roomId) {
        // รีเซ็ตรห้องให้เป็นว่าง
        if (roomManagement.resetRoom(roomId)) {
            System.out.println("Room " + roomId + " has been reset to available.");
        } else {
            System.out.println("Room " + roomId + " not found or invalid.");
        }
    }

    // เพิ่มฟังก์ชัน getHotel
    public Hotel getHotel() {
        return (Hotel) roomManagement;
    }
}