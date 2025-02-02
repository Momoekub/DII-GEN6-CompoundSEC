    public interface CardManagement {
    // ฟังก์ชันในการเปลี่ยน RoomID
    void changeRoomId(int oldRoomNumber, int newRoomNumber);

    // ฟังก์ชันในการเปลี่ยน CardID
    void changeCardId(int roomNumber, int newCardId);
}
