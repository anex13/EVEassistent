package com.anex13.eveassistent.classesForApi;

/**
 * Created by it.zavod on 29.11.2016.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CharShipInfo {

    @SerializedName("ship_type_id")
    @Expose
    private Integer shipTypeId;
    @SerializedName("ship_item_id")
    @Expose
    @SerializedName("ship_name")
    @Expose
    private String shipName;

    /**
     *
     * @return
     * The shipTypeId
     */
    public Integer getShipTypeId() {
        return shipTypeId;
    }

    /**
     *
     * @param shipTypeId
     * The ship_type_id
     */
    public void setShipTypeId(Integer shipTypeId) {
        this.shipTypeId = shipTypeId;
    }

    /**
     *
     * @return
     * The shipItemId
     */
        return shipItemId;
    }

    /**
     *
     * @param shipItemId
     * The ship_item_id
     */
        this.shipItemId = shipItemId;
    }

    /**
     *
     * @return
     * The shipName
     */
    public String getShipName() {
        return shipName;
    }

    /**
     *
     * @param shipName
     * The ship_name
     */
    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

}
