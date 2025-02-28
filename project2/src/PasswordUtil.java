import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;

public class PasswordUtil {
    private static final int ITERATIONS = 65536;
    private static final int KEY_LENGTH = 256;

    // สร้าง Salt แบบสุ่ม
    public static String generateSalt() {
        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    // แฮชรหัสผ่านโดยใช้ Salt
    public static String hashPassword(String password, String salt) {
        try {
            PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), ITERATIONS, KEY_LENGTH);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] hash = skf.generateSecret(spec).getEncoded();
            return Base64.getEncoder().encodeToString(hash) + ":" + salt; // เก็บ Hash และ Salt ไว้ด้วยกัน
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("Error while hashing password", e);
        }
    }

    // ตรวจสอบรหัสผ่าน
    public static boolean checkPassword(String password, String storedHashedPassword) {
        String[] parts = storedHashedPassword.split(":");
        if (parts.length != 2) return false; // ตรวจสอบว่ามี Hash และ Salt
        String hash = parts[0];
        String salt = parts[1];
        return hash.equals(hashPassword(password, salt).split(":")[0]); // เปรียบเทียบค่า Hash
    }
}
