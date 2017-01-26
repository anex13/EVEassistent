package com.anex13.eveassistent.classesForApi.mail;

/**
 * Created by it.zavod on 04.12.2016.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Mail {

    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("from")
    @Expose
    private Integer from;
    @SerializedName("labels")
    @Expose
    private List<Integer> labels = null;
    @SerializedName("read")
    @Expose
    private Boolean read;
    @SerializedName("subject")
    @Expose
    private String subject;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public List<Integer> getLabels() {
        return labels;
    }

    public void setLabels(List<Integer> labels) {
        this.labels = labels;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

}