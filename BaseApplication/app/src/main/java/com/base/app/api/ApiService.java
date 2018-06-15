package com.base.app.api;


import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {

    @FormUrlEncoded
    @POST("serviceHit.php/")
    Observable<Object> loginUser(
            @Field("username") String userName,
            @Field("password") String password,
            @Field("user_path") String userPath,
            @Field("service") String service,
            @Field("category") String category,
            @Field("subcategory") String subCategory,
            @Field("form_ids") String formIds);

}
