import javax.swing.*;
public class UserLoginStrategy implements LoginStrategy {
    private HotelControl hotelControl;
    private HotelUI hotelUI;

    public UserLoginStrategy(HotelControl hotelControl, HotelUI hotelUI) {
        this.hotelControl = hotelControl;
        this.hotelUI = hotelUI;
    }

    @Override
    public void login( ) {  // เพิ่ม JFrame เป็นพารามิเตอร์
        hotelUI.startUserMode();  // ส่ง frame ไปยัง startUserMode
    }
}
