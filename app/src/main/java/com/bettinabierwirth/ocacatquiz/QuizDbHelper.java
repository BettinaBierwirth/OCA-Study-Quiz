package com.bettinabierwirth.ocacatquiz;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
/**
 * Helper class for managing the Quiz database.
 * This class provides methods for creating and updating the Quiz database,
 * inserting questions into the database, and retrieving all questions from the database.
 */
public class QuizDbHelper extends SQLiteOpenHelper {
    /**
     * Represents the name of the database.
     */
    private static final String DATABASE_NAME = "OCAQuiz.db";
    /**
     * Represents the version of the database.
     */
    private static final int DATABASE_VERSION = 1;
    /**
     * Represents the SQLiteDatabase object for database operations.
     */

    private SQLiteDatabase db;


    /**
     * Constructor for the QuizDbHelper.
     *
     * @param context The context of the application.
     */
    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        //SQL statement to create the questions table.

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuizContract.QuestionsTable.TABLE_NAME + " ( " +
                QuizContract.QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract.QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION5 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION6 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_ANSWER_NR + " TEXT" +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }
    /**
     * Fills the questions table with predefined questions.
     */
    private void fillQuestionsTable() {
        List<Integer> answerNr1 = Arrays.asList(1, 2, 5);
        Question q1 = new Question("Which of the following are valid Java identifiers?",
                "A$B", "_helloWorld", "true", "java.lang", "Public", "1980_s", answerNr1);
        addQuestion(q1);

        List<Integer> answerNr2 = Collections.singletonList(4);
        Question q2 = new Question("What is the output of the following program? \n"
                + "\n" +
                "1: public class WaterBottle {\n" +
                "2: private String brand; \n" +
                "3: private boolean empty; \n" +
                "4: public static void main(String[] args) {\n" +
                "5:   WaterBottle wb = new WaterBottle(); \n" +
                "6:   System.out.print(\"Empty = \"\n     + wb.empty); \n" +
                "7:   System.out.print(\"Brand = \"\n     + wb.brand); \n" +
                "8: }}\n"
                , "Line 6 generates a compiler error",
                "Line 7 generates a compiler error",
                "There is no output",
                "Empty = false, Brand = null",
                "Empty = false, Brand =",
                "Empty = null, Brand = null", answerNr2);
        addQuestion(q2);

        List<Integer> answerNr3 = Collections.singletonList(3);
        Question q3 = new Question("Which are true?", "\"X extends Y\" is correct if and only if X is a class and Y is an interface",
                "\"X extends Y\" is correct if and only if X is an interface and Y is a class",
                "\"X extends Y\" is correct if X and Y are either both classes or both interfaces",
                "\"Y extends Y\" is correct if X and Y are either both classes or both interfaces",
                "\"X extends Y\" is correct only if X is an interface and Y is a class",
                "\"X extends Y\" is correct only if X and Y are of different types, where X is a class and Y is an interface or vice versa", answerNr3);
        addQuestion(q3);

        List<Integer> answerNr4 = Arrays.asList(2, 6);
        Question q4 = new Question("Given:\n" +
                "class Rocket {\n" +
                " private void blastOff() { System.out.print(\"bang \"); }\n" +
                "}\n" +
                "public class Shuttle extends Rocket {\n" +
                " public static void \n" +
                " main(String[] args) {\n" +
                " new Shuttle().go();\n" +
                " }\n" +
                " void go() {\n" +
                " blastOff();\n" +
                " // Rocket.blastOff(); // line A\n" +
                " }\n" +
                " private void blastOff() { \n" +
                " System.out.print(\"sh-bang \"); }\n" +
                "}\n" +
                " Which are true?",
                "As the code stands, the output is bang bang",
                "As the code stands, the output is  bang",
                "As the code stands, compilation fails",
                "If line A is uncommented, the output is bang bang",
                "If line A is uncommented, the output is sh-bang bang",
                "If line A is uncommented, compilation fails.",
                answerNr4);
        addQuestion(q4);

        List<Integer> answerNr5 = Collections.singletonList(2);
        Question q5 = new Question("Given that the for loop's syntax is correct, and given:\n" +
                "import static java.lang.System.*;\n" +
                "class _ {\n" +
                " static public void main(String[] __A_V_) {\n" +
                " String $ = \"\";\n" +
                " for(int x=0; ++x < __A_V_.length; ) // for loop\n" +
                " $ += __A_V_[x];\n" +
                " out.println($);\n" +
                " }\n" +
                "}\n" +
                " And the command line:\n" +
                "java _ - A .\n" +
                " What is the result?",
                "-A", "A.", "-A.", "_A.", "_-A.", "Compilation fails", answerNr5);
        addQuestion(q5);

        List<Integer> answerNr6 = Collections.singletonList(1);
        Question q6 = new Question("Given:\n" +
                " 1: enum Animals {\n" +
                " 2: DOG(\"woof\"), CAT(\"meow\"), FISH(\"burble\");\n" +
                " 3: String sound;\n" +
                " 4: Animals(String s) { sound = s; }\n" +
                " 5: }\n" +
                " 6: class TestEnum {\n" +
                " 7: static Animals a;\n" +
                " 8: public static void main(String[] args) {\n" +
                " 9: System.out.println(a.DOG.sound + \" \" + a.FISH.sound);\n" +
                "10: }\n" +
                "11: }\n" +
                " What is the result?",
                "woof burble",
                "Multiple compilation errors",
                "Compilation fails due to an error on line 2",
                "Compilation fails due to an error on line 3",
                "Compilation fails due to an error on line 4",
                "Compilation fails due to an error on line 9",
                answerNr6);
        addQuestion(q6);

        List<Integer> answerNr7 = Arrays.asList(4, 5);
        Question q7 = new Question("Given two files:\n" +
                " 1: package pkgA;\n" +
                " 2: public class Foo {\n" +
                " 3: int a = 5;\n" +
                " 4: protected int b = 6;\n" +
                " 5: public int c = 7;\n" +
                " 6: }\n" +
                " 3: package pkgB;\n" +
                " 4: import pkgA.*;\n" +
                " 5: public class Baz {\n" +
                " 6: public static void main(String[] args) {\n" +
                " 7: Foo f = new Foo();\n" +
                " 8: System.out.print(\" \" + f.a);\n" +
                " 9: System.out.print(\" \" + f.b);\n" +
                "10: System.out.println(\" \" + f.c);\n" +
                "11: }\n" +
                "12: }\n" +
                "What is the result?",
                "5 6 7",
                "5 followed by an exception",
                "Compilation fails with an error on line 7",
                "Compilation fails with an error on line 8",
                "Compilation fails with an error on line 9",
                "Compilation fails with an error on line 10",
                answerNr7);
        addQuestion(q7);

        List<Integer> answerNr8 = Collections.singletonList(1);
        Question q8 = new Question("Given:\n" +
                " 1: public class Electronic implements Device\n" +
                "    { public void doIt() { } }\n" +
                " 2:\n" +
                " 3: abstract class Phone1 extends\n" +
                "    Electronic { }\n" +
                " 4:\n" +
                " 5: abstract class Phone2 extends Electronic\n" +
                "    { public void doIt(int x) { } }\n" +
                " 6:\n" +
                " 7: class Phone3 extends \n" +
                "    Electronic implements Device\n" +
                "    { public void doStuff() { } }\n" +
                " 8:\n" +
                " 9: interface Device { public void doIt(); }\n" +
                "What is the result?",
                "Compilation succeeds",
                "Compilation fails with an error on line 1",
                "Compilation fails with an error on line 3",
                "Compilation fails with an error on line 5",
                "Compilation fails with an error on line 7",
                "Compilation fails with an error on line 9",
                answerNr8);
        addQuestion(q8);

        List<Integer> answerNr9 = Collections.singletonList(2);
        Question q9 = new Question("Given:\n" +
                "1: public class A {\n" +
                "2: public static void main(String args[ ])\n" +
                "3: {\n" +
                "4: char[ ][ ][ ] threeD = {{{'a','b'}," +
                "   {'d','e'}}, {{'f','g'},{'h'}}};\n" +
                "5: System.out.println(INSERT CODE HERE);\n" +
                "6: }\n" +
                "What code, inserted above, would enable the" +
                "character 'f' to be printed to the screen?",
                "threeD[1][0]",
                "threeD[1][0][0]",
                "threeD[1][2][0]",
                "threeD[0][0][0]",
                "threeD[1][2][2]",
                "None of the answers are correct",
                answerNr9);
        addQuestion(q9);

        List<Integer> answerNr10 = Collections.singletonList(3);
        Question q10 = new Question("public class A {\n" +
                "public static void main(String [] args) {\n" +
                "StringBuilder sb = new StringBuilder(\"abc\");\n" +
                "String s = \"abc\";\n" +
                "sb.reverse().append(\"d\");\n" +
                "s.toUpperCase().concat(\"d\");\n" +
                "System.out.println(\".\" + sb + \" . . \" + s + \".\");\n" +
                "}}\n" +
                "Which two substrings will be included in the result?\n",
                ". abc.",
                ". ABCd.",
                ".ABCD.",
                ". cbad.",
                ".ABC.",
                ".abcd.",
                answerNr10);
        addQuestion(q10);

        List<Integer> answerNr11 = Collections.singletonList(1);
        Question q11 = new Question("Which of the following are true?",
                "ArrayList class has a size() method that returns the number of elements that exist in the ArrayList.",
                "ArrayList class has a length() method that returns the size or length of the ArrayList.",
                "Arrays have a size() method that returns the number of elements that exist in the Array.",
                "Arrays have a length() method that returns the number of elements that exist in the Array.",
                "Nothing is true.",
                "ArrayList class has a length() method that returns the sum of the ArrayList.",
                answerNr11);
        addQuestion(q11);

        List<Integer> answerNr12 = Collections.singletonList(3);
        Question q12 = new Question("What is the name of the special area of memory used to store strings?",
                "StringBuilder Constant Pool",
                "Character Constant Pool",
                "String Constant Pool",
                "Builder Pool",
                "String Variable Pool",
                "None of the answers are correct",
                answerNr12);
        addQuestion(q12);

        List<Integer> answerNr13 = Collections.singletonList(3);
        Question q13 = new Question("Which of the following are correct?",
                "The String and StringBuilder class are immutable i.e. you cannot change them anyway.",
                "The StringBuilder class is immutable i.e. you cannot change it in any way.",
                "The String class is immutable i.e. you cannot change it in anyway.",
                "The StringBuilder class is immutable i.e. you can change it in any way.",
                "The String class is immutable i.e. you can change it in anyway.",
                "None of the answers are correct",
                answerNr13);
        addQuestion(q13);




    }

    /**
     * Adds the question to the database.
     *
     * @param question The question to be added.
     */

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuizContract.QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION4, question.getOption4());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION5, question.getOption5());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION6, question.getOption6());

        String answerNrsString = TextUtils.join(",", question.getAnswerNr());
        cv.put(QuizContract.QuestionsTable.COLUMN_ANSWER_NR, answerNrsString);

        db.insert(QuizContract.QuestionsTable.TABLE_NAME, null, cv);
    }

    /**
     * This method getAllQuestions() retrieves all the questions stored in the database.
     * This method encapsulates the logic to retrieve all questions from the database,
     * parse the data, and construct Question objects, providing a convenient way
     * to access the questions throughout the application.
     * ----------------------------------------------------------------------------------
     *     <b>Annotation: @SuppressLint("Range")</b>
     *         This annotation is used to suppress lint warnings,
     *         specifically the one related to the use of Cursor
     *         methods like getCount(), moveToFirst(), etc.
     *     <b>Return Type: List Question</b>
     *         This method returns a list of Question objects,
     *         which represents all the questions stored in the database.
     *     <b>Local Variables:</b>
     *         questionList: An ArrayList to store the retrieved questions.
     *         db: An instance of SQLiteDatabase obtained from getReadableDatabase().
     *         c: A Cursor object to iterate over the result set from the database query.
     *     <b>Database Query:</b>
     *        The method executes a raw SQL query using db.rawQuery() to select all
     *        rows from the QuizContract.QuestionsTable.TABLE_NAME.
     *     <b>Cursor Iteration:</b>
     *        It iterates over the result set using a do-while loop.
     *        For each row in the result set:
     *          It constructs a new Question object.
     *          It retrieves question details (question text, options, answer numbers) from the cursor using column indices.
     *          It constructs a list of answer numbers by parsing the comma-separated string obtained from the cursor.
     *          It sets the retrieved question details and answer numbers to the Question object.
     *          It adds the Question object to the questionList.
     *      <b>Exception Handling:</b>
     *          Any exceptions that occur during the database operation are caught.
     *          If an exception occurs, it's printed using printStackTrace().
     *      <b>Finally Block:</b>
     *         It ensures that the cursor is properly closed after use.
     *      <b>Return Statement:</b>
     *         Finally, it returns the populated questionList.
     *
     * @return The list of questions.
     */
    @SuppressLint("Range")
    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuizContract.QuestionsTable.TABLE_NAME, null);

        try {
            if (c.moveToFirst()) {
                do {
                    Question question = new Question();
                    question.setQuestion(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_QUESTION)));
                    question.setOption1(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION1)));
                    question.setOption2(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION2)));
                    question.setOption3(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION3)));
                    question.setOption4(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION4)));
                    question.setOption5(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION5)));
                    question.setOption6(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION6)));

                    String answerNrsString = c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_ANSWER_NR));
                    List<Integer> answerNrs = new ArrayList<>();
                    String[] answerNrsArray = answerNrsString.split(",");
                    for (String answerNr : answerNrsArray) {
                        answerNrs.add(Integer.parseInt(answerNr));
                    }
                    question.setAnswerNr(answerNrs);

                    questionList.add(question);
                } while (c.moveToNext());
            }
        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            if (c != null && !c.isClosed()) {
                c.close();
            }
        }
        return questionList;
    }

    /**
     * This method returns the number of questions stored in the database.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

}
