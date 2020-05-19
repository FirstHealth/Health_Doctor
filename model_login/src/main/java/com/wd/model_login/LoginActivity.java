package com.wd.model_login;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.wd.model_base.base.BaseActivity;
import com.wd.model_base.base.BasePresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ClassName LoginActivity
 * @Description TODO
 * @Author tys
 * @Date 2020/5/1821:47
 */
public class LoginActivity extends BaseActivity {
    @BindView(R2.id.shen)
    TextView shen;
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

    }
    @OnClick(R2.id.shen)
    public void onClick(View view){
        Intent intent = new Intent(this, MessageOneActivity.class);
        startActivity(intent);
    }
}
