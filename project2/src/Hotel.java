import java.util.HashMap;
import java.util.Map;

public class Hotel implements RoomManagement {
    private Map<Integer, String> roomPasswords;

    public Hotel() {
        roomPasswords = new HashMap<>();
    }

    @Override
    public boolean validateRoom(int roomId, String password) {
        return roomPasswords.containsKey(roomId) && roomPasswords.get(roomId).equals(password);
    }

    @Override
    public boolean setRoomPassword(int roomId, String password) {
        if (password == null) {
            roomPasswords.remove(roomId); // ลบห้องออกเพื่อให้ห้องว่าง
            return true;
        }
        roomPasswords.put(roomId, password); // อัปเดตรหัสห้อง
        return true;
    }


    @Override
    public boolean isRoomAvailable(int roomId) {
        return !roomPasswords.containsKey(roomId);  // ถ้าห้องไม่มีรหัสผ่าน แสดงว่าว่าง
    }
}
