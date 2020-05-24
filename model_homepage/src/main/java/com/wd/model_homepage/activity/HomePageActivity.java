package com.wd.model_homepage.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.wd.model_base.Bean.DoctorBean;
import com.wd.model_base.NetUtils;
import com.wd.model_base.base.BaseActivity;
import com.wd.model_base.base.BasePresenter;
import com.wd.model_homepage.R;
import com.wd.model_homepage.R2;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@Route(path = "/model_homepage/activity/HomePageActivity")
public class HomePageActivity extends BaseActivity {
    @BindView(R2.id.name)
    TextView name;
    @BindView(R2.id.loca)
    TextView loca;
    @BindView(R2.id.zhi)
    TextView zhi;
    @BindView(R2.id.ke)
    TextView ke;
    @BindView(R2.id.iv)
    ImageView iv;
    @BindView(R2.id.guanli)
    TextView guan;
    @BindView(R2.id.dayi)
    TextView dayi;
    @Override
    protected int getReasuce() {
        return R.layout.activity_home_page;
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
        String name = sp.getString("name", "");
        String hos = sp.getString("hos", "");
        String zhi = sp.getString("zhi", "");
        String ke = sp.getString("ke", "");
        String ispic = sp.getString("ispic", "");
        String pic = sp.getString("pic", "");

        this.name.setText(name);
        this.loca.setText(hos);
        this.zhi.setText(zhi);
        this.ke.setText(ke);

        if (Integer.valueOf(ispic) == 1){
            Glide.with(this).load(pic).into(iv);
        }else {
            iv.setImageResource(R.mipmap.meimei);
        }

        Toast.makeText(this, "1"+name, Toast.LENGTH_SHORT).show();

        guan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePageActivity.this, MineActivity.class);
                startActivity(intent);
            }
        });

        dayi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/model_sick_friends/activity/Sick_HomeActivity").navigation();
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
                        Glide.with(HomePageActivity.this).load(doctorBean.getResult().getImagePic()).into(iv);
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
