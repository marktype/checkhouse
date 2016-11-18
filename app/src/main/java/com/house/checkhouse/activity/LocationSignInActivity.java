package com.house.checkhouse.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.house.checkhouse.BascActivity;
import com.house.checkhouse.R;

public class LocationSignInActivity extends BascActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_sign_in);
        initWidget();
    }

    private void initWidget(){
        ImageView mSignHistory = (ImageView) findViewById(R.id.sign_history);


        mSignHistory.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sign_history:
                Intent intent = new Intent(this,SignHistoryActivity.class);
                startActivity(intent);
                break;
        }
    }
}
