import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogNode {
    String action;      
    String detail;      
    String timestamp;   
    LogNode prev, next; // Pointer ke node sebelumnya dan sesudahnya (untuk struktur double linked list)

    // Konstruktor untuk menginisialisasi node log baru
    public LogNode(String action, String detail) {
        this.action = action;    
        this.detail = detail;    
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy"));
        this.prev = null;       
        this.next = null;        
    }
}
