package com.anex13.eveassistent.classesForApi;

/**
 * Created by it.zavod on 26.11.2016.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurentPosition<S> {

    @SerializedName("solarSystem")
    @Expose
    private SolarSystem solarSystem;

    /**
     * No args constructor for use in serialization
     *
     */
    public CurentPosition() {
    }

    /**
     *
     * @param solarSystem
     */
    public CurentPosition(SolarSystem solarSystem) {
        this.solarSystem = solarSystem;
    }

    /**
     *
     * @return
     * The solarSystem
     */
    public SolarSystem getSolarSystem() {
        return solarSystem;
    }

    /**
     *
     * @param solarSystem
     * The solarSystem
     */
    public void setSolarSystem(SolarSystem solarSystem) {
        this.solarSystem = solarSystem;
    }


}
