package com.anex13.eveassistent;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

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


        }

        @Override
        public void onResume() {
            super.onResume();
        }

        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {

            return new CursorLoader(this, ContentProvider.CHARS_CONTENT_URI, null, null, null, null);
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
            lv.setAdapter(new CharAdapter(this, data));
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