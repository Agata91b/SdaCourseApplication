package agata91bcomgithub.sdacourseapplication.gallery;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.io.File;

import agata91bcomgithub.sdacourseapplication.R;
import agata91bcomgithub.sdacourseapplication.drawing.DrawingMainActivity;

/**
 * Created by RENT on 2017-02-21.
 */

public class GalleryActivity extends AppCompatActivity{

    private ViewPager viewPager;
    private DrawingPagerAdapter pagerAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_activity);
        viewPager = (ViewPager) findViewById(R.id.view_pager);


        File dir = getExternalFilesDir(DrawingMainActivity.DRAWING_GALLERY_DIR);
        File[] files = dir.listFiles();
        pagerAdapter = new DrawingPagerAdapter(files);
        viewPager.setAdapter(pagerAdapter);



//        File file = files[0];
//        try  {
//            FileInputStream fileInputStream = new FileInputStream(file);
//            BitmapFactory.decodeStream(fileInputStream);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.gallery_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.delete){
           pagerAdapter.deleteItem(viewPager.getCurrentItem());
        }
        return super.onOptionsItemSelected(item);
    }
}
