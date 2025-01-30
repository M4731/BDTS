import java.io.*;

public class Serial {

    public static void main(String[] sir) throws Exception {

        Angajat.firma = "SRL Serial";
        Angajat A1 = new Angajat("Vasile", 25, 1485);
        Angajat A2 = new Angajat("Ion", 24, 420);

        FileOutputStream fos = new FileOutputStream("Serial");
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeUTF(Angajat.firma);
        oos.writeObject(A1); oos.writeObject(A2);

        oos.close();
        fos.close();
    }
}
