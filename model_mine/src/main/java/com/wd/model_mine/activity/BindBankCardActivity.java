package com.wd.model_mine.activity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.FileUtils;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.baidu.ocr.sdk.OCR;
import com.baidu.ocr.sdk.OnResultListener;
import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.BankCardParams;
import com.baidu.ocr.sdk.model.BankCardResult;
import com.baidu.ocr.ui.camera.CameraActivity;
import com.bumptech.glide.Glide;
import com.wd.model_base.Bean.BindBean;
import com.wd.model_base.Bean.IdentifyBean;
import com.wd.model_base.NetUtils;
import com.wd.model_base.base.BaseActivity;
import com.wd.model_base.base.BasePresenter;
import com.wd.model_mine.R;
import com.wd.model_mine.R2;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

public class BindBankCardActivity extends BaseActivity {
    @BindView(R2.id.tv)
    TextView tv;
    private byte[] bytes;
    private String s;
    private byte[] bytes1;
    @BindView(R2.id.rl)
    RelativeLayout rl;
    @BindView(R2.id.iv)
    ImageView iv;
    @BindView(R2.id.next)
    Button bu;

    @Override
    protected int getReasuce() {
        return R.layout.activity_bind_bank_card;
    }

    @Override
    protected void getData() {
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BindBankCardActivity.this, CameraActivity.class);
                intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_BANK_CARD);
                intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,
                        FileUtil.getSaveFile(getApplication()).getAbsolutePath());
                startActivityForResult(intent, 100);
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 获取调用参数
        super.onActivityResult(requestCode, resultCode, data);
        String contentType = data.getStringExtra(CameraActivity.KEY_CONTENT_TYPE);
        // 通过临时文件获取拍摄的图片
        String filePath = FileUtil.getSaveFile(getApplicationContext()).getAbsolutePath();

        iv.setVisibility(0);
        Glide.with(this).load(filePath).into(iv);

//        try {
//            bytes = FileUtil.readFileByBytes(filePath);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        String imgStr = Base64Util.encode(bytes);
//        String imgParam = null;
//        try {
//            imgParam = URLEncoder.encode(imgStr, "UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        HashMap<String, String> hashMap = new HashMap<>();
//        hashMap.put("image",imgParam);
//        RequestBody body = NetUtils.getInstance().getRequsetBody1(new ArrayList<File>(), hashMap);
//        Log.i("xxx",imgParam);
//        NetUtils.getInstance().getApis().doBack("https://aip.baidubce.com/rest/2.0/ocr/v1/bankcard?access_token=24.1c0016fee58c08675b744080b20493ea.2592000.1593527192.282335-20161170",body)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<IdentifyBean>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(IdentifyBean identifyBean) {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
        bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = "6212260200183057286";
                String name = "中国工商银行";
                int num = 1;

                NetUtils.getInstance().getApis().doBind(number,name,num)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<BindBean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(BindBean bindBean) {
                                Toast.makeText(BindBankCardActivity.this, "绑定成功", Toast.LENGTH_SHORT).show();
                                finish();
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

    public File getFileByUri(Uri uri) {
        String path = null;
        if ("file".equals(uri.getScheme())) {
            path = uri.getEncodedPath();
            if (path != null) {
                path = Uri.decode(path);
                ContentResolver cr = getContentResolver();
                StringBuffer buff = new StringBuffer();
                buff.append("(").append(MediaStore.Images.ImageColumns.DATA).append("=").append("'" + path + "'").append(")");
                Cursor cur = cr.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[] { MediaStore.Images.ImageColumns._ID, MediaStore.Images.ImageColumns.DATA }, buff.toString(), null, null);
                int index = 0;
                int dataIdx = 0;
                for (cur.moveToFirst(); !cur.isAfterLast(); cur.moveToNext()) {
                    index = cur.getColumnIndex(MediaStore.Images.ImageColumns._ID);
                    index = cur.getInt(index);
                    dataIdx = cur.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    path = cur.getString(dataIdx);				}
                cur.close();
                if (index == 0) {

                } else {
                    Uri u = Uri.parse("content://media/external/images/media/" + index);
                    System.out.println("temp uri is :" + u);
                }
            }
            if (path != null) {
                return new File(path);
            }
        } else if ("content".equals(uri.getScheme())) {
            String[] proj = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(uri, proj, null, null, null);
            if (cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                path = cursor.getString(columnIndex);
            }
            cursor.close();
            return new File(path);
        } else {
            Log.i("xxx", "Uri Scheme:" + uri.getScheme());
        }
        return null;
    }



}
