package com.house.checkhouse.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.house.checkhouse.R;
import com.house.checkhouse.activity.ProblemDetialActivity;
import com.house.checkhouse.customer.MyListView;
import com.house.checkhouse.model.message.ProblemInfo;

import java.util.ArrayList;

/**
 * Created by 欢大哥 on 2016/10/26.
 */
public class ProblemAdapter extends BaseAdapter {
    private ArrayList<ProblemInfo> list;
    private Context context;
    private Boolean allShowCheckBoxCallBack;
    private Boolean allCheckedCallBack;
    public ProblemAdapter(Context context){
        this.context = context;
    }

    public void setData(ArrayList<ProblemInfo> list){
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
        ProblemInfo info = (ProblemInfo) getItem(i);
        ProblemItemAdapter adapter = new ProblemItemAdapter(context);
        adapter.setOnAllShowCallBack(allShowCheckBoxCallBack);
        adapter.setOnAllCheckCallBack(allCheckedCallBack);
        adapter.setData(info.getListTask());
        viewHolder.listView.setAdapter(adapter);

        viewHolder.time.setText(info.getTime());

        viewHolder.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(context,ProblemDetialActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });


        return view;
    }

    private class ViewHolder{
        MyListView listView;
        TextView time;
    }

}
