package com.house.checkhouse.activity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.house.checkhouse.BascActivity;
import com.house.checkhouse.R;

import java.util.Calendar;

public class SignInActivity extends BascActivity {
    private LinearLayout mDataLin;
    private String mWay,mYear,mMonth,mDay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        getNowData();
        initWidget();
    }

    private void initWidget(){
        mDataLin = (LinearLayout) findViewById(R.id.data_day);
        TextView time = (TextView) findViewById(R.id.data_time);
        time.setText(mYear+"年"+mMonth+"月"+mDay+"日");
        setLinData();
    }

    private void setLinData(){
        for (int i = 1;i<8;i++){
            LinearLayout layout = new LinearLayout(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            layout.setLayoutParams(params);
            params.weight = 1;
            layout.setOrientation(LinearLayout.VERTICAL);

            ImageView imgae = new ImageView(this);
            LinearLayout.LayoutParams imagePar = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            imgae.setLayoutParams(imagePar);
            imgae.setImageResource(R.mipmap.daosanjiao);
            if (mWay.equals(""+(i+1))){
                imgae.setVisibility(View.VISIBLE);
            }else {
                imgae.setVisibility(View.INVISIBLE);
            }
            imagePar.gravity = Gravity.CENTER_HORIZONTAL;
            layout.addView(imgae);

            TextView txtDay = new TextView(this);
            txtDay.setText(i+"");
            LinearLayout.LayoutParams txtPar = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            txtDay.setGravity(Gravity.CENTER);
            txtDay.setLayoutParams(txtPar);
            txtPar.setMargins(10,10,10,10);
            txtDay.setBackgroundResource(R.drawable.circle_tint_gray_shape);
            layout.addView(txtDay);

            TextView txtSign = new TextView(this);
            LinearLayout.LayoutParams txtSignPar = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            txtSignPar.gravity = Gravity.CENTER_HORIZONTAL;
            txtSign.setLayoutParams(txtSignPar);
            txtSign.setText("已签");
            layout.addView(txtSign);

            mDataLin.addView(layout);
        }
    }

    /**
     * 获取当前时间
     */
    public void getNowData(){
        Calendar c = Calendar.getInstance();
        mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));   //星期几
        mYear = String.valueOf(c.get(Calendar.YEAR)); // 获取当前年份
        mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
        mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));// 获取当前月份的日期号码

    }
}
