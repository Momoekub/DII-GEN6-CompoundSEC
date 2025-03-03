import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LoggingFacade {
    private List<String> logs;

    public LoggingFacade() {
        logs = new ArrayList<>();
    }

    public void log(String message) {
        String timestampedMessage = LocalDateTime.now() + " - " + message;
        logs.add(timestampedMessage);
        System.out.println(timestampedMessage);
    }

    public List<String> getLogs() {
        return logs;
    }
}