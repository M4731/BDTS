import java.io.*;
import java.net.*;
import java.util.*;

//se ruleaza Client de mai multe ori si se conecteaza la acelasi server.
class Client {
  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(System.in);
    System.out.print("Adresa serverului si portul : ");
    Socket cs = new Socket( sc.next(), sc.nextInt() );
    OutputStream os = cs.getOutputStream();
    int c = (int) (10 * Math.random());
    // cere serverului sa afiseze de 200 de ori cifra c
    os.write(c); os.write(200);
  }
}
