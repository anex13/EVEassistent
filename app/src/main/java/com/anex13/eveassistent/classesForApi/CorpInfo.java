package com.anex13.eveassistent.classesForApi;

/**
 * Created by it.zavod on 29.11.2016.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CorpInfo {

    @SerializedName("corporation_name")
    @Expose
    private String corporationName;
    @SerializedName("member_count")
    @Expose
    private Integer memberCount;
    @SerializedName("ticker")
    @Expose
    private String ticker;

    /**
     *
     * @return
     * The corporationName
     */
    public String getCorporationName() {
        return corporationName;
    }

    /**
     *
     * @param corporationName
     * The corporation_name
     */
    public void setCorporationName(String corporationName) {
        this.corporationName = corporationName;
    }

    /**
     *
     * @return
     * The memberCount
     */
    public Integer getMemberCount() {
        return memberCount;
    }

    /**
     *
     * @param memberCount
     * The member_count
     */
    public void setMemberCount(Integer memberCount) {
        this.memberCount = memberCount;
    }

    /**
     *
     * @return
     * The ticker
     */
    public String getTicker() {
        return ticker;
    }

    /**
     *
     * @param ticker
     * The ticker
     */
    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

}