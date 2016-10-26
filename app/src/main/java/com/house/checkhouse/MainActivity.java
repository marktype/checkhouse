package com.house.checkhouse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.house.checkhouse.activity.HomeActivity;

public class MainActivity extends BascActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tintManager.setStatusBarTintColor(getResources().getColor(android.R.color.transparent));

        initWidget();
    }

    private void initWidget(){
        TextView mLogin = (TextView) findViewById(R.id.login_txt);


        mLogin.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_txt:
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                break;
        }
    }
}
