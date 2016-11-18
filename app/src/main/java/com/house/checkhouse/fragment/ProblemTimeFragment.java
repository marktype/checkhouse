package com.house.checkhouse.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.house.checkhouse.R;
import com.house.checkhouse.activity.ZhiPaiInfoActivity;
import com.house.checkhouse.adapter.ProblemAdapter;
import com.house.checkhouse.model.message.ProblemInfo;
import com.house.checkhouse.model.message.ProblemItem;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProblemTimeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProblemTimeFragment extends Fragment implements OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View mView;
    private ProblemAdapter adapter;
    private TextView mZhiPai,mCancalTxt,mZhipai,mAllCheck;
    private  LinearLayout mLayout;


    public ProblemTimeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProblemTimeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProblemTimeFragment newInstance(String param1, String param2) {
        ProblemTimeFragment fragment = new ProblemTimeFragment();
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
            mView = inflater.inflate(R.layout.fragment_problem_time, container, false);
            initWidget();
        }
        return mView;
    }


    private void initWidget(){
        ListView mList = (ListView) mView.findViewById(R.id.problem_time_list);
        mZhiPai = (TextView) mView.findViewById(R.id.zhipai_txt);   //选择
        mLayout = (LinearLayout) mView.findViewById(R.id.three_select_problem_relat);
        mCancalTxt = (TextView) mView.findViewById(R.id.cancal_txt);
        mAllCheck = (TextView) mView.findViewById(R.id.all_check);
        mZhipai = (TextView) mView.findViewById(R.id.zhipai_go);   //跳转页面

        adapter = new ProblemAdapter(getContext());
        adapter.setOnAllShowCallBack(false);
        adapter.setOnAllCheckCallBack(false);
        adapter.setData(setData());
        mList.setAdapter(adapter);

        mZhiPai.setOnClickListener(this);
        mCancalTxt.setOnClickListener(this);
        mAllCheck.setOnClickListener(this);
        mZhipai.setOnClickListener(this);
    }

    private ArrayList<ProblemInfo> setData(){
        ArrayList<ProblemInfo> list = new ArrayList<>();
        for (int i = 0;i<2;i++){
            ProblemInfo info = new ProblemInfo();
            info.setTime("2016-10-2"+i);
            ArrayList<ProblemItem> itemList = new ArrayList<>();
            for (int j = 0;j<4;j++){
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


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.zhipai_txt:
                mZhiPai.setVisibility(View.GONE);
                mLayout.setVisibility(View.VISIBLE);
                adapter.setOnAllShowCallBack(true);
                adapter.notifyDataSetChanged();
                break;
            case R.id.cancal_txt:
                mZhiPai.setVisibility(View.VISIBLE);
                mLayout.setVisibility(View.GONE);
                adapter.setOnAllShowCallBack(false);
                adapter.notifyDataSetChanged();
                break;
            case R.id.all_check:
                adapter.setOnAllCheckCallBack(true);
                adapter.notifyDataSetChanged();
                break;
            case R.id.zhipai_go:
                Intent intent = new Intent(getContext(), ZhiPaiInfoActivity.class);
                startActivity(intent);
                break;
        }
    }


}
