package com.house.checkhouse.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.house.checkhouse.R;
import com.house.checkhouse.adapter.TaskAdapter;
import com.house.checkhouse.model.message.TaskInfo;
import com.house.checkhouse.model.message.TaskItem;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FinishTaskFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FinishTaskFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View mView;


    public FinishTaskFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FinishTaskFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FinishTaskFragment newInstance(String param1, String param2) {
        FinishTaskFragment fragment = new FinishTaskFragment();
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
            mView = inflater.inflate(R.layout.fragment_finish_task, container, false);
            initWidget();
        }
        return mView;
    }

    private void initWidget(){
        ListView mList = (ListView) mView.findViewById(R.id.wait_task_list);

        TaskAdapter adapter = new TaskAdapter(getContext());
        adapter.setData(setData());
        mList.setAdapter(adapter);

    }

    private ArrayList<TaskInfo> setData(){
        ArrayList<TaskInfo> list = new ArrayList<>();
        for (int i = 0;i<2;i++){
            TaskInfo info = new TaskInfo();
            info.setTime("2016-10-2"+i);
            ArrayList<TaskItem> itemList = new ArrayList<>();
            for (int j = 0;j<4;j++){
                TaskItem item = new TaskItem();
                item.setTitle("xxxx小区可以查看xxx");
                item.setContent("**验收");
                itemList.add(item);
            }
            info.setListTask(itemList);
            info.setType(1);
            list.add(info);
        }
        return list;
    }

}
