package com.anex13.eveassistent.classesForApi;

/**
 * Created by it.zavod on 02.12.2016.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CharCorpHistory {

    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("is_deleted")
    @Expose
    private Boolean isDeleted;
    @SerializedName("corporation_id")
    @Expose
    private Integer corporationId;
    @SerializedName("record_id")
    @Expose
    private Integer recordId;

    /**
     *
     * @return
     * The startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     *
     * @param startDate
     * The start_date
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     *
     * @return
     * The isDeleted
     */
    public Boolean getIsDeleted() {
        return isDeleted;
    }

    /**
     *
     * @param isDeleted
     * The is_deleted
     */
    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     *
     * @return
     * The corporationId
     */
    public Integer getCorporationId() {
        return corporationId;
    }

    /**
     *
     * @param corporationId
     * The corporation_id
     */
    public void setCorporationId(Integer corporationId) {
        this.corporationId = corporationId;
    }

    /**
     *
     * @return
     * The recordId
     */
    public Integer getRecordId() {
        return recordId;
    }

    /**
     *
     * @param recordId
     * The record_id
     */
    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

}