public class Main {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        HotelControl hotelControl = new HotelControl(hotel);
        AccessControlSystem accessControlSystem = new AccessControlSystem(); // Create an instance of AccessControlSystem

        HotelUI hotelUI = new HotelUI(hotelControl, accessControlSystem); // Pass both HotelControl and AccessControlSystem
        hotelUI.startLoginScreen();
    }
}
//Main.java
//คลาสนี้เป็นจุดเริ่มต้นของแอปพลิเคชัน มันสร้างคอมโพเนนต์ที่จำเป็นและเริ่มหน้าจอล็อกอิน
//
//Hotel.java
//คลาสนี้แทนโรงแรมและมีการเก็บรวบรวมห้องต่างๆ มันจัดการสถานะของแต่ละห้อง
//
//Room.java
//คลาสนี้แทนห้องแต่ละห้องในโรงแรม มันเก็บข้อมูลเกี่ยวกับการว่างของห้องและผู้ใช้ที่ถูกกำหนดให้กับห้องนั้น
//
//HotelControl.java
//คลาสนี้มีตรรกะสำหรับการจัดการโรงแรม รวมถึงการตรวจสอบห้อง การตั้งรหัสผ่าน และการรีเซ็ตการว่างของห้อง
//
//AccessCard.java
//คลาสนี้แทนบัตรเข้าถึง ซึ่งมีข้อมูลเกี่ยวกับรหัสบัตร รหัสประตูหลายประตู สิทธิ์การเข้าถึง และเวลาหมดอายุ
//
//AccessControlSystem.java
//คลาสนี้จัดการระบบควบคุมการเข้าถึง รวมถึงการสร้าง การแก้ไข และการยกเลิกบัตรเข้าถึง นอกจากนี้ยังตรวจสอบการเข้าถึงและบันทึกเหตุการณ์ต่างๆ
//
//AuditTrail.java
//คลาสนี้บันทึกเหตุการณ์ต่างๆ ที่เกี่ยวข้องกับความพยายามในการเข้าถึงและการจัดการบัตร
//
//HotelUI.java
//คลาสนี้ให้ส่วนติดต่อผู้ใช้สำหรับระบบการจัดการโรงแรม มันรวมฟังก์ชันการทำงานสำหรับทั้งโหมดผู้ใช้และผู้ดูแล รวมถึงการล็อกอิน ข้อมูลห้อง และฟีเจอร์ผู้ดูแลขั้นสูง