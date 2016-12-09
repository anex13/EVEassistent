package com.anex13.eveassistent;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.util.ArrayMap;

/**
 * Created by it.zavod on 08.12.2016.
 */

public class SQLHelper extends SQLiteOpenHelper {

    public static final int DB_VERSION = 2;
    public static final String DB_NAME = "running.COACH";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS ";
    public static ArrayMap<String, String> scriptsMap = new ArrayMap<>();

    static {
        scriptsMap.put(DBColumns.CharTable.TABLE_NAME, DBColumns.CharTable.CREATE_SCRIPT);
        scriptsMap.put(DBColumns.MailTable.TABLE_NAME, DBColumns.MailTable.CREATE_SCRIPT);
    }

    private Context context;

    public SQLHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for (String createScript : scriptsMap.values()) {
            db.execSQL(createScript);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for (String tableName : scriptsMap.keySet()) {
            db.execSQL(DROP_TABLE + tableName);
        }
        onCreate(db);
    }
}

