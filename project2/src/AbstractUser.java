public abstract class AbstractUser implements UserManagement {
    protected Hotel hotel;

    public AbstractUser(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public boolean login(String roomId, String password) {
        int roomIdInt = Integer.parseInt(roomId);
        return hotel.validateRoom(roomIdInt, password);
    }

    // ฟังก์ชันที่จะแตกต่างกันระหว่างผู้ใช้และแอดมิน
    public abstract boolean performSpecialAction();
}
