package com.wd.sick_friends.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wd.model_base.Bean.KeShiBean;
import com.wd.model_base.Bean.SickBean;
import com.wd.model_base.NetUtils;
import com.wd.model_base.base.BaseActivity;
import com.wd.model_base.base.BasePresenter;
import com.wd.sick_friends.R;
import com.wd.sick_friends.R2;
import com.wd.sick_friends.adapter.SickAdapter;
import com.wd.sick_friends.adapter.TVAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@Route(path = "/model_sick_friends/activity/Sick_HomeActivity")
public class Sick_HomeActivity extends BaseActivity {

    @BindView(R2.id.rv)
    RecyclerView rv;
    TVAdapter adapter;

    @BindView(R2.id.rv2)
    RecyclerView rv2;
    @Override
    protected int getReasuce() {
        return R.layout.activity_sick__home;
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
        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
        NetUtils.getInstance().getApis().doKeShi()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<KeShiBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(KeShiBean keShiBean) {
                        LinearLayoutManager manager = new LinearLayoutManager(Sick_HomeActivity.this, LinearLayoutManager.HORIZONTAL, false);
                        rv.setLayoutManager(manager);
                        adapter = new TVAdapter(Sick_HomeActivity.this, keShiBean.getResult());
                        rv.setAdapter(adapter);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getD(KeShiBean.ResultBean bean){
        bean.setColor(1);
        adapter.notifyDataSetChanged();

        NetUtils.getInstance().getApis().doSick(bean.getId(),1,10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SickBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SickBean sickBean) {
                        LinearLayoutManager manager = new LinearLayoutManager(Sick_HomeActivity.this, LinearLayoutManager.VERTICAL, false);
                        rv2.setLayoutManager(manager);
                        SickAdapter sickAdapter = new SickAdapter(Sick_HomeActivity.this, sickBean.getResult());
                        rv2.setAdapter(sickAdapter);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getDa(SickBean.ResultBean bean){
        int sickCircleId = bean.getSickCircleId();
        Intent intent = new Intent(this, Sick_MessageActivity.class);
        intent.putExtra("id",sickCircleId+"");
        startActivity(intent);
    }
}
