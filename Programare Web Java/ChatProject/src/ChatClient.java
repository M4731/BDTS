import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class ChatClient {
    // Adresa și portul serverului
    private String serverAddress = "localhost";
    private int port = 12345;

    // Fluxurile și socketul pentru comunicare
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Socket socket;

    // Componente GUI
    private JFrame frame;
    private JTextArea textArea;
    private JTextField inputField;
    private JButton sendButton;
    private String clientName;

    // Constructorul primește numele clientului și configurează interfața grafică
    public ChatClient(String clientName) {
        this.clientName = clientName;
        // Creăm fereastra principală
        frame = new JFrame("Chat Client - " + clientName);
        // Zona de text unde vor fi afișate mesajele
        textArea = new JTextArea(20, 50);
        textArea.setEditable(false);
        // Câmpul pentru introducerea mesajelor
        inputField = new JTextField(40);
        // Butonul de trimitere
        sendButton = new JButton("Send");

        // Panel-ul pentru câmpul de text și buton
        JPanel panel = new JPanel();
        panel.add(inputField);
        panel.add(sendButton);

        // Adăugăm componentele în fereastră
        frame.getContentPane().add(new JScrollPane(textArea), BorderLayout.CENTER);
        frame.getContentPane().add(panel, BorderLayout.SOUTH);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Listener pentru butonul de trimitere
        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        // Listener pentru Enter în câmpul de text
        inputField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
    }

    // Metoda start() stabilește conexiunea și pornește firul de citire
    public void start() {
        try {
            // Se conectează la server
            socket = new Socket(serverAddress, port);
            // Inițializează fluxurile
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            // Face fereastra vizibilă
            frame.setVisible(true);

            // Fir separat pentru a citi mesajele de la server
            new Thread(new Runnable() {
                public void run() {
                    try {
                        while (true) {
                            ChatMessage message = (ChatMessage) in.readObject();
                            if (message == null) break;
                            // Afișează mesajul în zona de text
                            textArea.append(message.getSender() + ": " + message.getMessage() + "\n");
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Metoda sendMessage() trimite mesajul scris de utilizator către server
    private void sendMessage() {
        String text = inputField.getText();
        if (text.trim().isEmpty()) return;
        // Creează un obiect ChatMessage cu numele clientului și textul mesajului
        ChatMessage message = new ChatMessage(clientName, text);
        try {
            out.writeObject(message);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        inputField.setText("");
    }

    public static void main(String[] args) {
        // Solicită utilizatorului numele printr-un dialog
        String name = JOptionPane.showInputDialog("Introdu numele tău:");
        if (name == null || name.trim().isEmpty()) {
            name = "Anonymous";
        }
        ChatClient client = new ChatClient(name);
        client.start();
    }
}
