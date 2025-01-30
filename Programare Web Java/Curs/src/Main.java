import java.util.*;

class C{
    int v;
    boolean sex;

    C(int z, boolean x){
        v = z;
        sex = x;
    }

    C() {}

    void met() {
        System.out.println(v + " " + sex);
    }

    boolean met(int k) {
        return k < v;
    }
}

class Main{

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();

        C Ob1 = new C(x, true);
        Ob1.met();

        C Ob2 = new C();
        System.out.println(Ob2.met(-4));
    }
}