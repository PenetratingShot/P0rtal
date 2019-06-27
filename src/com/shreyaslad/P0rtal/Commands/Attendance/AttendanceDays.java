/**
 * This changed from pseudocode:
 *
 * This wasn't planned in the pseudocode, since I took a different direction when planning it out
 * Basically it just splits up the code from ManageAssingnments. It's the same thing except I have all the repetitive functions separated so that I don't have an aneurysm scrolling through my actually important functions
 * User enters a specific day that they want to edit for a soecific student ID
 * Querying values and pushing them with getter and setter functions
 *
 */

package com.shreyaslad.P0rtal.Commands.Attendance;

import com.shreyaslad.P0rtal.Data.FinalAttendance;
import com.shreyaslad.P0rtal.Prompt.StringPrompt;

public class AttendanceDays {
    private static String editAnswer;
    private static int studentID;

    private static String mondayAnswer;
    private static String tuesdayAnswer;
    private static String wednesdayAnswer;
    private static String thursdayAnswer;
    private static String fridayAnswer;

    public static void manage(int studentNumber) {
        studentID = studentNumber;

        StringPrompt stringPrompt = new StringPrompt('>');

        System.out.println("\n\nEnter \"Monday\", \"Tuesday\", \"Wednesday\", \"Thursday\", or \"Friday\".\nCase doesn't matter\n");
        stringPrompt.setNextQuestion("Day: ");
        editAnswer = stringPrompt.getLastAnswer();

        switch (editAnswer.toLowerCase()) {
            case "monday":
                monday();
                break;
            case "tuesday":
                tuesday();
                break;
            case "wednesday":
                wednesday();
                break;
            case "thursday":
                thursday();
                break;
            case "friday":
                friday();
                break;
            default:
                System.out.println("\nThat is not a school day.\n");
                manage(studentID);
        }
    }

    @SuppressWarnings("Duplicates")
    private static void monday() {
        StringPrompt stringPrompt = new StringPrompt('>');

        do {
            System.out.println("Enter either: P=Present, A=Absent, T=Tardy. Case doesn't matter");
            stringPrompt.setNextQuestion("Status: ");
            mondayAnswer = stringPrompt.getLastAnswer();
        } while (mondayAnswer.isEmpty());

        if (!mondayAnswer.toLowerCase().equals("p") || !mondayAnswer.toLowerCase().equals("a") ||!mondayAnswer.toLowerCase().equals("t")) {
            FinalAttendance.parse(studentID, "monday", mondayAnswer.toLowerCase());
        } else {
            System.out.println("\nThat's not a valid input.\n");
            manage(studentID);
        }

        ManageAttendance.manage();
    }

    @SuppressWarnings("Duplicates")
    private static void tuesday() {
        StringPrompt stringPrompt = new StringPrompt('>');

        do {
            System.out.println("Enter either: P=Present, A=Absent, T=Tardy. Case doesn't matter");
            stringPrompt.setNextQuestion("Status: ");
            tuesdayAnswer = stringPrompt.getLastAnswer();
        } while (tuesdayAnswer.isEmpty());

        if (!tuesdayAnswer.toLowerCase().equals("p") || !tuesdayAnswer.toLowerCase().equals("a") ||!tuesdayAnswer.toLowerCase().equals("t")) {
            FinalAttendance.parse(studentID, "tuesday", tuesdayAnswer.toLowerCase());
        } else {
            System.out.println("\nThat's not a valid input.\n");
            manage(studentID);
        }

        ManageAttendance.manage();
    }

    @SuppressWarnings("Duplicates")
    private static void wednesday() {
        StringPrompt stringPrompt = new StringPrompt('>');

        do {
            System.out.println("Enter either: P=Present, A=Absent, T=Tardy. Case doesn't matter");
            stringPrompt.setNextQuestion("Status: ");
            wednesdayAnswer = stringPrompt.getLastAnswer();
        } while (wednesdayAnswer.isEmpty());

        if (!wednesdayAnswer.toLowerCase().equals("p") || !wednesdayAnswer.toLowerCase().equals("a") ||!wednesdayAnswer.toLowerCase().equals("t")) {
            FinalAttendance.parse(studentID, "wednesday", wednesdayAnswer.toLowerCase());
        } else {
            System.out.println("\nThat's not a valid input.\n");
            manage(studentID);
        }

        ManageAttendance.manage();
    }

    @SuppressWarnings("Duplicates")
    private static void thursday() {
        StringPrompt stringPrompt = new StringPrompt('>');

        do {
            System.out.println("Enter either: P=Present, A=Absent, T=Tardy. Case doesn't matter");
            stringPrompt.setNextQuestion("Status: ");
            thursdayAnswer = stringPrompt.getLastAnswer();
        } while (thursdayAnswer.isEmpty());

        if (!thursdayAnswer.toLowerCase().equals("p") || !thursdayAnswer.toLowerCase().equals("a") ||!thursdayAnswer.toLowerCase().equals("t")) {
            FinalAttendance.parse(studentID, "thursday", thursdayAnswer.toLowerCase());
        } else {
            System.out.println("\nThat's not a valid input.\n");
            manage(studentID);
        }

        ManageAttendance.manage();
    }

    @SuppressWarnings("Duplicates")
    private static void friday() {
        StringPrompt stringPrompt = new StringPrompt('>');

        do {
            System.out.println("Enter either: P=Present, A=Absent, T=Tardy. Case doesn't matter");
            stringPrompt.setNextQuestion("Status: ");
            fridayAnswer = stringPrompt.getLastAnswer();
        } while (fridayAnswer.isEmpty());

        if (!fridayAnswer.toLowerCase().equals("p") || !fridayAnswer.toLowerCase().equals("a") ||!fridayAnswer.toLowerCase().equals("t")) {
            FinalAttendance.parse(studentID, "friday", fridayAnswer.toLowerCase());
        } else {
            System.out.println("\nThat's not a valid input.\n");
            manage(studentID);
        }

        ManageAttendance.manage();
    }
}
