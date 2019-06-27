package com.shreyaslad.P0rtal.Data;

import java.util.LinkedList;

public class FinalStudent {
    private static LinkedList<String> studentNames = new LinkedList<>();

    private FinalStudent() {

    }

    public static void add(String thing) {
        studentNames.add(thing);
        int index = studentNames.indexOf(thing);
        FinalAttendance.edit(index, "p, p, p, p, p");
    }

    public static void set(int index, String thing) {
        studentNames.set(index, thing);
    }

    public static void remove(int index) {
        studentNames.remove(index);
        FinalAttendance.remove(index);
    }

    public static String get(int index) {
        return studentNames.get(index);
    }

    public static int size() {
        return studentNames.size();
    }
}
