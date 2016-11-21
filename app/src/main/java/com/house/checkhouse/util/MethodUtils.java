package com.house.checkhouse.util;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.WindowManager;

import java.io.File;

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
}
