package com.anex13.eveassistent.classesForApi.mail;

/**
 * Created by it.zavod on 04.12.2016.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MailMeta {

    @SerializedName("labels")
    @Expose
    private List<Integer> labels = null;
    @SerializedName("read")
    @Expose
    private Boolean read;

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