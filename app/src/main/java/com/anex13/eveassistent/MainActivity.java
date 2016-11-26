package com.anex13.eveassistent;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.anex13.eveassistent.api.AuthService;
import com.anex13.eveassistent.api.GetData;
import com.anex13.eveassistent.classesForApi.AuthToken;
import com.anex13.eveassistent.classesForApi.CharID;
import com.anex13.eveassistent.classesForApi.CurentPosition;
import com.anex13.eveassistent.classesForApi.SolarSystem;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final String PREF_ID_TAG = "char id";
    SharedPreferences spref;
    String barer;
    public static final String PREF_NAME_TAG="name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button signin = (Button) findViewById(R.id.button);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //start sign in
                refreshToken();
            }
        });
        final Button getData = (Button) findViewById(R.id.button2);
        getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calldata();
            }
        });
        spref = getSharedPreferences(CharManage.TOKEN_PREF, MODE_PRIVATE);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUserID();
            }

        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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
                    Log.e("id", "resp not succes");
                    Log.e("id", response.message());
                    Log.e("id", response.raw().toString());
                    refreshToken();
                }
                if (response.isSuccessful()) {
                    Log.e("id", response.body().toString());
                    SharedPreferences.Editor ed =spref.edit();
                    ed.putString(PREF_NAME_TAG,response.body().getCharacterName());
                    ed.putInt(PREF_ID_TAG,response.body().getCharacterID());
                    ed.apply();
                }
            }

            @Override
            public void onFailure(Call<CharID> call, Throwable t) {

                Log.e("get token fail", "FAIL");
                Log.e("fail", t.getMessage());
            }
        });
        //Log.i("Char", character.toString());

        // POST https://login.eveonline.com/oauth/token HTTP/1.1
        //   Authorization: Basic bG9...ZXQ=
        //   Content-Type: application/x-www-form-urlencoded
        // Host: login.eveonline.com
        //   grant_type=authorization_code&code=gEyuYF_rf...ofM0
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {
            Intent charactivity = new Intent(this, CharManage.class);
            startActivity(charactivity);

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void refreshToken() {
        Gson gson = new GsonBuilder()
              .setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://login.eveonline.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        AuthService service = retrofit.create(AuthService.class);
        String oldToken = spref.getString(CharManage.REF_TOKEN_TAG, "");
        Log.i("old token", spref.getString(CharManage.TOKEN_TAG, ""));
        Log.i("code", spref.getString(CharManage.CODE_TAG, ""));
        Log.i("ref", spref.getString(CharManage.REF_TOKEN_TAG, ""));

        Call<AuthToken> newToken = service.tokenRefresh("refresh_token",oldToken);

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
                    ed.putString(CharManage.TOKEN_TAG, response.body().toString());
                    ed.apply();
                    Log.e("newtoken", spref.getString(CharManage.TOKEN_TAG, ""));
                }
            }

            @Override
            public void onFailure(Call<AuthToken> call, Throwable t) {
                Log.e("get token fail", "FAIL");
                Log.e("fail", t.getMessage());
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        barer = "Bearer " + spref.getString(CharManage.TOKEN_TAG, "");
        Log.e("token from spref", barer);
    }

    public void calldata() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://crest-tq.eveonline.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetData service = retrofit.create(GetData.class);
        String barer1 = "Bearer " + spref.getString(CharManage.TOKEN_TAG, "");
        String path =Integer.toString(spref.getInt(PREF_ID_TAG,0));
        Call<CurentPosition<SolarSystem>> character = service.getData(barer1,path);
        character.enqueue(new Callback<CurentPosition<SolarSystem>>() {
            @Override
            public void onResponse(Call<CurentPosition<SolarSystem>> call, Response<CurentPosition<SolarSystem>> response) {
                if (!response.isSuccessful()) {

                    Log.e("token", "resp not succes");
                    Log.e("token", response.message());
                    Log.e("token", response.raw().toString());
                }
                if (response.isSuccessful()) {
                    Log.e("curent loc", response.toString());
                }
            }

            @Override
            public void onFailure(Call<CurentPosition<SolarSystem>> call, Throwable t) {

                Log.e("get token fail", "FAIL");
                Log.e("fail", t.getMessage());
            }
        });
    }
}

