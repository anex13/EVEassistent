package com.anex13.eveassistent.classesForApi.mail;

/**
 * Created by it.zavod on 04.12.2016.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Mail {

   // @SerializedName("recipients")
   // @Expose
   // private List<Recipient> recipient = null;
    @SerializedName("subject")
    @Expose
    private String subject;
    @SerializedName("from")
    @Expose
    private Integer from;
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("read")
    @Expose
    private Boolean read;

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
     * The body
     */
    public String getBody() {
        return body;
    }

    /**
     *
     * @param body
     * The body
     */
    public void setBody(String body) {
        this.body = body;
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
     * The read
     */
    public Boolean getRead() {
        return read;
    }

    /**
     *
     * @param read
     * The read
     */
    public void setRead(Boolean read) {
        this.read = read;
    }

}