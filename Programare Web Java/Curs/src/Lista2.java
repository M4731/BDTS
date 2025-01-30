import java.io.*;

public class Lista2 {
    public static void main(String[] sir) throws Exception {

        FileInputStream fis = new FileInputStream("lista");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Element.p = (Element) ois.readObject();

        Element Ob = new Element();
        System.out.println( Ob.parcurg(Element.p) );
        ois.close(); fis.close();
    }
}
