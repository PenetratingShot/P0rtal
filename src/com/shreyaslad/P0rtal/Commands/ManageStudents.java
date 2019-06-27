/**
 * Things different from pseudocode (or rather flowchart):
 *
 * Added more prompts for managing students: "Edit Students", "Remove Students", and "Go Back"
 * List of students printed out as table
 * Student ID is derived from LinkedList, not stored in the list itself
 * Catch IndexOutOfBoundsExeception and NumberFormatException and redirect user back to question or ManageStudents entry point (manage())
 *
 */

package com.shreyaslad.P0rtal.Commands;

import com.shreyaslad.P0rtal.Data.FinalStudent;
import com.shreyaslad.P0rtal.Main;
import com.shreyaslad.P0rtal.Prompt.StringPrompt;

import java.util.LinkedList;

public class ManageStudents {
    private static int finalAnswer;

    @SuppressWarnings("Duplicates")
    public static void manage() {
        System.out.println("\nManage Students");
        StringPrompt stringPrompt = new StringPrompt('>');
        String answer;

        try {
            stringPrompt.setNextQuestion("[1] View All Students\n[2] Add Students\n[3] Edit Student Names\n[4] Remove Students\n[5] Go Back");
            answer = stringPrompt.getLastAnswer();
            finalAnswer = Integer.parseInt(answer);
        } catch (NumberFormatException ex) {
            keep2();
        }

        switch (finalAnswer) {
            case 1:
                viewStudents();
                break;
            case 2:
                addStudents();
                break;
            case 3:
                editStudents();
                break;
            case 4:
                removeStudents();
                break;
            case 5:
                String[] hi = {"hi"};
                Main.main(hi); // Useless shitty forced arguments for main
                break;
            default:
                keep2();
        }
    }

    @SuppressWarnings("Duplicates")
    private static void viewStudents() {
        String leftAlignFormat = "| %-2s | %-45s |%n";
        System.out.format("\n\n+----+-----------------------------------------------+%n");
        System.out.format("| ID | Student Names                                 |%n");
        System.out.format("+----+-----------------------------------------------+%n");
        for (int i = 0; i < FinalStudent.size(); i++) {
            System.out.format(leftAlignFormat, i, FinalStudent.get(i));
        }
        System.out.format("+----+-----------------------------------------------+%n\n");

        manage();
    }

    @SuppressWarnings("Duplicates")
    public static void publicViewStudents() {
        String leftAlignFormat = "| %-2s | %-45s |%n";
        System.out.format("\n\n+----+-----------------------------------------------+%n");
        System.out.format("| ID | Student Names                                 |%n");
        System.out.format("+----+-----------------------------------------------+%n");
        for (int i = 0; i < FinalStudent.size(); i++) {
            System.out.format(leftAlignFormat, i, FinalStudent.get(i));
        }
        System.out.format("+----+-----------------------------------------------+%n\n");
    }

    public static void addStudents() {
        StringPrompt stringPrompt = new StringPrompt('>');
        String answer;

        do {
            stringPrompt.setNextQuestion("Name of student: ");
            answer = stringPrompt.getLastAnswer();
            FinalStudent.add(answer);
        } while (answer.isEmpty());
        System.out.println("Successfully added student with name " + answer);

        manage();
    }

    @SuppressWarnings("Duplicates")
    private static void removeStudents() {
        if (FinalStudent.size() == 0) {
            System.out.println("\nYou don't have any students in your roster. Let's add some.\n");
            ManageStudents.addStudents();
        }

        StringPrompt stringPrompt = new StringPrompt('>');
        String answer;

        do {
            stringPrompt.setNextQuestion("ID of student (press b to go back): ");
            answer = stringPrompt.getLastAnswer();
            if (answer.equals("b")) {
                manage();
            }

            try {
                if (Integer.parseInt(answer) > FinalStudent.size()) {
                    keep3();
                }

                System.out.println("\nSuccessfully removed student with name: " + FinalStudent.get(Integer.parseInt(answer)));
                FinalStudent.remove(Integer.parseInt(answer));
            } catch (NumberFormatException ex) {
                keep3();
            }

        } while (answer.isEmpty());

        manage();
    }

    @SuppressWarnings("Duplicates")
    private static void editStudents() {
        if (FinalStudent.size() == 0) {
            System.out.println("\nYou don't have any students in your roster. Let's add some.\n");
            ManageStudents.addStudents();
        }

        String[] questions = {"ID of the student: ", "New student name: "};
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
            System.out.println("\nThat student does not exist.\n");
            manage();
        } catch (NumberFormatException ex) {
            System.out.println("\nThat is not a valid student name\n");
            keep();
        }

        manage();
    }

    @SuppressWarnings("Duplicates")
    private static void keep() {
        if (FinalStudent.size() == 0) {
            System.out.println("\nYou don't have any students in your roster. Let's add some.\n");
            ManageStudents.addStudents();
        }

        String[] questions = {"ID of the student", "Name of the student"};
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
            System.out.println("\nThat student does not exist.\n");
            keep();
        } catch (NumberFormatException ex) {
            System.out.println("\nThat is not a valid student name\n");
            keep();
        }

        manage();
    }

    @SuppressWarnings("Duplicates")
    private static void keep2() {
        System.out.println("\nManage Students");
        StringPrompt stringPrompt = new StringPrompt('>');
        String answer;

        try {
            stringPrompt.setNextQuestion("[1] View All Students\n[2] Add Students\n[3] Edit Student Names\n[4] Remove Students\n[5] Go Back");
            answer = stringPrompt.getLastAnswer();
            finalAnswer = Integer.parseInt(answer);
        } catch (NumberFormatException ex) {
            keep2();
        }

        switch (finalAnswer) {
            case 1:
                viewStudents();
                break;
            case 2:
                addStudents();
                break;
            case 3:
                editStudents();
                break;
            case 4:
                removeStudents();
                break;
            case 5:
                String[] hi = {"hi"};
                Main.main(hi); // Useless shitty forced arguments for main
                break;
        }
    }

    @SuppressWarnings("Duplicates")
    private static void keep3() {
        if (FinalStudent.size() == 0) {
            System.out.println("\nYou don't have any students in your roster. Let's add some.\n");
            ManageStudents.addStudents();
        }

        StringPrompt stringPrompt = new StringPrompt('>');
        String answer;

        do {
            stringPrompt.setNextQuestion("ID of student (press b to go back): ");
            answer = stringPrompt.getLastAnswer();
            if (answer.equals("b")) {
                manage();
            }

            try {
                if (Integer.parseInt(answer) > FinalStudent.size()) {
                    keep3();
                }

                System.out.println("\nSuccessfully removed student with name: " + FinalStudent.get(Integer.parseInt(answer)));
                FinalStudent.remove(Integer.parseInt(answer));
            } catch (NumberFormatException ex) {
                keep3();
            }
            /*if (answer.equals("b")) {
                manage();
            } else {
                try {
                    if (Integer.parseInt(answer) > FinalStudent.size()) {
                        keep3();
                    } else {
                        FinalStudent.remove(Integer.parseInt(answer));
                    }
                } catch (NumberFormatException ex) {
                    keep3();
                } catch (IndexOutOfBoundsException ex) {
                    keep3();
                }
            }*/

        } while (answer.isEmpty());

        manage();
    }

}