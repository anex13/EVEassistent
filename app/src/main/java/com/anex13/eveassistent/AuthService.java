package com.anex13.eveassistent;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface AuthService {
    @FormUrlEncoded
    @Headers({"Authorization: Basic MGYzMTM2Mjc2MGM5NGM5ZjkyODBkZDI5MzQ3ZTljMjQ6QjEwQXBaMkJoc3lKUTVhcVNwSGV6Z2gxS1JWRGVVTlZqSlBFNVpOZA==",
            "Content-Type: application/x-www-form-urlencoded",
            "Host: login.eveonline.com"})
    @POST("oauth/token")
    Call<AuthToken> tokenGet(@Field("grant_type") String gt,@Field("code") String code);


    @Headers({"User-Agent: eveassistant", "Host: login.eveonline.com"})
    @GET("oauth/verify")
    Call<CharID> getID(@Header("Authorization") String auth);

    // GET https://login.eveonline.com/oauth/verify HTTP/1.1

    //User-Agent: ...
    // Authorization: Bearer uNEEh...a_WpiaA2
    // Host: login.eveonline.com

}

