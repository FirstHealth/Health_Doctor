package com.wd.model_mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wd.model_base.Bean.DoctorBankCardBean;
import com.wd.model_base.Bean.DoctorIdCardBean;
import com.wd.model_base.NetUtils;
import com.wd.model_base.RsaCoder;
import com.wd.model_base.base.BaseActivity;
import com.wd.model_base.base.BasePresenter;
import com.wd.model_mine.R;
import com.wd.model_mine.R2;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BindingActivity extends BaseActivity {

    @BindView(R2.id.back)
    ImageView back;
    @BindView(R2.id.rl1)
    RelativeLayout rl1;
    @BindView(R2.id.rl2)
    RelativeLayout rl2;
    @BindView(R2.id.tv_name)
    TextView tvName;
    @BindView(R2.id.tv_sex)
    TextView tvSex;
    @BindView(R2.id.tv_nation)
    TextView tvNation;
    @BindView(R2.id.tv_idNumber)
    TextView tvIdNumber;
    @BindView(R2.id.rl3)
    RelativeLayout rl3;
    @BindView(R2.id.tv_bankName)
    TextView tvBankName;
    @BindView(R2.id.tv_bankType)
    TextView tvBankType;
    @BindView(R2.id.tv_bankNumber)
    TextView tvBankNumber;
    @BindView(R2.id.rl4)
    RelativeLayout rl4;
    @BindView(R2.id.bindIdCard)
    TextView bindIdCard;
    @BindView(R2.id.bindBankCard)
    TextView bindBankCard;

    @Override
    protected int getReasuce() {
        return R.layout.activity_binding;
    }

    @Override
    protected void getData() {
        getBean();
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void getId() {

    }

    public void getBean() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bindBankCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BindingActivity.this, BindBankCardActivity.class);
                startActivity(intent);
            }
        });
        bindIdCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BindingActivity.this, BindIdCardActivity.class);
                startActivity(intent);
            }
        });
        NetUtils.getInstance().getApis().findBankCard()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DoctorBankCardBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DoctorBankCardBean doctorBankCardBean) {
                        if (doctorBankCardBean.getMessage().equals("未绑定银行卡")) {
                            rl4.setVisibility(View.GONE);
                            rl2.setVisibility(View.VISIBLE);
                        } else {
                            rl2.setVisibility(View.GONE);
                            rl4.setVisibility(View.VISIBLE);
                            tvBankName.setText(doctorBankCardBean.getResult().getBankName());
                            tvBankNumber.setText(doctorBankCardBean.getResult().getBankCardNumber());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        NetUtils.getInstance().getApis().findIdCard()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DoctorIdCardBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DoctorIdCardBean doctorIdCardBean) {
                        if (doctorIdCardBean.getMessage().equals("未绑定身份证")) {
                            rl1.setVisibility(View.VISIBLE);
                            rl3.setVisibility(View.GONE);
                        } else {
                            rl3.setVisibility(View.VISIBLE);
                            rl1.setVisibility(View.GONE);

                            DoctorIdCardBean.ResultBean result = doctorIdCardBean.getResult();
                            try {
                                String name = RsaCoder.decryptByPublicKey(result.getName());
                                String sex = RsaCoder.decryptByPublicKey(result.getSex());
                                String nation = RsaCoder.decryptByPublicKey(result.getNation());
                                String idnumber = RsaCoder.decryptByPublicKey(result.getIdNumber());

                                tvName.setText(name);
                                tvSex.setText(sex);
                                tvIdNumber.setText(idnumber);
                                tvNation.setText(nation);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getBean();
    }
}
