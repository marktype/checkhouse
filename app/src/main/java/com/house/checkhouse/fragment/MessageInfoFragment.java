package com.house.checkhouse.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.house.checkhouse.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MessageInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MessageInfoFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View mView;


    public MessageInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MessageInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MessageInfoFragment newInstance(String param1, String param2) {
        MessageInfoFragment fragment = new MessageInfoFragment();
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
            mView = inflater.inflate(R.layout.fragment_message_info, container, false);
            initWidget();
        }
        return mView;
    }

    private void initWidget(){
        RelativeLayout mToolRelat = (RelativeLayout) mView.findViewById(R.id.tool_lin);
        RelativeLayout mPlatformRelat = (RelativeLayout) mView.findViewById(R.id.platform_lin);
        RelativeLayout mDevelopRelat = (RelativeLayout) mView.findViewById(R.id.develop_lin);


        mToolRelat.setOnClickListener(this);
        mPlatformRelat.setOnClickListener(this);
        mDevelopRelat.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tool_lin:
                break;
            case R.id.platform_lin:
                break;
            case R.id.develop_lin:
                break;
        }
    }
}
