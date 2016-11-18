package com.house.checkhouse.util;

import android.util.Log;

/**
 * Created by 欢大哥 on 2016/10/20.
 */
public class Logs {

    private static String tag = "checkhouse";//tag
    private static boolean flag = true;//开关

    public static void v(String msg) {
        if (flag) {
            Log.v(tag, msg);
        }
    }

    public static void w(String msg) {
        if (flag) {
            Log.w(tag, msg);
        }
    }

    public static void d(String msg) {
        if (flag) {
            Log.d(tag, msg);
        }
    }

    public static void e(String msg) {
        if (flag) {
            Log.e(tag, msg);
        }
    }

}
