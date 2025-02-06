import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HotelControl accessControl = new HotelControl(); // สร้างตัวแปร HotelControl

        // โปรแกรมหลักที่ให้ผู้ใช้กรอกข้อมูล
        while (true) {
            System.out.println("Enter your RoomID (or type 'admin111' to access Admin Mode):");
            String roomInput = scanner.nextLine();  // รับ RoomID หรือคำว่า admin111

            if (roomInput.equals("admin111")) {
                // ถ้าผู้ใช้กรอก "admin111" ให้ขอรหัสยืนยัน
                System.out.println("Enter admin password to continue:");
                String adminPassword = scanner.nextLine();  // รับรหัสผู้ดูแล

                // ตรวจสอบรหัสผู้ดูแล
                if (accessControl.authenticateAdmin(adminPassword)) {
                    // ถ้ายืนยันรหัสผู้ดูแลแล้ว เข้าสู่โหมดผู้ดูแล
                    while (true) {
                        // ตัวเลือกในโหมดผู้ดูแล
                        System.out.println("Admin Mode Options:");
                        System.out.println("Press 'S' to show RoomID and CardID details");
                        System.out.println("Press 'C' to change CardID");
                        System.out.println("Press 'R' to change RoomID");
                        System.out.println("Press 'Esc' to exit Admin Mode");

                        String option = scanner.nextLine();  // รับคำสั่งจากผู้ใช้

                        if (option.equalsIgnoreCase("S")) {
                            // แสดงข้อมูลห้องและการ์ด
                            accessControl.showRoomDetails();
                        } else if (option.equalsIgnoreCase("C")) {
                            // เปลี่ยน CardID
                            System.out.println("Enter RoomID to change CardID:");
                            try {
                                int roomToUpdate = Integer.parseInt(scanner.nextLine()); // แปลง RoomID เป็นตัวเลข
                                System.out.println("Enter new CardID for room " + roomToUpdate + ":");
                                int newCardId = Integer.parseInt(scanner.nextLine()); // แปลง CardID เป็นตัวเลข
                                accessControl.changeCardId(roomToUpdate, newCardId); // เปลี่ยนการ์ด
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid input. Please enter valid numeric values.");
                            }
                        } else if (option.equalsIgnoreCase("R")) {
                            // เปลี่ยน RoomID
                            System.out.println("Enter old RoomID:");
                            try {
                                int oldRoom = Integer.parseInt(scanner.nextLine()); // แปลง RoomID เป็นตัวเลข
                                System.out.println("Enter new RoomID:");
                                int newRoom = Integer.parseInt(scanner.nextLine()); // แปลง RoomID ใหม่เป็นตัวเลข
                                accessControl.changeRoomId(oldRoom, newRoom); // เปลี่ยนห้อง
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid input. Please enter valid numeric values.");
                            }
                        } else if (option.equalsIgnoreCase("Esc")) {
                            // ออกจากโหมด Admin
                            System.out.println("Exiting Admin Mode...");
                            break; // ออกจากลูปในโหมด Admin
                        } else {
                            System.out.println("Invalid option. Please try again.");
                        }
                    }
                } else {
                    // ถ้ารหัสผู้ดูแลไม่ถูกต้อง
                    System.out.println("Admin authentication failed.");
                }
                continue;  // กลับไปที่การกรอกข้อมูลใหม่
            }

            // แปลง RoomID เป็นตัวเลข
            int room1;
            try {
                room1 = Integer.parseInt(roomInput); // แปลงค่า RoomID
            } catch (NumberFormatException e) {
                System.out.println("Invalid RoomID. Please enter a valid numeric RoomID.");
                continue;
            }

            System.out.println("Enter your CardID:");
            int card1;
            try {
                card1 = Integer.parseInt(scanner.nextLine());  // รับ CardID
            } catch (NumberFormatException e) {
                System.out.println("Invalid CardID. Please enter a valid numeric CardID.");
                continue;
            }

            // ตรวจสอบการเข้าถึงห้อง
            boolean access = accessControl.validateAccess(card1, room1);
            if (access) {
                System.out.println("Welcome to room " + room1);
                break; // ออกจากลูปเมื่อเข้าใช้งานห้องได้
            } else {
                System.out.println("Access denied. Please try again.");
            }
        }
    }
}
