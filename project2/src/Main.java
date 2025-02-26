public class Main {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        HotelControl hotelControl = new HotelControl(hotel);
        AccessControlSystem accessControlSystem = new AccessControlSystem();

        HotelUI hotelUI = new HotelUI(hotelControl, accessControlSystem);
        hotelUI.startLoginScreen();
    }
}