import java.net.InetAddress;
import java.net.UnknownHostException;

class Adrese {
    
    public static void main(String[] args) {
        try {
            System.out.println(InetAddress.getLocalHost());
        }
        catch(UnknownHostException e) {
            System.out.println("Gazda nu are adresa IP");
        }
    }
}
