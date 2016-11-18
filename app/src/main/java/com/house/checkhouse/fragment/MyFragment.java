package com.house.checkhouse.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.house.checkhouse.R;
import com.house.checkhouse.activity.AlterPasswordActivity;
import com.house.checkhouse.activity.HomeActivity;
import com.house.checkhouse.activity.WebDetialActivity;
import com.house.checkhouse.customer.CustomDialog;
import com.house.checkhouse.util.DataCleanManager;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link MyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View mView;

    public MyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyFragment newInstance(String param1, String param2) {
        MyFragment fragment = new MyFragment();
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
            mView = inflater.inflate(R.layout.fragment_my, container, false);
            initWidget();
        }
        return mView;
    }

    private void initWidget(){
        RelativeLayout mAlterPassword = (RelativeLayout) mView.findViewById(R.id.alter_password_layout);
        RelativeLayout mCleanData = (RelativeLayout) mView.findViewById(R.id.clean_data_layout);
        RelativeLayout mAbove = (RelativeLayout) mView.findViewById(R.id.above_own_layout);
        RelativeLayout mService = (RelativeLayout) mView.findViewById(R.id.service_layout);
        RelativeLayout mZhnengCe = (RelativeLayout) mView.findViewById(R.id.zhengce_layout);
        RelativeLayout mExit = (RelativeLayout) mView.findViewById(R.id.exit_layout);

        mAlterPassword.setOnClickListener(this);
        mCleanData.setOnClickListener(this);
        mAbove.setOnClickListener(this);
        mService.setOnClickListener(this);
        mZhnengCe.setOnClickListener(this);
        mExit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.alter_password_layout:
                Intent intent = new Intent(getContext(), AlterPasswordActivity.class);
                startActivity(intent);
                break;
            case R.id.clean_data_layout:
                popWinDow();
                break;
            case R.id.above_own_layout:
                intent = new Intent(getContext(), WebDetialActivity.class);
                startActivity(intent);
                break;
            case R.id.service_layout:
                intent = new Intent(getContext(), WebDetialActivity.class);
                startActivity(intent);
                break;
            case R.id.zhengce_layout:
                intent = new Intent(getContext(), WebDetialActivity.class);
                startActivity(intent);
                break;
            case R.id.exit_layout:
                popWinDowExit();
                break;
        }
    }


    /**
     * 清楚缓存
     */
    public void popWinDow(){
        final CustomDialog dialog = new CustomDialog(getContext());
        dialog.setMessageText("确认要清除缓存吗？");
        dialog.show();
        dialog.setOnPositiveListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataCleanManager.cleanCacheData(getContext());
                Toast.makeText(getContext(),"清除缓存成功！",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        dialog.setOnNegativeListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }

    /**
     * 退出弹框
     */
    public void popWinDowExit(){
        final CustomDialog dialog = new CustomDialog(getContext());
        dialog.setMessageText("确认要退出登录吗？");
        dialog.show();
        dialog.setOnPositiveListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                sharedPreferences.edit().clear().commit();   //清除缓存sp数据
//                if (userInfo != null){
//                    userInfo = null;
//                }
//                Intent intent = new Intent(getContext(),LoginActivity.class);
//                startActivity(intent);
                dialog.dismiss();
                Intent intent = new Intent();
                intent.setAction(HomeActivity.IS_CLOSE);
                getContext().sendBroadcast(intent);
            }
        });
        dialog.setOnNegativeListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}
