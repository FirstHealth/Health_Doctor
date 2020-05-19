package com.wd.model_base;

import com.wd.model_base.Bean.EmailBean;
import com.wd.model_base.Bean.KeShiBean;
import com.wd.model_base.Bean.ZhiChengBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
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
}
