package com.wd.model_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.wd.model_base.base.BaseActivity;
import com.wd.model_base.base.BasePresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class GuideActivity extends BaseActivity {
    @BindView(R2.id.bu)
    ImageView iv;
    @Override
    protected int getReasuce() {
        return R.layout.activity_guide;
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
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuideActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
