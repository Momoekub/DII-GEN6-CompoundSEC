public class    Main {
    public static void main(String[] args) {
        // สร้าง Hotel และ HotelControl
        Hotel hotel = new Hotel();
        HotelControl hotelControl = new HotelControl(hotel);

        // สร้างและเริ่มต้น UI
        HotelUI hotelUI = new HotelUI(hotelControl);
        hotelUI.start();
    }
}
