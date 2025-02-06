public abstract class AccessControl {
    // abstract method สำหรับตรวจสอบการเข้าถึง
    public abstract boolean validateAccess(int cardId, int roomNumber);

    // abstract method สำหรับการเพิ่มห้อง
    public abstract void addRoom(int roomNumber, int cardId);
}
