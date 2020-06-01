package com.wd.model_homepage.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.wd.model_base.App;
import com.wd.model_base.Bean.DoctorBean;
import com.wd.model_base.Bean.DoctorInfoBean;
import com.wd.model_base.NetUtils;
import com.wd.model_base.SPUtil;
import com.wd.model_base.base.BaseActivity;
import com.wd.model_base.base.BasePresenter;
import com.wd.model_homepage.R;
import com.wd.model_homepage.R2;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MineActivity extends BaseActivity {
    @BindView(R2.id.iv)
    ImageView iv;
    @BindView(R2.id.back)
    ImageView back;
    @BindView(R2.id.ll)
    LinearLayout ll;
    @BindView(R2.id.b2)
    Button b2;
    @BindView(R2.id.b1)
    Button b1;
    @BindView(R2.id.l3)
    LinearLayout l3;
    @BindView(R2.id.l2)
    LinearLayout l2;
    @Override
    protected int getReasuce() {
        return R.layout.activity_mine;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void getId() {

    }

    @Override
    protected void getData() {
        SharedPreferences sp = getSharedPreferences("message", MODE_PRIVATE);
        String ispic = sp.getString("ispic", "");
        String pic = sp.getString("pic", "");
        if (Integer.valueOf(ispic) == 1){
            Glide.with(this).load(pic).into(back);
        }else {
            back.setImageResource(R.mipmap.meimei);
        }
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NetUtils.getInstance().getApis().FindDoctorInfo()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<DoctorInfoBean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(DoctorInfoBean doctorInfoBean) {
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.i("xxx",e.toString());
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                ll.setVisibility(0);
                ll.getBackground().setAlpha(150);
                b2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ll.setVisibility(View.GONE);
                    }
                });
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/model_mine/activity/SetHeaderActivity").navigation();
            }
        });


        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/model_mine/activity/AcceptedActivity").navigation();
            }
        });

        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/model_mine/activity/MypurseActivity").navigation();
            }
        });
    }

    public void getRequest(){
        NetUtils.getInstance().getApis().doDoctor()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DoctorBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DoctorBean doctorBean) {
                        Glide.with(MineActivity.this).load(doctorBean.getResult().getImagePic()).into(back);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getRequest();
    }
}
