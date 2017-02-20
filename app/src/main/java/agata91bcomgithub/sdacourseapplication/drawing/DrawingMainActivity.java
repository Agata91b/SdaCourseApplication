package agata91bcomgithub.sdacourseapplication.drawing;

import android.app.DownloadManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import agata91bcomgithub.sdacourseapplication.R;

public class DrawingMainActivity extends AppCompatActivity {

    private SimpleDrawingView simpleDrawingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawing_activity_main);
        simpleDrawingView = (SimpleDrawingView) findViewById(R.id.drawing_view);

//        Button clearButton = (Button) findViewById(R.id.clear_button);
//        clearButton.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                simpleDrawingView.clear();
//            }
//        });

        Button blueButton = (Button) findViewById(R.id.blue_button);
        Button redButton = (Button) findViewById(R.id.red_button);

        blueButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                simpleDrawingView.setCurrentColor(ContextCompat.getColor(DrawingMainActivity.this,R.color.blue));

            }
        });

        redButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                simpleDrawingView.setCurrentColor(ContextCompat.getColor(DrawingMainActivity.this,R.color.red));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.clear){
            simpleDrawingView.clear();
        }
        return super.onOptionsItemSelected(item);
    }
}
