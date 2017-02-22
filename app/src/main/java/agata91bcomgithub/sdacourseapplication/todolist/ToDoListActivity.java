package agata91bcomgithub.sdacourseapplication.todolist;

import android.os.Bundle;
import android.os.PersistableBundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import agata91bcomgithub.sdacourseapplication.R;

/**
 * Created by RENT on 2017-02-22.
 */

public class ToDoListActivity extends AppCompatActivity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.to_do_list);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycle_view);

    }
}
