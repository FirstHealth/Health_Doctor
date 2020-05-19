package com.wd.model_base;

import android.app.Application;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;


public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("dj", "in app");

        ARouter.openLog();     // 打印日志
        ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        ARouter.init(this);
    }
}
