package com.shreyaslad.P0rtal.Alpha;

import java.util.LinkedList;

public class Singleton {
    private static Singleton single_instance = null;

    public LinkedList s;

    private Singleton() {
        s.add("hi");
    }

    public static Singleton getInstance() {
        if (single_instance == null)
            single_instance = new Singleton();

        return single_instance;
    }
}

class Main {
    public static void main(String[] args) {
        Singleton x = Singleton.getInstance();

        System.out.println(x.s);
    }
}