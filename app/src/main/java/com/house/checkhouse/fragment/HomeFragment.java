package com.house.checkhouse.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.house.checkhouse.R;
import com.house.checkhouse.activity.ChangeHouseActivity;
import com.house.checkhouse.activity.HousesActivity;
import com.house.checkhouse.activity.LocationSignInActivity;
import com.house.checkhouse.activity.MyUploadingActivity;
import com.house.checkhouse.activity.ProblemActivity;
import com.house.checkhouse.activity.TaskActivity;
import com.house.checkhouse.activity.WorkRecordActivity;
import com.house.checkhouse.util.CheckConstants;
import com.house.checkhouse.util.SaveUserInfoSharePreference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View mView;
    private String mLoginType;   //登录类型

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLoginType = getLoginType();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mView == null){
            mView = inflater.inflate(R.layout.fragment_home, container, false);
            initWidget();
        }
        return mView;
    }

    private void initWidget(){
        ListView mlist = (ListView) mView.findViewById(R.id.message_list);
        CircleImageView mHeadImg = (CircleImageView) mView.findViewById(R.id.user_image);
        ImageView mSign = (ImageView) mView.findViewById(R.id.sign_in_img);
        LinearLayout mTask = (LinearLayout) mView.findViewById(R.id.task_layout);
        LinearLayout mProblem = (LinearLayout) mView.findViewById(R.id.problem_layout);
        LinearLayout mChange = (LinearLayout) mView.findViewById(R.id.change_layout);
        RelativeLayout mWorkRecord = (RelativeLayout) mView.findViewById(R.id.word_record_layout);
        ImageView mLouPan = (ImageView) mView.findViewById(R.id.loupan_img);
        ImageView mShilei = (ImageView) mView.findViewById(R.id.shilei_img);
        ImageView mUpLoading = (ImageView) mView.findViewById(R.id.uploading_img);
        ImageView mJiaoFangPeiYan = (ImageView) mView.findViewById(R.id.jiaofangpeiyan_img);
        TextView mName = (TextView) mView.findViewById(R.id.user_name);


        isHideImg(mSign);
        isHideImg(mName);

        Picasso.with(getContext()).load(R.mipmap.ic_launcher).into(mHeadImg);

        ArrayAdapter adapter = new ArrayAdapter(getContext(),R.layout.txt_item_layout,setData());
        mlist.setAdapter(adapter);

        mSign.setOnClickListener(this);
        mTask.setOnClickListener(this);
        mProblem.setOnClickListener(this);
        mChange.setOnClickListener(this);
        mWorkRecord.setOnClickListener(this);
        mLouPan.setOnClickListener(this);
        mShilei.setOnClickListener(this);
        mUpLoading.setOnClickListener(this);
        mJiaoFangPeiYan.setOnClickListener(this);
    }

    private ArrayList<String> setData(){
        ArrayList<String> list = new ArrayList<>();
        list.add("1、XXXXXXX可验房");
        list.add("2、XXXXXXX可复检");
        list.add("3、XXXXXXX汇总整理");
        list.add("4、XXXXXXX可复检");
        list.add("5、XXXXXXX可验房");
        return list;
    }

    /**
     * 按钮控件是否隐藏
     * @param imageView
     */
    private void isHideImg(View imageView){
        switch (mLoginType){
            case CheckConstants.LOGIN_ONE:
                imageView.setVisibility(View.VISIBLE);
                break;
            case CheckConstants.LOGIN_TWO:
                imageView.setVisibility(View.GONE);
                break;
            case CheckConstants.LOGIN_THREE:
                imageView.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sign_in_img:
                Intent intent = new Intent(getContext(), LocationSignInActivity.class);
                startActivity(intent);
                break;
            case R.id.task_layout:
                intent = new Intent(getContext(), TaskActivity.class);
                startActivity(intent);
                break;
            case R.id.problem_layout:
                intent = new Intent(getContext(), ProblemActivity.class);
                startActivity(intent);
                break;
            case R.id.change_layout:
                intent = new Intent(getContext(), ChangeHouseActivity.class);
                startActivity(intent);
                break;
            case R.id.word_record_layout:
                intent = new Intent(getContext(), WorkRecordActivity.class);
                startActivity(intent);
                break;
            case R.id.loupan_img:
                //保存验房状态
                SharedPreferences.Editor editor = SaveUserInfoSharePreference.getShareSaveUserInfo(getContext()).edit();
                editor.putString(SaveUserInfoSharePreference.HOUSE_STATUS,CheckConstants.STATUS_ONE);
                editor.commit();
                intent = new Intent(getContext(), HousesActivity.class);
                intent.putExtra(HousesActivity.TITLE_NAME,"楼盘");
                startActivity(intent);
                break;
            case R.id.shilei_img:
                editor = SaveUserInfoSharePreference.getShareSaveUserInfo(getContext()).edit();
                editor.putString(SaveUserInfoSharePreference.HOUSE_STATUS,CheckConstants.STATUS_TWO);
                editor.commit();
                intent = new Intent(getContext(), HousesActivity.class);
                intent.putExtra(HousesActivity.TITLE_NAME,"预验房");
                startActivity(intent);
                break;
            case R.id.jiaofangpeiyan_img:
                editor = SaveUserInfoSharePreference.getShareSaveUserInfo(getContext()).edit();
                editor.putString(SaveUserInfoSharePreference.HOUSE_STATUS,CheckConstants.STATUS_THREE);
                editor.commit();
                intent = new Intent(getContext(), HousesActivity.class);
                intent.putExtra(HousesActivity.TITLE_NAME,"交房陪验");
                startActivity(intent);
                break;
            case R.id.uploading_img:
                intent = new Intent(getContext(), MyUploadingActivity.class);
                startActivity(intent);
                break;
        }
    }
}
