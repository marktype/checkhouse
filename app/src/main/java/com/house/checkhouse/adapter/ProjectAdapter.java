package com.house.checkhouse.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.house.checkhouse.R;
import com.house.checkhouse.model.message.ProjectInfo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 欢大哥 on 2016/10/25.
 */
public class ProjectAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ProjectInfo> list;
    public ProjectAdapter(Context context){
        this.context = context;
    }
    public void setData(ArrayList<ProjectInfo> list){
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
            view = LayoutInflater.from(context).inflate(R.layout.project_item_layout,null);
            viewHolder = new ViewHolder();
            viewHolder.time = (TextView) view.findViewById(R.id.project_time);
            viewHolder.image = (CircleImageView) view.findViewById(R.id.people_img);
            viewHolder.content = (TextView) view.findViewById(R.id.project_info);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }

        ProjectInfo info = (ProjectInfo) getItem(i);
        viewHolder.time.setText(info.getTime());
        Picasso.with(context).load(info.getImage()).placeholder(R.mipmap.ic_launcher).into(viewHolder.image);
        viewHolder.content.setText(info.getContent());


        return view;
    }

    private class ViewHolder{
        TextView time;
        CircleImageView image;
        TextView content;
    }
}
