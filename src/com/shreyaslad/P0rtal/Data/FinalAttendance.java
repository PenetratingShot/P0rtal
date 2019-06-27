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

    public static String getDays(int studentID) {
        return get(studentID);
    }
}
