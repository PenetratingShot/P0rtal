/**
 * FinalAttendance.java
 * Copyright Shreyas Lad (Penetratingshot) 2019
 *
 * File for managing data inside of private attendance hashmap
 * Getter and setter functions are called from other classes and easily manage data inside of the hashmap
 */

/**
 * Create hashmap
 * Map<Integer, String>
 * Integer is the student ID, second value is type for each day, separated by ", "
 */

package com.shreyaslad.P0rtal.Data;

import java.util.HashMap;
import java.util.Map;

public class FinalAttendance {
    private static String someString;

    private static Map<Integer, String> attendanceMap = new HashMap<>();

    private FinalAttendance() {

    }

    public static void reset() {
        for (int i = 0; i < FinalStudent.size(); i++) {
            attendanceMap.put(i, "p, p, p, p, p");
        }
    }

    public static void remove(int index) {
        attendanceMap.remove(index);
    }

    public static void edit(int studentID, String dateValues) {
        attendanceMap.put(studentID, dateValues);
    }

    public static String get(int studentID) {
        return attendanceMap.get(studentID);
    }

    public static boolean isEmpty() {
        return attendanceMap.isEmpty();
    }

    public static boolean contains(int studentID) {
        return attendanceMap.containsKey(studentID);
    }

    // Single parser for getting which day the user specified, then updating status manually, concatenating it into a string, and updating the value into the hashmap
    // This is only inside of this file since switch case statements are unnecessary lines in normal code since they contain barebones subroutine calls
    public static void parse(int studentID, String day, String status) {
        String[] values = attendanceMap.get(studentID).split(", ");

        switch (day.toLowerCase()) {
            case "monday":
                values[0] = status;
                edit(studentID, String.join(", ", values));
                break;
            case "tuesday":
                values[1] = status;
                edit(studentID, String.join(", ", values));
                break;
            case "wednesday":
                values[2] = status;
                edit(studentID, String.join(", ", values));
                break;
            case "thursday":
                values[3] = status;
                edit(studentID, String.join(", ", values));
                break;
            case "friday":
                values[4] = status;
                edit(studentID, String.join(", ", values));
                break;
        }
    }

    // Can simply call the data inside of the hashmao for any student with a valid index
    // Doesn't have a try catch in itself, because that would be redundant
    public static String getDays(int studentID) {
        return get(studentID);
    }
}
