package com.house.checkhouse.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.house.checkhouse.BascActivity;
import com.house.checkhouse.R;
import com.house.checkhouse.util.CheckConstants;
import com.house.checkhouse.util.MethodUtils;

import java.math.BigDecimal;

/**
 * 交房陪验的交付详情
 */
public class PayDetialActivity extends BascActivity implements View.OnClickListener{
    public static int REQUST_CODE_IMAGE = 1;
    private ImageView mSign;
    private TextView mSignTxt;
    private String fileUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_detial);
        initWidget();
    }

    private void initWidget(){
        mSign = (ImageView) findViewById(R.id.sign_name_img);


        mSignTxt = (TextView) findViewById(R.id.sign_name_txt);
        mSign.setOnClickListener(this);
        mSignTxt.setOnClickListener(this);
        isHideView();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sign_name_txt:
                Intent intent = new Intent(this,SignNameActivity.class);
                startActivityForResult(intent,REQUST_CODE_IMAGE);
                break;
            case R.id.sign_name_img:
                intent = new Intent(this,SignNameActivity.class);
                intent.putExtra(CheckConstants.SIGN_IMAGE,fileUri);
                startActivityForResult(intent,REQUST_CODE_IMAGE);
                break;
        }
    }

    /**
     * 是否显示签名文字
     */
    private void isHideView(){
        switch (getLoginType()){
            case CheckConstants.LOGIN_ONE:
                mSignTxt.setVisibility(View.GONE);
                break;
            case CheckConstants.LOGIN_TWO:
                mSignTxt.setVisibility(View.VISIBLE);
                break;
            case CheckConstants.LOGIN_THREE:
                mSignTxt.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
     if (requestCode == REQUST_CODE_IMAGE){
         fileUri = data.getStringExtra(SecondActivity.IMAGE);
         Bitmap bitmap = MethodUtils.readSdPic(fileUri);
         Bitmap map = reduce(bitmap,200,100,true);
         mSign.setImageBitmap(map);
         mSignTxt.setVisibility(View.GONE);
        }
    }


    /**
     * 压缩图片
     *
     * @param bitmap   源图片
     * @param width    想要的宽度
     * @param height   想要的高度
     * @param isAdjust 是否自动调整尺寸, true图片就不会拉伸，false严格按照你的尺寸压缩
     * @return Bitmap
     */
    public Bitmap reduce(Bitmap bitmap, int width, int height, boolean isAdjust) {
        // 如果想要的宽度和高度都比源图片小，就不压缩了，直接返回原图
        if (bitmap.getWidth() < width && bitmap.getHeight() < height) {
            return bitmap;
        }
        // 根据想要的尺寸精确计算压缩比例, 方法详解：public BigDecimal divide(BigDecimal divisor, int scale, int roundingMode);
        // scale表示要保留的小数位, roundingMode表示如何处理多余的小数位，BigDecimal.ROUND_DOWN表示自动舍弃
        float sx = new BigDecimal(width).divide(new BigDecimal(bitmap.getWidth()), 4, BigDecimal.ROUND_DOWN).floatValue();
        float sy = new BigDecimal(height).divide(new BigDecimal(bitmap.getHeight()), 4, BigDecimal.ROUND_DOWN).floatValue();
        if (isAdjust) {// 如果想自动调整比例，不至于图片会拉伸
            sx = (sx < sy ? sx : sy);
            sy = sx;// 哪个比例小一点，就用哪个比例
        }
        Matrix matrix = new Matrix();
        matrix.postScale(sx, sy);// 调用api中的方法进行压缩，就大功告成了
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }
}
