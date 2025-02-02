import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HotelControl implements CardManagement {

    private Map<Integer, Integer> roomCardMap; // ใช้ HashMap เพื่อเก็บข้อมูลห้องและรหัสการ์ด
    private boolean adminAccess = false; // ตัวแปรสำหรับตรวจสอบการเข้าถึงผู้ดูแล

    // สร้าง constructor เพื่อเริ่มต้นห้องและการ์ด
    public HotelControl() {
        roomCardMap = new HashMap<>();
        // เพิ่มห้องและรหัสการ์ดเริ่มต้น
        roomCardMap.put(101, 123); // ห้อง 101 ใช้การ์ด ID 123
        roomCardMap.put(102, 124); // ห้อง 102 ใช้การ์ด ID 124
        roomCardMap.put(201, 125); // ห้อง 201 ใช้การ์ด ID 125
        roomCardMap.put(202, 126); // ห้อง 202 ใช้การ์ด ID 126
    }

    // ฟังก์ชันตรวจสอบการเข้าถึงห้อง
    public boolean validateAccess(int cardId, int roomNumber) {
        Integer validCardId = roomCardMap.get(roomNumber); // หา CardID ที่ตรงกับ RoomID
        if (validCardId != null && validCardId == cardId) {
            System.out.println("Access successful to room " + roomNumber);
            return true;
        } else {
            System.out.println("Access denied to room " + roomNumber);
            return false;
        }
    }

    // Implement ฟังก์ชันจาก CardManagement Interface
    @Override
    public void addCard(int roomNumber, int cardId) {
        roomCardMap.put(roomNumber, cardId); // เพิ่มการ์ดเข้าไปใน HashMap
        System.out.println("Card ID " + cardId + " added to room " + roomNumber);
    }

    @Override
    public void updateCard(int roomNumber, int newCardId) {
        if (roomCardMap.containsKey(roomNumber)) {
            roomCardMap.put(roomNumber, newCardId); // เปลี่ยนการ์ดใหม่ให้ห้องที่ระบุ
            System.out.println("Card ID for room " + roomNumber + " updated to " + newCardId);
        } else {
            System.out.println("Room " + roomNumber + " does not exist.");
        }
    }

    @Override
    public void changeRoomId(int oldRoomNumber, int newRoomNumber) {
        if (roomCardMap.containsKey(oldRoomNumber)) {
            // ดึงข้อมูล CardID ของห้องเก่า
            int cardId = roomCardMap.remove(oldRoomNumber); // ลบห้องเก่าออกจาก roomCardMap
            roomCardMap.put(newRoomNumber, cardId); // เพิ่มห้องใหม่ที่มี RoomID ใหม่
            System.out.println("Room ID has been changed from " + oldRoomNumber + " to " + newRoomNumber);
        } else {
            System.out.println("Room " + oldRoomNumber + " not found.");
        }
    }

    @Override
    public void changeCardId(int roomNumber, int newCardId) {
        if (roomCardMap.containsKey(roomNumber)) {
            roomCardMap.put(roomNumber, newCardId); // เปลี่ยนการ์ดใหม่ให้ห้องที่ระบุ
            System.out.println("Card ID for room " + roomNumber + " has been updated to " + newCardId);
        } else {
            System.out.println("Room " + roomNumber + " not found.");
        }
    }

    // ฟังก์ชันสำหรับการเพิ่มห้องใหม่
    public void addRoom(int roomNumber, int cardId) {
        if (adminAccess) {
            roomCardMap.put(roomNumber, cardId); // เพิ่มห้องและการ์ดเข้าไปใน HashMap
            System.out.println("Room " + roomNumber + " added with Card ID " + cardId);
        } else {
            System.out.println("Admin access is required to add rooms.");
        }
    }

    // ฟังก์ชันสำหรับการเปิดใช้งานผู้ดูแล (admin)
    public boolean authenticateAdmin(String adminPassword) {
        if ("admin111".equals(adminPassword)) {
            // ถ้ากรอกรหัส admin111 แล้ว ให้ขอรหัสยืนยัน
            System.out.println("Enter confirmation password to continue:");
            Scanner scanner = new Scanner(System.in);  // สร้าง scanner ใหม่เพราะใช้ในที่อื่น
            String confirmPassword = scanner.nextLine();  // รับรหัสยืนยัน
            if ("000".equals(confirmPassword)) {
                adminAccess = true;
                System.out.println("Admin authentication successful!");
                return true;
            } else {
                System.out.println("Incorrect confirmation password.");
                return false;
            }
        } else {
            System.out.println("Incorrect admin password.");
            return false;
        }
    }

    // ฟังก์ชันเพื่อแสดงข้อมูลห้องและรหัสการ์ด
    public void showRoomDetails() {
        System.out.println("RoomID and CardID details:");
        for (Map.Entry<Integer, Integer> entry : roomCardMap.entrySet()) {
            System.out.println("Room " + entry.getKey() + " has Card ID " + entry.getValue());
        }
    }
}
