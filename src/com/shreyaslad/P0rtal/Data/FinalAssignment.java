/**
 * FinalAssignment.java
 * Copyright Shreyas Lad (Penetratingshot) 2019
 *
 * Hold the global map of assignments
 * Getter and setter functions are defined to edit this map and so can be called from any class inside of the com.shreyaslad.P0rtal package
 */

/**
 * Things changed from pseudocode:
 *
 * This is just a physical representation of the idea that there has to be a global list of assignment names that any class can edit
 * It's in a different form to avoid passing arguments to functions and having incredibly skewed memory management
 */

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
