package com.house.checkhouse.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.house.checkhouse.R;
import com.house.checkhouse.model.message.HousesInfo;

import java.util.ArrayList;

/**
 * Created by 欢大哥 on 2016/10/27.
 */
public class HouseGridViewAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<HousesInfo> list;
    public HouseGridViewAdapter(Context context){
        this.context = context;
    }

    public void setData(ArrayList<HousesInfo> list){
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
            view = LayoutInflater.from(context).inflate(R.layout.grid_view_item_layout,null);
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) view.findViewById(R.id.text_name);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        HousesInfo info= (HousesInfo) getItem(i);
        viewHolder.title.setText(info.getName());

        return view;
    }
    private class ViewHolder{
        TextView title;
    }
}
