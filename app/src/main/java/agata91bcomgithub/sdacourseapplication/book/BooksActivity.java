package agata91bcomgithub.sdacourseapplication.book;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import agata91bcomgithub.sdacourseapplication.R;

/**
 * Created by RENT on 2017-03-02.
 */

public class BooksActivity extends AppCompatActivity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.books_activity);

        ViewPager viewPager = (ViewPager) findViewById(R.id.book_view_pager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        Book effectiveJava = new Book(1,R.drawable.effective_java, "Effective Java");
        effectiveJava.setRead(preferences.getBoolean(String.valueOf(effectiveJava.getId()),false));
        Book headfirstDesign = new Book (2,R.drawable.headfirstdesignpatterns,
                "Head First Design Patterns");
        headfirstDesign.setRead(preferences.getBoolean(String.valueOf(headfirstDesign.getId())
                ,false));
        Book cleanCode = new Book (3, R.drawable.cleancode2, "Clean Code");
        cleanCode.setRead(preferences.getBoolean(String.valueOf(cleanCode.getId()), false));
        List<Book> list = Arrays.asList(effectiveJava,headfirstDesign,cleanCode  );

        BooksPagerAdapter adapter =
                new BooksPagerAdapter(list, preferences);
        viewPager.setAdapter(adapter);

    }
}
