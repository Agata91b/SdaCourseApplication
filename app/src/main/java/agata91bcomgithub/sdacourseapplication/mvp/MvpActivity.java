package agata91bcomgithub.sdacourseapplication.mvp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import agata91bcomgithub.sdacourseapplication.R;
import nucleus.view.NucleusAppCompatActivity;

public class MvpActivity extends NucleusAppCompatActivity<MvpPresenter> {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);

        final TextView resultTextView = (TextView) findViewById(R.id.result_text_view);
        Button  startButton = (Button) findViewById(R.id.start_task_button);
//        startButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//        });
    }

}
