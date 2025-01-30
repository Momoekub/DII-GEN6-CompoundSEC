import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HotelControl accessControl = new HotelControl();

        System.out.println("Enter your RoomID");
        int room1 = scanner.nextInt();

        while (true) {
            System.out.println("Enter your CardID");
            int card1 = scanner.nextInt();
            boolean access = accessControl.validateAccess(card1, room1);
            if (access) {
                System.out.println("Welcome");
                break;
            } else {
                System.out.println("Please Enter again");
            }

            System.out.println("Press 'R' to change room or any other key to try again.");
            String changeRoom = scanner.next(); // รับข้อความจากผู้ใช้

            if (changeRoom.equalsIgnoreCase("R")) {
                System.out.println("Enter new RoomID");
                room1 = scanner.nextInt();
            }
        }
    }
}
