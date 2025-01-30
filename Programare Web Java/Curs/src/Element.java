import java.io.*;
import java.util.*;

class Element implements Serializable {
    int info;
    Element leg;
    static Element p,u;

    Element() { }
    Element(int i) { info = i; }

    void creare() {
        Element x;
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt(); p = new Element(i); u = p;
        while ( sc.hasNextInt() ) {
            x = new Element( sc.nextInt() ); u.leg = x; u = x;
        }
        u.leg = p;
    }

    String parcurg(Element x) {
        if (x.leg == p) return x.info + "";
        else return x.info + "\t" + parcurg(x.leg);
    }
}
