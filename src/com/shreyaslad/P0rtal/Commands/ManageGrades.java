/**
 * ManageGrades.java
 * Copyright Shreyas Lad (Penetratingshot) 2019
 *
 * Code for managing grades for students in individual assignments
 * Checks for Student ID, Assignment ID, and then prompts for score as "Points Earned/Points Possible"
 * Calculates percentage from score and updates global list of grades (which is a nested hashmap)
 * If there are no students or assignments, user is taken to the appropriate section to add some
 */

/**
 * Things changed from pseudocode:
 *
 * HashMap contains Student ID instead of student name
 * Parser functions are used to efficiently update content inside of the hashmaps
 * Exception catching implemented — making sure that the user only inputs stuff that they're supposed to
 * Displaying information in tables and not just text with a lot of newlines
 * Different functions to make code more readable, portable across different classes
 * List of students is printed out for the user before they choose what student they want to choose
 * Checks to make sure that all students are in the HashMap
 * Automatically add studentID and set no grade when a student is added
 * Checks to make sure that there are students. If there are not, user is prompted to add students
 * Grade calculation is displayed as a percent with points displayed to the left of it
 *
 * Honestly this was a pain to make. I never want to do it again
 *
 */

package com.shreyaslad.P0rtal.Commands;

import com.shreyaslad.P0rtal.Data.FinalAssignment;
import com.shreyaslad.P0rtal.Data.FinalGrade;
import com.shreyaslad.P0rtal.Data.FinalStudent;
import com.shreyaslad.P0rtal.Main;
import com.shreyaslad.P0rtal.Prompt.StringPrompt;

import java.util.Arrays;

/**
 * Grades will be stored in a hashmap
 * HashMap<Integer, HashMap<Integer, String>
 *
 * The first integer is the assignment ID, the first integer is the student ID, and the second string is the points for the assignment
 */

public class ManageGrades {
    private static int assignmentID;
    private static String userAnswer;
    private static String userAnswer2;

    private static int studentID;

    // Main function which prompts user for the assignment ID
    // Reprompts this question is user enters an undesirable answer

    //CHANGELOG June 26, 2019: Made assignment selection permanent so that user doesn't have to constantly select the same assignment after they execute a subroutine
    @SuppressWarnings("Duplicates")
    public static void manage() {

        StringPrompt stringPrompt = new StringPrompt('>');
        String answer;

        try {
            do {
                if (FinalAssignment.size() == 0) {
                    System.out.println("\n\nThere are no assignments currently added. Go add some!\n");

                    ManageAssignments.manage();
                }
                ManageAssignments.publicViewAssignments();
                stringPrompt.setNextQuestion("Assignment ID (press 'b' to go back): ");
                answer = stringPrompt.getLastAnswer();

                if (answer.equals("b")) {
                    Main.main(null);
                } else {
                    try {
                        assignmentID = Integer.parseInt(answer);
                    } catch (IndexOutOfBoundsException ex) {
                        System.out.println("\nThat assignment doesn't exist. Maybe try adding one?\n");
                        manage();
                    } catch (NumberFormatException ex) {
                        System.out.println("\nThat assignment doesn't exist. Maybe try adding one?\n");
                        manage();
                    }
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

        try {
            System.out.println("\nSuccessfully selected assignment: " + FinalAssignment.get(assignmentID));
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("\nIt looks like you entered an assignment ID that doesn't exist. Try adding one?\n");
            manage();
        }

        grades();
    }

    // Function for grades prompt after user selects an assignment
    // Prompts the user and directs them to the appropriate subroutine
    private static void grades() {

        System.out.println("\nManage Grades\n");
        StringPrompt stringPrompt = new StringPrompt('>');
        stringPrompt.setNextQuestion("[1] View Grades\n[2] Set grades for specific students\n[3] Set grades for all students\n[4] Edit Grades\n[5] Go Back and select a new assingnment");
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
            case 5:
                manage();
            default:
                System.out.println("You have not entered a valid choice.");
                grades();
        }
    }

    @SuppressWarnings("Duplicates")
    private static void keep() {
        StringPrompt stringPrompt = new StringPrompt('>');
        String answer;

        try {
            do {
                if (FinalAssignment.size() == 0) {
                    System.out.println("\n\nThere are no assignments currently added. Go add some!\n");

                    ManageAssignments.manage();
                }
                ManageAssignments.publicViewAssignments();
                stringPrompt.setNextQuestion("Assignment ID (press 'b' to go back): ");
                answer = stringPrompt.getLastAnswer();

                try {
                    assignmentID = Integer.parseInt(answer);
                } catch (IndexOutOfBoundsException ex) {
                    System.out.println("\nThat assignment doesn't exist. Maybe try adding one?\n");
                    manage();
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

        try {
            System.out.println("\nSuccessfully selected assignment: " + FinalAssignment.get(assignmentID));
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("\nIt looks like you entered an assignment ID that doesn't exist. Try adding one?\n");
            manage();
        }

        grades();
    }

    // Grabs list of grades by iterating through all student IDs and supplying the assingment ID that the user entered at the start of this class
    @SuppressWarnings("Duplicates")
    private static void viewGrades() {

        if (FinalStudent.size() == 0) {
            System.out.println("\n\nThere are no students in this class. Go add some!\n");

            ManageStudents.manage();
        } else {
            try {
                // Get assignment ID
                // Loop through list of students
                // Add ID to hasmap, then put assignment ID and student name in a nested hashmap

                // Format and print out table
                String leftAlignFormat = "| %-15s | %-45s | %-30s |%n";
                System.out.format("+-----------------+-----------------------------------------------+--------------------------------+%n");
                System.out.format("| Assignment ID   | Student Name                                  | Score                          |%n");
                System.out.format("+-----------------+-----------------------------------------------+--------------------------------+%n");
                /**
                 * 1. Loop through hashmap to get scores for one student
                 * 2. Print out student name
                 * 3. Split score by character by character '/' and put into two different variables
                 */

                //TODO: Actually format the points in terms of a percentage and letter grade. For now we can just print out the score, if it exists
                for (int i = 0; i < FinalStudent.size(); i++) {
                    System.out.format(leftAlignFormat, assignmentID, FinalStudent.get(i), FinalGrade.get(assignmentID, i));
                }
                System.out.format("+-----------------+-----------------------------------------------+--------------------------------+%n");

                manage();
            } catch (NullPointerException ex) {
                for (int i = 0; i < FinalStudent.size(); i++) {
                    FinalGrade.addGrade(assignmentID, i , null);
                }

                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

                String leftAlignFormat = "| %-15s | %-45s | %-30s |%n";
                System.out.format("+-----------------+-----------------------------------------------+--------------------------------+%n");
                System.out.format("| Assignment ID   | Student Name                                  | Score                          |%n");
                System.out.format("+-----------------+-----------------------------------------------+--------------------------------+%n");

                for (int i = 0; i < FinalStudent.size(); i++) {
                    System.out.format(leftAlignFormat, assignmentID, FinalStudent.get(i), FinalGrade.get(assignmentID, i));
                }
                System.out.format("+-----------------+-----------------------------------------------+--------------------------------+%n");

                grades(); // Go back to the grades function to expose same options
            }
        }
    }

    // Prompt for getting the student ID and points received/points possible and also does grade calculations
    // Concatenates these two values into one string, and then uses setter function in FinalGrade to "replace" them in HashMap
    // Loops through all students and prompts the user for the score for every single one of them
    private static void addAllGrades() {
        if (FinalStudent.size() == 0) {
            System.out.println("\n\nThere are no students. Go add some!\n");
            ManageStudents.addStudents();
        } else {
            ManageStudents.publicViewStudents();

            System.out.println("\nEnter the points earned/total points for each student. e.g. 15/20");

            for (int i = 0; i < FinalStudent.size(); i++) {
                StringPrompt stringPrompt = new StringPrompt('>');
                stringPrompt.setNextQuestion("Grade for " + FinalStudent.get(i));

                String answer = stringPrompt.getLastAnswer();

                try {
                    String[] answerArray = answer.split("/");
                    double[] points = Arrays.stream(answerArray).mapToDouble(Double::parseDouble).toArray();

                    String percentage = Double.toString((points[0]/points[1])*100);
                    FinalGrade.addGrade(assignmentID, i, answer + " (" + percentage + "%" + ")"); // Theoretically this should work. Logic checks out
                } catch (NumberFormatException ex) {
                    System.out.println("\n\nIt looks like you didn't enter numbers when entering points\n");
                    addAllGrades();
                } catch (IndexOutOfBoundsException ex) {
                    System.out.println("\n\nSomething went wrong. Try re-entering the score.");
                    addAllGrades();
                }

            }

            grades(); // Go back to grades function to expose same functions
        }

    }

    // Prints out list of students and prompts users for the IDs of the students that they want to edit
    // Prompts user for each student name (derived from ID) and points for that student
    // Replaces current grade with points and calculation
    private static void addOneGrade() {
        if (FinalStudent.size() == 0) {
            System.out.println("\n\nThere are no students. Go add some!\n");
            ManageStudents.addStudents();
        } else {

            do {
                ManageStudents.publicViewStudents();
                System.out.println("\nEnter the points earned/total points for each student. e.g. 15/20");
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
                try {
                    int[] points = Arrays.stream(answerArray).mapToInt(Integer::parseInt).toArray();
                    int percentage = points[0] / points[1];

                    FinalGrade.addGrade(assignmentID, Integer.parseInt(answers[i]), answer + " (" + percentage + ")");
                } catch (ArrayIndexOutOfBoundsException ex) {
                    System.out.println();
                }
//            parseAndAdd(i, stringPrompt.getLastAnswer());
            }

            grades();
        }
    }

    // Almost the same thing as the addOneGrade() function. There's a dedcated function with some altered text to make the user feel less confused when trying to update grades
    private static void editGrade() {
        StringPrompt stringPrompt = new StringPrompt('>');
        do {
            System.out.println("Enter the ID of the student. \n");
            ManageStudents.publicViewStudents();
            stringPrompt.setNextQuestion("Student ID: ");

            try {
                studentID = Integer.parseInt(stringPrompt.getLastAnswer());
            } catch (NumberFormatException ex) {
                System.out.println("\nIt looks like you entered something other than a number.\n");
                manage();
            } catch (IndexOutOfBoundsException ex) {
                System.out.println("\nYou entered a student ID that doesn't exist.\n");
                manage();
            }

            System.out.println("Enter the points earned/total points for each student. e.g. 15/20");
            try {
                stringPrompt.setNextQuestion("Points for " + FinalStudent.get(Integer.parseInt(userAnswer)));

                String score = stringPrompt.getLastAnswer();
                String[] stringAnswer = score.split("/");
                int percentAnswer = (Integer.parseInt(stringAnswer[0])) / (Integer.parseInt(stringAnswer[1]));

                FinalGrade.addGrade(assignmentID, studentID, score + " (" + percentAnswer + ")");

            } catch (NumberFormatException ex) {
                System.out.println("\nSomething went wrong. You probably entered something that isn't a number.\n");
                ManageGrades.manage();
            } catch (IndexOutOfBoundsException ex) {
                System.out.println("\nYou entered a student that does not exist.\n");
                ManageGrades.manage();
            }

        } while (userAnswer.isEmpty());

        grades();
    }
}