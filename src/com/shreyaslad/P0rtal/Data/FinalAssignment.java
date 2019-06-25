package com.shreyaslad.P0rtal.Data;

import java.util.LinkedList;

public class FinalAssignment {
    private static final LinkedList<String> assignmentNames = new LinkedList<>();

    private FinalAssignment() {

    }

    public static void add(String thing) {
        assignmentNames.add(thing);
    }

    public static void set(int index, String thing) {
        assignmentNames.set(index, thing);
    }

    public static void remove(int index) {
        assignmentNames.remove(index);
    }

    public static int size() {
        return assignmentNames.size();
    }

    // Get string at set index
    public static String get(int index) {
        return assignmentNames.get(index);
    }
}
