package com.anex13.eveassistent.classesForApi.mail;

/**
 * Created by it.zavod on 04.12.2016.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MailHeaders {

    @SerializedName("mail_id")
    @Expose
    private Integer mailId;
    @SerializedName("subject")
    @Expose
    private String subject;
    @SerializedName("from")
    @Expose
    private Integer from;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("labels")
    @Expose
    private List<Integer> labels = null;
    @SerializedName("recipients")
    @Expose
    private List<Recipient> recipients = null;
    @SerializedName("is_read")
    @Expose
    private Boolean isRead;

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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public List<Integer> getLabels() {
        return labels;
    }

    public void setLabels(List<Integer> labels) {
        this.labels = labels;
    }

    public List<Recipient> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<Recipient> recipients) {
        this.recipients = recipients;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

}