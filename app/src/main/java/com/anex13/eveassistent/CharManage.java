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
import android.widget.ImageButton;
import android.widget.ListView;

public class CharManage extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    Intent intent;
    SharedPreferences spref;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_char_manage);
        spref = getSharedPreferences(ConstStr.AUTH_PREF, MODE_PRIVATE);
        ImageButton login = (ImageButton) findViewById(R.id.imageButton);
        lv = (ListView) findViewById(R.id.chars_list);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                charAddStart();
            }
        });
        getLoaderManager().initLoader(123,null,this);


    }

    public String getAuthCode(Intent authintent) {//допилить проверку стэйта
        Uri uri = authintent.getData();
        String valueOne = uri.getQueryParameter("code");
        String valueTwo = uri.getQueryParameter("state");
        Log.e("auth", valueOne);
        Log.e("auth", valueTwo);
        SharedPreferences.Editor ed = spref.edit();
        ed.putString(ConstStr.AUTH_CODE_TAG, valueOne);
        ed.apply();
        return valueOne;
        // TODO: 22.11.2016 записать куданибудь код и проверить стэйт
    }

    private void charAddStart() {
        String authMain = ConstStr.LOGIN_URL;
        String authRespType = ConstStr.LOGIN_RESP_TYPE;
        String authRedir = ConstStr.LOGIN_REDIR_URI;
        String authClientID = ConstStr.LOGIN_CLIENT_ID;
        String authScope = ConstStr.LOGIN_SCOPES;
        String authState = ConstStr.LOGIN_STATE;
        String authFull = authMain + authRespType + authRedir + authClientID + authScope + authState;
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(authFull));
        startActivity(browserIntent);
    }

    private void writeCharInfo() {

    }

    @Override
    public void onResume() {
        super.onResume();
        intent = getIntent();
        if (Intent.ACTION_VIEW.equals(intent.getAction())) {
            HttpService.getTokens(getApplicationContext(), getAuthCode(intent));
            HttpService.getCharInitialData(getApplicationContext());
            //перекинуть в мэйн.
        }
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


}
