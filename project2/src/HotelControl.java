public class HotelControl {
    private Hotel hotel;

    public HotelControl(Hotel hotel) {
        this.hotel = hotel;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public boolean setRoomPassword(int roomId, String password, String userName) {
        Room room = hotel.getRoom(roomId);
        if (room != null && room.isAvailable()) {
            String encryptedPassword = EncryptionUtil.encrypt(password);
            room.setAvailable(false);
            room.setUserName(userName);
            room.setPassword(encryptedPassword);
            System.out.println("Encrypted Password for Room " + roomId + ": " + encryptedPassword);
            return true;
        }
        return false;
    }

    public void resetRoomAvailability(int roomId) {
        Room room = hotel.getRoom(roomId);
        if (room != null) {
            room.setAvailable(true);
            room.setUserName(null);
            room.setPassword(null);
        }
    }

    public boolean isRoomAvailable(int roomId) {
        Room room = hotel.getRoom(roomId);
        return room != null && room.isAvailable();
    }

    public boolean validateRoom(int roomId, String password) {
        Room room = hotel.getRoom(roomId);
        if (room != null && !room.isAvailable()) {
            // ไม่ต้องแปลงรหัสผ่านใหม่เป็นแฮช เพราะผู้ใช้ป้อนแฮชมาแล้ว
            String userInputHash = password;  // ใช้รหัสผ่านที่ป้อนมาเลย (เช่น แฮชที่ผู้ใช้ป้อน)
            String storedHash = room.getPassword();  // ค่าที่เก็บในฐานข้อมูล
            return userInputHash.equals(storedHash);  // เปรียบเทียบแฮช
        }
        return false;
    }

    }
