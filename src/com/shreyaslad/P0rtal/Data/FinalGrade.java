package com.shreyaslad.P0rtal.Data;

import java.util.HashMap;
import java.util.Map;

public class FinalGrade {

    private static Map<Integer, HashMap<Integer, String>> gradesMap = new HashMap<>();

    private FinalGrade() {

    }

    public static void addGrade(int assignmentID, int studentID, String score) {
        // TODO: calculate percentage and put both in score field
        gradesMap.put(assignmentID, new HashMap(){{put(studentID, score);}});
    }

    public static void removeScore(int assignmentID, int studentID) {
        gradesMap.put(assignmentID, new HashMap(){{put(studentID, null);}});
    }

    public static void editScore(int assignmentID, int studentID, String score) {
        // TODO: calculate percentage and put both in score field
        gradesMap.put(assignmentID, new HashMap(){{put(studentID, score);}});
    }

    public static int size(int assignmentID) {
        return gradesMap.get(assignmentID).size();
    }

    public static String get(int assignmentID, int studentID) {
        return gradesMap.get(assignmentID).get(studentID);
    }

}
