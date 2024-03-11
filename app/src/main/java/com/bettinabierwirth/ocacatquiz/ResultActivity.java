package com.bettinabierwirth.ocacatquiz;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Diese Aktivität zeigt das Ergebnis des Quizes an und bietet die Möglichkeit, das Quiz erneut zu starten.
 */
public class ResultActivity extends AppCompatActivity {
    private static final int totalQuestions = 8;

    /**
     * @param savedInstanceState Wenn die Aktivität nach einer vorherigen Beendigung erneut initialisiert wird,
     * enthält dieser Bundle die zuletzt bereitgestellten Daten in {@link #onSaveInstanceState}.
     * <b><i>Hinweis: Andernfalls ist es null.</i></b>
     */
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Ergebnis aus dem Intent abrufen

        int score = getIntent().getIntExtra("TOTAL_SCORE", 0);
        //Das Ergebnis basierend auf dem Score anzeigen

        TextView resultMessage = findViewById(R.id.text_view_result_message);
        resultMessage.setText(getResultMessage(score));

        TextView textViewResult = findViewById(R.id.text_view_result);
        Button buttonStartQuiz = findViewById(R.id.button_start_quiz);

        // Setzt den OnClickListener für den Start-Quiz-Button

        buttonStartQuiz.setOnClickListener(v -> startQuiz());

         // Zeigt die Gesamtpunktzahl an

        textViewResult.setText("Your Score: " + score);
    }

    /**
     * Startet ein neues Quiz durch den Aufruf der QuizActivity.
     */
    private void startQuiz() {
        Intent intent = new Intent(ResultActivity.this, QuizActivity.class);
        startActivity(intent);

         // Beende die aktuelle Aktivität beim Starten eines neuen Quiz

        finish();
    }

    /**
     * Gibt eine Nachricht basierend auf dem übergebenen Score zurück.
     *
     * @param score Die erzielte Gesamtpunktzahl
     * @return Die entsprechende Nachricht basierend auf der Gesamtpunktzahl.
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

