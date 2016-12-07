package com.anex13.eveassistent;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
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
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by it.zavod on 22.11.2016.
 */

public class HttpService extends IntentService {
    private static final String ACTION_CREATE_NEW_CHAR = "create char and add to db";
    private static final String ACTION_GET_MAIL = "get mail action";
    private static final String MAIL_CHAR_ID = "char id for getmail";

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
                Log.i("created char", newchar.toString());
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        mainContext.getContentResolver().insert(ContentProvider.CHARS_CONTENT_URI, newchar.toContentValues());
                    }
                }).start();

            }
            break;

            case ACTION_GET_MAIL: {
                int id = intent.getIntExtra(MAIL_CHAR_ID, 0);
                CharDBClass char1 = getCharfrfomdb(id);
                tryToken(char1.getCharID(), char1.getAccesToken());
                updateMailList(char1.getCharID(), char1.getAccesToken());
            }
            default:
                break;
        }
    }

    //auth srv
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
        Call<AuthToken> newToken = service.tokenRefresh("refresh_token", refreshToken);
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
            if (resp.isSuccessful())
                return resp.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }                                          //get id and char name for token  //todo переделать в бд

    public static void tryToken(int charID, String acsToken) {
        getCharShipInfo(acsToken, charID);
    }

    //public data without token
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


    //tokens needed
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
            else refreshTokens(getRefrTokensFromDB(charID), charID);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    private static String getRefrTokensFromDB(int charID) {
        CharDBClass char1 = getCharfrfomdb(charID);


        return char1.getRefreshToken();  //todo get token fromdb for char id
    }

    public static CharDBClass getCharfrfomdb(int charID) {
        String selection = ContentProvider.CHAR_CREST_ID + " == ?";
        String[] selectionArgs = new String[]{Integer.toString(charID)};
        Cursor c = null;
        CharDBClass charDBitem = null;
        try {
            c = mainContext.getContentResolver().query(ContentProvider.CHARS_CONTENT_URI, null, selection, selectionArgs, null, null);
            if (c != null) {
                if (c.moveToFirst()) {
                    do {

                        charDBitem = new CharDBClass(c);


                    } while (c.moveToNext());
                }
            } else {
                Log.d("cursor", "Cursor is null");
            }

        } finally {
            c.close();
            Log.i("cursor", "close srvers cursr");
        }
        return charDBitem;
    }


    public static void updateMailList(int charID, String token) {
        //get mails list
        List<com.anex13.eveassistent.classesForApi.mail.Mail> listmails = null;
        for (com.anex13.eveassistent.classesForApi.mail.Mail mail : listmails) {
            //get mail body
            //create MailDBClass
            //write to db
            //send broadcast finished
        }
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

    public static void updateMail(Context context, int charID) {
        mainContext = context;
        spref = context.getSharedPreferences(CS.AUTH_PREF, MODE_PRIVATE);
        Intent getMail = new Intent(context, HttpService.class);
        getMail.setAction(ACTION_GET_MAIL);
        getMail.putExtra(MAIL_CHAR_ID, charID);
        context.startService(getMail);
    }

    // default methods
    public void onDestroy() {
        super.onDestroy();
    }


}
