package com.anex13.eveassistent.classesForApi;

/**
 * Created by it.zavod on 28.11.2016.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CharPublicData {

    @SerializedName("ancestry_id")
    @Expose
    private Integer ancestryId;
    @SerializedName("birthday")
    @Expose
    private String birthday;
    @SerializedName("bloodline_id")
    @Expose
    private Integer bloodlineId;
    @SerializedName("corporation_id")
    @Expose
    private Integer corporationId;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("race_id")
    @Expose
    private Integer raceId;

    /**
     *
     * @return
     * The ancestryId
     */
    public Integer getAncestryId() {
        return ancestryId;
    }

    /**
     *
     * @param ancestryId
     * The ancestry_id
     */
    public void setAncestryId(Integer ancestryId) {
        this.ancestryId = ancestryId;
    }

    /**
     *
     * @return
     * The birthday
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     *
     * @param birthday
     * The birthday
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     *
     * @return
     * The bloodlineId
     */
    public Integer getBloodlineId() {
        return bloodlineId;
    }

    /**
     *
     * @param bloodlineId
     * The bloodline_id
     */
    public void setBloodlineId(Integer bloodlineId) {
        this.bloodlineId = bloodlineId;
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
     * The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     * The gender
     */
    public String getGender() {
        return gender;
    }

    /**
     *
     * @param gender
     * The gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The raceId
     */
    public Integer getRaceId() {
        return raceId;
    }

    /**
     *
     * @param raceId
     * The race_id
     */
    public void setRaceId(Integer raceId) {
        this.raceId = raceId;
    }

}