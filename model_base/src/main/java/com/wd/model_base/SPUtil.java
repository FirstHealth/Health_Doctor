package com.wd.model_base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * @ProjectName: Doctor
 * @Package: com.wd.common.utils
 * @ClassName: SPUtil
 * @Description: (java类作用描述)
 * @Author: tys
 * @CreateDate: 2020/5/18 19:42
 */
public class SPUtil {
    public static final String FILE_NAME = "userInfo";
    public static final String KEY_DOCTORID = "doctorId";
    public static final String KEY_SESSIONID = "sessionId";
    public static final String KEY_NAME = "name";
    public static final String KEY_USERNAME = "userName";
    public static final String KEY_REVIEWSTATUS = "reviewStatus";
    public static final String KEY_JIGUANGPWD = "jiGuangPwd";
    public static final String KEY_IMAGEPIC = "imagePic";
    public static final String KEY_INAUGURALHOSPITAL = "inauguralHospital";
    public static final String KEY_JOBTITLE = "jobTitle";
    public static final String KEY_DEPARTMENTID = "departmentId";
    public static final String KEY_DEPARTMENTNAME = "departmentName";
    public static final String KEY_WHETHERHAVEIMAGEPIC = "whetherHaveImagePic";

    public static final String KEY_ZHANGHAO = "zhanghao";
    public static final String KEY_MIMA = "mima";

    private SPUtil() {
    }
    private static class SingleInstance{
        private static SPUtil sSPUtil = new SPUtil();
    }
    public static SPUtil getInstance(){
        return SingleInstance.sSPUtil;
    }
    //存储字符串信息
    @SuppressLint("CommitPrefEdits")
    public void saveDataString(Context context, String fileName, String key, String value){
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(key, value);
        edit.commit();
    }
    //读取信息
    public String getDataString(Context context, String fileName, String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        String string = sharedPreferences.getString(key, null);
        return string;
    }
}
