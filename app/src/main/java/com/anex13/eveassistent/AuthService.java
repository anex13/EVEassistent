package com.anex13.eveassistent;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface AuthService {

    @POST("oauth/token/")
    Call<AuthToken> tokenGet(@Header("Authorization") String basic, @Header("Content-Type") String ct, @Header("Host") String host, @Body String body);


    @Headers({"User-Agent: ...","Host: login.eveonline.com"})
    @GET("oauth/verify")
    Call<CharID> getID(@Header("Authorization") String auth);

    // GET https://login.eveonline.com/oauth/verify HTTP/1.1

    //User-Agent: ...
    // Authorization: Bearer uNEEh...a_WpiaA2
    // Host: login.eveonline.com

}

