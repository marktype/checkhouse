package com.house.checkhouse.util;

import android.app.Activity;
import android.view.WindowManager;

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
}
