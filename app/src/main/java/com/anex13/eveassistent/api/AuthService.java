package com.anex13.eveassistent.api;

import com.anex13.eveassistent.classesForApi.AuthToken;
import com.anex13.eveassistent.classesForApi.CharID;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AuthService {
    @FormUrlEncoded
    @Headers({"Authorization: Basic MGYzMTM2Mjc2MGM5NGM5ZjkyODBkZDI5MzQ3ZTljMjQ6QjEwQXBaMkJoc3lKUTVhcVNwSGV6Z2gxS1JWRGVVTlZqSlBFNVpOZA==",
            "Content-Type: application/x-www-form-urlencoded",
            "Host: login.eveonline.com"})
    @POST("oauth/token")
    Call<AuthToken> tokenGet(@Field("grant_type") String gt, @Field("code") String code);

    @FormUrlEncoded
    @Headers({"Authorization: Basic MGYzMTM2Mjc2MGM5NGM5ZjkyODBkZDI5MzQ3ZTljMjQ6QjEwQXBaMkJoc3lKUTVhcVNwSGV6Z2gxS1JWRGVVTlZqSlBFNVpOZA==",
            "Content-Type: application/x-www-form-urlencoded",
            "Host: login.eveonline.com"})
    @POST("oauth/token")
    Call<AuthToken> tokenRefresh(@Field("grant_type") String gt, @Field("refresh_token") String code);

    @Headers({"User-Agent: eveassistant", "Host: login.eveonline.com"})
    @GET("oauth/verify")
    Call<CharID> getID(@Header("Authorization") String auth);
}


/*
private void oauth() {
        String authMain = "https://login.eveonline.com/oauth/authorize/?";
        String authRespType = "response_type=code&";
        String authRedir = "redirect_uri=anexevetest://auth/&";
        String authClientID = "client_id=0f31362760c94c9f9280dd29347e9c24&";
        String authScope = "scope=characterContactsRead&";
        String authState = "state=uniquestate123";
        String authFull = authMain + authRespType + authRedir + authClientID + authScope + authState;
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(authFull));
        startActivity(browserIntent);
    }

    public static String getAuthCode(Intent authintent) {//допилить проверку стэйта
        Uri uri = authintent.getData();
        String valueOne = uri.getQueryParameter("code");
        String valueTwo = uri.getQueryParameter("state");
        return valueOne;
    }

    private void getToken(String code) {
        Gson gson = new GsonBuilder()
                .setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://login.eveonline.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        AuthService service = retrofit.create(AuthService.class);


        Call<AuthToken> token = service.tokenGet("authorization_code", code);

        token.enqueue(new Callback<AuthToken>() {
            @Override
            public void onResponse(Call<AuthToken> call, Response<AuthToken> response) {
                if (!response.isSuccessful()) {
                }
                if (response.isSuccessful()) {

                    SharedPreferences.Editor ed = spref.edit();
                    ed.putString(TOKEN_TAG, response.body().toString());
                    ed.apply();
                }
            }

            @Override
            public void onFailure(Call<AuthToken> call, Throwable t) {
                Log.e("fail", t.getMessage());
            }
        });
    }




 public void getUserID() {
        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl("https://login.eveonline.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AuthService service1 = retrofit1.create(AuthService.class);
        Call<CharID> character = service1.getID(barer);
        character.enqueue(new Callback<CharID>() {
            @Override
            public void onResponse(Call<CharID> call, Response<CharID> response) {
                if (!response.isSuccessful()) {
                }
                if (response.isSuccessful()) {
                    Log.e("id", response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<CharID> call, Throwable t) {

                Log.e("get token fail", "FAIL");
                Log.e("fail", t.getMessage());
            }
        });
    }
*/