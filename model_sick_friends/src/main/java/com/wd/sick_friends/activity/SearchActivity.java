package com.wd.sick_friends.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.wd.model_base.Bean.SearchSickBean;
import com.wd.model_base.NetUtils;
import com.wd.model_base.base.BaseActivity;
import com.wd.model_base.base.BasePresenter;
import com.wd.sick_friends.R;
import com.wd.sick_friends.R2;
import com.wd.sick_friends.adapter.Sick2Adapter;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SearchActivity extends BaseActivity {

    @BindView(R2.id.tv_search)
    TextView tv_search;
    @BindView(R2.id.et_search)
    EditText et_search;
    @BindView(R2.id.err)
    ImageView err;
    @BindView(R2.id.rv)
    RecyclerView rv;
    Sick2Adapter adapter;
    @Override
    protected int getReasuce() {
        return R.layout.activity_search;
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
        tv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = et_search.getText().toString();
                NetUtils.getInstance().getApis().doSearch(s)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<SearchSickBean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @SuppressLint("WrongConstant")
                            @Override
                            public void onNext(SearchSickBean searchSickBean) {
                                if (searchSickBean.getResult().size() != 0){
                                    err.setVisibility(8);
                                    LinearLayoutManager manager = new LinearLayoutManager(SearchActivity.this, LinearLayoutManager.VERTICAL, false);
                                    rv.setLayoutManager(manager);
                                    adapter = new Sick2Adapter(SearchActivity.this, searchSickBean.getResult());
                                    rv.setAdapter(adapter);
                                }else {
                                    err.setVisibility(0);
                                    rv.setVisibility(8);
                                    adapter.notifyDataSetChanged();
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
        });
    }
}
