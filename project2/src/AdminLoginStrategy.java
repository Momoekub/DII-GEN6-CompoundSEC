
public class AdminLoginStrategy implements LoginStrategy {
    private HotelControl hotelControl;
    private HotelUI hotelUI;

    public AdminLoginStrategy(HotelControl hotelControl, HotelUI hotelUI) {
        this.hotelControl = hotelControl;
        this.hotelUI = hotelUI;
    }

    @Override
    public void login() {  // เพิ่ม JFrame เป็นอาร์กิวเมนต์
        hotelUI.startAdminMode();  // ส่ง frame ไปยัง startAdminMode
    }
}
