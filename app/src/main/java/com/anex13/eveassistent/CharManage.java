package com.anex13.eveassistent;

import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.anex13.eveassistent.dbAdapters.CharAdapter;

public class CharManage extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final int MENU_DEL_CHAR = 1;
    Intent intent;
    SharedPreferences spref;
    ListView lv;
    CharDBClass menuchar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_char_manage);
        spref = getSharedPreferences(CS.AUTH_PREF, MODE_PRIVATE);
        ImageButton login = (ImageButton) findViewById(R.id.imageButton);
        lv = (ListView) findViewById(R.id.chars_list);
        registerForContextMenu(lv);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                charAddStart();
            }
        });
        getLoaderManager().initLoader(123, null, this);
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(0x000000);

        }
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String id = ((TextView) view.findViewById(R.id.adapter_char_id)).getText().toString();
                int intid = Integer.parseInt(id);
                SharedPreferences.Editor editor =spref.edit();
                editor.putInt(CS.SPREF_DEF_CHAR,intid);
                editor.apply();
                Log.i("def","char set"+intid);
            }
        });

    }

    public String getAuthCode(Intent authintent) {//допилить проверку стэйта
        Uri uri = authintent.getData();
        String valueOne = uri.getQueryParameter("code");
        String valueTwo = uri.getQueryParameter("state");
        Log.e("auth", valueOne);
        Log.e("auth", valueTwo);
        SharedPreferences.Editor ed = spref.edit();
        ed.putString(CS.AUTH_CODE_TAG, valueOne);
        ed.apply();
        return valueOne;
        // TODO: 22.11.2016 записать куданибудь код и проверить стэйт
    }

    private void charAddStart() {
        String authMain = CS.LOGIN_URL;
        String authRespType = CS.LOGIN_RESP_TYPE;
        String authRedir = CS.LOGIN_REDIR_URI;
        String authClientID = CS.LOGIN_CLIENT_ID;
        String authScope = CS.LOGIN_SCOPES;
        String authState = CS.LOGIN_STATE;
        String authFull = authMain + authRespType + authRedir + authClientID + authScope + authState;
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(authFull));
        startActivity(browserIntent);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(0x000000);
        }
        intent = getIntent();
        if (Intent.ACTION_VIEW.equals(intent.getAction())) {
            HttpService.charCreateNew(getApplicationContext(), getAuthCode(intent));
            //перекинуть в мэйн.
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        return new CursorLoader(this, DBColumns.CharTable.CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        lv.setAdapter(new CharAdapter(this, data));
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        Cursor c = ((Cursor) lv.getAdapter().getItem(info.position));
        menuchar = new CharDBClass(c);
        String name = menuchar.getCharName();
        menu.setHeaderTitle(name);
        menu.add(Menu.NONE, MENU_DEL_CHAR, Menu.NONE, "Delete character");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_DEL_CHAR:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Uri uri = ContentUris.withAppendedId(DBColumns.CharTable.CONTENT_URI, menuchar.getCharID());
                        Log.e("char del","uri"+uri.toString());
                        getApplicationContext().getContentResolver().delete(uri, null, null);
                    }
                }).start();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        Intent tomainactivity = new Intent(this, MainActivity.class);
        startActivity(tomainactivity);
    }
}
