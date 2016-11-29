package com.anex13.eveassistent;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import com.anex13.eveassistent.api.AuthService;
import com.anex13.eveassistent.api.GetData;
import com.anex13.eveassistent.classesForApi.AuthToken;
import com.anex13.eveassistent.classesForApi.CharID;
import com.anex13.eveassistent.classesForApi.CharPublicData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

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
    private static final String ACTION_CREATE_NEW_CHAR = "create char and add to db";
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
            case ACTION_CREATE_NEW_CHAR: {
                String code =intent.getStringExtra(ContentProvider.CHAR_ACS_CODE);
                AuthToken token = getTokens(code);
                CharID id =validate(token.getAccessToken());
                CharPublicData charData = getPublicData(id.getCharacterID());
                String accstoken =token.getAccessToken();



            }
            break;

            default:
                break;
        }
    }
// внутренние
    private static void updateToken(String accessToken, String refreshToken) {
    }                  //writ tokens to db  //todo переделать в бд

    @Nullable
    private static AuthToken getTokens(String authcode) {
        Gson gson = new GsonBuilder()
                .setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://login.eveonline.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        AuthService service = retrofit.create(AuthService.class);
        Call<AuthToken> token = service.tokenGet("authorization_code", authcode);
        try {
            Response<AuthToken> resp = token.execute();
            if (resp.isSuccessful()) {
                return resp.body();
            } else {
                Log.e("token", resp.message());
                Log.e("token", resp.raw().toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    return null;}                                       //exchange acces code for tokens  //todo переделать в бд

    @Nullable
    private static AuthToken refreshTokens(String refreshToken,int charID) {
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
        try {
            Response<AuthToken> resp = newToken.execute();
            if (resp.isSuccessful()) {
                return resp.body();
            } else {
                Log.e("token", resp.message());
                Log.e("token", resp.raw().toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

return null;
    }                    //refresh tokens   //todo переделать в бд

    @Nullable
    private static CharID validate(String accsToken) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://login.eveonline.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AuthService service = retrofit.create(AuthService.class);
        String barer = "Bearer " + spref.getString(ConstStr.AUTH_TOKEN_TAG, "");
        Call<CharID> character = service.getID(barer);
        try {
            Response<CharID> resp = character.execute();;
            return resp.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }                                          //get id and char name for token  //todo переделать в бд

    @Nullable
    private static CharPublicData getPublicData(int charID) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://login.eveonline.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetData service = retrofit.create(GetData.class);
        String barer = "Bearer " + spref.getString(ConstStr.AUTH_TOKEN_TAG, "");
        Call<CharPublicData> character = service.getPublicData(charID);
        try {
            Response<CharPublicData> resp = character.execute();
            if (resp.isSuccessful())
                return resp.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }                                   //get char public data for charID

//вызовы

    public static void charCreateNew (Context context,String authcode ) {
        mainContext = context;
        spref = context.getSharedPreferences(ConstStr.AUTH_PREF, MODE_PRIVATE);
        Intent newChar = new Intent(context, HttpService.class);
        newChar.setAction(ACTION_CREATE_NEW_CHAR);
        newChar.putExtra(ContentProvider.CHAR_ACS_CODE,authcode);
        context.startService(newChar);
    }




// default methods
    public void onDestroy() {
        super.onDestroy();
    }
}
