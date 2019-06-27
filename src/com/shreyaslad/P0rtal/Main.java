/**
 * Things changed from pseudocode:
 *
 * Keep function isn't the only function with the main function code
 * Using stringprompt to create recursive prompts instead of making them myself
 * Catching NumberFormatException to make sure that the user is only entering numbers and I can still use StringPrompt
 * Added "P0rtal" ascii art before welcome message shows up
 * Public variables have their own classes with getters and setters so that all classes can access them
 *      Now, I can shift code from being just in Main to their own respective classes
 *
 */

package com.shreyaslad.P0rtal;

import com.shreyaslad.P0rtal.Commands.ManageAssignments;
import com.shreyaslad.P0rtal.Commands.Attendance.ManageAttendance;
import com.shreyaslad.P0rtal.Commands.ManageGrades;
import com.shreyaslad.P0rtal.Commands.ManageStudents;
import com.shreyaslad.P0rtal.Data.FinalAttendance;
import com.shreyaslad.P0rtal.Prompt.StringPrompt;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        FinalAttendance.reset();

        clearScreen(); // This doesn't work and it makes me sad :c

        Calendar now = Calendar.getInstance();
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
        DateFormat dateFormat = new SimpleDateFormat("dd MMMM, yyyy");
        String today = dateFormat.format(date);


        int hour = now.get(Calendar.HOUR_OF_DAY);

        System.out.println(" ____   __  ____  ____  __   __   \n" +
                "(  _ \\ /  \\(  _ \\(_  _)/ _\\ (  )  \n" +
                " ) __/(  0 ))   /  )( /    \\/ (_/\\\n" +
                "(__)   \\__/(__\\_) (__)\\_/\\_/\\____/\n");

        if (hour >= 0 && hour < 12) {
            System.out.print("Good morning. ");
        } else if (hour >= 12 && hour < 20) {
            System.out.print("Good afternoon. ");
        } else {
            System.out.print("Good evening. ");
        }

        System.out.println("Today is " + simpleDateFormat.format(date) + ", " + today + ".");
        System.out.println("Enter a number to get started.");

        String options = "\n[1] Manage Assignments\n[2] Manage Grades\n[3] Manage Students\n[4] Manage Attendance\n[5] Exit";

        StringPrompt stringPrompt = new StringPrompt('>');
        String answer;

        do {
            stringPrompt.setNextQuestion(options);
            answer = stringPrompt.getLastAnswer();
        } while (answer.isEmpty() || Integer.parseInt(answer) > 5);

        decider(Integer.parseInt(answer));
    }

    @SuppressWarnings("Duplicates")
    private static void decider(int answer) {
        switch (answer) {
            case 1:
                ManageAssignments.manage();
                break;
            case 2:
                ManageGrades.manage();
                break;
            case 3:
                ManageStudents.manage();
                break;
            case 4:
                // TODO: Implement check to make sure that there are students
                ManageAttendance.manage();
                break;
            case 5:
                System.out.println("\n\nGoodbye :)\n\n");
                System.exit(0);
                break;
            default:
                String[] ugh = {":/"};
                main(ugh);
        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}