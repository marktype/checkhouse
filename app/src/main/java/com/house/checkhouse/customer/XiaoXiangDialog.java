package com.house.checkhouse.customer;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.house.checkhouse.R;

/**
 * Created by 欢大哥 on 2016/11/24.
 */
public class XiaoXiangDialog extends Dialog{
    private EditText editText;
    private TextView positiveButton, negativeButton;
    private TextView title;
    private ImageView mAddImg;
    private RadioGroup mGroup;
    public XiaoXiangDialog(Context context) {
        super(context, R.style.myDialog);
        setCustomDialog();
    }

    private void setCustomDialog() {
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.xiaoxiang_problem_dialog_layout, null);
        title = (TextView) mView.findViewById(R.id.title);
        editText = (EditText) mView.findViewById(R.id.problem_edit);
        mAddImg = (ImageView) mView.findViewById(R.id.add_img);
        mGroup = (RadioGroup) mView.findViewById(R.id.content);
        positiveButton = (TextView) mView.findViewById(R.id.positiveButton);
        negativeButton = (TextView) mView.findViewById(R.id.negativeButton);
        super.setContentView(mView);
    }

    /**
     * 设置对话框title
     * @param text
     */
    public void setTitle(String text){
        title.setText(text);
    }

    /**
     * 获取编辑框中的类容
     * @return
     */
    public String getEditTxt(){
        return editText.getText().toString().trim();
    }

    /**
     * 确定键监听器
     * @param listener
     */
    public void setOnPositiveListener(View.OnClickListener listener){
        positiveButton.setOnClickListener(listener);
    }
    /**
     * 取消键监听器
     * @param listener
     */
    public void setOnNegativeListener(View.OnClickListener listener){
        negativeButton.setOnClickListener(listener);
    }

    /**
     * 图片点击监听
     * @param listener
     */
    public void setOnImageListener(View.OnClickListener listener){
        mAddImg.setOnClickListener(listener);
    }

    /**
     * 组合按钮监听
     * @param listener
     */
    public void setOnGroupListener(RadioGroup.OnCheckedChangeListener listener){
        mGroup.setOnCheckedChangeListener(listener);
    }


}
