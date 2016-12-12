package com.anex13.eveassistent;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.anex13.eveassistent.db.CharDBClass;
import com.anex13.eveassistent.db.DBColumns;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    SharedPreferences spref;
    String barer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        spref = getSharedPreferences(CS.AUTH_PREF, MODE_PRIVATE);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }

        });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        final com.squareup.picasso.Transformation transformation = new RoundedTransformationBuilder()
                .borderColor(Color.BLACK)
                .borderWidthDp(3)
                .cornerRadiusDp(30)
                .oval(false)
                .build();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View rootView = navigationView.getHeaderView(0);
        final ImageView navUsrPic =(ImageView) rootView.findViewById(R.id.nav_headrr_imageView);
        TextView navName = (TextView) rootView.findViewById(R.id.nav_header_name);
        TextView navCorp = (TextView) rootView.findViewById(R.id.nav_header_corp);
        if (spref.getInt(CS.SPREF_DEF_CHAR,0)==0)
        {
            Intent charactivity = new Intent(this, CharManage.class);
            startActivity(charactivity);
        }
        else {
            CharDBClass pers = getCharfrfomdb(spref.getInt(CS.SPREF_DEF_CHAR,0),getApplicationContext());
            navName.setText(pers.getCharName());
            navCorp.setText(pers.getCorpName());
            final String userpicurl = CS.BASE_URL_IMG + CS.CHAR_URL_IMG + pers.getCharID() + CS.IMG_SIZE_128 + ".jpg";
            Picasso.with(getApplicationContext())
                    .load(userpicurl)
                    .networkPolicy(NetworkPolicy.OFFLINE)
                    .transform(transformation)
                    .into(navUsrPic, new Callback() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError() {
                            //Try again online if cache failed
                            Picasso.with(getApplicationContext())
                                    .load(userpicurl)
                                    .transform(transformation)
                                    .into(navUsrPic, new Callback() {
                                        @Override
                                        public void onSuccess() {

                                        }

                                        @Override
                                        public void onError() {
                                            Log.v("Picasso", "Could not fetch image");
                                        }
                                    });
                        }
                    });
        }
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
        switch (id) {
            case R.id.nav_charmanage: {
                Intent charactivity = new Intent(this, CharManage.class);
                startActivity(charactivity);
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
            default: {
                Intent mailactivity = new Intent(this, MailActivity.class);
                startActivity(mailactivity);
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        }

    }


    @Override
    public void onResume() {
        super.onResume();
        barer = "Bearer " + spref.getString(CS.AUTH_TOKEN_TAG, "");
        Log.e("token from spref", barer);
    }
    public static CharDBClass getCharfrfomdb(int charID,Context context) {
        String selection = DBColumns.CharTable.CHAR_CREST_ID + " == ?";
        String[] selectionArgs = new String[]{Integer.toString(charID)};
        Cursor c = null;
        CharDBClass charDBitem = null;
        try {
            c = context.getContentResolver().query(DBColumns.CharTable.CONTENT_URI, null, selection, selectionArgs, null, null);
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
            Log.i("cursor", "close cursr");
        }
        return charDBitem;
    }

}
// TODO: 29.11.2016 ss