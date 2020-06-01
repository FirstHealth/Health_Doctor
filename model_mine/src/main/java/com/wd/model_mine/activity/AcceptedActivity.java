package com.wd.model_mine.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wd.model_base.Bean.AcceptedBean;
import com.wd.model_base.NetUtils;
import com.wd.model_base.base.BaseActivity;
import com.wd.model_base.base.BasePresenter;
import com.wd.model_mine.R;
import com.wd.model_mine.R2;
import com.wd.model_mine.adapter.AcceptAdapter;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@Route(path = "/model_mine/activity/AcceptedActivity")
public class AcceptedActivity extends BaseActivity {
    @BindView(R2.id.ll)
    LinearLayout ll;
    @BindView(R2.id.rv)
    RecyclerView rv;
    @Override
    protected int getReasuce() {
        return R.layout.activity_accepted;
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
        NetUtils.getInstance().getApis().doAccepted(1,10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AcceptedBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AcceptedBean acceptedBean) {
                        if (acceptedBean.getResult().size() != 0){
                            LinearLayoutManager manager = new LinearLayoutManager(AcceptedActivity.this, LinearLayoutManager.VERTICAL, false);
                            rv.setLayoutManager(manager);
                            AcceptAdapter adapter = new AcceptAdapter(AcceptedActivity.this, acceptedBean.getResult());
                            rv.setAdapter(adapter);
                        }else {
                            ll.setVisibility(0);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
