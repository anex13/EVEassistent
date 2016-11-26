package com.anex13.eveassistent.classesForApi;

/**
 * Created by it.zavod on 26.11.2016.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SolarSystem {

    @SerializedName("id_str")
    @Expose
    private String idStr;
    @SerializedName("href")
    @Expose
    private String href;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;

    /**
     * No args constructor for use in serialization
     *
     */
    public SolarSystem() {
    }

    /**
     *
     * @param id
     * @param name
     * @param href
     * @param idStr
     */
    public SolarSystem(String idStr, String href, Integer id, String name) {
        this.idStr = idStr;
        this.href = href;
        this.id = id;
        this.name = name;
    }

    /**
     *
     * @return
     * The idStr
     */
    public String getIdStr() {
        return idStr;
    }

    /**
     *
     * @param idStr
     * The id_str
     */
    public void setIdStr(String idStr) {
        this.idStr = idStr;
    }

    /**
     *
     * @return
     * The href
     */
    public String getHref() {
        return href;
    }

    /**
     *
     * @param href
     * The href
     */
    public void setHref(String href) {
        this.href = href;
    }

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
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

}

