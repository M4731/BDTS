import java.util.*; import java.util.concurrent.*;

class Fil extends Thread {
  static int n;
  static Semaphore[] b; static Semaphore AccesLiber;

  static void delay(int i) {
    try { Thread.sleep( (int) (i*Math.random()) ); }
    catch(InterruptedException e) { }
  }
  
  int id,k;
  Fil(int i) { id = i; }
  
  public void run() {
    for(k=0; k<10; k++) {
      try {
        AccesLiber.acquire();
          b[id].acquire();
          b[ (id+1) % n ].acquire();
            System.out.print("M" + id + "   ");
            delay(100);
            System.out.print("G" + id + "   ");
          b[id].release();
          b[ (id+1) % n ].release();
          delay(100);
        AccesLiber.release();
      }
      catch(Exception e) { }
    }
  }
}

class FilSem {
  public static void main(String[] qqq) {
    int i;
    Scanner sc = new Scanner(System.in);
    System.out.print("Nr. filozofi = ");
    Fil.n = sc.nextInt();
    Fil.b = new Semaphore[Fil.n];
    for(i=0; i<Fil.n; i++) Fil.b[i] = new Semaphore(1);
    Fil.AccesLiber = new Semaphore(Fil.n-1);
    Fil[] filozofi = new Fil[Fil.n];
    for(i=0; i<Fil.n; i++) filozofi[i] = new Fil(i);
    for(i=0; i<Fil.n; i++) filozofi[i].start();
  }
}
