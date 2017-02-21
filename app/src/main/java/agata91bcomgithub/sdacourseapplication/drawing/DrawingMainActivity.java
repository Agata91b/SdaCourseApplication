package agata91bcomgithub.sdacourseapplication.drawing;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import agata91bcomgithub.sdacourseapplication.R;
import agata91bcomgithub.sdacourseapplication.gallery.GalleryActivity;

public class DrawingMainActivity extends AppCompatActivity {

    public static final String DRAWING_GALLERY_DIR = "drawing_gallery";
    private SimpleDrawingView simpleDrawingView;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


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
                simpleDrawingView.setCurrentColor(ContextCompat.getColor(DrawingMainActivity.this, R.color.blue));

            }
        });

        redButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                simpleDrawingView.setCurrentColor(ContextCompat.getColor(DrawingMainActivity.this, R.color.red));
            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.clear) {
            simpleDrawingView.clear();
        } else if
                (item.getItemId() == R.id.save) {
            saveDrawingToFile();
        }else if(item.getItemId() == R.id.drawing_gallery){
            Intent intent = new Intent(this, GalleryActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


    private void saveDrawingToFile() {

        File drawingFile = new File(getDrawingGalleryDrawing(),createFileName());
        try{
            FileOutputStream fileOutputStream = new FileOutputStream(drawingFile);
            Bitmap bitmap = convertViewToBitmap(simpleDrawingView);
            bitmap.compress(Bitmap.CompressFormat.PNG,100, fileOutputStream);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }

    }

    private String createFileName() {
        String timeStamp = new SimpleDateFormat("yyyyMMmm_HHmmss").format(new Date());
        return "my_drawing" + timeStamp + ".png";
    }

    private File getDrawingGalleryDrawing() {
        return getExternalFilesDir(DRAWING_GALLERY_DIR);
    }

    private Bitmap convertViewToBitmap(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.WHITE);
        view.draw(canvas);
        return bitmap;
    }


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("DrawingMain Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
