package com.house.checkhouse.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.house.checkhouse.R;
import com.house.checkhouse.model.message.ProblemItem;
import com.house.checkhouse.model.message.TaskItem;

import java.util.ArrayList;

/**
 * Created by 欢大哥 on 2016/10/27.
 */
public class ChangeItemAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ProblemItem> list;
    public ChangeItemAdapter(Context context){
        this.context = context;
    }

    public void setData(ArrayList<ProblemItem> list){
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        if (view == null){
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.problem_item_layout,null);
            viewHolder.title = (TextView) view.findViewById(R.id.title_task);
            viewHolder.content = (TextView) view.findViewById(R.id.content_task);
            viewHolder.type = (TextView) view.findViewById(R.id.problem_type);
            viewHolder.time = (TextView) view.findViewById(R.id.time_txt);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        TaskItem item = (TaskItem) getItem(i);
        viewHolder.title.setText(item.getTitle());
        viewHolder.content.setText(item.getContent());
        if (i == getCount()-1){
            viewHolder.time.setVisibility(View.VISIBLE);
        }else {
            viewHolder.time.setVisibility(View.GONE);
        }
        return view;
    }

    private class ViewHolder{
        TextView title;
        TextView content;
        TextView type;
        TextView time;
    }
}
