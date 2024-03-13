package com.bettinabierwirth.ocacatquiz;

import android.provider.BaseColumns;
/**
 * In summary, the QuizContract class acts as a contract defining the
 * structure of the database tables used in the OCat Quiz application.
 * It ensures consistency in table structure and column names across
 * different parts of the application, making it easier to maintain
 * and work with the database. The QuestionsTable inner class specifically
 * defines the structure of the "quiz_questions" table, including its column names and types.
 * In the context of software development, particularly in Android app development,
 * a contract serves as a formal agreement or interface that defines how different
 * components of the system interact with each other.
 * In this case the contract has the following features:
 * <b>Structure Definition:</b>
 *The contract defines the structure of the
 *database tables used in the application.
 *It specifies the tables' names and the
 * names of their columns.
 *<b>Consistency:</b>
 *By centralizing the definition of table structures
 *and column names in a contract class, it ensures
 *consistency across different parts of the application
 *that interact with the database. For example, if multiple
 *parts of the application need to access the same table or column,
 *they can refer to the contract class to ensure they use the same names.
 * <b>Encapsulation:</b>
 * The contract class encapsulates the details of the database
 * schema within a single class. This encapsulation makes it easier to
 * manage changes to the database schema because modifications can be
 * made in one central location.
 *<b>Ease of Maintenance:</b>
 *Since the contract class consolidates the definitions of
 *database structures and column names, it simplifies maintenance
 *and updates. If the database schema needs to change, developers
 *can update the contract class, and all parts of the application
 *referencing it will automatically reflect the changes.
 *<b>Documentation:</b>
 *The contract class serves as documentation for other developers
 *working on the project. By examining the contract, developers
 *can quickly understand the database schema and how different
 *components of the application interact with it.
 */
public final class QuizContract {

    /**
     * Inner class defining the structure of the "quiz_questions" table.
     * Implements the {@link BaseColumns} interface for standard column names.
     */
    public static class QuestionsTable implements BaseColumns {
        /**
         * The name of the table where the quiz questions are stored.
         */
        public static final String TABLE_NAME = "quiz_questions";
        /**
         * Column for the text of the quiz question.
         */
        public static final String COLUMN_QUESTION = "question";

        /**
         * Column for option 1.
         */
        public static final String COLUMN_OPTION1 = "option1";
        /**
         * Column for option 2.
         */
        public static final String COLUMN_OPTION2 = "option2";
        /**
         * Column for option 3.
         */
        public static final String COLUMN_OPTION3 = "option3";
        /**
         * Column for option 4.
         */
        public static final String COLUMN_OPTION4 = "option4";
        /**
         * Column for option 5.
         */
        public static final String COLUMN_OPTION5 = "option5";
        /**
         * Column for option 6.
         */
        public static final String COLUMN_OPTION6 = "option6";

        /**
         * Column for the correct answer(s).
         */
        public static final String COLUMN_ANSWER_NR = "answer_nr";
    }


}