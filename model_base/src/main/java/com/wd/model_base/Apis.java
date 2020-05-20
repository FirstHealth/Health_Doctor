package com.wd.model_base;

import com.wd.model_base.Bean.EmailBean;
import com.wd.model_base.Bean.JoinBean;
import com.wd.model_base.Bean.KeShiBean;
import com.wd.model_base.Bean.LoginBean;
import com.wd.model_base.Bean.ZhiChengBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * @ClassName Apis
 * @Description TODO
 * @Author tys
 * @Date 2020/5/1720:46
 */
public interface Apis {

    @POST("doctor/v1/sendEmailCode")
    @FormUrlEncoded
    Observable<EmailBean> doEmail(@Field("email")String email);

    @GET("share/knowledgeBase/v1/findDepartment")
    Observable<KeShiBean> doKeShi();

    @GET("doctor/v1/findJobTitleList")
    Observable<ZhiChengBean> doZhiCheng();

    @POST("doctor/v1/applyJoin")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Observable<JoinBean> doSettleln(@Body RequestBody requestBody);

    //登录
    @POST("doctor/v1/login")
    @FormUrlEncoded
    Observable<LoginBean> doLogin(@Field("email")String email, @Field("pwd")String pwd);

}
