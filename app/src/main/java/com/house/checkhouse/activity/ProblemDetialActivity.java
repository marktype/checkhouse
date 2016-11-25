package com.house.checkhouse.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.house.checkhouse.BascActivity;
import com.house.checkhouse.R;
import com.house.checkhouse.customer.XiaoXiangDialog;

public class ProblemDetialActivity extends BascActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_detial);
        initWidget();
    }

    private void initWidget(){
        TextView mXiaoXiangProblem = (TextView) findViewById(R.id.xiaoxiang_problem_txt);


        mXiaoXiangProblem.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.xiaoxiang_problem_txt:
                getXiaoXiangDialog();
                break;
        }
    }

    /**
     * 销项弹框
     */
    private void getXiaoXiangDialog(){
        final XiaoXiangDialog dialog = new XiaoXiangDialog(this);
        dialog.show();

        dialog.setOnPositiveListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
}
