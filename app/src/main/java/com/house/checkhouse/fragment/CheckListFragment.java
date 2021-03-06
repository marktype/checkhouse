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
import com.house.checkhouse.activity.CheckDetialActivity;
import com.house.checkhouse.activity.ConstructionCheckDetialActivity;
import com.house.checkhouse.adapter.ItemSelectAdapter;
import com.house.checkhouse.util.CheckConstants;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CheckListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CheckListFragment extends BaseFragment implements AdapterView.OnItemClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View mView;

    public CheckListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CheckListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CheckListFragment newInstance(String param1, String param2) {
        CheckListFragment fragment = new CheckListFragment();
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
            mView = inflater.inflate(R.layout.fragment_check_list, container, false);
            initWidget();
        }
        return mView;
    }

    private void initWidget(){
        ListView mList = (ListView) mView.findViewById(R.id.check_list);

        ItemSelectAdapter adapter = new ItemSelectAdapter(getContext());
        adapter.setData(setData());
        mList.setAdapter(adapter);

        mList.setOnItemClickListener(this);
    }

    public ArrayList<String> setData(){
        ArrayList<String> list= new ArrayList<>();
        for(int i = 0;i<20;i++){
            list.add(i+"-室内检测");
        }
        return list;
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (getLoginType()){
            case CheckConstants.LOGIN_ONE:
                Intent intent = new Intent(getContext(), CheckDetialActivity.class);
                startActivity(intent);
                break;
            case CheckConstants.LOGIN_TWO:
                intent = new Intent(getContext(), ConstructionCheckDetialActivity.class);
                startActivity(intent);
                break;
            case CheckConstants.LOGIN_THREE:
                intent = new Intent(getContext(), ConstructionCheckDetialActivity.class);
                startActivity(intent);
                break;
        }

    }
}
