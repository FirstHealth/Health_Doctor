package com.wd.model_mine.activity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.baidu.ocr.ui.camera.CameraActivity;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.wd.model_base.Bean.BindIdCardBean;
import com.wd.model_base.Bean.SettleInBean;
import com.wd.model_base.Bean.ShenBean;
import com.wd.model_base.NetUtils;
import com.wd.model_base.RsaCoder;
import com.wd.model_base.base.BaseActivity;
import com.wd.model_base.base.BasePresenter;
import com.wd.model_mine.R;
import com.wd.model_mine.R2;
import com.wildma.pictureselector.PictureSelector;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class BindIdCardActivity extends BaseActivity {
    @BindView(R2.id.tv)
    TextView tv;
    @BindView(R2.id.tv2)
    TextView tv2;
    @BindView(R2.id.iv)
    ImageView iv;
    @BindView(R2.id.iv2)
    ImageView iv2;
    @BindView(R2.id.next)
    Button bu;
    @Override
    protected int getReasuce() {
        return R.layout.activity_bind_id_card;
    }

    @Override
    protected void getData() {
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BindIdCardActivity.this, CameraActivity.class);
                intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_BANK_CARD);
                intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH, FileUtil.getSaveFile1(getApplication()).getAbsolutePath());
                startActivityForResult(intent, 300);
            }
        });

        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BindIdCardActivity.this, CameraActivity.class);
                intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_BANK_CARD);
                intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH, FileUtil.getSaveFile2(getApplication()).getAbsolutePath());
                startActivityForResult(intent, 200);
            }
        });

        bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = "56";
                String name = "谭亚森";
                String sex = "男";
                String nation = "汉";
                String birthday = "19991126";
                String address = "山西省运城市平陆县常乐镇斜坡村四组";
                String idNumber = "142732199911262515";
                String issueOffice = "平陆县公安局";

                try {
                    String name1 = RsaCoder.encryptByPublicKey(name);
                    String name2 = RsaCoder.encryptByPublicKey(sex);
                    String name3 = RsaCoder.encryptByPublicKey(nation);
                    String name4 = RsaCoder.encryptByPublicKey(birthday);
                    String name5 = RsaCoder.encryptByPublicKey(address);
                    String name6 = RsaCoder.encryptByPublicKey(idNumber);
                    String name7 = RsaCoder.encryptByPublicKey(issueOffice);


                    BindIdCardBean bean = new BindIdCardBean();
                    bean.setDoctorId(Integer.valueOf(id+""));
                    bean.setName(name1);
                    bean.setSex(name2);
                    bean.setNation(name3);
                    bean.setBirthday(name4);
                    bean.setAddress(name5);
                    bean.setIdNumber(name6);
                    bean.setIssueOffice(name7);

                    Gson gson = new Gson();
                    String json = gson.toJson(bean);
                    RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);

                    NetUtils.getInstance().getApis().doBindIdCard(requestBody)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<SettleInBean>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(SettleInBean settleInBean) {
                                    finish();
                                    Toast.makeText(BindIdCardActivity.this, "绑定成功", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onError(Throwable e) {
                                    Log.i("xxx",e.toString());
                                }

                                @Override
                                public void onComplete() {

                                }
                            });
                } catch (Exception e) {
                    e.printStackTrace();
                }

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

    @SuppressLint("WrongConstant")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 获取调用参数
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 300){
           String filePath = FileUtil.getSaveFile1(getApplicationContext()).getAbsolutePath();
            iv.setVisibility(0);
           Glide.with(this).load(filePath).into(iv);
        }
        if (requestCode == 200){
            String filePath = FileUtil.getSaveFile2(getApplicationContext()).getAbsolutePath();
            iv2.setVisibility(0);
            Glide.with(this).load(filePath).into(iv2);
        }
    }
}
