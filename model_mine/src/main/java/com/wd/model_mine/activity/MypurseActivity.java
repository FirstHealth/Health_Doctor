package com.wd.model_mine.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wd.model_base.Bean.DoctorWalletBean;
import com.wd.model_base.Bean.RevenueBean;
import com.wd.model_base.NetUtils;
import com.wd.model_base.base.BaseActivity;
import com.wd.model_base.base.BasePresenter;
import com.wd.model_mine.R;
import com.wd.model_mine.R2;
import com.wd.model_mine.adapter.DoctorRevenueAdapter;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
@Route(path = "/model_mine/activity/MypurseActivity")
public class MypurseActivity extends BaseActivity {

    @BindView(R2.id.back)
    ImageView back;
    @BindView(R2.id.bind)
    TextView bind;
    @BindView(R2.id.tv_HB)
    TextView tvHB;
    @BindView(R2.id.tixian)
    Button tixian;
    @BindView(R2.id.rc1)
    RecyclerView rc1;
    @BindView(R2.id.bind1)
    TextView bind1;
    private DoctorWalletBean.ResultBean result;

    @Override
    protected int getReasuce() {
        return R.layout.activity_mypurse;
    }

    @Override
    protected void getData() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bind1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MypurseActivity.this,BindingActivity.class);
                startActivity(intent);
            }
        });
        bind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MypurseActivity.this,BindingActivity.class);
                startActivity(intent);
            }
        });
        tixian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(result.getBalance()<20000){
                    Toast.makeText(MypurseActivity.this, "H币不足无法提现", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(MypurseActivity.this,WithdrawActivity.class);
                    startActivity(intent);
                }
            }
        });
        NetUtils.getInstance().getApis().findDoctorWallet()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DoctorWalletBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DoctorWalletBean doctorWalletBean) {
                        result = doctorWalletBean.getResult();
                        tvHB.setText(result.getBalance()+"");
                        if(result.getWhetherBindBankCard()==2 && result.getWhetherBindIdCard()==2){
                            bind1.setVisibility(View.GONE);
                            bind.setVisibility(View.VISIBLE);
                        }else{
                            bind.setVisibility(View.GONE);
                            bind1.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        NetUtils.getInstance().getApis().findDoctorRevenue(1,6)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RevenueBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RevenueBean revenueBean) {
                        List<RevenueBean.ResultBean> result = revenueBean.getResult();
                        LinearLayoutManager manager  = new LinearLayoutManager(MypurseActivity.this,RecyclerView.VERTICAL,false);
                        rc1.setLayoutManager(manager);
                        DoctorRevenueAdapter adapter = new DoctorRevenueAdapter(MypurseActivity.this, result);
                        rc1.setAdapter(adapter);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void getId() {

    }

}