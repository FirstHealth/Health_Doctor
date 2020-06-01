package com.wd.model_base;

import com.wd.model_base.Bean.AcceptedBean;
import com.wd.model_base.Bean.BindBean;
import com.wd.model_base.Bean.CheckCodeBean;
import com.wd.model_base.Bean.ChoseBean;
import com.wd.model_base.Bean.DoctorBankCardBean;
import com.wd.model_base.Bean.DoctorBean;
import com.wd.model_base.Bean.DoctorIdCardBean;
import com.wd.model_base.Bean.DoctorInfoBean;
import com.wd.model_base.Bean.DoctorWalletBean;
import com.wd.model_base.Bean.EmailBean;
import com.wd.model_base.Bean.IdentifyBean;
import com.wd.model_base.Bean.ImagerBean;
import com.wd.model_base.Bean.JoinBean;
import com.wd.model_base.Bean.KeShiBean;
import com.wd.model_base.Bean.LoginBean;
import com.wd.model_base.Bean.PicturBean;
import com.wd.model_base.Bean.ResetPwdBean;
import com.wd.model_base.Bean.RevenueBean;
import com.wd.model_base.Bean.SearchSickBean;
import com.wd.model_base.Bean.SettleInBean;
import com.wd.model_base.Bean.ShenBean;
import com.wd.model_base.Bean.SickBean;
import com.wd.model_base.Bean.SickMessageBean;
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
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

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

    //重置密码
    @PUT("doctor/v1/resetUserPwd")
    @FormUrlEncoded
    Observable<ResetPwdBean> doResetPwd(@Field("email")String email, @Field("pwd1")String pwd1, @Field("pwd2")String pwd2);

    //验证二维码
    @POST("doctor/v1/checkCode")
    @FormUrlEncoded
    Observable<CheckCodeBean>doCheckCode(@Field("email")String email, @Field("code")String code);

    //查询医生信息
    @GET("doctor/verify/v1/findDoctorById")
    Observable<DoctorInfoBean> FindDoctorInfo();

    @GET("doctor/sickCircle/v1/findSickCircleList")
    Observable<SickBean> doSick(@Query("departmentId")int departmentId,@Query("page")int page,@Query("count")int count);

    @GET("doctor/sickCircle/v1/findSickCircleInfo")
    Observable<SickMessageBean> doSickMessage(@Query("sickCircleId")int sickCircleId);

    @POST("doctor/sickCircle/verify/v1/publishComment")
    @FormUrlEncoded
    Observable<EmailBean> doPingJia(@Field("sickCircleId")int sickCircleId,@Field("content")String content);

    @GET("user/sickCircle/v1/searchSickCircle")
    Observable<SearchSickBean> doSearch(@Query("keyWord")String keyWord);

    @GET("doctor/v1/findSystemImagePic")
    Observable<ImagerBean> doImage();

    @POST("doctor/verify/v1/chooseImagePic")
    @FormUrlEncoded
    Observable<ChoseBean> doChose(@Field("imagePic")String imagePic);

    @GET("doctor/verify/v1/findDoctorById")
    Observable<DoctorBean> doDoctor();

    @POST("doctor/verify/v1/uploadImagePic")
    Observable<PicturBean> doPictor(@Body RequestBody body);

    @GET("doctor/verify/v1/findMyAdoptedCommentList")
    Observable<AcceptedBean> doAccepted(@Query("page")int page,@Query("count")int count);

    //查询医生钱包
    @GET("doctor/verify/v1/findDoctorWallet")
    Observable<DoctorWalletBean> findDoctorWallet();

    //查询医生收入和支出记录
    @GET("doctor/verify/v1/findDoctorIncomeRecordList")
    Observable<RevenueBean>findDoctorRevenue(@Query("page")int page, @Query("count")int count);

    //查询医生银行卡信息
    @GET("doctor/verify/v1/findDoctorBankCardById")
    Observable<DoctorBankCardBean> findBankCard();

    //查询医生身份证信息
    @GET("doctor/verify/v1/findDoctorIdCardInfo")
    Observable<DoctorIdCardBean>findIdCard();

    @POST()
    @FormUrlEncoded
    Observable<IdentifyBean> doMess(@Url() String url, @Field("grant_type")String grant_type, @Field("client_id")String client_id, @Field("client_secret")String client_secret);

    @POST()
    Observable<IdentifyBean> doBack(@Url() String url,@Body RequestBody body);

    @POST()
    @FormUrlEncoded
    Observable<IdentifyBean> doID(@Url() String url,@Field("access_token")String access_token,@Field("image")String image,@Field("id_card_side")String id_card_side);

    @POST("doctor/verify/v1/bindDoctorBankCard")
    @FormUrlEncoded
    Observable<BindBean> doBind(@Field("bankCardNumber")String bankCardNumber, @Field("bankName")String bankName, @Field("bankCardType")int bankCardType);

    @POST("doctor/verify/v1/bindDoctorIdCard")
    @FormUrlEncoded
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    Observable<ShenBean> doShen(@Body RequestBody body);

    //绑定身份证
    @POST("doctor/verify/v1/bindDoctorIdCard")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Observable<SettleInBean> doBindIdCard(@Body RequestBody body);

}
