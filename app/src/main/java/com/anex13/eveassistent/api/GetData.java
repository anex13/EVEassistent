package com.anex13.eveassistent.api;

import com.anex13.eveassistent.classesForApi.CharPublicData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface GetData {

    @Headers({"Accept: application/json"})
    @GET("characters/{}/?datasource=tranquility")
    Call<CharPublicData> getPublicData(@Path("id") int id);
}


//https://esi.tech.ccp.is/legacy/
/*
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