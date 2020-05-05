package com.bawei.dianshangjinweek03.contact;

import com.bawei.dianshangjinweek03.bean.Login;
import com.bawei.dianshangjinweek03.bean.OrderList;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IContact {
    interface IView<T> {
        void success(T t);
        void fail(String err);
    }
    interface IRequest{
        @POST("small/user/v1/login")
        @FormUrlEncoded
        Observable<Login> login(@Field("phone") String phone,@Field("pwd") String pwd);
        @GET("small/order/verify/v1/findOrderListByStatus")
        Observable<OrderList> findOrderListByStatus(@Header("userId") int userId, @Header("sessionId") String sessionId,
                                                    @Query("status") int status, @Query("page") int page, @Query("count") int count);
    }
}
