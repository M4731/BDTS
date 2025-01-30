import java.io.*;
import java.util.*;

public class Copiere {

    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(System.in);
        System.out.print("Fisier cerut: ");
        String fisier = sc.next();
        InputStream is = null;

        try { is = new FileInputStream(fisier); }
        catch ( FileNotFoundException e){
            System.out.println("Fisier inexistent");
            System.exit(0);
        }

        System.out.print("Numele copiei: ");
        fisier = sc.next();
        OutputStream os = new FileOutputStream(fisier);

        int c;
        while ( (c= is.read()) != -1) {
            os.write(c);
        }
        System.out.println("Fisier copiat!");
        is.close();
        os.close();
    }

}
