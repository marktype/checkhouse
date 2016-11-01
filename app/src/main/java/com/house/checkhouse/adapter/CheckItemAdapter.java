package com.house.checkhouse.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.house.checkhouse.R;
import com.house.checkhouse.model.message.HousesInfo;

import java.util.ArrayList;

/**
 * Created by 欢大哥 on 2016/10/31.
 */
public class CheckItemAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<HousesInfo> list;
    public CheckItemAdapter(Context context){
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
        TextView textView;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.text_item_layout,null);
            textView = (TextView) view.findViewById(R.id.name_txt);
            view.setTag(textView);
        }else {
            textView = (TextView) view.getTag();
        }
        HousesInfo info = (HousesInfo) getItem(i);

        textView.setText(info.getName());
        switch (info.getType()){
            case 0:
                break;
            case 1:
                Drawable drawable = context.getResources().getDrawable(R.drawable.circle_gray_green_shape);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                textView.setCompoundDrawables(drawable, null, null, null);
                textView.setCompoundDrawablePadding(10);
                break;
        }

        return view;
    }
}
