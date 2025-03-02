public class Main {
    public static void main(String[] args) {
        LoggingFacade loggingFacade = new LoggingFacade();
        Hotel hotel = new Hotel(loggingFacade);
        HotelControl hotelControl = new HotelControl(hotel, loggingFacade);
        AccessControlSystem accessControlSystem = new AccessControlSystem(loggingFacade);

        HotelUI hotelUI = new HotelUI(hotelControl, accessControlSystem, loggingFacade);
        hotelUI.startLoginScreen();
    }
}