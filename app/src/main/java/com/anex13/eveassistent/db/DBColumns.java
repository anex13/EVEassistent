package com.anex13.eveassistent.db;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by it.zavod on 08.12.2016.
 */

public class DBColumns {
    public static final String CONTENT = "content://";
    public static final String SLASH = "/";
    public static final int TRUE = 0;
    public static final int FALSE = 1;
    public static final String CHAR_ID_SEL = "/char";


    public static abstract class CharTable implements BaseColumns {
        public static final String TABLE_NAME = "Characters";
        public static final Uri CONTENT_URI = Uri.parse(CONTENT + ContentProvider.AUTHORITY + SLASH + TABLE_NAME);

        public static final String CHAR_CREST_ID = "_id";
        public static final String CHAR_ACS_TOKEN = "acstoken";
        public static final String CHAR_REFRESH_TOKEN = "refreshtoken";
        public static final String CHAR_NAME = "charname";
        public static final String CHAR_GENDER = "gender";
        public static final String CHAR_BIRTHDAY = "birthday";
        public static final String CHAR_RACE_INT = "raceint";
        public static final String CHAR_RACE_STR = "racestring";
        public static final String CHAR_DESCRIPTION = "desription";
        public static final String CHAR_CORP_ID = "corpid";
        public static final String CHAR_CORP_NAME = "charcorpname";
        public static final String CHAR_CORP_MEMBERS = "memberscount";
        public static final String CHAR_CORP_TIKER = "tiker";
        public static final String CHAR_SHIP_NAME = "shipname";
        public static final String CHAR_SHIP_ID = "shipid";
        public static final String CHAR_SHIP_ITEM_ID = "shipitemid";
        public static final String CHAR_WALLET = "wallet";
        public static final String CHAR_SP_TOTAL = "sptotal";


        static final String CREATE_SCRIPT = "create table " + TABLE_NAME + "("
                + CHAR_CREST_ID + " integer primary key, "
                + CHAR_ACS_TOKEN + " text,"
                + CHAR_REFRESH_TOKEN + " text,"
                + CHAR_NAME + " text,"
                + CHAR_GENDER + " text,"
                + CHAR_BIRTHDAY + " text,"
                + CHAR_RACE_INT + " integer,"
                + CHAR_RACE_STR + " text,"
                + CHAR_DESCRIPTION + " text,"
                + CHAR_CORP_ID + " integer,"
                + CHAR_CORP_NAME + " text,"
                + CHAR_CORP_MEMBERS + " integer,"
                + CHAR_CORP_TIKER + " text,"
                + CHAR_SHIP_NAME + " text,"
                + CHAR_SHIP_ID + " integer,"
                + CHAR_SHIP_ITEM_ID + " integer, "
                +CHAR_WALLET + " integer, "
                +CHAR_SP_TOTAL + " integer"
                + ");";

        public static final String TYPE_DIR = "vnd.android.cursor.dir/me.runningcoach.db." + TABLE_NAME;
        public static final String TYPE_ITEM = "vnd.android.cursor.item/me.runningcoach.db." + TABLE_NAME;
    }

    public static abstract class MailTable implements BaseColumns {
        public static final String TABLE_NAME = "MailTable";

        public static final Uri CONTENT_URI = Uri.parse(CONTENT + ContentProvider.AUTHORITY + SLASH + TABLE_NAME);
        public static final String MAIL_ID = "_id";
        public static final String MAIL_FROM = "mail_sender";
        public static final String MAIL_FROM_ID = "mail_from_id";
        public static final String MAIL_SUBJ = "mail_subject";
        public static final String MAIL_BODY = "mail_body";
        public static final String MAIL_MAIL_TIME = "mail_time";
        public static final String MAIL_CHAR_ID = "mail_char_id";
        public static final String MAIL_ISREAD = "mail_isread";

        static final String CREATE_SCRIPT = "create table " + TABLE_NAME + "("
                + MAIL_ID + " integer primary key, "
                + MAIL_FROM + " text, "
                + MAIL_FROM_ID + " integer, "
                + MAIL_SUBJ + " text, "
                + MAIL_BODY + " text, "
                + MAIL_MAIL_TIME + " text, "
                + MAIL_CHAR_ID + " integer, "
                + MAIL_ISREAD + " integer"
                + ");";

        public static final String TYPE_DIR = "vnd.android.cursor.dir/me.runningcoach.db." + TABLE_NAME;
        public static final String TYPE_ITEM = "vnd.android.cursor.item/me.runningcoach.db." + TABLE_NAME;
    }

}