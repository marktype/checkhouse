package com.house.checkhouse.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.house.checkhouse.R;
import com.house.checkhouse.model.message.ProblemItem;
import com.house.checkhouse.model.message.TaskItem;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by 欢大哥 on 2016/10/26.
 */
public class ProblemItemAdapter extends BaseAdapter {
    private Context context;
    private Boolean isAllSelect = true;
    private ArrayList<ProblemItem> list;
    private Boolean allShowCheckBoxCallBack;
    private Boolean allCheckedCallBack;
    private HashMap<Integer,CheckBox> map = new HashMap<>();
    public ProblemItemAdapter(Context context){
        this.context = context;
    }

    public void setData(ArrayList<ProblemItem> list){
        this.list = list;
        notifyDataSetChanged();
    }

    public void setOnAllShowCallBack(Boolean allShowCheckBoxCallBack){
        this.allShowCheckBoxCallBack = allShowCheckBoxCallBack;
    }

    public void setOnAllCheckCallBack(Boolean allCheckedCallBack){
       this.allCheckedCallBack = allCheckedCallBack;
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
            viewHolder.checkBox = (CheckBox) view.findViewById(R.id.check_problem);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        TaskItem item = (TaskItem) getItem(i);
        viewHolder.title.setText(item.getTitle());
        viewHolder.content.setText(item.getContent());
        if (allShowCheckBoxCallBack){
            viewHolder.checkBox.setVisibility(View.VISIBLE);
        }else {
            viewHolder.checkBox.setVisibility(View.GONE);
        }
        viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!b){
                    isAllSelect = false;
                }
            }
        });

        if (allCheckedCallBack&&isAllSelect){
            viewHolder.checkBox.setChecked(true);
        }





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
        CheckBox checkBox;
    }

    /**
     * 是否选中所有
     * @param map
     * @return
     */
    public Boolean getIsAllSelect(HashMap<Integer,CheckBox> map){
       for (Integer i:map.keySet()){
           if (!map.get(i).isChecked()){
               return false;
           }
       }
        return true;
    }
}
