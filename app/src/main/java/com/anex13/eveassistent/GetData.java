package com.anex13.eveassistent;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface GetData {
    @Headers({"User-Agent: eveassistant", "Host: login.eveonline.com"})
    @GET("{path}")
    Call<CharID> getData(@Header("Authorization") String auth, @Path("path") String path);
}
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