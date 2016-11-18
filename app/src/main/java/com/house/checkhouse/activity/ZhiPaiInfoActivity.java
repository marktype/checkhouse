package com.house.checkhouse.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.house.checkhouse.BascActivity;
import com.house.checkhouse.R;

public class ZhiPaiInfoActivity extends BascActivity implements View.OnClickListener{
    public static final int  COMPANY_CODE = 1;
    public static final int  COMPANY_WORK_CODE = 2;
    public static final String COMAPNY_NAME = "name";
    private TextView mCompany,mWorkCompany;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhi_pai_info);
        initWidget();
    }

    private void initWidget(){
        mCompany = (TextView) findViewById(R.id.zeren_company_txt);
        mWorkCompany = (TextView) findViewById(R.id.work_company_txt);

        mCompany.setOnClickListener(this);
        mWorkCompany.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.zeren_company_txt:
                Intent intent = new Intent(this,ZhiPaiActivity.class);
                startActivityForResult(intent,COMPANY_CODE);
                break;
            case R.id.work_company_txt:
                intent = new Intent(this,ZhiPaiActivity.class);
                startActivityForResult(intent,COMPANY_WORK_CODE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String param = data.getStringExtra(COMAPNY_NAME);
        if (requestCode == COMPANY_CODE&&resultCode == RESULT_OK){
            mCompany.setText(param);
        }else if (requestCode == COMPANY_WORK_CODE&&resultCode == RESULT_OK){
            mWorkCompany.setText(param);
        }
    }
}
