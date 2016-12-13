package com.anex13.eveassistent;

import android.app.IntentService;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import com.anex13.eveassistent.api.AuthService;
import com.anex13.eveassistent.api.GetDataESI;
import com.anex13.eveassistent.classesForApi.AuthToken;
import com.anex13.eveassistent.classesForApi.CharID;
import com.anex13.eveassistent.classesForApi.CharPublicData;
import com.anex13.eveassistent.classesForApi.CharShipInfo;
import com.anex13.eveassistent.classesForApi.CorpInfo;
import com.anex13.eveassistent.classesForApi.Skill;
import com.anex13.eveassistent.classesForApi.SkillsDone;
import com.anex13.eveassistent.classesForApi.mail.Mail;
import com.anex13.eveassistent.classesForApi.mail.MailHeaders;
import com.anex13.eveassistent.classesForApi.Wallet;
import com.anex13.eveassistent.db.CharDBClass;
import com.anex13.eveassistent.db.DBColumns;
import com.anex13.eveassistent.db.MailDBClass;
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
    private static final String ACTION_UPDATE_ALL_CHARS = "update all chars main info";

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
                List<Wallet> wallets = getWallet(id.getCharacterID(), token.getAccessToken());
                SkillsDone skillsDone = getSkills(id.getCharacterID(), token.getAccessToken());
                Wallet wallet = wallets.get(0);
                if (wallet.getWalletId() != 1000)
                    wallet = wallets.get(1);
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
                        ship.getShipItemId(),
                        wallet.getBalance(),
                        skillsDone.getTotalSp());
                Log.i("created char", newchar.toString());
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        mainContext.getContentResolver().insert(DBColumns.CharTable.CONTENT_URI, newchar.toContentValues());
                    }
                }).start();
                SharedPreferences.Editor editor = spref.edit();
                editor.putInt(CS.SPREF_DEF_CHAR, id.getCharacterID());
                editor.apply();
                break;
            }

            case ACTION_GET_MAIL: {
                int id = intent.getIntExtra(MAIL_CHAR_ID, 0);
                CharDBClass char1 = getCharfrfomdb(id);
                tryToken(char1.getAccesToken(), char1.getCharID());
                //List<MailHeaders> list =updateMailList(char1.getCharID(),char1.getAccesToken());
                //if (list!=null)
                getMailtoDB(updateMailList(char1.getCharID(), char1.getAccesToken()), char1.getCharID(), char1.getAccesToken());
                // else Log.i("mail list","list is null");
            }
            case ACTION_UPDATE_ALL_CHARS: {
                Cursor c=null;
                try {
                    c = mainContext.getContentResolver().query(DBColumns.CharTable.CONTENT_URI, null, null, null, null, null);
                    if (c != null) {
                        if (c.moveToFirst()) {
                            do {

                                CharDBClass user = new CharDBClass(c);
                                CharPublicData pd=getPublicData(user.getCharID());
                                CorpInfo ci=getCorpInfo(pd.getCorporationId());
                                CharShipInfo ship=getCharShipInfo(user.getAccesToken(),user.getCharID());
                                Wallet isk=getWallet(user.getCharID(),)



                            } while (c.moveToNext());

                        }
                    } else {
                        Log.d("chars udate all", "Cursor is null");
                    }

                } finally {
                    if (c!=null)
                    c.close();
                    Log.i("chars udate all", "close srvers cursr");
                }
            }

            default:
                break;
        }
    }

    // auth srv
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

    public static void refreshTokens(int charID, final Context context) {
        final Uri uri = ContentUris.withAppendedId(DBColumns.CharTable.CONTENT_URI, charID);
        final CharDBClass pers = getCharfrfomdb(charID);
        String refreshToken = pers.getRefreshToken();
        Gson gson = new GsonBuilder()
                .setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CS.BASE_URL_AUTH)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        AuthService service = retrofit.create(AuthService.class);
        Call<AuthToken> newToken = service.tokenRefresh("refresh_token", refreshToken);
        try {
            final Response<AuthToken> resp = newToken.execute();
            if (resp.isSuccessful()) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        pers.setAccesToken(resp.body().getAccessToken());
                        pers.setRefreshToken(resp.body().getRefreshToken());
                        context.getContentResolver().update(uri, pers.toContentValues(), null, null);
                    }
                }).start();
            } else {
                Log.e("token", resp.message());
                Log.e("token", resp.raw().toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }                       //refresh tokens   //todo переделать в бд

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

    public static void tryToken(String acsToken, int charID) {
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


    // tokens needed
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
            else refreshTokens(charID, mainContext);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static CharDBClass getCharfrfomdb(int charID) {
        String selection = DBColumns.CharTable.CHAR_CREST_ID + " == ?";
        String[] selectionArgs = new String[]{Integer.toString(charID)};
        Cursor c = null;
        CharDBClass charDBitem = null;
        try {
            c = mainContext.getContentResolver().query(DBColumns.CharTable.CONTENT_URI, null, selection, selectionArgs, null, null);
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
            if (c != null)
                c.close();
            Log.i("cursor", "close cursr");
        }
        return charDBitem;
    }

    @Nullable
    public static List<MailHeaders> updateMailList(int charID, String accsToken) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CS.BASE_URL_ESI)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetDataESI service = retrofit.create(GetDataESI.class);
        Call<List<MailHeaders>> mails = service.getMailHeaders(charID, ("Bearer " + accsToken));
        try {
            Response<List<MailHeaders>> resp = mails.execute();
            if (resp.isSuccessful())
                return resp.body();
            else {
                Log.e("mailList", resp.raw().toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Nullable
    public static List<Wallet> getWallet(int charID, String accsToken) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CS.BASE_URL_ESI)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetDataESI service = retrofit.create(GetDataESI.class);
        String barer = "Bearer " + accsToken;
        Call<List<Wallet>> character = service.getWallet(charID, barer);
        try {
            Response<List<Wallet>> resp = character.execute();
            if (resp.isSuccessful())
                return resp.body();
            else refreshTokens(charID, mainContext);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Nullable
    public static SkillsDone getSkills(int charID, String accsToken) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://esi.tech.ccp.is/dev/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetDataESI service = retrofit.create(GetDataESI.class);
        String barer = "Bearer " + accsToken;
        Call<SkillsDone> skills = service.getSkillDone(charID, barer);
        try {
            Response<SkillsDone> resp = skills.execute();
            if (resp.isSuccessful())
                return resp.body();
            else refreshTokens(charID, mainContext);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void getMailtoDB(List<MailHeaders> listmails, int charID, String accsToken) {
        if (listmails == null)
            Log.i("getMailtoDB", "entry list is empty");
        else {
            for (MailHeaders mail : listmails) {
                MailDBClass mailfull = null;
                Retrofit retrofita = new Retrofit.Builder()
                        .baseUrl(CS.BASE_URL_ESI)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                GetDataESI servicea = retrofita.create(GetDataESI.class);
                Call<Mail> mailbody = servicea.getMailBody(charID, mail.getMailId(), ("Barer " + accsToken));
                try {
                    Response<Mail> resp = mailbody.execute();
                    if (resp.isSuccessful())

                        mailfull = new MailDBClass(mail.getIsRead(), mail.getMailId(), mail.getFrom(), getPublicData(charID).getName(), mail.getSubject(), resp.body().getBody(), resp.body().getTimestamp(), charID);
                    Log.e("MAil", "mail " + resp.body().getSubject() + "got ");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (mailfull != null)
                    mainContext.getContentResolver().insert(DBColumns.MailTable.CONTENT_URI, mailfull.toContentValues());
            }
        }
    }

    public static void getSkillsToDB(List<Skill> skills, int charID) {

    }



    // вызовы
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

    public static void updateAllCharsMainInfo(Context context) {
        mainContext = context;
        spref = context.getSharedPreferences(CS.AUTH_PREF, MODE_PRIVATE);
        Intent charsUpdate = new Intent(context, HttpService.class);
        charsUpdate.setAction(ACTION_UPDATE_ALL_CHARS);
        context.startService(charsUpdate);

    }





    // default methods
    public void onDestroy() {
        super.onDestroy();
    }


}
