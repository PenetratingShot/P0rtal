package com.shreyaslad.P0rtal.Commands;

import com.shreyaslad.P0rtal.Data.FinalAssignment;
import com.shreyaslad.P0rtal.Data.FinalGrade;
import com.shreyaslad.P0rtal.Data.FinalStudent;
import com.shreyaslad.P0rtal.Main;
import com.shreyaslad.P0rtal.Prompt.StringPrompt;

import java.util.Arrays;

/**
 * Grades will be stored in a hashmap
 * HashMap<Integer, HashMap<String, String>
 *
 * The first integer is the assignment ID, the first string is the student name, and the second string is the points for the assignment
 */

public class ManageGrades {
    private static int assignmentID;
    private static String userAnswer;
    private static String userAnswer2;

    private static int studentID;

    @SuppressWarnings("Duplicates")
    public static void manage() {

        ManageAssignments.publicViewAssignments();

        StringPrompt stringPrompt = new StringPrompt('>');
        String answer;

        try {
            do {
                stringPrompt.setNextQuestion("Assignment ID (press b to go back): ");
                answer = stringPrompt.getLastAnswer();
                if (answer.equals("b")) {
                    String[] hi = {"hi"};
                    Main.main(hi);
                } else {
                    assignmentID = Integer.parseInt(answer);
                }
            } while (answer.isEmpty());
        } catch (NumberFormatException ex) {
            if (FinalAssignment.get(0).isEmpty()) {
                System.out.println("\nThere are no current assignments. Go and add one.\n");
                ManageAssignments.manage();
            } else {
                keep();
            }
        }

        for (int i = 0; i < FinalStudent.size(); i++) {
            FinalGrade.addGrade(assignmentID, i, "None");
        }

        System.out.println("\nSuccessfully selected assignment: " + FinalAssignment.get(assignmentID));

        grades();
    }

    private static void grades() {

        System.out.println("\nManage Grades\n");
        StringPrompt stringPrompt = new StringPrompt('>');
        stringPrompt.setNextQuestion("[1] View Grades\n[2] Set grades for specific students\n[3] Set grades for all students\n[4] Edit Grades\n[5] Go Back");
        String answer = stringPrompt.getLastAnswer();

        switch (Integer.parseInt(answer)) {
            case 1:
                viewGrades();
                break;
            case 2:
                addOneGrade();
                break;
            case 3:
                addAllGrades();
                break;
            case 4:
                editGrade();
                break;
            default:
                System.out.println("You have not entered a valid choice. You are now viewing grades.");
                viewGrades();
        }
    }

    @SuppressWarnings("Duplicates")
    private static void keep() {
        ManageAssignments.publicViewAssignments();

        StringPrompt stringPrompt = new StringPrompt('>');
        String answer;

        try {
            do {
                stringPrompt.setNextQuestion("Assignment ID (press 'b' if there are no assignments): ");
                answer = stringPrompt.getLastAnswer();
                if (answer.equals("b")) {
                    String[] hi = {"hi"};
                    Main.main(hi);
                } else {
                    assignmentID = Integer.parseInt(answer);
                }
            } while (answer.isEmpty());
        } catch (NumberFormatException ex) {
            if (FinalAssignment.get(0).isEmpty()) {
                System.out.println("\nThere are no current assignments. Go and add one.\n");
                ManageAssignments.manage();
            } else {
                keep();
            }
        }

        System.out.println("\nSuccessfully selected assignment: " + FinalAssignment.get(assignmentID));

        grades();
    }

    private static void viewGrades() {
        // Get assignment ID
        // Loop through list of students
        // Add ID to hasmap, then put assignment ID and student name in a nested hashmap

        // Format and print out table
        String leftAlignFormat = "| %-15s | %-45s | %-22s |%n";
        System.out.format("+-----------------+-----------------------------------------------+------------------------+%n");
        System.out.format("| Assignment ID   | Student Name                                  | Score                  |%n");
        System.out.format("+-----------------+-----------------------------------------------+------------------------+%n");
        /**
         * 1. Loop through hashmap to get scores for one student
         * 2. Print out student name
         * 3. Split score by character by character '/' and put into two different variables
         */

        //TODO: Actually format the points in terms of a percentage and letter grade. For now we can just print out the score, if it exists
        for (int i = 0; i < FinalStudent.size(); i++) {
            System.out.format(leftAlignFormat, assignmentID, FinalStudent.get(i), FinalGrade.get(assignmentID, i));
        }
        System.out.format("+-----------------+-----------------------------------------------+------------------------+%n");
    }

    private static void addAllGrades() {
        System.out.println("\nEnter the points earned/total points for each student. e.g. 15/20");

        for (int i = 0; i < FinalStudent.size(); i++) {
            StringPrompt stringPrompt = new StringPrompt('>');
            stringPrompt.setNextQuestion("Grade for " + FinalStudent.get(i));

            String answer = stringPrompt.getLastAnswer();

            FinalGrade.addGrade(assignmentID, i, answer); // Theoretically this should work. Logic checks out
        }

        manage();
    }

    private static void addOneGrade() {
        System.out.println("\nEnter the points earned/total points for each student. e.g. 15/20");

        do {
            ManageStudents.publicViewStudents();

            System.out.println("\nEnter the indexes of each student that you want to edit, separated by commas.\ne.g. 0,2,5\n");
            StringPrompt stringPrompt = new StringPrompt('>');
            stringPrompt.setNextQuestion("Indexes: ");

            userAnswer = stringPrompt.getLastAnswer();
        } while (userAnswer.isEmpty());

        String[] answers = userAnswer.split(", ");

        for (int i = 0; i < answers.length; i++) {
            StringPrompt stringPrompt = new StringPrompt('>');
            stringPrompt.setNextQuestion("Points for: " + FinalStudent.get(Integer.parseInt(answers[i])));

            String answer = stringPrompt.getLastAnswer();
            String[] answerArray = answer.split("/");
            int[] points = Arrays.stream(answerArray).mapToInt(Integer::parseInt).toArray();
            int percentage = points[0]/points[1];

            FinalGrade.addGrade(assignmentID, Integer.parseInt(answers[i]), answer + " (" + percentage + ")");
//            parseAndAdd(i, stringPrompt.getLastAnswer());
        }

        manage();
    }

    private static void editGrade() {
        StringPrompt stringPrompt = new StringPrompt('>');
        do {
            System.out.println("Enter the ID of the student. \n");
            ManageStudents.publicViewStudents();
            stringPrompt.setNextQuestion("Student ID: ");

            try {

            } catch (NumberFormatException ex) {

            } catch (IndexOutOfBoundsException ex) {

            }
            studentID = Integer.parseInt(stringPrompt.getLastAnswer());

            System.out.println("Enter the points earned/total points for each student. e.g. 15/20");
            try {
                stringPrompt.setNextQuestion("Points for " + FinalStudent.get(Integer.parseInt(userAnswer)));

                String score = stringPrompt.getLastAnswer();
                String[] stringAnswer = score.split("/");
                int percentAnswer = (Integer.parseInt(stringAnswer[0]))/(Integer.parseInt(stringAnswer[1]));

                FinalGrade.addGrade(assignmentID, studentID, score + " (" + percentAnswer + ")");

            } catch (NumberFormatException ex) {
                System.out.println("\nSomething went wrong. You probably entered something that isn't a number.\n");
                ManageGrades.manage();
            } catch (IndexOutOfBoundsException ex) {
                System.out.println("\nYou entered a student that does not exist.\n");
                ManageGrades.manage();
            }

        } while(userAnswer.isEmpty());

        manage();
    }
}