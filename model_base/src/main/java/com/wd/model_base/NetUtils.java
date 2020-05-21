package com.wd.model_base;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @ClassName NetUtils
 * @Description TODO
 * @Author tys
 * @Date 2020/3/614:44
 */
public class NetUtils {

    private OkHttpClient client;
    private Apis apis;

    private NetUtils(){
        initOkHttp();
    }

    private static class SingTance{
        private static NetUtils utils = new NetUtils();
    }

    public static NetUtils getInstance(){
        return SingTance.utils;
    }

    Handler handler = new Handler();
    private void initOkHttp() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .addInterceptor(new HeaderInterceptor())
                .addInterceptor(httpLoggingInterceptor);
        client = builder.build();

        Retrofit build = new Retrofit.Builder()
                .baseUrl("http://mobile.bwstudent.com/health/")
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apis = build.create(Apis.class);

    }
    public Apis getApis(){
        return apis;
    }

    public boolean isNetWork(Context context){
        ConnectivityManager ma = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = ma.getActiveNetworkInfo();
        if (info!=null){
            return true;
        }
        return false;
    }

    public class HeaderInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Log.i("xxx","1212");
            String doctorId = SPUtil.getInstance().getDataString(App.getContext(), SPUtil.FILE_NAME, SPUtil.KEY_DOCTORID);
            Log.i("xxx","123123");
            String sessionId = SPUtil.getInstance().getDataString(App.getContext(), SPUtil.FILE_NAME, SPUtil.KEY_SESSIONID);

            if(TextUtils.isEmpty(doctorId) || TextUtils.isEmpty(sessionId)){
                return chain.proceed(request);
            }else {
                Request requestNew = request.newBuilder().addHeader("ak", "0110010010000")
                        .addHeader("doctorId", doctorId)
                        .addHeader("sessionId", sessionId)
                        .addHeader("userId", String.valueOf(0))
                        .addHeader("Content-Type", "application/x-www-form-urlencoded")
                        .build();
                return chain.proceed(requestNew);
            }

        }
    }

    public RequestBody getRequsetBody(List<File> files,HashMap<String,String> map){
        if (map.size() < 1){
            return null;
        }

        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);

        for (Map.Entry<String,String> entry:map.entrySet()){
            Log.i("xxx","key = "+entry.getKey()+"value = "+entry.getValue());
            builder.addFormDataPart(entry.getKey(),entry.getValue()+"");
        }

        for (int i = 0; i <files.size(); i++){
            builder.addFormDataPart("image",files.get(i).getName(),RequestBody.create(MediaType.parse("image/jepg"),files.get(i)));
        }

        return builder.build();
    }

    public void scaleImageView(final View rootView, Context context, float scaleSzie, Bitmap bp) {

        Matrix matrix = new Matrix();
        // float scaleSzie = ((float)
        // getWindowManager().getDefaultDisplay().getWidth() - 20) /
        // bp.getWidth();

        matrix.postScale(scaleSzie, scaleSzie);

        Bitmap newBitmap = Bitmap.createBitmap(bp, 0, 0, bp.getWidth(), bp.getHeight(), matrix, true);

        // final View rootView = getRootView();

        final LinearLayout linearLayout = new LinearLayout(context);
        linearLayout
                .setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setGravity(Gravity.CENTER);
//        linearLayout.setBackgroundColor(context.getResources().getColor(R.color.));
        ImageView imageView = new ImageView(context);
        imageView.setImageBitmap(newBitmap);
        linearLayout.addView(imageView);

        linearLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ((ViewGroup) rootView).removeView(linearLayout);
            }
        });
        ((ViewGroup) rootView).addView(linearLayout);
    }


}
