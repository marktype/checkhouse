package com.house.checkhouse.activity;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.house.checkhouse.BascActivity;
import com.house.checkhouse.R;
import com.house.checkhouse.model.message.ImageInfo;
import com.house.checkhouse.util.MethodUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class AddProblemActivity extends BascActivity implements View.OnClickListener{
    private static int CAMERA_REQUST_CODE = 1;
    private static int GALLERY_REQUST_CODE = 2;
    private static int CROP_REQUST_CODE = 3;
    public static int REQUST_CODE_IMAGE = 10;
    private String mPictureFile, filePath;
    private Uri fileUri;//通过此uri得到本地图片,设置为背景
    private String localTempImgFileName;
    private String localTempImgDir = "com.stock";
    private LinearLayout mLinImg;
    private ArrayList<Bitmap> bitmapList = new ArrayList<Bitmap>();   //图片bitmap
    private ArrayList<String> stringList = new ArrayList<String>();   //保存图片路径
    private ImageView mAddImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_problem_layout);
        initWidget();
    }



    private void initWidget(){
        mLinImg = (LinearLayout) findViewById(R.id.image_lin);
        mAddImg = (ImageView) findViewById(R.id.add_img);

        mAddImg.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.add_img:
                showChangeBgDialog();
                break;

        }
    }

    private void initAddMorePicture() {
//
        ImageView img = (ImageView) LayoutInflater.from(this).inflate(R.layout.item_add_picture, mLinImg, false);
        if (bitmapList != null && bitmapList.size() > 0) {
            int size = bitmapList.size();
            for (int i = 0; i < size; i++) {
                int childCount = mLinImg.getChildCount();
                if (childCount == 3) {
                    mAddImg.setImageBitmap(bitmapList.get(i));
                    mAddImg.setClickable(false);
                    return;
                }
                img.setImageBitmap(bitmapList.get(i));
                img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(AddProblemActivity.this,ImageContentActivity.class);
                        intent.putStringArrayListExtra("aa",stringList);
                        startActivity(intent);
                    }
                });
            }
            mLinImg.addView(img, size - 1);

        }
    }

    /**
     * 显示更换背景对话框
     */
    public void showChangeBgDialog() {


        final Dialog dialog = new Dialog(this, R.style.myDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        LinearLayout dialogLayout = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.pic_select_item_layout, null, false);
        dialog.setContentView(dialogLayout);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();

        /**
         * 相册获取图片
         */
        dialogLayout.findViewById(R.id.pick_picture_album_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_REQUST_CODE);
                dialog.dismiss();
            }
        });
        /**
         * 拍照获取图片
         */
        dialogLayout.findViewById(R.id.pick_picture_camera_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String status = Environment.getExternalStorageState();
                if (status.equals(Environment.MEDIA_MOUNTED)) {
                    try {
                        Uri uri = setSaveUri();
                        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        intent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                        startActivityForResult(intent, CAMERA_REQUST_CODE);
                        dialog.dismiss();
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(getApplicationContext(), "没有找到储存目录", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "没有储存卡", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //如果返回码是照相机返回码,就进行以下处理
        if (requestCode == CAMERA_REQUST_CODE) {

            fileUri = getFilePath();    //保存uri
            startImageZoom(fileUri);

            //如果返回码是相册,就进行处理
        } else if (requestCode == GALLERY_REQUST_CODE) {
            if (data != null){
                Uri contentUri = data.getData();
                Bitmap bitmap = getBitmapFromUri(contentUri);
                bitmap = reduce(bitmap, 300, 300, true);

                ImageInfo imageInfo = new ImageInfo();
                imageInfo.setBitmap(bitmap);
                Intent intent = new Intent(this,SecondActivity.class);
                intent.putExtra("image",imageInfo);
                startActivityForResult(intent,REQUST_CODE_IMAGE);
            }
        }else if (requestCode == CROP_REQUST_CODE) {
            if (data == null) {
                return;
            }
            Bundle bundle = data.getExtras();
            if (bundle != null){
                Bitmap bitmap = bundle.getParcelable("data");
                ImageInfo imageInfo = new ImageInfo();
                imageInfo.setBitmap(bitmap);
                Intent intent = new Intent(this,SecondActivity.class);
                intent.putExtra("image",imageInfo);
                startActivityForResult(intent,REQUST_CODE_IMAGE);
            }
        }else if (requestCode == REQUST_CODE_IMAGE){
            String fileUri = data.getStringExtra(SecondActivity.IMAGE);
            if (!stringList.contains(bitmapList)){
                stringList.add(fileUri);
            }
            Bitmap bitmap = MethodUtils.readSdPic(fileUri);
            if (!bitmapList.contains(bitmap)) {
                bitmapList.add(bitmap);
            }
            initAddMorePicture();
        }
    }

    private Bitmap getBitmapFromUri(Uri uri) {
        try {
            // 读取uri所在的图片
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
            return bitmap;
        } catch (Exception e) {
            Log.e("[Android]", e.getMessage());
            Log.e("[Android]", "目录为：" + uri);
            e.printStackTrace();
            return null;
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
        Log.d("tag","sx----"+sx+"-----sy---"+sy);
        Matrix matrix = new Matrix();
        matrix.postScale(sx, sy);// 调用api中的方法进行压缩，就大功告成了
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }
    @Override
    public void onConfigurationChanged(Configuration config) {
        super.onConfigurationChanged(config);
    }
    /**
     * 启动图片裁剪界面
     *
     * @param uri
     */
    private void startImageZoom(Uri uri) {
        Intent imageZoomIntent = new Intent("com.android.camera.action.CROP");
        imageZoomIntent.setDataAndType(uri, "image/*");
        imageZoomIntent.putExtra("crop", "true");    //出现裁剪页面
        imageZoomIntent.putExtra("aspectX", 1);     //裁剪比例
        imageZoomIntent.putExtra("aspectY", 1);
        imageZoomIntent.putExtra("outputX", 300);    //显示宽高,清晰度,不能太高，容易报错
        imageZoomIntent.putExtra("outputY", 300);
        imageZoomIntent.putExtra("return-data", true);
        imageZoomIntent.putExtra("scale", true);
        imageZoomIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(imageZoomIntent, CROP_REQUST_CODE);
    }

    public String getPhotoPath() {
        return Environment.getExternalStorageDirectory() + "/DCIM/";
    }


    private Uri setSaveUri() {
        //获取保存到的文件夹路劲
        File dir = new File(Environment.getExternalStorageDirectory() + "/DCIM" + "/" + localTempImgDir);
        if (!dir.exists())
            dir.mkdirs();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");//格式大小写有区别
        String sysDatetime = fmt.format(Calendar.getInstance().getTime());//2016年02月25日  13:23:40
        localTempImgFileName = sysDatetime + ".jpg";
        File file = new File(dir, localTempImgFileName);//localTempImgDir和localTempImageFileName是自己定义的名字
        Uri uri = Uri.fromFile(file);
        return uri;
    }

    private Uri getFilePath() {
        File file = new File(Environment.getExternalStorageDirectory() + "/DCIM"
                + "/" + localTempImgDir + "/" + localTempImgFileName);
        Uri uri = null;
        try {
            uri = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(),
                    file.getAbsolutePath(), null, null));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return uri;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
    }
}
