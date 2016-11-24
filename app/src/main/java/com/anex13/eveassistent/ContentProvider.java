package com.anex13.eveassistent;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.text.TextUtils;

/**
 * Created by it.zavod on 22.11.2016.
 */

public class ContentProvider extends android.content.ContentProvider {
    static final String DB_NAME = "MAIN DB";
    static final int DB_VERSION = 1;

    // Таблица
    static final String CHAR_TABLE = "characters";

    // Поля
    static final String CHAR_ID = "_id";
    static final String CHAR_CREST_ID = "charid";
    static final String CHAR_ACS_TOKEN = "characstoken";
    static final String CHAR_ACS_TOKEN_EXPIRE = "characstokenexp";
    static final String CHAR_NAME = "charname";
    static final String CHAR_SUB_EXPIRE = "charplex";
    static final String CHAR_USR_PIC = "charava";
    static final String CHAR_CORP_NAME = "charcorp";
    static final String CHAR_CORP_PIC = "charcorppic";


    // Скрипт создания таблицы
    static final String DB_CREATE = "create table " + CHAR_TABLE + "("
            + CHAR_ID + " integer primary key autoincrement, "
            + CHAR_CREST_ID + " integer, "
            + CHAR_ACS_TOKEN + " text,"
            + CHAR_ACS_TOKEN_EXPIRE + " text,"
            + CHAR_NAME + " text,"
            + CHAR_SUB_EXPIRE + " text,"
            + CHAR_USR_PIC + " text,"
            + CHAR_CORP_NAME + " text, "
            + CHAR_CORP_PIC+" text"+");";

    // // Uri
    // authority
    static final String AUTHORITY = "com.anex13.providers.ContentProvider";

    // path
    static final String CHAR_PATH = "characters";

    // Общий Uri
    public static final Uri SERVERS_CONTENT_URI = Uri.parse("content://"
            + AUTHORITY + "/" + CHAR_PATH);

    // Типы данных
    // набор строк
    static final String SERVERS_CONTENT_TYPE = "vnd.android.cursor.dir/vnd."
            + AUTHORITY + "." + CHAR_PATH;

    // одна строка
    static final String SERVERS_CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd."
            + AUTHORITY + "." + CHAR_PATH;

    //// UriMatcher
    // общий Uri
    static final int URI_CHARS = 1;

    // Uri с указанным ID
    static final int URI_CHARS_ID = 2;

    // описание и создание UriMatcher
    private static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, CHAR_PATH, URI_CHARS);
        uriMatcher.addURI(AUTHORITY, CHAR_PATH + "/#", URI_CHARS_ID);
    }

    DBHelper dbHelper;
    SQLiteDatabase db;

    public boolean onCreate() {
        dbHelper = new DBHelper(getContext());
        return true;
    }

    // чтение
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        // проверяем Uri
        switch (uriMatcher.match(uri)) {
            case URI_CHARS: // общий Uri
                // если сортировка не указана, ставим свою - по имени
                if (TextUtils.isEmpty(sortOrder)) {
                    sortOrder = CHAR_NAME + " ASC";
                }
                break;
            case URI_CHARS_ID: // Uri с ID
                String id = uri.getLastPathSegment();
                // добавляем ID к условию выборки
                if (TextUtils.isEmpty(selection)) {
                    selection = CHAR_ID + " = " + id;
                } else {
                    selection = selection + " AND " + CHAR_ID + " = " + id;
                }
                break;
            default:
                throw new IllegalArgumentException("Wrong URI: " + uri);
        }
        db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(CHAR_TABLE, projection, selection,
                selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(),
                SERVERS_CONTENT_URI);
        return cursor;
    }

    public Uri insert(Uri uri, ContentValues values) {
        if (uriMatcher.match(uri) != URI_CHARS)
            throw new IllegalArgumentException("Wrong URI: " + uri);

        db = dbHelper.getWritableDatabase();
        long rowID = db.insert(CHAR_TABLE, null, values);
        Uri resultUri = ContentUris.withAppendedId(SERVERS_CONTENT_URI, rowID);
        // уведомляем ContentResolver, что данные по адресу resultUri изменились
        getContext().getContentResolver().notifyChange(resultUri, null);
        return resultUri;
    }

    public int delete(Uri uri, String selection, String[] selectionArgs) {
        switch (uriMatcher.match(uri)) {
            case URI_CHARS:
                break;
            case URI_CHARS_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    selection = CHAR_ID + " = " + id;
                } else {
                    selection = selection + " AND " + CHAR_ID + " = " + id;
                }
                break;
            default:
                throw new IllegalArgumentException("Wrong URI: " + uri);
        }
        db = dbHelper.getWritableDatabase();
        int cnt = db.delete(CHAR_TABLE, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return cnt;
    }

    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        switch (uriMatcher.match(uri)) {
            case URI_CHARS:

                break;
            case URI_CHARS_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    selection = CHAR_ID + " = " + id;
                } else {
                    selection = selection + " AND " + CHAR_ID + " = " + id;
                }
                break;
            default:
                throw new IllegalArgumentException("Wrong URI: " + uri);
        }
        db = dbHelper.getWritableDatabase();
        int cnt = db.update(CHAR_TABLE, values, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return cnt;
    }


    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case URI_CHARS:
                return SERVERS_CONTENT_TYPE;
            case URI_CHARS_ID:
                return SERVERS_CONTENT_ITEM_TYPE;
        }
        return null;
    }


    private class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DB_CREATE);
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL ("DROP TABLE " + CHAR_TABLE);
            onCreate(db);
        }
    }
}

