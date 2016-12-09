package com.anex13.eveassistent;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.anex13.eveassistent.dbAdapters.CharAdapter;
import com.anex13.eveassistent.dbAdapters.MailAdapter;

public class MailActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int MENU_DEL_CHAR = 1;
    Intent intent;
    SharedPreferences spref;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);
        spref = getSharedPreferences(CS.AUTH_PREF, MODE_PRIVATE);
        lv = (ListView) findViewById(R.id.mail_mail_list);
        getLoaderManager().initLoader(123, null, this);
        Button back = (Button) findViewById(R.id.mail_back_btn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        Button newMail = (Button) findViewById(R.id.mail_new_mail_btn);
        newMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //// TODO: 07.12.2016 запилить вызов активити нюмэйл
            }
        });
        Button update = (Button) findViewById(R.id.mail_update_btn);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int charID = spref.getInt(CS.SPREF_DEF_CHAR, 0);
                if (charID != 0)
                    HttpService.updateMail(getApplicationContext(), charID);
                else Log.i("DEFAULT CHAR", "not set");
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri uri = Uri.parse(DBColumns.MailTable.CONTENT_URI + "/char/" + spref.getInt(CS.SPREF_DEF_CHAR, 0));
        return new CursorLoader(this, uri, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        lv.setAdapter(new MailAdapter(this, data));
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void onBackPressed() {
        Intent tomainactivity = new Intent(this, MainActivity.class);
        startActivity(tomainactivity);
    }
}