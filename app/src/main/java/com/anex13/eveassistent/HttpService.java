package com.anex13.eveassistent;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.util.Log;
import com.anex13.eveassistent.api.AuthService;
import com.anex13.eveassistent.api.GetDataESI;
import com.anex13.eveassistent.classesForApi.AuthToken;
import com.anex13.eveassistent.classesForApi.CharID;
import com.anex13.eveassistent.classesForApi.CharPublicData;
import com.anex13.eveassistent.classesForApi.CharShipInfo;
import com.anex13.eveassistent.classesForApi.CorpInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by it.zavod on 22.11.2016.
 */

public class HttpService extends IntentService {
    private static final String ACTION_CREATE_NEW_CHAR = "create char and add to db";

    public HttpService() {
        super("httpservice");
    }

    static Context mainContext;
    static SharedPreferences spref;


    @Override
    protected void onHandleIntent(Intent intent) {
        switch (intent.getAction()) {
            case ACTION_CREATE_NEW_CHAR: {
                String code = intent.getStringExtra(CS.AUTH_CODE_TAG);
                AuthToken token = getTokens(code);
                CharID id = validate(token.getAccessToken());
                CharPublicData charData = getPublicData(id.getCharacterID());
                CharShipInfo ship = getCharShipInfo(token.getAccessToken(), id.getCharacterID());
                CorpInfo corp = getCorpInfo(charData.getCorporationId());
                final CharDBClass newchar = new CharDBClass(
                        token.getAccessToken(),
                        token.getRefreshToken(),
                        id.getCharacterID(),
                        id.getCharacterName(),
                        charData.getGender(),
                        charData.getBirthday(),
                        charData.getRaceId(),
                        charData.getDescription(),
                        charData.getCorporationId(),
                        corp.getCorporationName(),
                        corp.getMemberCount(),
                        corp.getTicker(),
                        ship.getShipName(),
                        ship.getShipTypeId(),
                        ship.getShipItemId());
                Log.i("created char",newchar.toString());
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        mainContext.getContentResolver().insert(ContentProvider.CHARS_CONTENT_URI, newchar.toContentValues());
                    }
                }).start();

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
                .baseUrl(CS.BASE_URL_AUTH)
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
        return null;
    }                                       //exchange acces code for tokens  //todo переделать в бд

    @Nullable
    public static AuthToken refreshTokens(String refreshToken, int charID) {
        Gson gson = new GsonBuilder()
                .setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CS.BASE_URL_AUTH)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        AuthService service = retrofit.create(AuthService.class);
        String oldToken = spref.getString(CS.AUTH_REFRESH_TOKEN_TAG, "");
        Log.i("old token", spref.getString(CS.AUTH_TOKEN_TAG, ""));
        Log.i("code", spref.getString(CS.AUTH_CODE_TAG, ""));
        Log.i("ref", spref.getString(CS.AUTH_REFRESH_TOKEN_TAG, ""));

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
                .baseUrl(CS.BASE_URL_AUTH)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AuthService service = retrofit.create(AuthService.class);
        String barer = "Bearer " + accsToken;
        Call<CharID> character = service.getID(barer);
        try {
            Response<CharID> resp = character.execute();
            ;
            return resp.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }                                          //get id and char name for token  //todo переделать в бд

    @Nullable
    private static CharPublicData getPublicData(int charID) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CS.BASE_URL_ESI)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetDataESI service = retrofit.create(GetDataESI.class);
        String barer = "Bearer " + spref.getString(CS.AUTH_TOKEN_TAG, "");
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

    @Nullable
    private static CharShipInfo getCharShipInfo(String token, int charID) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CS.BASE_URL_ESI)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetDataESI service = retrofit.create(GetDataESI.class);
        String barer = "Bearer " + token;
        Call<CharShipInfo> character = service.getCharShipInfo(charID, barer);
        try {
            Response<CharShipInfo> resp = character.execute();
            if (resp.isSuccessful())
                return resp.body();
            else refreshTokens(getTokensFromDB(charID), charID);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Nullable
    private static CorpInfo getCorpInfo(int corpID) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CS.BASE_URL_ESI)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetDataESI service = retrofit.create(GetDataESI.class);
        Call<CorpInfo> character = service.getCorpInfo(corpID);
        try {
            Response<CorpInfo> resp = character.execute();
            if (resp.isSuccessful())
                return resp.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getTokensFromDB(int charID) {
        return "token";  //todo get token fromdb for char id
    }


//вызовы

    public static void charCreateNew(Context context, String authcode) {
        mainContext = context;
        spref = context.getSharedPreferences(CS.AUTH_PREF, MODE_PRIVATE);
        Intent newChar = new Intent(context, HttpService.class);
        newChar.setAction(ACTION_CREATE_NEW_CHAR);
        newChar.putExtra(CS.AUTH_CODE_TAG, authcode);
        context.startService(newChar);
    }


    // default methods
    public void onDestroy() {
        super.onDestroy();
    }


}
