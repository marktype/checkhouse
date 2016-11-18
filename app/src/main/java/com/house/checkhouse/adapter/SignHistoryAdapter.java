package com.house.checkhouse.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.house.checkhouse.R;
import com.house.checkhouse.model.message.SignHistoryInfo;

import java.util.ArrayList;

/**
 * Created by 欢大哥 on 2016/11/15.
 */
public class SignHistoryAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<SignHistoryInfo> list;
    public SignHistoryAdapter(Context context){
        this.context = context;
    }

    public void setData(ArrayList<SignHistoryInfo> list){
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
            view = LayoutInflater.from(context).inflate(R.layout.sign_item_layout,null);
            viewHolder = new ViewHolder();
            viewHolder.data = (TextView) view.findViewById(R.id.time_num);
            viewHolder.week = (TextView) view.findViewById(R.id.week_txt);
            viewHolder.status = (TextView) view.findViewById(R.id.status_txt);
            viewHolder.startTime = (TextView) view.findViewById(R.id.start_time);
            viewHolder.endTime = (TextView) view.findViewById(R.id.end_time);
            viewHolder.startSign = (TextView) view.findViewById(R.id.start_sign);
            viewHolder.endSign = (TextView) view.findViewById(R.id.end_sign);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        SignHistoryInfo info = (SignHistoryInfo) getItem(i);
        viewHolder.data.setText(info.getData());
        viewHolder.week.setText(info.getWeek());
        viewHolder.status.setText(info.getStatus());
        viewHolder.startTime.setText(info.getStartTime());
        viewHolder.endTime.setText(info.getEndTime());
        viewHolder.startSign.setText(info.getStartSign());
        viewHolder.endSign.setText(info.getEndSign());

        return view;
    }

    private class ViewHolder{
        TextView data;
        TextView week;
        TextView status;
        TextView startTime;
        TextView endTime;
        TextView startSign;
        TextView endSign;
    }
}
