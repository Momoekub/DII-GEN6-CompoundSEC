public abstract class AccessControl {
    public abstract boolean validateAccess(int cardId, int roomNumber);
    public abstract void addRoom(int roomNumber, int cardId); // ฟังก์ชันเพิ่มห้องใหม่
}
