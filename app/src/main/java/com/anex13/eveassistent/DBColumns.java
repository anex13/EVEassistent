package com.anex13.eveassistent;

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

        static final String CHAR_CREST_ID = "_id";
        static final String CHAR_ACS_TOKEN = "acstoken";
        static final String CHAR_REFRESH_TOKEN = "refreshtoken";
        //static final String CHAR_CREST_ID = "charid";
        static final String CHAR_NAME = "charname";
        static final String CHAR_GENDER = "gender";
        static final String CHAR_BIRTHDAY = "birthday";
        static final String CHAR_RACE_INT = "raceint";
        static final String CHAR_RACE_STR = "racestring";
        static final String CHAR_DESCRIPTION = "desription";
        static final String CHAR_CORP_ID = "corpid";
        static final String CHAR_CORP_NAME = "charcorpname";
        static final String CHAR_CORP_MEMBERS = "memberscount";
        static final String CHAR_CORP_TIKER = "tiker";
        static final String CHAR_SHIP_NAME = "shipname";
        static final String CHAR_SHIP_ID = "shipid";
        static final String CHAR_SHIP_ITEM_ID = "shipitemid";


        static final String CREATE_SCRIPT = "create table " + TABLE_NAME + "("
                + CHAR_CREST_ID + " integer primary key, "
                + CHAR_ACS_TOKEN + " text,"
                + CHAR_REFRESH_TOKEN + " text,"
                //+ CHAR_CREST_ID + " integer, "
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
                + CHAR_SHIP_ITEM_ID + " integer"
                + ");";

        public static final String TYPE_DIR = "vnd.android.cursor.dir/me.runningcoach.db." + TABLE_NAME;
        public static final String TYPE_ITEM = "vnd.android.cursor.item/me.runningcoach.db." + TABLE_NAME;
    }

    public static abstract class MailTable implements BaseColumns {
        public static final String TABLE_NAME = "MailTable";

        public static final Uri CONTENT_URI = Uri.parse(CONTENT + ContentProvider.AUTHORITY + SLASH + TABLE_NAME);
        static final String MAIL_ID = "_id";
        static final String MAIL_FROM = "mail_sender";
        static final String MAIL_FROM_ID = "mail_from_id";
        static final String MAIL_SUBJ = "mail_subject";
        static final String MAIL_BODY = "mail_body";
        static final String MAIL_MAIL_TIME = "mail_time";
        static final String MAIL_CHAR_ID = "mail_char_id";
        static final String MAIL_ISREAD = "mail_isread";

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