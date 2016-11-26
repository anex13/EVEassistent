package com.anex13.eveassistent;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;


import com.anex13.eveassistent.api.AuthService;
import com.anex13.eveassistent.classesForApi.AuthToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CharManage extends AppCompatActivity {
    public static final String TOKEN_PREF = "token pref";
    public static final String TOKEN_TAG = "auth token";
    public static final String REF_TOKEN_TAG = "ref token first";
    public static final String CODE_TAG = "auth code";
    final String authBasic = "MGYzMTM2Mjc2MGM5NGM5ZjkyODBkZDI5MzQ3ZTljMjQ6QjEwQXBaMkJoc3lKUTVhcVNwSGV6Z2gxS1JWRGVVTlZqSlBFNVpOZA==";
    Intent intent;
    SharedPreferences spref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_char_manage);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        spref = getSharedPreferences(TOKEN_PREF,MODE_PRIVATE);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oauth();
            }
        });


    }

    private void oauth() {
        String authMain = "https://login.eveonline.com/oauth/authorize/?";
        String authRespType = "response_type=code&";
        String authRedir = "redirect_uri=anexevetest://auth/&";
        String authClientID = "client_id=0f31362760c94c9f9280dd29347e9c24&";
        String authScope = "scope=characterContactsRead characterLocationRead&";
        String authState = "state=uniquestate123";
        String authFull = authMain + authRespType + authRedir + authClientID + authScope + authState;
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(authFull));
        startActivity(browserIntent);
    }

    public String getAuthCode(Intent authintent) {//допилить проверку стэйта
        Uri uri = authintent.getData();
        String valueOne = uri.getQueryParameter("code");
        String valueTwo = uri.getQueryParameter("state");
        Log.e("auth", valueOne);
        Log.e("auth", valueTwo);
        SharedPreferences.Editor ed = spref.edit();
        ed.putString(CODE_TAG, valueOne);
        ed.apply();
        return valueOne;
        // TODO: 22.11.2016 записать куданибудь код и проверить стэйт
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
                    Log.e("token", "resp not succes");
                    Log.e("token", response.message());
                    Log.e("token", response.raw().toString());
                }
                if (response.isSuccessful()) {

                    SharedPreferences.Editor ed = spref.edit();
                    ed.putString(TOKEN_TAG, response.body().toString());
                    ed.putString(REF_TOKEN_TAG,response.body().getRefreshToken());
                    ed.apply();
                    Log.e("token", spref.getString(TOKEN_TAG,""));
                }
            }

            @Override
            public void onFailure(Call<AuthToken> call, Throwable t) {
                Log.e("get token fail", "FAIL");
                Log.e("fail", t.getMessage());
            }
        });
    }



    private void writeCharInfo() {

    }

    @Override
    public void onResume() {
        super.onResume();
        intent = getIntent();
        if (Intent.ACTION_VIEW.equals(intent.getAction())) {
            getToken(getAuthCode(intent));
        }
    }

}
