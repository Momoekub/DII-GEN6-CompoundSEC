public class UserLoginStrategy implements LoginStrategy {
    private HotelControl hotelControl;
    private HotelUI hotelUI;

    public UserLoginStrategy(HotelControl hotelControl, HotelUI hotelUI) {
        this.hotelControl = hotelControl;
        this.hotelUI = hotelUI;
    }

    @Override
    public void login() {
        hotelUI.startUserMode();
    }
}