package com.house.checkhouse.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.house.checkhouse.R;
import com.house.checkhouse.activity.WaitTaskDetialActivity;
import com.house.checkhouse.adapter.ProjectAdapter;
import com.house.checkhouse.model.message.ProjectInfo;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProjectFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProjectFragment extends Fragment implements AdapterView.OnItemClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View mView;


    public ProjectFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProjectFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProjectFragment newInstance(String param1, String param2) {
        ProjectFragment fragment = new ProjectFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mView == null){
            mView = inflater.inflate(R.layout.fragment_project, container, false);
            initWidget();
        }
        return mView;
    }

    private void initWidget(){
        ListView mList = (ListView) mView.findViewById(R.id.project_list);

        ProjectAdapter adapter = new ProjectAdapter(getContext());
        adapter.setData(setData());
        mList.setAdapter(adapter);

        mList.setOnItemClickListener(this);
    }

    private ArrayList<ProjectInfo> setData(){
        ArrayList<ProjectInfo> list = new ArrayList<>();
        for (int i = 0;i<4;i++){
            ProjectInfo info = new ProjectInfo();
            info.setImage("http://i1.hexunimg.cn/2014-08-15/167580248.jpg");
            info.setTime("2016.10.25");
            info.setContent("[通知] 成都市光华街xxxx小区整改完成，可前往查看");
            list.add(info);
        }

        return list;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(getContext(), WaitTaskDetialActivity.class);
        startActivity(intent);
    }
}
