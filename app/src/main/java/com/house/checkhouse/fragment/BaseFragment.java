package com.house.checkhouse.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.house.checkhouse.R;
import com.house.checkhouse.util.SaveUserInfoSharePreference;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment extends Fragment {


    public BaseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setText(R.string.hello_blank_fragment);
        return textView;
    }

    /**
     * 获取登录类型（1检验人员，2施工单位，3房产公司）
     */
    protected String getLoginType(){
        return SaveUserInfoSharePreference.getShareSaveUserInfo(getContext()).getString(SaveUserInfoSharePreference.USER_POSITION,null);
    }

}
