package agata91bcomgithub.sdacourseapplication.todolist;

import android.os.Bundle;
import android.os.PersistableBundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import agata91bcomgithub.sdacourseapplication.R;

/**
 * Created by RENT on 2017-02-22.
 */

public class ToDoListActivity extends AppCompatActivity implements OnItemCheckStateChanged{

    private ToDoListAdapter toDoListAdapter;
    private String activityTitle;

    @Override
    public void onItemCheckStateChanged(int checkedItemsCount) {
        if(checkedItemsCount > 0) {
            getSupportActionBar().setTitle("Checked items " + checkedItemsCount);
        }else{
            getSupportActionBar().setTitle(activityTitle);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.to_do_list);
        activityTitle = getSupportActionBar().getTitle().toString();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        toDoListAdapter = new ToDoListAdapter();
        recyclerView.setAdapter((toDoListAdapter));
        toDoListAdapter.setCheckListener(this);

        final EditText editText = (EditText) findViewById(R.id.todo_edit_text);

        Button addButton = (Button) findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toDoListAdapter.addItem((editText.getText()).toString());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.todolist_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.delete_item){
            toDoListAdapter.deleteAllCheckedItems();
        }
        return super.onOptionsItemSelected(item);
    }


}
