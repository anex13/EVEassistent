package com.anex13.eveassistent;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;

import com.anex13.eveassistent.api.AuthService;
import com.anex13.eveassistent.api.GetData;
import com.anex13.eveassistent.classesForApi.AuthToken;
import com.anex13.eveassistent.classesForApi.CharID;
import com.anex13.eveassistent.classesForApi.CharPublicData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;

/**
 * Created by it.zavod on 22.11.2016.
 */

public class HttpService extends IntentService {
    private static final String ACTION_GET_TOKENS = "exchange tokens";
    private static final String ACTION_REFRESH_TOKENS = "refresh tokens";
    private static final String ACTION_GET_CHAR_DATA = "get char initial data";

    public HttpService() {
        super("httpservice");
    }

    static Context mainContext;
    static SharedPreferences spref;


    @Override
    protected void onHandleIntent(Intent intent) {
        switch (intent.getAction()) {
            case ACTION_GET_TOKENS: {
                String code = intent.getStringExtra(ConstStr.AUTH_CODE_TAG);
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
                            Log.e("token", "resp not succes");
                            Log.e("token", response.message());
                            Log.e("token", response.raw().toString());
                        }
                        if (response.isSuccessful()) {
                            SharedPreferences.Editor ed = spref.edit();
                            ed.putString(ConstStr.AUTH_TOKEN_TAG, response.body().getAccessToken());
                            ed.putString(ConstStr.AUTH_REFRESH_TOKEN_TAG, response.body().getRefreshToken());
                            ed.apply();
                            Log.e("token", spref.getString(ConstStr.AUTH_TOKEN_TAG, ""));
                        }
                    }

                    @Override
                    public void onFailure(Call<AuthToken> call, Throwable t) {
                        Log.e("get token fail", "FAIL");
                        Log.e("fail", t.getMessage());
                    }
                });
            }
            break;
            case ACTION_REFRESH_TOKENS:
                Gson gson = new GsonBuilder()
                        .setLenient().create();
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://login.eveonline.com/")
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();
                AuthService service = retrofit.create(AuthService.class);
                String oldToken = spref.getString(ConstStr.AUTH_REFRESH_TOKEN_TAG, "");
                Log.i("old token", spref.getString(ConstStr.AUTH_TOKEN_TAG, ""));
                Log.i("code", spref.getString(ConstStr.AUTH_CODE_TAG, ""));
                Log.i("ref", spref.getString(ConstStr.AUTH_REFRESH_TOKEN_TAG, ""));

                Call<AuthToken> newToken = service.tokenRefresh("refresh_token", oldToken);

                newToken.enqueue(new Callback<AuthToken>() {
                    @Override
                    public void onResponse(Call<AuthToken> call, Response<AuthToken> response) {
                        if (!response.isSuccessful()) {
                            Log.e("token", "resp not succes");
                            Log.e("token", response.message());
                            Log.e("token", response.raw().toString());
                        }
                        if (response.isSuccessful()) {

                            SharedPreferences.Editor ed = spref.edit();
                            ed.putString(ConstStr.AUTH_TOKEN_TAG, response.body().toString());
                            ed.apply();
                            Log.e("newtoken", spref.getString(ConstStr.AUTH_TOKEN_TAG, ""));
                        }
                    }

                    @Override
                    public void onFailure(Call<AuthToken> call, Throwable t) {
                        Log.e("get token fail", "FAIL");
                        Log.e("fail", t.getMessage());
                    }
                });
                break;
            case ACTION_GET_CHAR_DATA:
                if (getUserID()){


                }

                break;
            default:
                break;
        }
    }

    public static void getTokens(Context context, String code) {
        mainContext = context;
        spref = context.getSharedPreferences(ConstStr.AUTH_PREF, MODE_PRIVATE);
        Intent getTokenIntent = new Intent(context,HttpService.class);
        getTokenIntent.setAction(ACTION_GET_TOKENS);
        getTokenIntent.putExtra(ConstStr.AUTH_CODE_TAG,code);
        context.startService(getTokenIntent);
    }
    public static void refreshTokens(Context context) {
        mainContext = context;
        spref = context.getSharedPreferences(ConstStr.AUTH_PREF, MODE_PRIVATE);
        Intent getTokenIntent = new Intent(context,HttpService.class);
        getTokenIntent.setAction(ACTION_REFRESH_TOKENS);
        context.startService(getTokenIntent);
    }
    public static void getCharInitialData(Context context) {
        mainContext = context;
        spref = context.getSharedPreferences(ConstStr.AUTH_PREF, MODE_PRIVATE);
        Intent getTokenIntent = new Intent(context, HttpService.class);
        getTokenIntent.setAction(ACTION_GET_CHAR_DATA);
        context.startService(getTokenIntent);
    }
    public void onDestroy() {
        super.onDestroy();
    }

    public boolean getUserID() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://login.eveonline.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AuthService service = retrofit.create(AuthService.class);
        String barer = "Bearer " + spref.getString(ConstStr.AUTH_TOKEN_TAG, "");
        Call<CharID> character = service.getID(barer);
        character.enqueue(new Callback<CharID>() {
            @Override
            public void onResponse(Call<CharID> call, Response<CharID> response) {
                if (!response.isSuccessful()) {
                    Log.e("id", "resp not succes");
                    Log.e("id", response.message());
                    Log.e("id", response.raw().toString());
                }
                if (response.isSuccessful()) {
                    Log.e("id", response.body().toString());
                    SharedPreferences.Editor ed = spref.edit();
                    ed.putString(ConstStr.CHAR_NAME, response.body().getCharacterName());
                    ed.putInt(ConstStr.CHAR_ID,response.body().getCharacterID());
                    ed.apply();
                }
            }

            @Override
            public void onFailure(Call<CharID> call, Throwable t) {

                Log.e("get token fail", "FAIL");
                Log.e("fail", t.getMessage());
            }
        });
        return character.isExecuted();
    }

    public boolean getPublicData(int charID) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://login.eveonline.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetData service = retrofit.create(GetData.class);
        String barer = "Bearer " + spref.getString(ConstStr.AUTH_TOKEN_TAG, "");
        Call<CharPublicData> character = service.getPublicData(charID);
        character.enqueue(new Callback<CharPublicData>() {
            @Override
            public void onResponse(Call<CharPublicData> call, Response<CharPublicData> response) {
                if (!response.isSuccessful()) {
                    Log.e("id", "resp not succes");
                    Log.e("id", response.message());
                    Log.e("id", response.raw().toString());
                }
                if (response.isSuccessful()) {
                    Log.e("id", response.body().toString());
                    SharedPreferences.Editor ed = spref.edit();
                    //записать в шаредпрефы поля
                    ed.apply();
                }
            }

            @Override
            public void onFailure(Call<CharPublicData> call, Throwable t) {
                Log.e("get token fail", "FAIL");
                Log.e("fail", t.getMessage());
            }
        });
        return character.isExecuted();
    }
}
