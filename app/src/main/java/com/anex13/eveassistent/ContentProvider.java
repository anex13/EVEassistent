package com.anex13.eveassistent;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import com.anex13.eveassistent.DBColumns.MailTable;
import com.anex13.eveassistent.DBColumns.CharTable;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static com.anex13.eveassistent.DBColumns.CHAR_ID_SEL;

/**
 * Created by it.zavod on 22.11.2016.
 */

public class ContentProvider extends android.content.ContentProvider {

    public static final String AUTHORITY = "com.anex13.providers.ContentProvider";

    static final int URI_CHARS = 1;
    static final int URI_CHARS_ID = 2;
    static final int URI_MAILS = 3;
    static final int URI_MAILS_ID = 4;
    static final int URI_MAILS_CHARID=5;

    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
    public static final String TAG = ContentProvider.class.getName();

    static {

        URI_MATCHER.addURI(AUTHORITY, CharTable.TABLE_NAME, URI_CHARS);
        URI_MATCHER.addURI(AUTHORITY, CharTable.TABLE_NAME + "/#", URI_CHARS_ID);
        URI_MATCHER.addURI(AUTHORITY, MailTable.TABLE_NAME, URI_MAILS);
        URI_MATCHER.addURI(AUTHORITY, MailTable.TABLE_NAME + "/#", URI_MAILS_ID);
        URI_MATCHER.addURI(AUTHORITY, MailTable.TABLE_NAME+CHAR_ID_SEL+"/#",URI_MAILS_CHARID);

    }

    private SQLHelper eveSQLHelper;

    public ContentProvider() {
    }

    @Override
    public boolean onCreate() {
        eveSQLHelper = new SQLHelper(getContext());
        return true;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        String table = null;
        switch (URI_MATCHER.match(uri)) {
            case URI_CHARS:
                table = CharTable.TABLE_NAME;
                break;
            case URI_MAILS:
                table = MailTable.TABLE_NAME;
                break;
            case URI_CHARS_ID:
                table = CharTable.TABLE_NAME;
                String cid = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    selection = CharTable.CHAR_CREST_ID + " = " + cid;
                } else {
                    selection = selection + " AND " + CharTable.CHAR_CREST_ID + " = " + cid;
                }
                break;
            case URI_MAILS_ID:
                table = MailTable.TABLE_NAME;
                String mid = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    selection = MailTable.MAIL_ID + " = " + mid;
                } else {
                    selection = selection + " AND " + MailTable.MAIL_ID + " = " + mid;
                }
                break;
            case URI_MAILS_CHARID:
                table = MailTable.TABLE_NAME;
                String qid = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    selection = MailTable.MAIL_CHAR_ID + " = " + qid;
                } else {
                    selection = selection + " AND " + MailTable.MAIL_CHAR_ID + " = " + qid;
                }
                break;
            default:
                throw new IllegalArgumentException("Wrong URI: " + uri);
        }
        SQLiteDatabase database = eveSQLHelper.getWritableDatabase();
        int delCount = database.delete(table, selection, selectionArgs);
        if (delCount > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return delCount;
    }

    @Override
    public String getType(Uri uri) {
        String type = null;
        switch (URI_MATCHER.match(uri)) {
            case URI_MAILS:
                type = MailTable.TYPE_DIR;
                break;
            case URI_CHARS:
                type = CharTable.TYPE_DIR;
                break;
            case URI_MAILS_ID:
                type = MailTable.TYPE_ITEM;
                break;
            case URI_CHARS_ID:
                type = CharTable.TYPE_ITEM;
                break;
            case URI_MAILS_CHARID:
                type = MailTable.TYPE_DIR;
                break;

            default:
                throw new UnsupportedOperationException("Not yet implemented: " + uri.toString());
        }
        return type;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        String table = null;
        switch (URI_MATCHER.match(uri)) {
            case URI_CHARS:
                table = CharTable.TABLE_NAME;
                break;
            case URI_MAILS:
                table = MailTable.TABLE_NAME;
                break;
            default:
                throw new UnsupportedOperationException("Not yet implemented: " + uri.toString());
        }
        SQLiteDatabase sqLiteDatabase = eveSQLHelper.getWritableDatabase();
        long id = sqLiteDatabase.replace(table, null, values);
        getContext().getContentResolver().notifyChange(uri, null);
        return Uri.withAppendedPath(uri, String.valueOf(id));
    }

  /*  @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        int count = 0;
        if (values != null && values.length > 0) {
            String tableName = null;
            SQLiteDatabase sqLiteDatabase = trainingsSQLHelper.getWritableDatabase();
            switch (URI_MATCHER.match(uri)) {
                case TRAININGS:
                    tableName = TrainingsTable.TABLE_NAME;
                    break;
                case EQUIPMENT:
                    tableName = EquipmentTable.TABLE_NAME;
                    break;
                case WAYPOINTS:
                    tableName = WayPointsTable.TABLE_NAME;
                    break;
                default:
                    throw new UnsupportedOperationException("not implemented yet");
            }

            try {
                sqLiteDatabase.beginTransaction();
                for (ContentValues contentValues : values) {
                    long id = sqLiteDatabase.replace(tableName, null, contentValues);
                    if (id >= 0) {
                        count++;
                    }
                }
                sqLiteDatabase.setTransactionSuccessful();
            } finally {
                sqLiteDatabase.endTransaction();
            }
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return count;
    }*/

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Log.d(TAG, "query:" + uri.toString() + ", projection: " + Arrays.toString(projection) +
                ", selection: " + selection + ", selectionargs: " + Arrays.toString(selectionArgs) +
                ", sortOrder: " + sortOrder);
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        switch (URI_MATCHER.match(uri)) {
            case URI_CHARS:
                builder.setTables(CharTable.TABLE_NAME);
                break;
            case URI_MAILS:
                builder.setTables(MailTable.TABLE_NAME);
                break;
            case URI_CHARS_ID:
                builder.setTables(CharTable.TABLE_NAME);
                builder.appendWhere(CharTable.TABLE_NAME + "." + CharTable.CHAR_CREST_ID + " = " + uri.getLastPathSegment());
                break;
            case URI_MAILS_ID:
                builder.setTables(MailTable.TABLE_NAME);
                builder.appendWhere(MailTable.TABLE_NAME + "." + MailTable.MAIL_ID + " = " + uri.getLastPathSegment());
                break;
            case URI_MAILS_CHARID:
                builder.setTables(MailTable.TABLE_NAME);
                builder.appendWhere(MailTable.TABLE_NAME + "."+ MailTable.MAIL_CHAR_ID + " = " + uri.getLastPathSegment());
                break;

            default:
                throw new UnsupportedOperationException("Not yet implemented: " + uri.toString());
        }

        SQLiteDatabase sqLiteDatabase = eveSQLHelper.getReadableDatabase();
        Cursor cursor = builder.query(sqLiteDatabase, projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        Log.d(TAG, "update: " + uri + ", selection: " + selection
                + ", selection args: " + Arrays.toString(selectionArgs) + ", values: " + values);
        String table = null;
        switch (URI_MATCHER.match(uri)) {
            case URI_CHARS:
                table = CharTable.TABLE_NAME;
                break;
            case URI_CHARS_ID:
                table = CharTable.TABLE_NAME;
                String request = CharTable.CHAR_CREST_ID + " = " + uri.getLastPathSegment();
                if (selection == null) {
                    selection = request;
                } else {
                    selection += " AND " + request;
                }
                break;
            case URI_MAILS_ID:
                table = MailTable.TABLE_NAME;
                String eRequest = MailTable.MAIL_ID + " = " + uri.getLastPathSegment();
                if (selection == null) {
                    selection = eRequest;
                } else {
                    selection += " AND " + eRequest;
                }
                break;
            case URI_MAILS:
                table = MailTable.TABLE_NAME;
                break;
            default:
                throw new UnsupportedOperationException("Not yet implemented: " + uri.toString());
        }

        SQLiteDatabase sqLiteDatabase = eveSQLHelper.getWritableDatabase();
        int count = sqLiteDatabase.update(table, values, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }
}
