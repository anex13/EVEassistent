package com.anex13.eveassistent.classesForApi.mail;

/**
 * Created by it.zavod on 04.12.2016.
 */
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewMail {

    @SerializedName("approved_cost")
    @Expose
    private Integer approvedCost;
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("recipients")
    @Expose
    private List<Recipient> recipients = null;
    @SerializedName("subject")
    @Expose
    private String subject;

    /**
     *
     * @return
     * The approvedCost
     */
    public Integer getApprovedCost() {
        return approvedCost;
    }

    /**
     *
     * @param approvedCost
     * The approved_cost
     */
    public void setApprovedCost(Integer approvedCost) {
        this.approvedCost = approvedCost;
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
     * The recipients
     */
    public List<Recipient> getRecipients() {
        return recipients;
    }

    /**
     *
     * @param recipients
     * The recipients
     */
    public void setRecipients(List<Recipient> recipients) {
        this.recipients = recipients;
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

}