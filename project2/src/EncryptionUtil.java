import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Base64;

public class EncryptionUtil {

    public static String encrypt(String password) {
        String timeKey = getTimeKey();
        return encryptWithTime(password, timeKey);
    }

    private static String encryptWithTime(String password, String timeKey) {
        try {
            String combined = password + timeKey;
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(combined.getBytes());
            return Base64.getEncoder().encodeToString(encodedhash);
        } catch (Exception e) {
            throw new RuntimeException("Encryption failed", e);
        }
    }

    public static boolean validate(String encryptedPassword, String storedEncryptedPassword, LocalDateTime passwordSetTime) {
        // Check if the encrypted password matches the stored encrypted password
        boolean isValid = encryptedPassword.equals(storedEncryptedPassword);

        // Check if the password is still valid (not expired)
        LocalDateTime now = LocalDateTime.now();
        long minutesElapsed = ChronoUnit.MINUTES.between(passwordSetTime, now);
        boolean isNotExpired = minutesElapsed <= 1;

        return isValid && isNotExpired;
    }
    private static String getTimeKey() {
        return ZonedDateTime.now().truncatedTo(ChronoUnit.MINUTES)
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    private static String getPreviousTimeKey() {
        return ZonedDateTime.now().minusMinutes(1).truncatedTo(ChronoUnit.MINUTES)
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}