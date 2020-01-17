package com.bawei.day0117;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * ProjectName: Day0117
 * PackageName: com.bawei.day0117
 * ClassName:   FindOrderService
 * Description: Java类的作用
 * Author: Lazy
 * CreateDate: 2020/1/17_9:17
 */
public interface FindOrderService {

    @GET(Api.FIND_ORDER_URL)
    Observable<FindOrderEntity> getData(@Header("userId") String uId, @Header("sessionId") String ssId, @Query("status") int status, @Query("page") int page, @Query("count") int count);
}
