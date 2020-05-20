package com.wd.model_login;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.wd.model_base.Bean.LoginBean;
import com.wd.model_base.NetUtils;
import com.wd.model_base.RsaCoder;
import com.wd.model_base.base.BaseActivity;
import com.wd.model_base.base.BasePresenter;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @ClassName LoginActivity
 * @Description TODO
 * @Author tys
 * @Date 2020/5/1821:47
 */
public class LoginActivity extends BaseActivity {
    @BindView(R2.id.shen)
    TextView shen;
    @BindView(R2.id.email)
    EditText email;
    @BindView(R2.id.pass)
    EditText pass;
    @BindView(R2.id.bu)
    Button bu;
    @BindView(R2.id.forget)
    TextView forget;
    @Override
    protected int getReasuce() {
        return R.layout.activity_login;
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
        bu.setOnClickListener(new View.OnClickListener() {

            private String pas;

            @Override
            public void onClick(View v) {
                String email = LoginActivity.this.email.getText().toString();
                String pass = LoginActivity.this.pass.getText().toString();
                try {
                    pas = RsaCoder.encryptByPublicKey(pass);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (!TextUtils.isEmpty(email)){
                    if (!TextUtils.isEmpty(pass)){
                        NetUtils.getInstance().getApis().doLogin(email,pas)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Observer<LoginBean>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {

                                    }

                                    @Override
                                    public void onNext(LoginBean loginBean) {

                                    }

                                    @Override
                                    public void onError(Throwable e) {

                                    }

                                    @Override
                                    public void onComplete() {

                                    }
                                });
                    }else {
                        Toast.makeText(LoginActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }else {
                    Toast.makeText(LoginActivity.this, "请输入正确的邮箱地址", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/model_homepage/activity/HomePageActivity").navigation();
            }
        });
    }
    @OnClick(R2.id.shen)
    public void onClick(View view){
        Intent intent = new Intent(this, MessageOneActivity.class);
        startActivity(intent);
    }
}
