import java.time.LocalDateTime;

public class HotelControl {
    private Hotel hotel;
    private LoggingFacade loggingFacade;

    public HotelControl(Hotel hotel, LoggingFacade loggingFacade) {
        this.hotel = hotel;
        this.loggingFacade = loggingFacade;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public boolean isRoomAvailable(int roomId) {
        Room room = hotel.getRoom(roomId);
        return room != null && room.isAvailable();
    }

    public boolean resetRoomAvailability(int roomId) {
        Room room = hotel.getRoom(roomId);
        if (room != null && !room.isAvailable()) {
            room.setAvailable(true);
            room.setPassword(null);
            room.setUserName(null);
            room.setPasswordSetTime(null);
            loggingFacade.log("Room " + roomId + " is now available.");
            System.out.println("Room " + roomId + " is now available.");
            return true;
        }
        return false;
    }

    public boolean setRoomPassword(int roomId, String encryptedPassword, String userName) {
        Room room = hotel.getRoom(roomId);
        if (room != null && room.isAvailable()) {
            room.setAvailable(false);
            room.setUserName(userName);
            room.setPassword(encryptedPassword); // Store the encrypted password directly
            LocalDateTime now = LocalDateTime.now();
            room.setPasswordSetTime(now);

            LocalDateTime expirationTime = now.plusMinutes(1);
            loggingFacade.log("Set password for room " + roomId + " with expiry time " + expirationTime);
            System.out.println("Encrypted Password for Room " + roomId + ": " + encryptedPassword);
            System.out.println("Password set at: " + now);
            System.out.println("Password will expire at: " + expirationTime);

            return true;
        }
        return false;
    }

    public boolean validateRoom(int roomId, String encryptedPassword) {
        Room room = hotel.getRoom(roomId);
        if (room != null && !room.isAvailable()) {
            LocalDateTime passwordSetTime = room.getPasswordSetTime();
            boolean isValid = EncryptionUtil.validate(encryptedPassword, room.getPassword(), passwordSetTime);
            loggingFacade.log("Validation attempt for room " + roomId + " - " + (isValid ? "Success" : "Failure"));
            return isValid;
        }
        return false;
    }
}