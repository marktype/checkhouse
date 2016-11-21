package com.house.checkhouse.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 欢大哥 on 2016/11/15.
 * 保存用户信息
 */
public class SaveUserInfoSharePreference {
    private static SaveUserInfoSharePreference saveUserInfoSharePreference;
    private static SharedPreferences user;

    public static SaveUserInfoSharePreference newInstanceShare(){
        if (saveUserInfoSharePreference == null){
            saveUserInfoSharePreference = new SaveUserInfoSharePreference();
        }
        return saveUserInfoSharePreference;
    }

    /**
     * 保存用户token
     * @param context
     */
    public static final String USER_INFO = "com.checkhouse.user";
    public static final String TOKEN = "token";      //用户token
    public static final String USER_PHONE = "phone";    //电话
    public static final String USER_PASSWORD = "password";    //密码
    public static final String USER_ID = "uid";       //用户id
    public static final String USER_LATAING = "lating";     //纬度
    public static final String USER_LONTITIDE = "lontitude";    //经度
    public static final String USER_POSITION = "position";    //用户职业   （1检验人员，2施工单位，3房产公司）
    public static final String HOUSE_STATUS = "status";    //楼盘状态   （1楼盘，2预验房，3交房陪验）

    public static SharedPreferences getShareSaveUserInfo(Context context){
        if (user == null){
            user = context.getSharedPreferences(USER_INFO,Context.MODE_PRIVATE);
        }
        return user;
    }
}
