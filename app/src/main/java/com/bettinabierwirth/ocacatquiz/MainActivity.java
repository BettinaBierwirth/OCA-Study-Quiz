package com.bettinabierwirth.ocacatquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/**
 * The main activity of the application. This is the first
 * activity screen the user sees when starting the application.
 * It displays a button to start the quiz.
 */

public class MainActivity extends AppCompatActivity {
    /**
     * Called when the activity is starting. This is where most initialization should go.
     * @param savedInstanceState If the activity is being re-initialized after previously
     *                            being shut down then this Bundle contains the data it most
     *                            recently supplied in onSaveInstanceState(Bundle). Otherwise,
     *                            it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the reference to the button_start_quiz from the layout
        Button buttonStartQuiz = findViewById(R.id.button_start_quiz);
        // Set an OnClickListener to the buttonStartQuiz
        buttonStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Call the method startQuiz() when the button is clicked
                startQuiz();
            }
        });
    }

    /**
     * Starts a new quiz by launching the QuizActivity.
     */
    private void startQuiz() {
        // Create a new Intent to start the QuizActivity
        Intent intent = new Intent(MainActivity.this, QuizActivity.class);
        // Start the QuizActivity
        startActivity(intent);
    }
}