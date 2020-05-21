package com.wd.model_login;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.wd.model_base.Bean.CheckCodeBean;
import com.wd.model_base.Bean.EmailBean;
import com.wd.model_base.NetUtils;
import com.wd.model_base.base.BaseActivity;
import com.wd.model_base.base.BasePresenter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ForgetPasswordActivityOne extends BaseActivity {


    @BindView(R2.id.back)
    ImageView back;
    @BindView(R2.id.et_email)
    EditText etEmail;
    @BindView(R2.id.et_code)
    EditText etCode;
    @BindView(R2.id.next)
    Button next;
    @BindView(R2.id.bt_getcode)
    Button btGetcode;
    private String email;

    @Override
    protected int getReasuce() {
        return R.layout.activity_forget_password_one;
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
        btGetcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = etEmail.getText().toString();
                if(!TextUtils.isEmpty(email) && Email(email)){
                    NetUtils.getInstance().getApis().doEmail(email)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<EmailBean>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(EmailBean emailBean) {
                                    Toast.makeText(ForgetPasswordActivityOne.this, ""+emailBean.getMessage(), Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onError(Throwable e) {

                                }

                                @Override
                                public void onComplete() {

                                }
                            });

                }else{
                    Toast.makeText(ForgetPasswordActivityOne.this, "邮箱输入有误", Toast.LENGTH_SHORT).show();
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = etCode.getText().toString();
                if(!TextUtils.isEmpty(email)){
                    if(!TextUtils.isEmpty(code)){
                        NetUtils.getInstance().getApis().doCheckCode(email,code)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Observer<CheckCodeBean>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {

                                    }

                                    @Override
                                    public void onNext(CheckCodeBean checkCodeBean) {

                                        if(checkCodeBean.getMessage().equals("验证通过")){
                                            Intent intent = new Intent(ForgetPasswordActivityOne.this,ForgetPasswordActivityTwo.class);
                                            intent.putExtra("email",email);
                                            startActivity(intent);
                                        }else{
                                            Toast.makeText(ForgetPasswordActivityOne.this, ""+checkCodeBean.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onError(Throwable e) {

                                    }

                                    @Override
                                    public void onComplete() {

                                    }
                                });

                    }else{
                        Toast.makeText(ForgetPasswordActivityOne.this, "验证码不能为空", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(ForgetPasswordActivityOne.this, "邮箱不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    //正则校验
    public boolean Email(String str){
        Pattern compile = Pattern.compile("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
        Matcher matcher = compile.matcher(str);
        return matcher.matches();
    }

}
