package com.wd.model_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.wd.model_base.Bean.KeShiBean;
import com.wd.model_base.Bean.ZhiChengBean;
import com.wd.model_base.NetUtils;
import com.wd.model_base.base.BaseActivity;
import com.wd.model_base.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MessageOneActivity extends BaseActivity {
    @BindView(R2.id.next)
    Button next;
    @BindView(R2.id.name)
    EditText name;
    @BindView(R2.id.yiyuan)
    EditText yiyuan;
    @BindView(R2.id.keshi)
    EditText keshi;
    @BindView(R2.id.zhicheng)
    EditText zhicheng;
    @BindView(R2.id.sp1)
    Spinner sp1;
    @BindView(R2.id.sp2)
    Spinner sp2;
    int k;
    int z;
    @Override
    protected int getReasuce() {
        return R.layout.activity_message_one;
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
        NetUtils.getInstance().getApis().doKeShi()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<KeShiBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(KeShiBean keShiBean) {
                        List<KeShiBean.ResultBean> result = keShiBean.getResult();
                        ArrayList<String> list = new ArrayList<>();
                        list.add("");
                        for (int i = 0; i < result.size(); i ++){
                            list.add(result.get(i).getDepartmentName());
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(MessageOneActivity.this, android.R.layout.simple_expandable_list_item_1,list);
                        sp1.setAdapter(arrayAdapter);
                       sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                           @Override
                           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                               keshi.setText(list.get(position)+"");
                               k = result.get(position+1).getId();
                           }

                           @Override
                           public void onNothingSelected(AdapterView<?> parent) {

                           }
                       });
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        NetUtils.getInstance().getApis().doZhiCheng()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ZhiChengBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ZhiChengBean zhiChengBean) {
                        List<ZhiChengBean.ResultBean> result = zhiChengBean.getResult();
                        ArrayList<String> list = new ArrayList<>();
                        list.add("");
                        Toast.makeText(MessageOneActivity.this, ""+result.get(0).getJobTitle(), Toast.LENGTH_SHORT).show();
                        for (int i = 0; i < result.size(); i++){
                            list.add(result.get(i).getJobTitle());
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(MessageOneActivity.this, android.R.layout.simple_expandable_list_item_1,list);
                        sp2.setAdapter(arrayAdapter);

                        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                zhicheng.setText(list.get(position)+"");
                                z = result.get(position+1).getId();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @OnClick(R2.id.next)
    public void onClick(View view){

        String name = this.name.getText().toString();
        if (TextUtils.isEmpty(name)){
            Toast.makeText(this, "姓名不能为空", Toast.LENGTH_SHORT).show();
            return;
        }else {
            SharedPreferences sp = getSharedPreferences("info", MODE_PRIVATE);
            SharedPreferences.Editor edit = sp.edit();
            edit.putString("name",name);
            edit.commit();
        }

        String yi = yiyuan.getText().toString();
        if (TextUtils.isEmpty(yi)){
            Toast.makeText(this, "医院名称不能为空", Toast.LENGTH_SHORT).show();
            return;
        }else {
            SharedPreferences sp = getSharedPreferences("info", MODE_PRIVATE);
            SharedPreferences.Editor edit = sp.edit();
            edit.putString("yiyuan",yi);
            edit.commit();
        }

        String ke = keshi.getText().toString();
        if (TextUtils.isEmpty(ke)){
            Toast.makeText(this, "科室名称不能为空", Toast.LENGTH_SHORT).show();
            return;
        }else {
            SharedPreferences sp = getSharedPreferences("info", MODE_PRIVATE);
            SharedPreferences.Editor edit = sp.edit();
            edit.putString("keshi",k+"");
            edit.commit();
        }

        String zhi = zhicheng.getText().toString();
        if (TextUtils.isEmpty(zhi)){
            Toast.makeText(this, "职务名称不能为空", Toast.LENGTH_SHORT).show();
            return;
        }else {
            SharedPreferences sp = getSharedPreferences("info", MODE_PRIVATE);
            SharedPreferences.Editor edit = sp.edit();
            edit.putString("zhicheng",z+"");
            edit.commit();
        }

        Intent intent = new Intent(this, MessageTwoActivity.class);
        startActivity(intent);
    }
}
