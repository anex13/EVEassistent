package com.anex13.eveassistent.classesForApi;

/**
 * Created by it.zavod on 01.12.2016.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CharDataCREST {

    @SerializedName("id_str")
    @Expose
    private String idStr;
   // @SerializedName("fittings")
   // @Expose
  //  private Fittings fittings;
    @SerializedName("name")
    @Expose
    private String name;
   // @SerializedName("contacts")
  //  @Expose
   // private Contacts contacts;
  //  @SerializedName("loyaltyPoints")
  //  @Expose
  //  private LoyaltyPoints loyaltyPoints;



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
     * The fittings
     */
  //  public Fittings getFittings() {
  //      return fittings;
  //  }

    /**
     *
     * @param fittings
     * The fittings
     */
  //  public void setFittings(Fittings fittings) {
   //     this.fittings = fittings;
   // }

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
     * The contacts
     */
  //  public Contacts getContacts() {
  //      return contacts;
  //  }

    /**
     *
     * @param contacts
     * The contacts
     */
  //  public void setContacts(Contacts contacts) {
  //      this.contacts = contacts;
  //  }


    /**
     *
     * @return
     * The gender
     */
  //  public Integer getGender() {
  //      return gender;
  //  }

    /**
     *
     * @param gender
     * The gender
     */
 //   public void setGender(Integer gender) {
  //      this.gender = gender;
  //  }

    /**
     *
     * @return
     * The genderStr
     */
  //  public String getGenderStr() {
  //      return genderStr;
  //  }

    /**
     *
     * @param genderStr
     * The gender_str
     */
 //   public void setGenderStr(String genderStr) {
 //       this.genderStr = genderStr;
 //   }

    /**
     *
     * @return
     * The loyaltyPoints
     */
  //  public LoyaltyPoints getLoyaltyPoints() {
 //       return loyaltyPoints;
 //   }

    /**
     *
     * @param loyaltyPoints
     * The loyaltyPoints
     */
 //   public void setLoyaltyPoints(LoyaltyPoints loyaltyPoints) {
  //      this.loyaltyPoints = loyaltyPoints;
  //  }


}