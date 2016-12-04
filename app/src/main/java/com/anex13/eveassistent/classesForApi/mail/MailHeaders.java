package com.anex13.eveassistent.classesForApi.mail;

/**
 * Created by it.zavod on 04.12.2016.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MailHeaders {
    @SerializedName("recipients")
    @Expose
    private List<Recipient> recipients = null;
    @SerializedName("is_read")
    @Expose
    private Boolean isRead;
    @SerializedName("subject")
    @Expose
    private String subject;
    @SerializedName("mail_id")
    @Expose
    private Integer mailId;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("from")
    @Expose
    private Integer from;
    @SerializedName("labels")
    @Expose
    private List<Integer> labels = null;

    /**
     *
     * @return
     * The isRead
     */
    public Boolean getIsRead() {
        return isRead;
    }

    /**
     *
     * @param isRead
     * The is_read
     */
    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

    /**
     *
     * @return
     * The subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     *
     * @param subject
     * The subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     *
     * @return
     * The mailId
     */
    public Integer getMailId() {
        return mailId;
    }

    /**
     *
     * @param mailId
     * The mail_id
     */
    public void setMailId(Integer mailId) {
        this.mailId = mailId;
    }

    /**
     *
     * @return
     * The timestamp
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     *
     * @param timestamp
     * The timestamp
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }


    /**
     *
     * @return
     * The from
     */
    public Integer getFrom() {
        return from;
    }

    /**
     *
     * @param from
     * The from
     */
    public void setFrom(Integer from) {
        this.from = from;
    }

    /**
     *
     * @return
     * The labels
     */
    public List<Integer> getLabels() {
        return labels;
    }

    /**
     *
     * @param labels
     * The labels
     */
    public void setLabels(List<Integer> labels) {
        this.labels = labels;
    }

}