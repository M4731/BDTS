import java.io.*;

public class Lista1 {
    public static void main(String[] sir) throws Exception {
        Element Ob = new Element(); Ob.creare();
        System.out.println(Ob.parcurg(Element.p));

        FileOutputStream fos = new FileOutputStream("lista");
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(Element.p);
        oos.close();
        fos.close();
    }
}

