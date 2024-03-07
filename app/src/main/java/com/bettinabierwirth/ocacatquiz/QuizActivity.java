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
/*
 * Diese Aktivität stellt das Quiz dar und verwaltet den Ablauf des Quizspiels.
 */
public class QuizActivity extends AppCompatActivity {
    /*
     * Initialisierung der Variablen
     */
    /*
     * Setzen des Countdowns pro Zeile auf 90 Sekunden (wie für den OCA Test vorgeschlagen.
     * */
    private static final long COUNTDOWN_IN_MILLIS = 90000;
    /*
     * Maximale Anzahl der Fragen pro Quiz.
     */
    private static final int MAX_QUESTIONS = 8;
    private TextView textViewQuestion;

    private TextView textViewQuestionCount;
    private TextView textViewCountDown;
    private RadioGroup rbGroup;
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
    /*
     * Diese Methode wird aufgerufen, wenn die Aktivität erstellt wird. Hier wird die Initialisierung
     * der UI-Elemente, Datenbankzugriffe und die Konfiguration des Quiz-Spiels durchgeführt.
     *
     * @param savedInstanceState Ein Bundle-Objekt, das den Zustand der Aktivität enthält.
     */
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        /*
          Initialisierung der UI-Elemente durch Zuweisung der Views aus dem Layout zu den entsprechenden Variablen.
         */

        textViewQuestion = findViewById(R.id.text_view_question);
        textViewQuestionCount = findViewById(R.id.text_view_question_count);
        textViewCountDown = findViewById(R.id.text_view_countdown);
        rbGroup = findViewById(R.id.radio_group);
        rb1 = findViewById(R.id.radio_button1);
        rb2 = findViewById(R.id.radio_button2);
        rb3 = findViewById(R.id.radio_button3);
        rb4 = findViewById(R.id.radio_button4);
        rb5 = findViewById(R.id.radio_button5);
        rb6 = findViewById(R.id.radio_button6);
        buttonConfirmNext = findViewById(R.id.button_confirm_next);

        /*
         * Speichern der Standardtextfarben der Countdown-Anzeige sowie der CheckBoxen.
         */

        textColorDefaultCd = textViewCountDown.getTextColors();

        textColorDefaultRb = rb1.getTextColors();
        textColorDefaultCb = rb1.getTextColors();

        /*
         * Initialisierung eines QuizDbHelper-Objekts, um auf die Datenbank zuzugreifen.
         */

        QuizDbHelper dbHelper = new QuizDbHelper(this);

        /*
         * Laden der Fragen aus der Datenbank und Speichern in einer Liste und das Durchmischen der Fragen.
         */

        questionList = dbHelper.getAllQuestions();
        questionCountTotal = questionList.size();
        Collections.shuffle(questionList);

        /*
         * Initialisierung des CountDownTimers ohne Startzeit.
         */
        countDownTimer = new CountDownTimer(COUNTDOWN_IN_MILLIS, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                /*
                 * Aktualisierung der verbleibenden Zeit in der Countdown-Anzeige.
                 */
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {

                /*
                 * Wenn der Countdown abgelaufen ist, wird die Antwort überprüft.
                 */

                timeLeftInMillis = 0;
                updateCountDownText();
                checkAnswer();
            }
        };

        /*
         * Anzeigen der nächsten Frage, um das Quiz zu starten.
         */

        showNextQuestion();

        /*
          Hinzufügen eines Click-Listeners für den "Bestätigen"/"Nächste"-Button.
         */

        buttonConfirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answered) {

                    /*
                     * Wenn noch keine Antwort ausgewählt wurde, wird die ausgewählte Antwort überprüft.
                     */

                    if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked() || rb5.isChecked() || rb6.isChecked()) {
                        checkAnswer();

                        /*
                         * Der Countdown-Timer wird gestoppt, nachdem der Benutzer die Antwort bestätigt hat.
                         */

                        countDownTimer.cancel();

                        /*
                         * Aufforderung, eine Antwort auszuwählen, falls keine ausgewählt wurde.
                         */

                    } else {
                        Toast.makeText(QuizActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                    }
                } else {

                    /*
                     * Anzeigen der nächsten Frage und Starten des Countdown-Timers für die nächste Frage.
                     */

                    showNextQuestion();
                    startCountDown();
                }
            }
        });

    }

    private void showNextQuestion() {

        /*
         * Aktivieren aller RadioButtons und CheckBoxen für die neue Frage.
         */

        rb1.setEnabled(true);
        rb2.setEnabled(true);
        rb3.setEnabled(true);
        rb4.setEnabled(true);
        rb5.setEnabled(true);
        rb6.setEnabled(true);

        /*
         * Zurücksetzen der Textfarben der RadioButtons auf die Standardfarbe.
         */

        rb1.setTextColor(textColorDefaultRb);
        rb2.setTextColor(textColorDefaultRb);
        rb3.setTextColor(textColorDefaultRb);
        rb4.setTextColor(textColorDefaultRb);
        rb5.setTextColor(textColorDefaultRb);
        rb6.setTextColor(textColorDefaultRb);

        /*
          Zurücksetzen der Checkboxen auf den nicht ausgewählten Zustand.
         */

        rb1.setChecked(false);
        rb2.setChecked(false);
        rb3.setChecked(false);
        rb4.setChecked(false);
        rb5.setChecked(false);
        rb6.setChecked(false);

        /*
          Überprüfung, ob es noch weitere Fragen gibt, die gestellt werden können.
         */

        if (questionCounter < Math.min(MAX_QUESTIONS, questionCountTotal)){

            /*
              Abrufen der nächsten Frage aus der Fragenliste.
             */

            currentQuestion = questionList.get(questionCounter);

            /*
              Aktualisierung der UI mit den Daten der aktuellen Frage.
             */

            textViewQuestion.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());
            rb4.setText(currentQuestion.getOption4());
            rb5.setText(currentQuestion.getOption5());
            rb6.setText(currentQuestion.getOption6());

            /*
             * Inkrementieren des Fragezählers und Aktualisierung der Anzeige.
             */

            questionCounter++;
            textViewQuestionCount.setText("Question: " + questionCounter + "/" + Math.min(MAX_QUESTIONS, questionCountTotal));

            /*
              Zurücksetzen des Antwortstatus und Ändern der Button-Beschriftung.
             */

            answered = false;
            buttonConfirmNext.setText("Confirm");

            /*
              Stopp des vorherigen Countdown-Timers.
             */

            countDownTimer.cancel();

            /*
              Zurücksetzen des Timers auf 90 Sekunden für die neue Frage.
             */

            timeLeftInMillis = COUNTDOWN_IN_MILLIS;
            updateCountDownText();

            /*
              Starten des Countdown-Timers für die neue Frage.
             */

            startCountDown();

            /*
              Setzt die Scroll-View zurück nach oben.
             */
            ScrollView scrollView = findViewById(R.id.scroll_view);
            scrollView.smoothScrollTo(0, 0);

        } else {

            /*
              Wenn alle Fragen beantwortet wurden, wird das Quiz beendet.
             */

            finishQuiz();
        }
    }

    private void startCountDown() {

        /*
         * Started den Countdown Timer.
         */

        countDownTimer.start();
    }
    /*
     * Diese Methode aktualisiert die Anzeige des Countdowns basierend auf der verbleibenden Zeit.
     * Sie zeigt die Minuten und Sekunden im richtigen Format an und ändert die Textfarbe
     * basierend auf der verbleibenden Zeit.
     */
    private void updateCountDownText() {

        /*
         *  Berechnung der verbleibenden Minuten und Sekunden aus der verbleibenden Zeit in Millisekunden.
         */

        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        /*
         * Formatierung der verbleibenden Zeit als String im HH:MM-Format.
         */

        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        /*
         * Setzen des formatierten Zeitstrings in die Countdown-Anzeige.
         */

        textViewCountDown.setText(timeFormatted);

        if (timeLeftInMillis < 10000) {

            /*
             * Ändern der Textfarbe auf Rot, wenn die verbleibende Zeit unter 10 Sekunden liegt, sonst auf die Standardfarbe.
             */

            textViewCountDown.setTextColor(Color.RED);
        } else {
            textViewCountDown.setTextColor(textColorDefaultCd);
        }
    }

    /*
     * Diese Methode überprüft die ausgewählten Antworten des Benutzers und zeigt die Lösung an.
     * Der Antwortstatus wird auf "beantwortet" gesetzt. Falls die Frage oder die Antwortmöglichkeiten nicht verfügbar sind,
     * wird eine Toast-Nachricht mit einem Fehlerhinweis angezeigt.
     */
    private void checkAnswer() {

        /*
         * Setzen des Antwortstatus auf "beantwortet".
         */

        answered = true;

        /*
          Überprüfen, ob currentQuestion und ihre Antwortnummern nicht null sind
         */

        if (currentQuestion != null && currentQuestion.getAnswerNr() != null) {

            /*
              Initialisieren der Liste für ausgewählte Antworten.
             */

            selectedAnswers = new ArrayList<>();

            /*
              Überprüfen und Hinzufügen der ausgewählten Antworten zu der Liste.
             */

            if (rb1.isChecked()) selectedAnswers.add(1);
            if (rb2.isChecked()) selectedAnswers.add(2);
            if (rb3.isChecked()) selectedAnswers.add(3);
            if (rb4.isChecked()) selectedAnswers.add(4);
            if (rb5.isChecked()) selectedAnswers.add(5);
            if (rb6.isChecked()) selectedAnswers.add(6);

            /*
              Anzeigen der Lösung.
             */

            showSolution();

            /*
             * Aktualisieren der Anzahl der korrekten Fragen basierend auf der Überprüfung der Antworten.
             */
            if (currentQuestion.getAnswerNr().size() == selectedAnswers.size() &&
                    currentQuestion.getAnswerNr().containsAll(selectedAnswers) &&
                    selectedAnswers.containsAll(currentQuestion.getAnswerNr())) {
                correctQuestions++;
            }


        } else {
            /*
             * Behandeln des Falls, wenn currentQuestion oder ihre Antwortnummern null sind.
             */
            Toast.makeText(QuizActivity.this, "Error: Question or answer options not available.", Toast.LENGTH_SHORT).show();
        }
    }

    /*
     * Diese Methode zeigt die Lösung der aktuellen Frage an. Sie deaktiviert die Checkboxen,
     * um weitere Interaktionen zu verhindern, setzt die Standardtextfarben und hebt die korrekten
     * Antworten in grüner Farbe hervor.
     */
    private void showSolution() {

        /*
         * Deaktivieren der Checkboxen, um weitere Interaktionen zu verhindern.
         */


        rb1.setEnabled(false);
        rb2.setEnabled(false);
        rb3.setEnabled(false);
        rb4.setEnabled(false);
        rb5.setEnabled(false);
        rb6.setEnabled(false);

        /*
         * Setzen der Standardtextfarben für alle Checkboxen.
         */
        rb1.setTextColor(textColorDefaultCb);
        rb2.setTextColor(textColorDefaultCb);
        rb3.setTextColor(textColorDefaultCb);
        rb4.setTextColor(textColorDefaultCb);
        rb5.setTextColor(textColorDefaultCb);
        rb6.setTextColor(textColorDefaultCb);

        /*
         * Hervorheben der korrekten Antworten in grün.
         */
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

        /*
         * Hervorheben der ungültigen Antworten in Rot.
         */
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


        /*
         * Aktualisieren der Anzahl der korrekten Fragen basierend auf der Prüfung der Antworten.
         */

        if (questionCounter < questionCountTotal) {
            buttonConfirmNext.setText(R.string.next);
        } else {
            buttonConfirmNext.setText(R.string.finish);
        }
    }

    /*
     * Diese Methode beendet das Quiz und leitet die Benutzerergebnisse an die ResultActivity weiter.
     * Die Gesamtpunktzahl entspricht der Anzahl der korrekten Fragen. Optional können die Gesamtpunktzahl
     * und die Anzahl der korrekten Fragen an die ResultActivity übergeben werden. Nach der Weiterleitung
     * wird die aktuelle Quiz-Aktivität beendet.
     */
    private void finishQuiz() {

        /*
         * Berechnen der Gesamtpunktzahl als Anzahl der korrekten Fragen.
         */

        int totalScore = correctQuestions;

        /*
         * Senden der Gesamtpunktzahl und der Anzahl der korrekten Fragen an die ResultActivity.
         */
        Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
        intent.putExtra("TOTAL_SCORE", totalScore);
        intent.putExtra("CORRECT_QUESTIONS", correctQuestions);
        startActivity(intent);

        /*
         * Beendet das Quiz.
         */
        finish();
    }

}
