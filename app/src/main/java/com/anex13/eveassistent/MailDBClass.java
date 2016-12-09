package com.anex13.eveassistent;

import android.content.ContentValues;
import android.database.Cursor;

import com.anex13.eveassistent.classesForApi.mail.Recipient;

import java.util.List;

/**
 * Created by it.zavod on 05.12.2016.
 */

public class MailDBClass {
    private Integer isRead;
    private Integer mailId;
    private Integer fromID;
    private String fromName;
    private String subject;
    private String body;
    private String timestamp;
    private Integer charid;

    public MailDBClass(Boolean isRead, Integer mailId, int fromID, String fromName, String subject, String body, String timestamp, int charid) {
        if (isRead)
            this.isRead=1;
        else this.isRead=0;
        this.mailId = mailId;
        this.fromID = fromID;
        this.fromName = fromName;
        this.subject = subject;
        this.body = body;
        this.timestamp = timestamp;
        this.charid = charid;
    }
    public MailDBClass(Cursor cursor) {

        if (cursor.getInt(cursor.getColumnIndex(DBColumns.MailTable.MAIL_ISREAD))!=0)
            isRead=1;
        else isRead=0;
        mailId = cursor.getInt(cursor.getColumnIndex(DBColumns.MailTable.MAIL_ID));
        fromID = cursor.getInt(cursor.getColumnIndex(DBColumns.MailTable.MAIL_FROM_ID));
        fromName = cursor.getString(cursor.getColumnIndex(DBColumns.MailTable.MAIL_FROM));
        subject = cursor.getString(cursor.getColumnIndex(DBColumns.MailTable.MAIL_SUBJ));
        body = cursor.getString(cursor.getColumnIndex(DBColumns.MailTable.MAIL_BODY));
        timestamp = cursor.getString(cursor.getColumnIndex(DBColumns.MailTable.MAIL_MAIL_TIME));
        charid = cursor.getInt(cursor.getColumnIndex(DBColumns.MailTable.MAIL_CHAR_ID));
    }
    public ContentValues toContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(DBColumns.MailTable.MAIL_ID,mailId);
        cv.put(DBColumns.MailTable.MAIL_ISREAD,isRead);
        cv.put(DBColumns.MailTable.MAIL_FROM_ID,fromID);
        cv.put(DBColumns.MailTable.MAIL_FROM,fromName);
        cv.put(DBColumns.MailTable.MAIL_SUBJ,subject);
        cv.put(DBColumns.MailTable.MAIL_BODY,body);
        cv.put(DBColumns.MailTable.MAIL_MAIL_TIME,timestamp);
        cv.put(DBColumns.MailTable.MAIL_CHAR_ID,charid);
        return cv;
    }
    public Boolean getRead() {
        return !(isRead!=0);
    }

    public void setRead(Boolean isRead) {
        if (isRead)
            this.isRead=1;
        else this.isRead=0;
    }

    public int getCharid() {
        return charid;
    }

    public void setCharid(int charid) {
        this.charid = charid;
    }

    public Integer getMailId() {
        return mailId;
    }

    public void setMailId(Integer mailId) {
        this.mailId = mailId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getFromID() {
        return fromID;
    }

    public void setFromID(Integer fromID) {
        this.fromID = fromID;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public void setCharid(Integer charid) {
        this.charid = charid;
    }
}
