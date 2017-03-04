package agata91bcomgithub.sdacourseapplication.quiz;

import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.xml.datatype.Duration;

import agata91bcomgithub.sdacourseapplication.R;

/**
 * Created by RENT on 2017-02-25.
 */

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String INDEX_KEY = "index_key";
    private int currentQuestionIndex;
    boolean wasViewCliked;
    private QuizContainer quizContainer;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_activity);

        currentQuestionIndex = getIntent().getIntExtra(INDEX_KEY, 0);

        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        ValueAnimator objectAnimator = ObjectAnimator.ofInt(0, 100);

        objectAnimator.setDuration(2000);
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                progressBar.setProgress((Integer) animation.getAnimatedValue());


            }
        });


        progressBar.setProgress(0);
        objectAnimator.start();


        String json = null;

        try {
            json = loadQuizJson();
            quizContainer = new Gson().fromJson(json, QuizContainer.class);

            TextView textView = (TextView) findViewById(R.id.question_view);
            QuizQuestion quizQuestion = quizContainer.getQuestions().get(currentQuestionIndex);
            textView.setText(quizQuestion.getQuestion());

            Button button1 = (Button) findViewById(R.id.question1_button);
            Button button2 = (Button) findViewById(R.id.question2_button);
            Button button3 = (Button) findViewById(R.id.question3_button);
            Button button4 = (Button) findViewById(R.id.question4_button);


            SingleAnswer firstAnswer = quizQuestion.getAnswers().get(0);
            button1.setText(firstAnswer.getText());
            button1.setTag(firstAnswer.isCorrect());


            SingleAnswer secondAnswer = quizQuestion.getAnswers().get(1);
            button2.setText(secondAnswer.getText());
            button2.setTag(secondAnswer.isCorrect());

            SingleAnswer thirdAnswer = quizQuestion.getAnswers().get(2);
            button3.setText(thirdAnswer.getText());
            button3.setTag(thirdAnswer.isCorrect());

            SingleAnswer fourthAnswer = quizQuestion.getAnswers().get(3);
            button4.setText(fourthAnswer.getText());
            button4.setTag(fourthAnswer.isCorrect());

            button1.setOnClickListener(this);
            button2.setOnClickListener(this);
            button3.setOnClickListener(this);
            button4.setOnClickListener(this);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {

        if (!wasViewCliked) {
            if ((Boolean) v.getTag()) {
                Toast.makeText(v.getContext(), "Odp poprawna", Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(v.getContext(), "Zła odp", Toast.LENGTH_LONG).show();
            }
            v.postDelayed(new Runnable() {
                @Override
                public void run() {

                    if (currentQuestionIndex < quizContainer.getQuestionsCount() -1) {
                        Intent intent = new Intent(QuizActivity.this, QuizActivity.class);
                        intent.putExtra(INDEX_KEY, ++currentQuestionIndex);
                        startActivity(intent);
                    } else {

                        Toast.makeText(QuizActivity.this, "Quiz ends", Toast.LENGTH_LONG).show();

                    }
                }
            }, 1000);
            wasViewCliked = true;
        }


    }


//        MediaPlayer mediaPlayer = MediaPlayer.create()


    private String loadQuizJson() throws Exception {
        StringBuilder buf = new StringBuilder();
        InputStream json = null;

        json = getAssets().open("quiz_data.json");
        BufferedReader in = new BufferedReader(new InputStreamReader(json, "UTF-8"));
        String str;
        while ((str = in.readLine()) != null) {
            buf.append(str);

        }
        in.close();
        return buf.toString();


    }


}
