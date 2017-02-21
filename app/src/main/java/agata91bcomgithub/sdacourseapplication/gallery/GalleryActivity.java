package agata91bcomgithub.sdacourseapplication.gallery;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import agata91bcomgithub.sdacourseapplication.R;
import agata91bcomgithub.sdacourseapplication.drawing.DrawingMainActivity;

/**
 * Created by RENT on 2017-02-21.
 */

public class GalleryActivity extends AppCompatActivity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_activity);
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);



        File dir = getExternalFilesDir(DrawingMainActivity.DRAWING_GALLERY_DIR);
        File[] files = dir.listFiles();
        viewPager.setAdapter(new DrawingPagerAdapter(files));

//        File file = files[0];
//        try  {
//            FileInputStream fileInputStream = new FileInputStream(file);
//            BitmapFactory.decodeStream(fileInputStream);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
    }
}
