package com.wd.model_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wd.model_base.base.BaseActivity;
import com.wd.model_base.base.BasePresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class MessageThreeActivity extends BaseActivity {
    @BindView(R2.id.jian)
    EditText jian;
    @BindView(R2.id.shan)
    EditText shan;
    @BindView(R2.id.over)
    Button bu;
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



    }
}
