package com.wd.model_homepage.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.wd.model_base.App;
import com.wd.model_base.Bean.DoctorInfoBean;
import com.wd.model_base.NetUtils;
import com.wd.model_base.SPUtil;
import com.wd.model_base.base.BaseActivity;
import com.wd.model_base.base.BasePresenter;
import com.wd.model_homepage.R;
import com.wd.model_homepage.R2;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MineActivity extends BaseActivity {
    @BindView(R2.id.iv)
    ImageView iv;
    @Override
    protected int getReasuce() {
        return R.layout.activity_mine;
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

                NetUtils.getInstance().getApis().FindDoctorInfo()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<DoctorInfoBean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(DoctorInfoBean doctorInfoBean) {

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
        });
    }
}
