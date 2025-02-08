import java.util.*;
public class Sumator { 
  public int[] a;
  private int suma=0, indice=0;
  private int nt=0; // cati termeni au fost adunati la suma
  private int n=0, nr_fire=10;

  public Sumator() {
    Scanner sc = new Scanner(System.in);
    System.out.print("n = "); n = sc.nextInt();
    a = new int[n];
    for (int i=0; i<n; i++) a[i] = i;
    for (int i=0; i<10; i++) new Fir(this,i).start();
  }

  public synchronized int indiceNou() {
    if (indice < n) return indice++; 
    else return -1;
  }

  public synchronized void adunap(int sumap) {
    suma += sumap;
    if (++nt == nr_fire)
      System.out.println("Suma totala este " + suma);
  }

  public static void main(String args[]) {
    new Sumator();
  }
}

class Fir extends Thread {
  // nr = nr. de ordine al firului;
  // sumap = suma partiala calculata de firul curent
  int sumap = 0, nr; 
  Sumator s;

  public Fir(Sumator s, int nr){
      this.s = s; this.nr = nr;
  }

  public void run() {
    int indice = s.indiceNou();
    while(indice != -1) {
      try { Thread.sleep(2); } catch(InterruptedException ie) {}
      sumap += s.a[indice]; indice = s.indiceNou();
    }
    System.out.println("Suma partiala a firului "
                       + nr + " este " + sumap);
    s.adunap(sumap);  
  }
} 

