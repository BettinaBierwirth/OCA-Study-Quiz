package com.bettinabierwirth.ocacatquiz;

import java.util.List;

/**
        * Diese Klasse repräsentiert eine einzelne Frage in der Quiz Anwendung.
        */
public class Question {

    /**
     * Der Text der Frage.
     */
    private String question;

    /**
     * Die Antwortmöglichkeiten 1 - 6:
     */
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String option5;
    private String option6;

    /**
     * Eine Liste von Ganzzahlen, die die richtige(n) Antwort(en) repräsentieren (Indizes der Optionen).
     */
    private List<Integer> answerNr;

    /**
     * Erstellt ein leeres Frageobjekt.
     */
    public Question() {
    }

    /**
     * Erstellt ein Frageobjekt mit den angegebenen Details.
     *
     * @param question Der Text der Frage.
     * @param option1 Die erste Antwortmöglichkeit.
     * @param option2 Die zweite Antwortmöglichkeit.
     * @param option3 Die dritte Antwortmöglichkeit.
     * @param option4 Die vierte Antwortmöglichkeit (optional).
     * @param option5 Die fünfte Antwortmöglichkeit (optional).
     * @param option6 Die sechste Antwortmöglichkeit (optional).
     * @param answerNr Eine Liste von Ganzzahlen, die die richtige(n) Antwort(en) repräsentieren (Indizes der Optionen).
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
     * Ruft den Text der Frage ab.
     *
     * @return Der Text der Frage.
     */
    public String getQuestion() {
        return question;
    }
    /**
     * Setzt den Text der Frage.
     *
     * @param question Der neue Text der Frage.
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() { return option4; }

    public void setOption4(String option4) { this.option4 = option4; }

    public String getOption5() { return option5; }

    public void setOption5(String option5) { this.option5 = option5; }

    public String getOption6() { return option6; }

    public void setOption6(String option6) { this.option6 = option6; }

    /**
     * Ruft die Liste der richtigen Antwortnummern ab.
     *
     * @return Die Liste der richtigen Antwortnummern (Indizes der Optionen).
     */

    public List<Integer> getAnswerNr() {
        return answerNr;
    }

    /**
     * Setzt die Liste der richtigen Antwortnummern.
     *
     * @param answerNr Die neue Liste der richtigen Antwortnummern (Indizes der Optionen).
     */
    public void setAnswerNr(List<Integer> answerNr) {
        this.answerNr = answerNr;
    }
}