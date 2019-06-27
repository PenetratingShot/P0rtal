/**
 * Things changed from pseudocode:
 *
 * Attendance is stored inside of a normal hashmap instead of a multudimensional array
 * HashMap can expand and automatically update to a growing number of students
 * Parser functions can get and set values from this map
 * HashMap is a weekly list of attendance
 * Everything is organized, where the Student ID is the same across the entire program
 * HashMap automatically removes students when they are removed in ManageStudents (rather it just checks to make sure that all students are there)
 *     This is run when the map is displayed to prevent slowing down ManageStudents
 * Week number is not displayed since only one week's worth of attendance is possible
 * Added "Go Back" option to prompt
 *
 */

package com.shreyaslad.P0rtal.Commands.Attendance;

import com.shreyaslad.P0rtal.Commands.ManageStudents;
import com.shreyaslad.P0rtal.Data.FinalAttendance;
import com.shreyaslad.P0rtal.Data.FinalStudent;
import com.shreyaslad.P0rtal.Main;
import com.shreyaslad.P0rtal.Prompt.StringPrompt;

public class ManageAttendance {
    private static int initAnswer;
    private static String editAnswer;
    private static String studentAnswer;

    private static int studentID;

    public static void manage() {
        System.out.println("\nManage Attendance");
        StringPrompt stringPrompt = new StringPrompt('>');
        stringPrompt.setNextQuestion("[1] View Attendance\n[2] Edit Attendance\n[3] Go Back");

        try {
            initAnswer = Integer.parseInt(stringPrompt.getLastAnswer());
        } catch (NumberFormatException ex) {
            manage();
        }

        switch (initAnswer) {
            case 1:
                viewAttendance();
                break;
            case 2:
                editAttendance();
                break;
            case 3:
                Main.main(null);
                break;
            default:
                manage();
        }

    }

    public static void viewAttendance() {
        System.out.println("\nViewing attendance for this week only\n");
        String leftAlignFormat = "| %-2s | %-45s | %-20s |%n";
        System.out.format("+----+-----------------------------------------------+----------------------+%n");
        System.out.format("| ID | Student Name                                  | Weekly Attendance    |%n");
        System.out.format("+----+-----------------------------------------------+----------------------+%n");
        for (int i = 0; i < FinalStudent.size(); i++) {
            System.out.format(leftAlignFormat, i, FinalStudent.get(i), FinalAttendance.getDays(i));
        }
        System.out.format("+----+-----------------------------------------------+----------------------+%n");

        manage();
    }

    public static void editAttendance() {
        if (FinalStudent.size() == 0) {
            System.out.println("\n\nThere are no students. Go add some!\n");
            ManageStudents.manage();
        } else {
            StringPrompt stringPrompt = new StringPrompt('>');

            do {
                ManageStudents.publicViewStudents();
                stringPrompt.setNextQuestion("Student ID: ");
                studentAnswer = stringPrompt.getLastAnswer();
            } while(studentAnswer.isEmpty());

            try {
                studentID = Integer.parseInt(studentAnswer);
                FinalStudent.get(studentID); // Check to make sure that the student actually exists
            } catch (NumberFormatException ex) {
                System.out.println("\nThat's not a number.\n");
                editAttendance();
            } catch (IndexOutOfBoundsException ex) {
                System.out.println("\nThat student doesn't exist.\n");
                editAttendance();
            }

            AttendanceDays.manage(studentID);
        }
    }
}
