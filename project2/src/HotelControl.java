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
            room.setAvailable(false);
            room.setUserName(userName);
            room.setPassword(password);
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
        return room != null && !room.isAvailable() && password.equals(room.getPassword());
    }
}