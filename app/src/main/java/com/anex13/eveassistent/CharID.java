package com.anex13.eveassistent;

/**
 * Created by it.zavod on 24.11.2016.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class CharID {

    @SerializedName("CharacterID")
    @Expose
    private Integer characterID;
    @SerializedName("CharacterName")
    @Expose
    private String characterName;
    @SerializedName("ExpiresOn")
    @Expose
    private String expiresOn;
    @SerializedName("Scopes")
    @Expose
    private String scopes;
    @SerializedName("TokenType")
    @Expose
    private String tokenType;
    @SerializedName("CharacterOwnerHash")
    @Expose
    private String characterOwnerHash;

    /**
     * No args constructor for use in serialization
     *
     */
    public CharID() {
    }

    /**
     *
     * @param scopes
     * @param tokenType
     * @param characterName
     * @param expiresOn
     * @param characterOwnerHash
     * @param characterID
     */
    public CharID(Integer characterID, String characterName, String expiresOn, String scopes, String tokenType, String characterOwnerHash) {
        this.characterID = characterID;
        this.characterName = characterName;
        this.expiresOn = expiresOn;
        this.scopes = scopes;
        this.tokenType = tokenType;
        this.characterOwnerHash = characterOwnerHash;
    }

    /**
     *
     * @return
     * The characterID
     */
    public Integer getCharacterID() {
        return characterID;
    }

    /**
     *
     * @param characterID
     * The CharacterID
     */
    public void setCharacterID(Integer characterID) {
        this.characterID = characterID;
    }

    /**
     *
     * @return
     * The characterName
     */
    public String getCharacterName() {
        return characterName;
    }

    /**
     *
     * @param characterName
     * The CharacterName
     */
    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    /**
     *
     * @return
     * The expiresOn
     */
    public String getExpiresOn() {
        return expiresOn;
    }

    /**
     *
     * @param expiresOn
     * The ExpiresOn
     */
    public void setExpiresOn(String expiresOn) {
        this.expiresOn = expiresOn;
    }

    /**
     *
     * @return
     * The scopes
     */
    public String getScopes() {
        return scopes;
    }

    /**
     *
     * @param scopes
     * The Scopes
     */
    public void setScopes(String scopes) {
        this.scopes = scopes;
    }

    /**
     *
     * @return
     * The tokenType
     */
    public String getTokenType() {
        return tokenType;
    }

    /**
     *
     * @param tokenType
     * The TokenType
     */
    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    /**
     *
     * @return
     * The characterOwnerHash
     */
    public String getCharacterOwnerHash() {
        return characterOwnerHash;
    }

    /**
     *
     * @param characterOwnerHash
     * The CharacterOwnerHash
     */
    public void setCharacterOwnerHash(String characterOwnerHash) {
        this.characterOwnerHash = characterOwnerHash;
    }

    @Override
    public String toString(){
        return "ID= "+characterID+"  Name= "+characterName;
    }

}
