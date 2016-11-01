package com.house.checkhouse.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.house.checkhouse.R;

import java.util.List;

/**
 * Created by 欢大哥 on 2016/7/6.
 */
public class ItemSelectAdapter extends BaseAdapter {
    private List<String> mList;
    private Context context;
    public ItemSelectAdapter(Context context){
        this.context = context;
    }
    public void setData(List<String> list){
        this.mList = list;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
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


        textView.setText((CharSequence) getItem(i));

        return view;
    }
}
