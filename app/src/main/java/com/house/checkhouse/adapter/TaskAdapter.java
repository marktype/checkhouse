package com.house.checkhouse.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.house.checkhouse.R;
import com.house.checkhouse.customer.MyListView;
import com.house.checkhouse.model.message.TaskInfo;

import java.util.ArrayList;

/**
 * Created by 欢大哥 on 2016/10/26.
 */
public class TaskAdapter extends BaseAdapter {
    private ArrayList<TaskInfo> list;
    private Context context;
    public TaskAdapter(Context context){
        this.context = context;
    }

    public void setData(ArrayList<TaskInfo> list){
        this.list = list;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.task_list_layout,null);
            viewHolder = new ViewHolder();
            viewHolder.listView = (MyListView) view.findViewById(R.id.task_item_list);
            viewHolder.time = (TextView) view.findViewById(R.id.data_time);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        TaskInfo info = (TaskInfo) getItem(i);
        TaskItemAdapter adapter = new TaskItemAdapter(context);
        adapter.setData(info.getListTask());
        viewHolder.listView.setAdapter(adapter);

        viewHolder.time.setText(info.getTime());


        return view;
    }

    private class ViewHolder{
        MyListView listView;
        TextView time;
    }
}
