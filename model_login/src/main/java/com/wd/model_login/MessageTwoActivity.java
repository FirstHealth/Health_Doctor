package com.wd.model_login;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wd.model_base.Bean.EmailBean;
import com.wd.model_base.NetUtils;
import com.wd.model_base.base.BaseActivity;
import com.wd.model_base.base.BasePresenter;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MessageTwoActivity extends BaseActivity {
    @BindView(R2.id.next)
    Button next;
    @BindView(R2.id.email)
    EditText email;
    @BindView(R2.id.yanzheng)
            EditText yan;
    @BindView(R2.id.pass)
            EditText pass;
    @BindView(R2.id.password)
            EditText password;
    @BindView(R2.id.bu)
            Button bu;

    int a = 30;
    @SuppressLint("HandlerLeak")
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(a>1){
                a--;
                bu.setText("重新获取("+a+")");
                handler.sendEmptyMessageDelayed(0,1000);
            }
        }
    };
    @Override
    protected int getReasuce() {
        return R.layout.activity_message_two;
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
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ema = email.getText().toString();
                if (TextUtils.isEmpty(ema)){
                    Toast.makeText(MessageTwoActivity.this, "邮箱不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    SharedPreferences sp = getSharedPreferences("info", MODE_PRIVATE);
                    SharedPreferences.Editor edit = sp.edit();
                    edit.putString("email",ema);
                    edit.commit();
                }

                String yan = MessageTwoActivity.this.yan.getText().toString();
                if (TextUtils.isEmpty(yan)){
                    Toast.makeText(MessageTwoActivity.this, "验证码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    SharedPreferences sp = getSharedPreferences("info", MODE_PRIVATE);
                    SharedPreferences.Editor edit = sp.edit();
                    edit.putString("yan",yan);
                    edit.commit();
                }

                String pass = MessageTwoActivity.this.pass.getText().toString();
                if (TextUtils.isEmpty(pass)){
                    Toast.makeText(MessageTwoActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    SharedPreferences sp = getSharedPreferences("info", MODE_PRIVATE);
                    SharedPreferences.Editor edit = sp.edit();
                    edit.putString("pass",pass);
                    edit.commit();
                }

                String password = MessageTwoActivity.this.password.getText().toString();
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(MessageTwoActivity.this, "请再次输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }else if (password.equals(pass)){
                    SharedPreferences sp = getSharedPreferences("info", MODE_PRIVATE);
                    SharedPreferences.Editor edit = sp.edit();
                    edit.putString("password",password);
                    edit.commit();
                }else {
                    Toast.makeText(MessageTwoActivity.this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(MessageTwoActivity.this, MessageThreeActivity.class);
                startActivity(intent);
            }
        });
    }

    @OnClick(R2.id.bu)
    public void onClick(View view){
        handler.sendEmptyMessageDelayed(0,1000);
        String ema = email.getText().toString();
        Toast.makeText(this, "验证码已发送，请验收", Toast.LENGTH_SHORT).show();
        NetUtils.getInstance().getApis().doEmail(ema)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<EmailBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(EmailBean emailBean) {

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
}
