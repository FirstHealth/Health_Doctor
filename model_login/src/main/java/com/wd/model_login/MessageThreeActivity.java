package com.wd.model_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wd.model_base.Bean.JoBean;
import com.wd.model_base.Bean.JoinBean;
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
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class MessageThreeActivity extends BaseActivity {
    @BindView(R2.id.jian)
    EditText jian;
    @BindView(R2.id.shan)
    EditText shan;
    @BindView(R2.id.over)
    Button bu;
    @BindView(R2.id.tvFirst)
    TextView tvFirst;
    @BindView(R2.id.tvSecond)
    TextView tvSecond;
    @Override
    protected int getReasuce() {
        return R.layout.activity_message_three;
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
        jian.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        jian.setGravity(Gravity.TOP);
        jian.setSingleLine(false);

        shan.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        shan.setGravity(Gravity.TOP);
        shan.setSingleLine(false);

        jian.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int length = jian.getText().length();
                tvFirst.setText(500-length+"/500");

                if(length>=500){
                    jian.setFocusable(false);
                    jian.setFocusableInTouchMode(false);
                }
            }
        });
        shan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int length = shan.getText().length();
                tvSecond.setText(300-length+"/300");
                if(length>=300){
                    shan.setFocusable(false);
                    shan.setFocusableInTouchMode(false);
                }
            }
        });

    }

    @OnClick(R2.id.over)
    public void onClick(View view){
        String jian = this.jian.getText().toString();
        if (TextUtils.isEmpty(jian)){
            Toast.makeText(this, "请输入个人简介", Toast.LENGTH_SHORT).show();
            return;
        }else {
            SharedPreferences sp = getSharedPreferences("info", MODE_PRIVATE);
            SharedPreferences.Editor edit = sp.edit();
            edit.putString("jian",jian);
            edit.commit();
        }

        String shan = this.shan.getText().toString();
        if (TextUtils.isEmpty(shan)){
            Toast.makeText(this, "请输入擅长领域", Toast.LENGTH_SHORT).show();
            return;
        }else {
            SharedPreferences sp = getSharedPreferences("info", MODE_PRIVATE);
            SharedPreferences.Editor edit = sp.edit();
            edit.putString("shan",shan);
            edit.commit();
        }

        SharedPreferences sharedPreferences = getSharedPreferences("info", MODE_PRIVATE);
        String email = sharedPreferences.getString("email", "");
        String yan = sharedPreferences.getString("yan", "");
        String pass = sharedPreferences.getString("pass", "");
        String password = sharedPreferences.getString("password", "");
        String name = sharedPreferences.getString("name", "");
        String yiyuan = sharedPreferences.getString("yiyuan", "");
        String keshi = sharedPreferences.getString("keshi", "");
        String zhicheng = sharedPreferences.getString("zhicheng", "");
        String jian1 = sharedPreferences.getString("jian", "");
        String shan1 = sharedPreferences.getString("shan", "");
        try {
            String pas = RsaCoder.encryptByPublicKey(pass);
            String passw = RsaCoder.encryptByPublicKey(password);
            JoBean joBean = new JoBean(email, yan, pas, pas, name, yiyuan, Integer.valueOf(keshi), Integer.valueOf(zhicheng), jian1, shan1);
            Gson gson = new Gson();
            String s = gson.toJson(joBean);
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), s);
            NetUtils.getInstance().getApis().doSettleln(requestBody)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<JoinBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(JoinBean joinBean) {
                            Intent intent = new Intent(MessageThreeActivity.this, RunnishActivity.class);
                            startActivity(intent);
                            finish();
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
