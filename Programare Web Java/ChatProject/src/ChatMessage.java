// Importăm interfața pentru serializare
import java.io.Serializable;

// Definim clasa ChatMessage care implementează Serializable
public class ChatMessage implements Serializable {
    // Această constantă ajută la compatibilitatea serializării
    private static final long serialVersionUID = 1L;

    // Variabile pentru numele expeditorului și conținutul mesajului
    private String sender;
    private String message;

    // Constructor: inițializează câmpurile
    public ChatMessage(String sender, String message) {
        this.sender = sender;
        this.message = message;
    }

    // Getter pentru numele expeditorului
    public String getSender() {
        return sender;
    }

    // Getter pentru mesaj
    public String getMessage() {
        return message;
    }
}
