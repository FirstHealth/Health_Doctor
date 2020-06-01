package com.wd.consultation.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wd.consultation.R;
import com.wd.model_base.base.BaseActivity;
import com.wd.model_base.base.BasePresenter;
@Route(path = "/model_consultation/activity/ConsultationActivity")
public class ConsultationActivity extends BaseActivity {


    @Override
    protected int getReasuce() {
        return R.layout.activity_consultation;
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
}
