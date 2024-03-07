package com.bettinabierwirth.ocacatquiz;

import android.provider.BaseColumns;
/**
 * Diese Klasse definiert den Contract für den Quiz-Datenanbieter der OCat Quiz Anwendung.
 * Sie enthält Definitionen für die Tabellenstruktur und Spaltennamen.
 */
public final class QuizContract {

    private QuizContract() {
    }
    /**
     * Innere Klasse, die die Struktur der "quiz_questions" Tabelle definiert.
     * Implementiert die {@link BaseColumns} Schnittstelle für Standard-Spaltennamen.
     */
    public static class QuestionsTable implements BaseColumns {
        /**
         * Der Name der Tabelle, in der die Quizfragen gespeichert sind.
         */
        public static final String TABLE_NAME = "quiz_questions";
        /**
         * Spalte für den Text der Quizfrage.
         */
        public static final String COLUMN_QUESTION = "question";

        /**
         * Spalte für die Antwortmöglichkeiten 1-6.
         */
        public static final String COLUMN_OPTION1 = "option1";
        public static final String COLUMN_OPTION2 = "option2";
        public static final String COLUMN_OPTION3 = "option3";
        public static final String COLUMN_OPTION4 = "option4";
        public static final String COLUMN_OPTION5 = "option5";
        public static final String COLUMN_OPTION6 = "option6";

        /**
         * Spalte für die richtige(n) Antwort(en).
         */
        public static final String COLUMN_ANSWER_NR = "answer_nr";
    }


}