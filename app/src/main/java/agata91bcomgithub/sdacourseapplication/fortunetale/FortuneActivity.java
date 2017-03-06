package agata91bcomgithub.sdacourseapplication.fortunetale;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Color;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.squareup.seismic.ShakeDetector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import agata91bcomgithub.sdacourseapplication.R;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class FortuneActivity extends AppCompatActivity implements ShakeDetector.Listener {

    private FrameLayout layer;
    private Random random = new Random();
    private List<String> list = Arrays.asList("Eh, ogarnij się!", "Dziś jest nowy dzień!",
            "Będzie dobrze!", "Uśmiechnij się!");
    private Animator hidingAnimator;
    private Animator showingAnimator;

    @Override
    public void hearShake() {

        animateCircularReveal(random.nextInt(layer.getWidth()),
                random.nextInt(layer.getHeight()), layer);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fortune);


        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        ShakeDetector sd = new ShakeDetector(this);
        sd.start(sensorManager);

        layer = (FrameLayout) findViewById(R.id.fortune_container);
        final FrameLayout parentLayout = (FrameLayout) findViewById(R.id.parent_layout);

        parentLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    animateCircularReveal((int) event.getY(), (int) event.getY(), layer);
                }

                return true;
            }
        });


//        parentLayout.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//                if (layer.getVisibility() == View.VISIBLE) {
//                    Animator circularReveal = ViewAnimationUtils.createCircularReveal(layer, 0, 0,
//                            layer.getHeight(), 0);
//                    circularReveal.addListener(new AnimatorListenerAdapter() {
//                        @Override
//                        public void onAnimationEnd(Animator animation) {
//                            layer.setVisibility(View.INVISIBLE);
//                        }
//                    });
//                    circularReveal.start();
//                } else {
//                    Animator circularReveal = ViewAnimationUtils.createCircularReveal(layer, 0, 0, 0,
//                            layer.getHeight());
//                    layer.setVisibility(View.VISIBLE);
//                    circularReveal.start();
//                }
//            }
//        });

    }


    private void animateCircularReveal(int x, int y, final FrameLayout layer) {
        if((hidingAnimator != null && hidingAnimator.isRunning())||
                (showingAnimator != null && showingAnimator.isRunning())){
            return;
        }
        if (layer.getVisibility() == View.VISIBLE) {
            hidingAnimator = ViewAnimationUtils.createCircularReveal(layer,
                    x, y, layer.getHeight(), 0);
            hidingAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    layer.setVisibility(View.INVISIBLE);
                }
            });
            hidingAnimator.start();
        } else {
            int color = Color.argb(255, random.nextInt(255), random.nextInt(255),
                    random.nextInt(255));
            layer.setBackgroundColor(color);
            TextView textView = (TextView) findViewById(R.id.omen_view);
            textView.setText(list.get(random.nextInt(list.size())));

            showingAnimator = ViewAnimationUtils.createCircularReveal(layer,
                    x, y, 0, layer.getHeight());
            layer.setVisibility(View.VISIBLE);
            showingAnimator.start();
        }
    }


}
