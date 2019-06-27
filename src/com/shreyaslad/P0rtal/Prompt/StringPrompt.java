/**
 * Changed from pseudocode:
 *
 * I didn't include this in the pseudocode since farhan didn't have access to this class
 * Rewrote part of it to take an array of questions and return a linkedlist of answers
 */

package com.shreyaslad.P0rtal.Prompt;

import java.util.LinkedList;
import java.util.Scanner;

public class StringPrompt {

    private char beginCharacter;

    private String[] questions;
    private String question;
    private LinkedList<String> answers = new LinkedList<>();
    private String answer;

    private String currentQuestion;

    public StringPrompt(char beginCharacter) {
        this.beginCharacter = beginCharacter;
    }

    public void setAndPrintQuestions(String[] questions) {
        Scanner scanner = new Scanner(System.in);

        if (questions.length == 0) {
            throw new ArrayIndexOutOfBoundsException("Questions array cannot be empty");
        }

        try {
            this.questions = questions;

            for (int i = 0; i < questions.length; i++) {
                this.currentQuestion = questions[i];

                String userAnswer;

                do {
                    System.out.println(questions[i]);
                    System.out.print(beginCharacter + " ");
                    userAnswer = scanner.nextLine();
                } while(userAnswer.isEmpty());

                this.answer = userAnswer;
                answers.add(userAnswer);
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void setNextQuestion(String question) {
        Scanner scanner = new Scanner(System.in);

        this.question = question;

        String userInput;

        do {
            System.out.println(question);
            System.out.print(beginCharacter + " ");
            userInput = scanner.nextLine();
        } while (userInput.isEmpty());

        this.answer = userInput;
    }

    public LinkedList<String> getAnswers() {
        return answers;
    }

    public String getLastAnswer() {
        return answer;
    }
}
