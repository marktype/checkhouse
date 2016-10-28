package com.house.checkhouse.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.house.checkhouse.R;
import com.house.checkhouse.adapter.ChangeAdapter;
import com.house.checkhouse.model.message.ProblemInfo;
import com.house.checkhouse.model.message.ProblemItem;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WaitChangeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WaitChangeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View mView;


    public WaitChangeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WaitChangeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WaitChangeFragment newInstance(String param1, String param2) {
        WaitChangeFragment fragment = new WaitChangeFragment();
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
            mView = inflater.inflate(R.layout.fragment_wait_change, container, false);
            initWidget();
        }
        return mView;
    }


    private void initWidget(){
        ListView mList = (ListView) mView.findViewById(R.id.wait_change_list);

        ChangeAdapter adapter = new ChangeAdapter(getContext());
        adapter.setData(setData());
        mList.setAdapter(adapter);
    }

    private ArrayList<ProblemInfo> setData(){
        ArrayList<ProblemInfo> list = new ArrayList<>();
        for (int i = 0;i<2;i++){
            ProblemInfo info = new ProblemInfo();
            info.setTime("2016-10-2"+i);
            ArrayList<ProblemItem> itemList = new ArrayList<>();
            for (int j = 0;j<3;j++){
                ProblemItem item = new ProblemItem();
                item.setTitle("xxxx小区可以查看xxx");
                item.setContent("可以复检");
                itemList.add(item);
            }
            info.setListTask(itemList);
            list.add(info);
        }
        return list;
    }

}
