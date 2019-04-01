package cn.company1075.xiaofu;

import java.util.Map;

import cn.company1075.xiaofu.utils.ADBean;
import cn.company1075.xiaofu.utils.Goods;
import cn.company1075.xiaofu.utils.GoodsDetail;
import cn.company1075.xiaofu.utils.MyResult;
import cn.company1075.xiaofu.utils.ShopData;
import cn.company1075.xiaofu.utils.ShopUserDeviceMan;
import cn.company1075.xiaofu.utils.Skin;
import cn.company1075.xiaofu.utils.SkinDeatil;
import cn.company1075.xiaofu.utils.Tc;
import cn.company1075.xiaofu.utils.UserData;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

public interface GankService {

    //全脸分析带性别 年龄
    @Multipart
    @POST("external/putDistinguishResult2")
    Call<SkinDeatil> uploadFile3(@PartMap Map<String, RequestBody> params, @Query("appId")String appId, @Query("sex")String sex, @Query("age")String age);

    //测试返回数据
    @Multipart
    @POST("external/putdistinguishresult2")
    Call<ResponseBody> uploadFile2(@PartMap Map<String, RequestBody> params, @Query("appId") String appId, @Query("sex")String sex, @Query("age")String age);

    //获得产品介绍的网格列表
    @GET("/shopselplagodcontroller/selallshopgods")
    Call<Goods> getShopGoods(@Query("shopId") int shopId , @Query("page") int page, @Query("limit") int limit);

    //获得产品详情
    @POST("/platformgodscontroller/selgoodbygoodid")
    Call<GoodsDetail> getShopGoodsDetail(@Query("goodId") Long goodId);

    //获得产品广告
    @GET("/shopadcontroller/selallshopad")
    Call<ADBean> getAD(@Query("shopId") int shopId , @Query("page") int page, @Query("limit") int limit);

    //获得所有平台用户
    @GET("/plauser/selallplaformuser")
    Call<UserData> getUserData();

    //获得商铺
    @FormUrlEncoded
    @POST("/shopmancontroller/getallshop")
    Call<ShopData> getAllShop(@Field("platformUserId")long  latformUserId);

    //获得套餐全部图片
    @POST("/shopselplapaccontroller/selallshoppacs")
    Call<Tc> getTcAll(@Query("shopId") int shopId);

    //绑定数据
//    @POST("/shopuserdevicemancontroller/addshopuserevice")
//    Call<ResponseBody> Bind(@Body ShopUserDeviceMan shopUserDeviceMan);

    //绑定数据
    @POST("/shopuserdevicemancontroller/addshopuserevice")
    Call<MyResult> Bind2(@Query("shopId") String shopId , @Query("mirrorName") String mirrorName,
                         @Query("mirrorMac") String mirrorMac, @Query("mirrorVersion")String mirrorVersion );

    //获得后端返回的全脸测试结果
    @POST("skinddeatilcontroller/getskindeatil")
    Call<ResponseBody> getFullSkinData(@Body SkinDeatil skinDeatil);

    //登录
    @POST("shopusercontroller/login")
    Call<ResponseBody> Login(@Query("shopUserOpenid")String name,@Query("shopUserToken")String pwd);

    //获得推荐产品
    @GET("/shopselplagodcontroller/selpushgoods")
    Call<Goods> getRecommendGoods(@Query("shopId") int shopId ,@Query("goodTag")int goodTag, @Query("page") int page, @Query("limit") int limit);

}
