package com.bettinabierwirth.ocacatquiz;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This line declares a public class named ResultActivity
 * that extends AppCompatActivity. AppCompatActivity is a
 * class provided by the Android Support Library that serves
 * as a base class for activities using the support library features.
 * The ResultActivity class is responsible for displaying the result
 * of the quiz taken by the user. It also provides an option to restart the quiz.
 */
public class ResultActivity extends AppCompatActivity {

    /** This field represents the total number of questions in the quiz.
     * It's set as static and final to ensure that it remains
     * constant throughout the execution of the program.  */
    private static final int totalQuestions = 8;

    /**
     * This method is called when the activity is starting.
     * It initializes the activity layout, retrieves the result
     * from the intent, displays the result message based on the
     * score, sets an OnClickListener for the "Start Quiz" button,
     * and displays the total score.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously
     *                            being shut down then this Bundle contains the data it most
     *                            recently supplied in onSaveInstanceState(Bundle). Otherwise,
     *                            it is null.
     * <b><i>Note: It might be null if the activity is created for the first time.</i></b>
     */
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Retrieve result from the intent.
        int score = getIntent().getIntExtra("TOTAL_SCORE", 0);

        // Display the result based on the score
        TextView resultMessage = findViewById(R.id.text_view_result_message);
        resultMessage.setText(getResultMessage(score));

        TextView textViewResult = findViewById(R.id.text_view_result);
        Button buttonStartQuiz = findViewById(R.id.button_start_quiz);

        // Set OnClickListener for the Start Quiz Button
        buttonStartQuiz.setOnClickListener(v -> startQuiz());

        // Display the total score
        textViewResult.setText("Your Score: " + score);
    }

    /**
     * This method starts a new quiz by launching the QuizActivity.
     * It creates an intent to start the quiz activity and finishes
     * the current activity to avoid stacking multiple instances
     * of the result activity.
     */
    private void startQuiz() {
        Intent intent = new Intent(ResultActivity.this, QuizActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * This method returns a message based on the provided score.
     * It calculates the percentage score, categorizes the score
     * into different ranges, and returns a message accordingly.
     *
     * @param score The total score achieved.
     * @return The corresponding message based on the total score.
     */
    private String getResultMessage(int score) {
        double percentage = (double) score / totalQuestions * 100;

        if (score == 0) {
            return "Try again. You scored 0%.";
        } else if (score == totalQuestions) {
            return "Congratulations! You scored 100%.";
        } else if (percentage > 0 && percentage <= 25) {
            return "You scored 0-25%.";
        } else if (percentage > 25 && percentage <= 50) {
            return "You scored 25-50%.";
        } else if (percentage > 50 && percentage <= 75) {
            return "You scored 50-75%.";
        } else if (percentage > 75 && percentage < 100) {
            return "You scored 75-99%.";
        } else {
            return "Invalid score. Try again.";
        }
    }
}

