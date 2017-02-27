package agata91bcomgithub.sdacourseapplication.quiz;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.xml.datatype.Duration;

import agata91bcomgithub.sdacourseapplication.R;

/**
 * Created by RENT on 2017-02-25.
 */

public class QuizActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_activity);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        ValueAnimator objectAnimator = ObjectAnimator.ofInt(0, 100);

        objectAnimator.setDuration(1000);
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                progressBar.setProgress((Integer) animation.getAnimatedValue());
            }
        });

        objectAnimator.start();


        String json = null;

            try {
                json = loadQuizJson();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

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
