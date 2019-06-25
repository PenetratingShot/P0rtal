package com.shreyaslad.P0rtal;

import com.shreyaslad.P0rtal.Commands.ManageAssignments;
import com.shreyaslad.P0rtal.Commands.ManageGrades;
import com.shreyaslad.P0rtal.Commands.ManageStudents;
import com.shreyaslad.P0rtal.Prompt.BooleanPrompt;
import com.shreyaslad.P0rtal.Prompt.StringPrompt;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
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

        String options = "\n[1] Manage Assignments\n[2] Manage Grades\n[3] Manage Students\n[4] Manage Attendance\n[5] Show class progress";

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
                BooleanPrompt booleanPrompt = new BooleanPrompt('>', false);
                System.out.println("This is very buggy and likely to break. \nOnly \"Manage Assignments\" and \"Manage Students\" have been completed.");
                booleanPrompt.createPromptWithOneQuestion("Continue?");

                boolean booleanAnswer = booleanPrompt.getLastAnswer();

                if (booleanAnswer) {
                    ManageGrades.manage();
                } else {
                    String[] bad = {":/"};
                    Main.main(bad);
                }
                break;
            case 3:
                ManageStudents.manage();
                break;
            case 4:
                System.out.println("Work on this section has not been started yet.\nOnly \"Manage Assignments\" and \"Manage Students\" have been completed.");
                String[] hi = {"hi"};
                Main.main(hi);
                break;
            case 5:
                System.out.println("Work on this section has not been started yet.\nOnly \"Manage Assignments\" and \"Manage Students\" have been completed.");
                String[] again = {"again"};
                Main.main(again);
                /*try {
                    System.out.println(FinalAssignment.get(0));
                } catch (IndexOutOfBoundsException ex) {
                    System.out.println("There are no assignments.");
                    String[] hi = {"hi"};
                    main(hi);
                }*/
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