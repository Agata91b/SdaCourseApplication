package agata91bcomgithub.sdacourseapplication.todolist;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.PersistableBundle;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import agata91bcomgithub.sdacourseapplication.R;

import static java.lang.System.out;

/**
 * Created by RENT on 2017-02-22.
 */

public class ToDoListActivity extends AppCompatActivity implements OnItemCheckStateChanged {

    private static final String ADAPTER_DATA ="adapter_data";
    private ToDoListAdapter toDoListAdapter;
    private String activityTitle;
    private ActionMode actionMode;

    @Override
    public void onItemCheckStateChanged(int checkedItemsCount) {
        if (checkedItemsCount > 0) {
            if (actionMode == null) {
                createActionMode();
            }

            actionMode.setTitle(getResources().getQuantityString(R.plurals.checked_items_plurals,
                    checkedItemsCount,checkedItemsCount));
        } else {
            if (actionMode != null) {
                actionMode.finish();
            }
        }
    }

    private void createActionMode() {
        actionMode = startSupportActionMode(new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                mode.getMenuInflater().inflate(R.menu.todolist_action_menu, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                if (item.getItemId() == R.id.action_delete) {
                    toDoListAdapter.deleteAllCheckedItems();
                    return true;
                }
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                toDoListAdapter.deselectAllItems();
                actionMode = null;
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
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
                editText.setText("");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.todolist_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(ADAPTER_DATA,
                new ArrayList<>(toDoListAdapter.getItems()));
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        toDoListAdapter
                .setItems(savedInstanceState.<ToDoListItem>getParcelableArrayList(ADAPTER_DATA));
    }
}
