package agata91bcomgithub.sdacourseapplication.todolist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import agata91bcomgithub.sdacourseapplication.R;

/**
 * Created by RENT on 2017-02-22.
 */

public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.MyViewHolder>{

    private List<ToDoListItem> items =  new ArrayList();
    private OnItemCheckStateChanged checkListener;

    public ToDoListAdapter(List<ToDoListItem> items) {
        this.items = items;
    }

    public void setCheckListener(OnItemCheckStateChanged checkListener) {
        this.checkListener = checkListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder,  int position) {

        final  ToDoListItem listItem = items.get(position);
        holder.textView.setText(items.get(position).getText());
        holder.checkBox.setOnCheckedChangeListener(null);
        holder.checkBox.setChecked(listItem.isChecked());
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                listItem.setChecked(isChecked);
                if(checkListener != null){
                    checkListener.onItemCheckStateChanged(getCheckedItemsCount());
                }
            }
        });


    }

    private int getCheckedItemsCount(){
        int count = 0;
        for(ToDoListItem item  : items){
            if(item.isChecked()){
                count++;
            }
        }
        return count;
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem (String item){
        items.add(new ToDoListItem(item));
        notifyDataSetChanged();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        CheckBox checkBox;

        public MyViewHolder(View itemView){
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.item_text);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkbox);
        }

    }
    public void deselectAllItems(){
        for (ToDoListItem item:items) {
            item.setChecked(false);
        }
        notifyDataSetChanged();
    }

     public void deleteAllCheckedItems() {
        List<ToDoListItem> newListItems = new ArrayList<>();

         for (int i = 0 ; i < items.size(); i++){

             if(!items.get(i).isChecked()){
                 newListItems.add(items.get(i));
             }
         }
         items = newListItems;

         notifyDataSetChanged();
         if(checkListener != null){
             checkListener.onItemCheckStateChanged(getCheckedItemsCount());
         }

     }

    public List<ToDoListItem> getItems() {
        return items;
    }


}
