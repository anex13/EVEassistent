package com.anex13.eveassistent;

import com.anex13.eveassistent.classesForApi.mail.Recipient;

import java.util.List;

/**
 * Created by it.zavod on 05.12.2016.
 */

public class MailDBClass {
    private Boolean isRead;
    private Integer mailId;
    //private List<Integer> labels = null;
    //private List<Recipient> recipients = null;
    private String subject;
    private Integer from;
    private String body;
    private String timestamp;
    private Boolean read;
    private int charid;

    public MailDBClass(Boolean isRead, Integer mailId, String subject, Integer from, String body, String timestamp, Boolean read, int charid) {
        this.isRead = isRead;
        this.mailId = mailId;
        this.subject = subject;
        this.from = from;
        this.body = body;
        this.timestamp = timestamp;
        this.read = read;
        this.charid = charid;
    }

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
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

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
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
}
