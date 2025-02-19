import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    // Set sincronizat pentru a stoca handler-ele clienților
    private static Set<ClientHandler> clientHandlers = Collections.synchronizedSet(new HashSet<>());

    public static void main(String[] args) {
        int port = 12345; // Alege un port pentru server
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Serverul a pornit pe portul " + port);
            // Loop infinit: acceptă conexiuni noi
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client nou conectat: " + socket);
                // Creează un handler pentru clientul nou
                ClientHandler clientHandler = new ClientHandler(socket);
                clientHandlers.add(clientHandler);
                clientHandler.start(); // Pornește firul pentru client
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Metodă sincronizată pentru a trimite un mesaj tuturor clienților
    public static synchronized void broadcast(ChatMessage message) {
        for (ClientHandler client : clientHandlers) {
            client.sendMessage(message);
        }
    }

    // Metodă pentru a elimina un client din listă la deconectare
    public static void removeClient(ClientHandler clientHandler) {
        clientHandlers.remove(clientHandler);
    }

    // Clasa internă care gestionează fiecare client
    static class ClientHandler extends Thread {
        private Socket socket;
        private ObjectOutputStream out;
        private ObjectInputStream in;
        private String clientName;

        public ClientHandler(Socket socket) {
            this.socket = socket;
            try {
                // Inițializează fluxul de ieșire pentru a trimite obiecte către client
                out = new ObjectOutputStream(socket.getOutputStream());
                // Inițializează fluxul de intrare pentru a primi obiecte de la client
                in = new ObjectInputStream(socket.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void run() {
            try {
                // Citește mesaje de la client într-un loop
                while (true) {
                    ChatMessage message = (ChatMessage) in.readObject();
                    if (message == null) break;
                    System.out.println("Mesaj de la " + message.getSender() + ": " + message.getMessage());
                    // Setează numele clientului dacă nu a fost deja stabilit
                    if (clientName == null) {
                        clientName = message.getSender();
                    }
                    // Trimite mesajul tuturor clienților
                    ChatServer.broadcast(message);
                }
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Clientul " + clientName + " s-a deconectat.");
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    // Ignorăm erorile la închidere
                }
                // Eliminăm clientul din lista activă
                ChatServer.removeClient(this);
            }
        }

        // Metodă pentru trimiterea unui mesaj către acest client
        public void sendMessage(ChatMessage message) {
            try {
                out.writeObject(message);
                out.flush(); // Forțează trimiterea imediată
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
