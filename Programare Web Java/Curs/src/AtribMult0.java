class C{
    static int n; 
    int r;

    void delay(){
        try {
            Thread.sleep( (int) (100 * Math.random()) );
        } 
        catch (InterruptedException e) {}
    }

    synchronized void incr(){
        r = n;
        delay();
        r++;
        delay();
        n = r;
        delay();
    }
}

class Tip extends Thread{
    static C Ob =  new C(); 
    int i;
    
    Tip(int i){
        this.i = i;
    }

    @Override
    public void run() {
        for (int j = 0; j < 10; j++) {
            Ob.incr();
            System.out.print(" " + i);
            
        }
    }
}

public class AtribMult0 {
    public static void main(String[] args){
        Tip[] T = new Tip[5];
        for (int i = 0; i < 5; i++) {
            T[i] = new Tip(i);
        }
        for (int i = 0; i < 5; i++) {
            T[i].start();
        }
        try {
            for (int i = 4; i >= 0; i--){
                T[i].join();
            }
        } 
        catch (Exception e) {}

        System.out.println("\n n = " + C.n);
    }
    
}
