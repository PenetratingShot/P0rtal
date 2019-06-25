// TODO: Make removing assignments idiot proof

package com.shreyaslad.P0rtal.Commands;

import com.shreyaslad.P0rtal.Data.FinalAssignment;
import com.shreyaslad.P0rtal.Main;
import com.shreyaslad.P0rtal.Prompt.BooleanPrompt;
import com.shreyaslad.P0rtal.Prompt.StringPrompt;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.MissingFormatArgumentException;

public class ManageAssignments {
    private static String addAnswer;
    private static String answer1;
    private static String answer2;

    public static void manage() {
        System.out.println("\nManage Assignments");
        StringPrompt stringPrompt = new StringPrompt('>');
        stringPrompt.setNextQuestion("[1] View All Assignments\n[2] Add Assignments\n[3] Remove Assignments\n[4] Edit Assignments\n[5] Go Back");
        String answer = stringPrompt.getLastAnswer();

        switch (Integer.parseInt(answer)) {
            case 1:
                viewAssignments();
                break;
            case 2:
                addAssignments();
                break;
            case 3:
                removeAssignments();
                break;
            case 4:
                editAssignments();
                break;
            case 5:
                String[] hi = {"hi"};
                Main.clearScreen();
                Main.main(hi); // Useless shitty forced arguments for main
                break;
        }
    }

    @SuppressWarnings("Duplicates")
    private static void viewAssignments() {
        Date date = new Date();
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String today = formatter.format(date);

        String leftAlignFormat = "| %-2s | %-45s | %-16s |%n";
        System.out.format("\n\n+----+-----------------------------------------------+------------------+%n");
        System.out.format("| ID | Assignment Name                               | Date Assigned    |%n");
        System.out.format("+----+-----------------------------------------------+------------------+%n");
        for (int i = 0; i < FinalAssignment.size(); i++) {
            System.out.format(leftAlignFormat, i, FinalAssignment.get(i), today);
        }
        System.out.format("+----+-----------------------------------------------+------------------+%n\n");

        manage();
    }

    @SuppressWarnings("Duplicates")
    public static void publicViewAssignments() {
        Date date = new Date();
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String today = formatter.format(date);

        String leftAlignFormat = "| %-2s | %-45s | %-16s |%n";
        System.out.format("\n\n+----+-----------------------------------------------+------------------+%n");
        System.out.format("| ID | Assignment Name                               | Date Assigned    |%n");
        System.out.format("+----+-----------------------------------------------+------------------+%n");
        for (int i = 0; i < FinalAssignment.size(); i++) {
            System.out.format(leftAlignFormat, i, FinalAssignment.get(i), today);
        }
        System.out.format("+----+-----------------------------------------------+------------------+%n\n");
    }

    @SuppressWarnings("Duplicates")
    private static void addAssignments() {
        StringPrompt stringPrompt = new StringPrompt('>');
        String answer;
        try {
            do {
                stringPrompt.setNextQuestion("Name of the assignment: ");
                answer = stringPrompt.getLastAnswer();
                FinalAssignment.add(answer);
            } while (answer.isEmpty());
            addAnswer = answer;
        } catch (NumberFormatException ex) {
            keep2();
        }
        System.out.println("Successfully added assignment with name " + addAnswer);
        manage();
    }

    @SuppressWarnings("Duplicates")
    private static void removeAssignments() {
        StringPrompt stringPrompt = new StringPrompt('>');
        String answer;

        do {
            stringPrompt.setNextQuestion("ID of the assignment: ");
            answer = stringPrompt.getLastAnswer();
            if (Integer.parseInt(answer) > FinalAssignment.size()) {
                keep();
            } else {
                FinalAssignment.remove(Integer.parseInt(answer));
            }

        } while (answer.isEmpty());
        manage();
    }

    @SuppressWarnings("Duplicates")
    private static void editAssignments() {

        StringPrompt stringPrompt = new StringPrompt('>');
        do {
            stringPrompt.setNextQuestion("Enter the ID of the assignment: ");
            answer1 = stringPrompt.getLastAnswer();
            stringPrompt.setNextQuestion("Enter the new value: ");
            answer2 = stringPrompt.getLastAnswer();
        } while (answer1.isEmpty());

        try {
            int index = Integer.parseInt(answer1);
            FinalAssignment.set(index, answer2);
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("\nIt looks like the assignment ID that you entered does not exist. \n");
            BooleanPrompt booleanPrompt = new BooleanPrompt('>', true);
            booleanPrompt.createPromptWithOneQuestion("Create assignment? ");

            boolean personAnswer = booleanPrompt.getLastAnswer();
            if (personAnswer) {
                ManageAssignments.manage();
            } else {
                String[] hi = {"hi"};
                Main.main(hi);
            }
        } catch (NumberFormatException ex) {
            System.out.println("\nUh oh. It looks like you entered something other than a number.");
            BooleanPrompt booleanPrompt = new BooleanPrompt('>', false);
            booleanPrompt.createPromptWithOneQuestion("Try again? ");

            boolean personAnwer = booleanPrompt.getLastAnswer();
            if (personAnwer) {
                keep3();
            } else {
                String[] hi = {"hi"};
                Main.main(hi);
            }
        }
        /*String[] questions = {"Enter the ID of the assignment: ", "Enter the new value: "};
        StringPrompt stringPrompt = new StringPrompt('>');
        LinkedList<String> answer;

        do {
            stringPrompt.setAndPrintQuestions(questions);
            answer = stringPrompt.getAnswers();
        } while (answer.isEmpty());

        try {
            int index = Integer.parseInt(answer.get(0));
            String name = answer.get(1);
            FinalStudent.set(index, name);
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("\nThat assignment does not exist.\n");
            keep();
        } catch (NumberFormatException ex) {
            System.out.println("\nThat is not a valid assignment name\n");
            keep();
        }*/

        manage();
    }

    @SuppressWarnings("Duplicates")
    private static void keep() {
        StringPrompt stringPrompt = new StringPrompt('>');
        String answer;
        do {
            stringPrompt.setNextQuestion("ID of the assignment: ");
            answer = stringPrompt.getLastAnswer();

            try {
                if (Integer.parseInt(answer) > FinalAssignment.size()) {
                    keep();
                } else {
                    FinalAssignment.remove(Integer.parseInt(answer));
                }
            } catch (MissingFormatArgumentException ex) {
                keep();
            }
        } while (answer.isEmpty());
    }

    @SuppressWarnings("Duplicates")
    private static void keep2() {
        StringPrompt stringPrompt = new StringPrompt('>');
        String answer;
        try {
            do {
                stringPrompt.setNextQuestion("Name of the assignment: ");
                answer = stringPrompt.getLastAnswer();
                FinalAssignment.add(answer);
            } while (answer.isEmpty());
            addAnswer = answer;
        } catch (NumberFormatException ex) {
            keep2();
        }
        System.out.println("Successfully added assignment with name " + addAnswer);
        manage();
    }

    @SuppressWarnings("Duplicates")
    private static void keep3() {
        StringPrompt stringPrompt = new StringPrompt('>');
        do {
            stringPrompt.setNextQuestion("Enter the ID of the assignment: ");
            answer1 = stringPrompt.getLastAnswer();
            stringPrompt.setNextQuestion("Enter the new value: ");
            answer2 = stringPrompt.getLastAnswer();
        } while (answer1.isEmpty());

        try {
            int index = Integer.parseInt(answer1);
            FinalAssignment.set(index, answer2);
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("\nIt looks like the assignment ID that you entered does not exist. \n");
            BooleanPrompt booleanPrompt = new BooleanPrompt('>', true);
            booleanPrompt.createPromptWithOneQuestion("Create assignment? ");

            boolean personAnswer = booleanPrompt.getLastAnswer();
            if (personAnswer) {
                ManageAssignments.manage();
            } else {
                String[] hi = {"hi"};
                Main.main(hi);
            }
        } catch (NumberFormatException ex) {
            System.out.println("\nUh oh. It looks like you entered something other than a number.");
            BooleanPrompt booleanPrompt = new BooleanPrompt('>', false);
            booleanPrompt.createPromptWithOneQuestion("Try again? ");

            boolean personAnswer = booleanPrompt.getLastAnswer();
            if (personAnswer) {
                keep3();
            } else {
                String[] hi = {"hi"};
                Main.main(hi);
            }
        }
        /*String[] questions = {"Enter the ID of the assignment: ", "Enter the new value: "};
        StringPrompt stringPrompt = new StringPrompt('>');
        LinkedList<String> answer;

        do {
            stringPrompt.setAndPrintQuestions(questions);
            answer = stringPrompt.getAnswers();
        } while (answer.isEmpty());

        try {
            int index = Integer.parseInt(answer.get(0));
            String name = answer.get(1);
            FinalStudent.set(index, name);
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("\nThat assignment does not exist.\n");
            keep();
        } catch (NumberFormatException ex) {
            System.out.println("\nThat is not a valid assignment name\n");
            keep();
        }*/

        manage();
    }
}