public class RoomManagementFacade {
    private HotelControl hotelControl;
    private LoggingFacade loggingFacade;

    public RoomManagementFacade(HotelControl hotelControl, LoggingFacade loggingFacade) {
        this.hotelControl = hotelControl;
        this.loggingFacade = loggingFacade;
    }

    public boolean isRoomAvailable(int roomId) {
        loggingFacade.log("Checking availability for room " + roomId);
        return hotelControl.isRoomAvailable(roomId);
    }

    public boolean resetRoomAvailability(int roomId) {
        loggingFacade.log("Resetting availability for room " + roomId);
        return hotelControl.resetRoomAvailability(roomId);
    }

    public boolean setRoomPassword(int roomId, String password, String userName) {
        String encryptedPassword = EncryptionUtil.encrypt(password);
        loggingFacade.log("Setting password for room " + roomId);
        return hotelControl.setRoomPassword(roomId, encryptedPassword, userName);
    }

    public boolean validateRoom(int roomId, String password) {
        String encryptedPassword = EncryptionUtil.encrypt(password);
        loggingFacade.log("Validating room " + roomId);
        return hotelControl.validateRoom(roomId, encryptedPassword);
    }
}