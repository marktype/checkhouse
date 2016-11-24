package com.house.checkhouse;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.house.checkhouse.activity.HomeActivity;
import com.house.checkhouse.util.CheckConstants;
import com.house.checkhouse.util.SaveUserInfoSharePreference;

public class MainActivity extends BascActivity implements View.OnClickListener{
    private EditText mPhone,mPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tintManager.setStatusBarTintColor(getResources().getColor(android.R.color.transparent));

        initWidget();
    }

    private void initWidget(){
        TextView mLogin = (TextView) findViewById(R.id.login_txt);
        mPhone = (EditText) findViewById(R.id.edit_phone);
        mPassword = (EditText) findViewById(R.id.edit_password);


        mLogin.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_txt:
                String phone = mPhone.getText().toString().trim();
                String password = mPassword.getText().toString();
                SharedPreferences.Editor editor = SaveUserInfoSharePreference.getShareSaveUserInfo(this).edit();
                if (phone.equals("1")&&password.equals("1")){
                    showToast("检验人员登录成功");
                    editor.putString(SaveUserInfoSharePreference.USER_POSITION, CheckConstants.LOGIN_ONE);
                    Intent intent = new Intent(this, HomeActivity.class);
                    startActivity(intent);
                }else if (phone.equals("2")&&password.equals("2")){
                    showToast("施工单位登录成功");
                    editor.putString(SaveUserInfoSharePreference.USER_POSITION,CheckConstants.LOGIN_TWO);
                    Intent intent = new Intent(this, HomeActivity.class);
                    startActivity(intent);
                }else if (phone.equals("3")&&password.equals("3")){
                    showToast("房产公司登录成功");
                    editor.putString(SaveUserInfoSharePreference.USER_POSITION,CheckConstants.LOGIN_THREE);
                    Intent intent = new Intent(this, HomeActivity.class);
                    startActivity(intent);
                }else {
                    showToast("现在是测试阶段，登录账号密码，1为检验人员，2为施工单位，3为房产公司");
                    Intent intent = new Intent(this, HomeActivity.class);
                    startActivity(intent);
                }
                editor.commit();
                break;
        }
    }
}
