package com.wd.sick_friends.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.wd.model_base.Bean.SickMessageBean;
import com.wd.model_base.NetUtils;
import com.wd.model_base.base.BaseActivity;
import com.wd.model_base.base.BasePresenter;
import com.wd.sick_friends.R;
import com.wd.sick_friends.R2;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Sick_MessageActivity extends BaseActivity {
    @BindView(R2.id.people)
    TextView people;
    @BindView(R2.id.name)
    TextView name;
    @BindView(R2.id.bingzheng)
    TextView bingzheng;
    @BindView(R2.id.keshi)
    TextView keshi;
    @BindView(R2.id.message)
    TextView message;
    @BindView(R2.id.zhiliao)
    TextView zhiliao;
    @BindView(R2.id.iv)
    ImageView iv;
    @BindView(R2.id.jieda)
    TextView jieda;
    @BindView(R2.id.jie)
    TextView jie;
    @BindView(R2.id.ll)
    LinearLayout ll;
    @BindView(R2.id.bu)
    Button bu;
    @BindView(R2.id.rl_mengceng)
    RelativeLayout rlMengceng;
    @BindView(R2.id.know)
    ImageView know;
    @Override
    protected int getReasuce() {
        return R.layout.activity_sick__message;
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

//        rlMengceng.getBackground().setAlpha(150);
//        know.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                rlMengceng.setVisibility(View.GONE);
//            }
//        });


        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        Toast.makeText(this, ""+id, Toast.LENGTH_SHORT).show();

        NetUtils.getInstance().getApis().doSickMessage(Integer.valueOf(id))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SickMessageBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(SickMessageBean sickMessageBean) {
                        int whetherContent = sickMessageBean.getResult().getWhetherContent();
                        if (whetherContent == 2){
                            jie.setVisibility(8);
                            jieda.setVisibility(8);
                        }
                        if (sickMessageBean.getResult().getAmount() == 0){
                            ll.setVisibility(8);
                        }else {
                            rlMengceng.setVisibility(0);
                            rlMengceng.getBackground().setAlpha(150);
                            know.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    rlMengceng.setVisibility(View.GONE);
                                }
                            });
                        }

                        name.setText(sickMessageBean.getResult().getTitle());
                        people.setText(sickMessageBean.getResult().getAuthorName());
                        bingzheng.setText(sickMessageBean.getResult().getDisease());
                        keshi.setText(sickMessageBean.getResult().getDepartmentName());
                        message.setText(sickMessageBean.getResult().getDetail());
                        zhiliao.setText(sickMessageBean.getResult().getTreatmentProcess());

                        if (sickMessageBean.getResult().getPicture() != null) {
                            String picture = sickMessageBean.getResult().getPicture();
                            String[] split = picture.split(",");
                            Glide.with(Sick_MessageActivity.this).load(split[0]).into(iv);
                        }

                        if (sickMessageBean.getResult().getContent() != null){
                            jieda.setText(sickMessageBean.getResult().getContent());
                        }

                        if (sickMessageBean.getResult().getAmount() != 0){
                            bu.setText(sickMessageBean.getResult().getAmount()+"H币");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
