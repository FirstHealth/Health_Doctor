package com.wd.model_login;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.wd.model_base.Bean.ResetPwdBean;
import com.wd.model_base.NetUtils;
import com.wd.model_base.RsaCoder;
import com.wd.model_base.base.BaseActivity;
import com.wd.model_base.base.BasePresenter;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ForgetPasswordActivityTwo extends BaseActivity {

    @BindView(R2.id.back)
    ImageView back;
    @BindView(R2.id.et_pwd1)
    EditText etPwd1;
    @BindView(R2.id.et_pwd2)
    EditText etPwd2;
    @BindView(R2.id.finish)
    Button finish;

    @Override
    protected int getReasuce() {
        return R.layout.activity_forget_password_two;
    }

    @Override
    protected void getData() {
        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password1 = etPwd1.getText().toString();
                String password2 = etPwd2.getText().toString();
                if(password1.equals(password2)){
                    try {
                        String pwd1 = RsaCoder.encryptByPublicKey(password1);

/*                    HashMap<String, String> map = new HashMap<>();
                    map.put("email",email);

                    map.put("pwd1",pwd1);

                    map.put("pwd2",pwd2);

                    RequestBody requsetBody = NetUtils.getInstance().getRequsetBody(new ArrayList<>(), map);

                    Log.i("xxx",requsetBody.toString());*/

                        NetUtils.getInstance().getApis().doResetPwd(email,pwd1,pwd1)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Observer<ResetPwdBean>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {

                                    }

                                    @Override
                                    public void onNext(ResetPwdBean resetPwdBean) {
                                        Toast.makeText(ForgetPasswordActivityTwo.this, ""+resetPwdBean.getMessage(), Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        Log.i("aa",e.toString());
                                    }

                                    @Override
                                    public void onComplete() {

                                    }
                                });

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(ForgetPasswordActivityTwo.this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
                }

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void getId() {

    }

}