package com.anex13.eveassistent;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CharManage extends AppCompatActivity {
    private static final String TOKEN_PREF ="token pref" ;
    private static final String TOKEN_TAG = "auth token";
    final String authBasic = "MGYzMTM2Mjc2MGM5NGM5ZjkyODBkZDI5MzQ3ZTljMjQ6QjEwQXBaMkJoc3lKUTVhcVNwSGV6Z2gxS1JWRGVVTlZqSlBFNVpOZA==";
    Intent intent ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_char_manage);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
        String authScope = "scope=characterContactsRead&";
        String authState = "state=uniquestate123";
        String authFull = authMain + authRespType + authRedir + authClientID + authScope + authState;
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(authFull));
        startActivity(browserIntent);
    }
    public static String getAuthCode (Intent authintent){//допилить проверку стэйта
        Uri uri = authintent.getData();
        String valueOne = uri.getQueryParameter("code");
        String valueTwo = uri.getQueryParameter("state");
        Log.e("auth", valueOne);
        Log.e("auth", valueTwo);
        return valueOne;
        // TODO: 22.11.2016 записать куданибудь код и проверить стэйт
    }

    private void getToken(String code){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://login.eveonline.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AuthService service = retrofit.create(AuthService.class);
        String basic="Basic "+authBasic;
        String content="application/x-www-form-urlencoded";
        String host ="login.eveonline.com";
        String body ="{\n" +
                "  \"grant_type\":\"authorization_code\",\n" +
                "  \"code\":\""+code+"\"\n" +
                "}";

          Call<AuthToken> token = service.tokenGet(basic,content,host,body);

        SharedPreferences spref = getApplicationContext().getSharedPreferences(TOKEN_PREF,MODE_PRIVATE);


       SharedPreferences.Editor ed=spref.edit();
        ed.putString(TOKEN_TAG,token.toString());
        Log.i("token",token.toString());
        String barer="Bearer "+token.toString();
        Call<CharID> character= service.getID(barer);
        Log.i("Char",character.toString());

       // POST https://login.eveonline.com/oauth/token HTTP/1.1

     //   Authorization: Basic bG9...ZXQ=
             //   Content-Type: application/x-www-form-urlencoded
       // Host: login.eveonline.com

             //   grant_type=authorization_code&code=gEyuYF_rf...ofM0


    }
    private void writeCharInfo(){

    }
@Override
    public void onResume(){
    super.onResume();
    intent= getIntent();
    if (Intent.ACTION_VIEW.equals(intent.getAction())) {
        getToken(getAuthCode(intent));
    }
}

}
