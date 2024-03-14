package com.bettinabierwirth.ocacatquiz;

import java.util.List;

/**
 * This line declares a public class named Question.
 * A class is a blueprint for creating objects, and Question serves as a blueprint
 * for creating question objects in the quiz application.
 * The Question class encapsulates the data and behavior related to a single
 * question in the quiz application. It provides constructors to create
 * question objects with specific details and methods to access and modify those
 * details. Each question object contains a question text, multiple options,
 * and the indices of correct answers.
 */
public class Question {

    /**
     * These lines declare private instance variables to store
     * the details of a question object. Each question has a
     * text (question) and up to six options
     * (option1, option2, ..., option6). Additionally,
     * there's a list of integers (answerNr) representing
     * the indices of the correct answer(s) among the options.
     */
    private String question;
    /**
     * Represents the first option string.
     */
    private String option1;
    /**
     * Represents the second option string.
     */
    private String option2;
    /**
     * Represents the third option string.
     */
    private String option3;
    /**
     * Represents the fourth option string.
     */
    private String option4;
    /**
     * Represents the fifth option string.
     */
    private String option5;
    /**
     * Represents the sixth option string.
     */
    private String option6;
    /**
     *Represents a list of integers corresponding to answer numbers.
     */
    private List<Integer> answerNr;

    /**
     * This constructor initializes an empty
     * Question object. It doesn't set any
     * values to the fields.
     */
    public Question() {
    }

    /**
     * This constructor initializes a Question object with
     * the specified details. It sets the question,
     * option1 to option6, and answerNr fields based on
     * the provided parameters.
     *
     * @param question The text of the question.
     * @param option1  The first option for answering the question.
     * @param option2  The second option for answering the question.
     * @param option3  The third option for answering the question.
     * @param option4  The fourth option for answering the question (optional).
     * @param option5  The fifth option for answering the question (optional).
     * @param option6  The sixth option for answering the question (optional).
     * @param answerNr A list of integers representing the correct answer(s) (indices of the options).
     */

    public Question(String question, String option1, String option2,  String option3, String option4, String option5, String option6, List<Integer> answerNr) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.option5 = option5;
        this.option6 = option6;
        this.answerNr = answerNr;
    }

    /**
     * These methods are getter and setter methods for accessing and
     * modifying the fields of the Question class. They allow other
     * parts of the program to retrieve (get) or change (set) the
     * values of the question text, options, and correct answer indices.
     * __________________________________________________________________
     * Gets the text of the question.
     *
     * @return The text of the question.
     */
    public String getQuestion() {
        return question;
    }
    /**
     * Sets the text of the question.
     *
     * @param question The new text of the question.
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * Gets the first option for answering the question.
     *
     * @return The first option.
     */

    public String getOption1() {
        return option1;
    }

   /**
     * Sets the first option for answering the question.
     *
     * @param option1 The new first option.
     */

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    /**
     * Gets the second option for answering the question.
     *
     * @return The second option.
     */

    public String getOption2() {
        return option2;
    }

    /**
     * Sets the second option for answering the question.
     *
     * @param option2 The new second option.
     */

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    /**
     * Gets the third option for answering the question.
     *
     * @return The third option.
     */

    public String getOption3() {
        return option3;
    }

    /**
     * Sets the third option for answering the question.
     *
     * @param option3 The new third option.
     */

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    /**
     * Gets the fourth option for answering the question.
     *
     * @return The fourth option.
     */

    public String getOption4() { return option4; }

    /**
     * Sets the fourth option for answering the question.
     *
     * @param option4 The new fourth option.
     */

    public void setOption4(String option4) { this.option4 = option4; }

    /**
     * Gets the fifth option for answering the question.
     *
     * @return The fifth option.
     */

    public String getOption5() { return option5; }

    /**
     * Sets the fifth option for answering the question.
     *
     * @param option5 The new fifth option.
     */

    public void setOption5(String option5) { this.option5 = option5; }

    /**
     * Gets the sixth option for answering the question.
     *
     * @return The sixth option.
     */

    public String getOption6() { return option6; }

    /**
     * Sets the sixth option for answering the question.
     *
     * @param option6 The new sixth option.
     */

    public void setOption6(String option6) { this.option6 = option6; }

    /**
     * Gets the list of correct answers.
     *
     * @return The list of correct answers.
     */

    public List<Integer> getAnswerNr() {
        return answerNr;
    }

    /**
     * Sets the list of correct answers.
     *
     * @param answerNr The new list of correct answers.
     */
    public void setAnswerNr(List<Integer> answerNr) {
        this.answerNr = answerNr;
    }
}