package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Super{
    public int x = 1;
}

class A extends Super{
    public A(int a){
        x = a;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        Integer a = null;
        int b = 0;
        Integer c = null;

        A obj = new A(10);
        System.out.println(obj.x);

        //char string
        //System.out.println(a==c);
    }
}