import java.io.*;
import java.util.*;

class FluxUnu {

    public static void main(String[] sir) throws Exception {
        int n;
        Scanner sc = new Scanner(System.in);
        DataOutputStream dos = new DataOutputStream(
                new FileOutputStream("out.dat"));
        System.out.print("n = "); n = sc.nextInt();

        dos.writeInt(n);
        System.out.print("Introduceti " +n+ " numere reale: ");

        for(int i=0; i<n; i++) {
            dos.writeDouble(sc.nextDouble());
        }
        dos.close();
    }

}