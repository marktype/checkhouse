package com.house.checkhouse.util;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;
import android.view.WindowManager;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by 欢大哥 on 2016/11/15.
 */
public class MethodUtils {

    public static void changeWindowAlpha(Activity activity, float degree) {
        //产生背景变暗效果
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = degree;
        activity.getWindow().setAttributes(lp);
    }

    /**
     * 读取文件
     */
    public static Bitmap readSdPic(String fileUri){
        Log.d("tag","fileUri---"+fileUri);
        File file = new File(fileUri);
        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        return bitmap;
    }

    /**
     * 获取文件路径和名字
     * @return
     */
    public static String getNowFileName(){
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmss");//格式大小写有区别
        String sysDatetime = fmt.format(Calendar.getInstance().getTime());//2016年02月25日  13:23:40
        String fileName = Environment.getExternalStorageDirectory()+File.separator+"com.house"+File.separator+"check"+sysDatetime+".png";
        Logs.d("fileName-----"+fileName);
        File file = new File(Environment.getExternalStorageDirectory()+File.separator+"com.house");
        if (!file.exists()){
            file.mkdir();
        }
        return fileName;
    }

}
