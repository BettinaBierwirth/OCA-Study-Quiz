package com.bettinabierwirth.ocacatquiz;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
/**
 * This activity represents the quiz and manages the flow of the quiz game.
 */
public class QuizActivity extends AppCompatActivity {

    /**
     * Initialization of variables.
     */
    private static final long COUNTDOWN_IN_MILLIS = 90000;
    private static final int MAX_QUESTIONS = 8;
    private TextView textViewQuestion;

    private TextView textViewQuestionCount;
    private TextView textViewCountDown;
    private CheckBox rb1;
    private CheckBox rb2;
    private CheckBox rb3;
    private CheckBox rb4;
    private CheckBox rb5;
    private CheckBox rb6;
    private Button buttonConfirmNext;
    private ColorStateList textColorDefaultRb;
    private ColorStateList textColorDefaultCb;
    private List<Integer> selectedAnswers;
    private List<Question> questionList;
    private int questionCounter;
    private int questionCountTotal = 0;
    private Question currentQuestion;
    private boolean answered;
    private ColorStateList textColorDefaultCd;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;
    private int correctQuestions = 0;
    /**
     * This method is called when the activity is created. It performs the initialization
     * of UI elements, database access, and configuration of the quiz game.
     *
     * @param savedInstanceState A Bundle object containing the activity's previous state.
     * __________________________________________________________________________________
     *<b>Set Content View:</b>
     *<i>setContentView(R.layout.activity_quiz);:</i>
     *Sets the content view of the activity to the layout defined in activity_quiz.xml.
     *<b>Initialize UI Elements:</b>
     * Initializes various UI elements by finding their respective views in the layout:
     * textViewQuestion, textViewQuestionCount, textViewCountDown: TextViews for displaying the question, question count, and countdown timer.
     * rb1 to rb6: RadioButtons for selecting answer options.
     * buttonConfirmNext: Button for confirming the answer or moving to the next question.
     * rbGroup: RadioGroup for managing RadioButton selection.
     * Stores default text colors for the countdown timer and RadioButtons.
     *<b>Initialize Database and Questions:</b>
     * Creates a QuizDbHelper instance to access the database.
     * Retrieves all questions from the database using getAllQuestions() method of QuizDbHelper.
     * Shuffles the order of questions using Collections.shuffle(questionList).
     *<b>Initialize Countdown Timer:</b>
     * Creates a CountDownTimer instance with the specified countdown duration (COUNTDOWN_IN_MILLIS) and interval.
     * Defines onTick() and onFinish() methods to handle countdown updates and actions when the countdown finishes, respectively.
     *<b>Display First Question:</b>
     * Calls the showNextQuestion() method to display the first question.
     *<b>Set Button Click Listener:</b>
     * Sets a click listener on the "Confirm"/"Next" button (buttonConfirmNext) to handle user interactions:
     * If no answer is selected (!answered), checks if any RadioButton is checked. If yes, calls checkAnswer() method to validate the answer and cancels the countdown timer.
     * If an answer is already selected, calls showNextQuestion() to display the next question and starts the countdown timer.
     * Overall, this method initializes the activity, sets up the UI components, retrieves questions from the database, starts the countdown timer, and handles user interactions for answering questions and navigating through the quiz.
     */
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        textViewQuestion = findViewById(R.id.text_view_question);
        textViewQuestionCount = findViewById(R.id.text_view_question_count);
        textViewCountDown = findViewById(R.id.text_view_countdown);
        RadioGroup rbGroup = findViewById(R.id.radio_group);
        rb1 = findViewById(R.id.radio_button1);
        rb2 = findViewById(R.id.radio_button2);
        rb3 = findViewById(R.id.radio_button3);
        rb4 = findViewById(R.id.radio_button4);
        rb5 = findViewById(R.id.radio_button5);
        rb6 = findViewById(R.id.radio_button6);
        buttonConfirmNext = findViewById(R.id.button_confirm_next);
        textColorDefaultCd = textViewCountDown.getTextColors();
        textColorDefaultRb = rb1.getTextColors();
        textColorDefaultCb = rb1.getTextColors();
        QuizDbHelper dbHelper = new QuizDbHelper(this);

        questionList = dbHelper.getAllQuestions();
        questionCountTotal = questionList.size();
        Collections.shuffle(questionList);
        countDownTimer = new CountDownTimer(COUNTDOWN_IN_MILLIS, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }
            @Override
            public void onFinish() {

                timeLeftInMillis = 0;
                updateCountDownText();
                checkAnswer();
            }
        };

        showNextQuestion();

        buttonConfirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answered) {



                    if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked() || rb5.isChecked() || rb6.isChecked()) {
                        checkAnswer();


                        countDownTimer.cancel();


                    } else {
                        Toast.makeText(QuizActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                    }
                } else {

                    showNextQuestion();
                    startCountDown();
                }
            }
        });

    }

    /**
     * This method, showNextQuestion(), is responsible for displaying the
     * next question in the quiz.
     *<b>Enable RadioButtons:</b>
     * rb1.setEnabled(true);, rb2.setEnabled(true);, ..., rb6.setEnabled(true);:
     * Enables all the RadioButton options for the next question,
     * allowing the user to select an answer.
     *<b>Reset RadioButton Text Color and Checked State:</b>>
     * rb1.setTextColor(textColorDefaultRb);, rb2.setTextColor(textColorDefaultRb);, ...,
     * rb6.setTextColor(textColorDefaultRb);:
     * Resets the text color of all RadioButtons to the default color (textColorDefaultRb).
     * rb1.setChecked(false);, rb2.setChecked(false);, ..., rb6.setChecked(false);:
     * Unchecks all RadioButtons, resetting their state.
     *<b>Load Next Question:</b>
     *Checks if there are more questions to display
     *(if (questionCounter < Math.min(MAX_QUESTIONS, questionCountTotal))).
     *<b>If there are more questions:</b>
     *Retrieves the next question from the questionList.
     *Sets the question text and options in their respective TextViews and RadioButtons.
     *Increments the questionCounter and updates the question count display.
     *Sets the answered flag to false and updates the button text to "Confirm".
     *Cancels the countdown timer from the previous question.
     *Resets the countdown timer to the default time and updates the countdown display.
     *Starts the countdown timer for the new question.
     *Scrolls the ScrollView to the top to ensure the question is visible.
     *If there are no more questions, calls the finishQuiz() method to end the quiz.
     * Overall, this method manages the UI state to prepare for displaying the next
     * question in the quiz, ensuring that the appropriate elements are enabled,
     * reset, and updated to provide a seamless user experience.
     */
    @SuppressLint("SetTextI18n")
    private void showNextQuestion() {

        rb1.setEnabled(true);
        rb2.setEnabled(true);
        rb3.setEnabled(true);
        rb4.setEnabled(true);
        rb5.setEnabled(true);
        rb6.setEnabled(true);

        rb1.setTextColor(textColorDefaultRb);
        rb2.setTextColor(textColorDefaultRb);
        rb3.setTextColor(textColorDefaultRb);
        rb4.setTextColor(textColorDefaultRb);
        rb5.setTextColor(textColorDefaultRb);
        rb6.setTextColor(textColorDefaultRb);

        rb1.setChecked(false);
        rb2.setChecked(false);
        rb3.setChecked(false);
        rb4.setChecked(false);
        rb5.setChecked(false);
        rb6.setChecked(false);

        if (questionCounter < Math.min(MAX_QUESTIONS, questionCountTotal)){

            currentQuestion = questionList.get(questionCounter);

            textViewQuestion.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());
            rb4.setText(currentQuestion.getOption4());
            rb5.setText(currentQuestion.getOption5());
            rb6.setText(currentQuestion.getOption6());

            questionCounter++;
            textViewQuestionCount.setText("Question: " + questionCounter + "/" + Math.min(MAX_QUESTIONS, questionCountTotal));

            answered = false;
            buttonConfirmNext.setText("Confirm");

            countDownTimer.cancel();

            timeLeftInMillis = COUNTDOWN_IN_MILLIS;
            updateCountDownText();

            startCountDown();

            ScrollView scrollView = findViewById(R.id.scroll_view);
            scrollView.smoothScrollTo(0, 0);

        } else {

            finishQuiz();
        }
    }

    private void startCountDown() {

        countDownTimer.start();
    }
    /**
     * This method, updateCountDownText(), is responsible for updating
     * the countdown display based on the remaining time.
     * <b>Calculate Remaining Time:</b>
     * <i>int minutes = (int) (timeLeftInMillis / 1000) / 60;:</i>
     * Calculates the remaining minutes by dividing the remaining time
     * in milliseconds by 1000 to convert it to seconds and then by 60 to get the minutes.
     * <i>int seconds = (int) (timeLeftInMillis / 1000) % 60;:</i> Calculates the
     * remaining seconds by dividing the remaining time in milliseconds by
     * 1000 to convert it to seconds and then finding the remainder when divided by 60.
     *<b>Format Time:</b>
     * <i>String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);:</i>
     * Formats the remaining minutes and seconds into a string with two digits for minutes and seconds,
     * separated by a colon.
     *<b>Update TextView:</b>
     * <i>textViewCountDown.setText(timeFormatted);:</i>
     * Sets the formatted time string to the textViewCountDown TextView,
     * updating the countdown display.
     *<b>Change Text Color:</b>
     *Checks if the remaining time is less than 10 seconds (timeLeftInMillis < 10000).
     *If true, sets the text color of textViewCountDown to red (Color.RED) to indicate
     * that time is running out.
     *If false, sets the text color of textViewCountDown back to the
     * default color (textColorDefaultCd), ensuring it matches the default color scheme.
     * Overall, this method ensures that the countdown display accurately
     * reflects the remaining time, formatting it properly and adjusting the text
     * color to provide visual feedback to the user about the urgency of the situation
     * as the time runs out.
     */
    private void updateCountDownText() {

        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        textViewCountDown.setText(timeFormatted);

        if (timeLeftInMillis < 10000) {

            textViewCountDown.setTextColor(Color.RED);
        } else {
            textViewCountDown.setTextColor(textColorDefaultCd);
        }
    }

    /**
     * This method, checkAnswer(), is responsible for validating the user's selected
     * answers against the correct answers for the current question and showing
     * the solution. Let's go through each part of the method:
     *<b>Mark as Answered:</b>
     *answered = true;: Sets the answered flag to true,
     * indicating that the user has provided an answer.
     *<b>Check for Null Question or Answers:</b>
     *if (currentQuestion != null && currentQuestion.getAnswerNr() != null) { ... }:
     * Checks if the current question object and its answer numbers are not null.
     *<b>Initialize Selected Answers List:</b>
     *selectedAnswers = new ArrayList<>();: Initializes a new ArrayList to store
     * the selected answer numbers.
     *<b>Check CheckBox States:</b>
     *Checks each CheckBox to see if it is checked. If it is,
     * the corresponding answer number is added to the selectedAnswers list.
     * <b>Show Solution:</b>
     * Calls the showSolution() method to display the correct
     * and incorrect answers to the user.
     *<b>Check if Answer is Correct:</b>
     *Compares the size and content of the selectedAnswers list with the
     * correct answer numbers (currentQuestion.getAnswerNr()).
     * If they match, it means the user provided all correct answers,
     * and the correctQuestions counter is incremented.
     *<b>Handle Null or Incomplete Questions:</b>
     *If the current question or its answer numbers are null,
     * it displays a toast message indicating an error.
     *------------------------------------------------------------------------
     * Overall, this method ensures that the user's selected answers are validated
     * against the correct answers, and appropriate feedback is provided to the user.
     * If there are any issues with the question or its answers, it handles them
     * by displaying an error message.
     */
    private void checkAnswer() {
        answered = true;
        if (currentQuestion != null && currentQuestion.getAnswerNr() != null) {
            selectedAnswers = new ArrayList<>();
            if (rb1.isChecked()) selectedAnswers.add(1);
            if (rb2.isChecked()) selectedAnswers.add(2);
            if (rb3.isChecked()) selectedAnswers.add(3);
            if (rb4.isChecked()) selectedAnswers.add(4);
            if (rb5.isChecked()) selectedAnswers.add(5);
            if (rb6.isChecked()) selectedAnswers.add(6);
            showSolution();
            if (currentQuestion.getAnswerNr().size() == selectedAnswers.size() &&
                    currentQuestion.getAnswerNr().containsAll(selectedAnswers) &&
                    selectedAnswers.containsAll(currentQuestion.getAnswerNr())) {
                correctQuestions++;
            }
        } else {
            Toast.makeText(QuizActivity.this, "Error: Question or answer options not available.", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * This method, showSolution(), is responsible for displaying the correct and incorrect
     * answers after the user has answered a question. Let's break down each part of the method:
     * <b>Disable CheckBoxes:</b>
     * rb1.setEnabled(false);, rb2.setEnabled(false);, ..., rb6.setEnabled(false);:
     * Disables all the CheckBoxes so the user cannot change their selections
     * after answering the question.
     *<b>Set Default Text Color:</b>
     *rb1.setTextColor(textColorDefaultCb);, rb2.setTextColor(textColorDefaultCb);, ...,
     * rb6.setTextColor(textColorDefaultCb);: Sets the text color of all CheckBoxes
     * to the default color, restoring them to their original state.
     * <b>Highlight Correct Answers:</b>
     * for (int i : currentQuestion.getAnswerNr()) { switch (i) { ... } }:
     * Iterates over the list of correct answer numbers for the current question.
     * Inside the loop, it switches based on the correct answer number and sets
     * the text color of the corresponding CheckBox to green (Color.GREEN).
     * This highlights the correct answers.
     *<b>Highlight Incorrect Answers:</b>
     * for (int i : selectedAnswers) { if (!currentQuestion.getAnswerNr().contains(i))
     * { switch (i) { ... } } }: Iterates over the list of selected answers by the user.
     * Checks if the selected answer is not present in the list of correct answers for
     * the current question.
     * If it's not correct, it switches based on the selected answer number and sets the
     * text color of the corresponding CheckBox to red (Color.RED). This highlights the
     * incorrect answers.
     *<b>Update Button Text:</b>
     *if (questionCounter < questionCountTotal) { buttonConfirmNext.setText(R.string.next); }
     * else { buttonConfirmNext.setText(R.string.finish); }: Updates the text of the
     * confirmation button based on whether there are more questions remaining (R.string.next)
     * or if it's the last question (R.string.finish).
     * Overall, this method provides visual feedback to the user about which answers
     * they got right and wrong after answering a question. It disables further interaction
     * with the CheckBoxes and updates the button text accordingly.
     */
    private void showSolution() {

        rb1.setEnabled(false);
        rb2.setEnabled(false);
        rb3.setEnabled(false);
        rb4.setEnabled(false);
        rb5.setEnabled(false);
        rb6.setEnabled(false);

        rb1.setTextColor(textColorDefaultCb);
        rb2.setTextColor(textColorDefaultCb);
        rb3.setTextColor(textColorDefaultCb);
        rb4.setTextColor(textColorDefaultCb);
        rb5.setTextColor(textColorDefaultCb);
        rb6.setTextColor(textColorDefaultCb);

        for (int i : currentQuestion.getAnswerNr()) {
            switch (i) {
                case 1:
                    rb1.setTextColor(Color.GREEN);
                    break;
                case 2:
                    rb2.setTextColor(Color.GREEN);
                    break;
                case 3:
                    rb3.setTextColor(Color.GREEN);
                    break;
                case 4:
                    rb4.setTextColor(Color.GREEN);
                    break;
                case 5:
                    rb5.setTextColor(Color.GREEN);
                    break;
                case 6:
                    rb6.setTextColor(Color.GREEN);
                    break;
            }
        }

        for (int i : selectedAnswers)
            if (!currentQuestion.getAnswerNr().contains(i)) {
                switch (i) {
                    case 1:
                        rb1.setTextColor(Color.RED);
                        break;
                    case 2:
                        rb2.setTextColor(Color.RED);
                        break;
                    case 3:
                        rb3.setTextColor(Color.RED);
                        break;
                    case 4:
                        rb4.setTextColor(Color.RED);
                        break;
                    case 5:
                        rb5.setTextColor(Color.RED);
                        break;
                    case 6:
                        rb6.setTextColor(Color.RED);
                        break;
                }
            }

        if (questionCounter < questionCountTotal) {
            buttonConfirmNext.setText(R.string.next);
        } else {
            buttonConfirmNext.setText(R.string.finish);
        }
    }

    /**
     * Finishes the quiz and passes the user's results to the ResultActivity.
     * The total score equals the number of correct questions. Optionally, the total score
     * and the number of correct questions can be passed to the ResultActivity. After forwarding
     * the results, the current quiz activity is finished.
     */
    private void finishQuiz() {

        int totalScore = correctQuestions;

        Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
        intent.putExtra("TOTAL_SCORE", totalScore);
        intent.putExtra("CORRECT_QUESTIONS", correctQuestions);
        startActivity(intent);

        finish();
    }

}
