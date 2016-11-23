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
import com.house.checkhouse.activity.CheckModelActivity;
import com.house.checkhouse.activity.PayDetialActivity;
import com.house.checkhouse.customer.MyGridView;
import com.house.checkhouse.model.message.CheckHouseInfo;

import java.util.ArrayList;

/**
 * Created by 欢大哥 on 2016/10/27.
 */
public class CheckHouseAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<CheckHouseInfo> list;
    public CheckHouseAdapter(Context context){
        this.context = context;
    }
    public void setData(ArrayList<CheckHouseInfo> list){
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
            view = LayoutInflater.from(context).inflate(R.layout.check_list_layout,null);
            viewHolder = new ViewHolder();
            viewHolder.num = (TextView) view.findViewById(R.id.building_num);
            viewHolder.gridView = (MyGridView) view.findViewById(R.id.loupan_grid);
            viewHolder.publicTxt = (TextView) view.findViewById(R.id.public_text_name);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }

        final CheckHouseInfo info = (CheckHouseInfo) getItem(i);
        viewHolder.num.setText(info.getNum());
        viewHolder.publicTxt.setText(info.getNum()+"标准层公区");
        HouseGridViewAdapter adapter = new HouseGridViewAdapter(context);
        adapter.setData(info.getList());
        viewHolder.gridView.setAdapter(adapter);

        viewHolder.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if ("3".equals(info.getType())){
                    Intent intent = new Intent(context,PayDetialActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }else {
                    Intent intent = new Intent(context,CheckModelActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            }
        });

        return view;
    }

    private class ViewHolder{
        TextView num;
        TextView publicTxt;
        MyGridView gridView;
    }

}
