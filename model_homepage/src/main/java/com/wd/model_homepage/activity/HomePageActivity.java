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
import com.wd.model_base.base.BaseActivity;
import com.wd.model_base.base.BasePresenter;
import com.wd.model_homepage.R;
import com.wd.model_homepage.R2;

import butterknife.BindView;

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
    }
}
