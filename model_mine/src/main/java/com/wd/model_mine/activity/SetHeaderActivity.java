package com.wd.model_mine.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.wd.model_base.Bean.ChoseBean;
import com.wd.model_base.Bean.ImagerBean;
import com.wd.model_base.Bean.PicturBean;
import com.wd.model_base.NetUtils;
import com.wd.model_base.base.BaseActivity;
import com.wd.model_base.base.BasePresenter;
import com.wd.model_mine.R;
import com.wd.model_mine.R2;
import com.wildma.pictureselector.PictureSelector;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

@Route(path = "/model_mine/activity/SetHeaderActivity")
public class SetHeaderActivity extends BaseActivity {

    @BindView(R2.id.vp)
    ViewPager vp;
    ArrayList<Object> imas = new ArrayList<>();
    ArrayList<View> list = new ArrayList<>();
    @BindView(R2.id.bu)
    Button bu;
    ImageView iv;
    int a = 0;
    private PagerAdapter adapter;
    ArrayList<File> files = new ArrayList<>();
    @Override
    protected int getReasuce() {
        return R.layout.activity_set_header;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void getId() {

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void getData() {

        NetUtils.getInstance().getApis().doImage()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ImagerBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ImagerBean imagerBean) {
                        for (int i = 0; i < imagerBean.getResult().size(); i++){
                            imas.add(imagerBean.getResult().get(i).getImagePic());
                        }
                        imas.add(R.mipmap.aa);
                        for (int i = 0; i < imas.size() - 1; i++) {
                            View view = LayoutInflater.from(SetHeaderActivity.this).inflate(R.layout.item_image, null);
                            ImageView iv = view.findViewById(R.id.iv);
                            Glide.with(SetHeaderActivity.this).load(imas.get(i)).into(iv);
                            list.add(view);
                        }
                        View view = LayoutInflater.from(SetHeaderActivity.this).inflate(R.layout.item_image, null);
                        iv = view.findViewById(R.id.iv);
                        iv.setImageResource(Integer.valueOf(imas.get(imas.size()-1).toString()));
                        list.add(view);
                        vp.setAdapter(adapter);

                        iv.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(Intent.ACTION_PICK, null);
                                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
                                startActivityForResult(intent,100);
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


        adapter = new PagerAdapter() {
            @Override
            // 获取当前窗体界面数
            public int getCount() {
                // TODO Auto-generated method stub
                return list.size();
            }

            @Override
            // 断是否由对象生成界面
            public boolean isViewFromObject(View arg0, Object arg1) {
                // TODO Auto-generated method stub
                return arg0 == arg1;
            }

            // 是从ViewGroup中移出当前View
            @Override
            public void destroyItem(View arg0, int arg1, Object arg2) {
                ((ViewPager) arg0).removeView(list.get(arg1));
            }

            // 返回一个对象，这个对象表明了PagerAdapter适配器选择哪个对象放在当前的ViewPager中
            @Override
            public Object instantiateItem(View arg0, int arg1) {
                ((ViewPager) arg0).addView(list.get(arg1));
                return list.get(arg1);
            }
        };


        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                a = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String o = (String) imas.get(a);
                NetUtils.getInstance().getApis().doChose(o)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<ChoseBean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(ChoseBean choseBean) {
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

    @SuppressLint("WrongConstant")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        final Uri uri = data.getData();
        iv.setImageURI(uri);

        File file = getFileByUri(uri);
        files.add(file);
        Log.i("xxx",file+"");
        RequestBody body = NetUtils.getInstance().getRequsetBody(files, new HashMap<String, String>());
        NetUtils.getInstance().getApis().doPictor(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PicturBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PicturBean picturBean) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

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
