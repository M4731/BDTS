import java.util.*;

class Tip1 extends Thread {
  int i;
  Tip1(int i) { this.i = i; }

  public void run() {
    for( ; ; ) System.out.print(i + " ");
  }
}

class Fire1 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Tip1 Ob1 = new Tip1(1), Ob2 = new Tip1(2);
    Ob1.setDaemon(true); Ob2.setDaemon(true);
    Ob1.start(); Ob2.start();
    sc.next();
    System.out.println("Gata");
  }
}
