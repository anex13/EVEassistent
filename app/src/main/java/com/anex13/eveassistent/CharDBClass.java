package com.anex13.eveassistent;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;

/**
 * Created by it.zavod on 28.11.2016.
 */

public class CharDBClass {
    String accesToken;
    String accesCode;
    String refreshToken;
    int charID;
    String charName;
    String gender;
    String birthday;
    int race;
    String raceStr;
    int secStatus;
    String description;
    int corpID;
    String corpName;
    int corpMembers;
    String corpLogoUrl;
    String corpTiker;
    String shipName;
    int shipid;
    int shipItemID;

    public CharDBClass(String accesToken, String accesCode, String refreshToken, int charID, String charName, String gender, String birthday, int race, int secStatus, String description, int corpID, String corpName, int corpMembers, String corpLogoUrl, String corpTiker, String shipName, int shipid, int shipItemID) {
        this.accesToken = accesToken;
        this.accesCode = accesCode;
        this.refreshToken = refreshToken;
        this.charID = charID;
        this.charName = charName;
        this.gender = gender;
        this.birthday = birthday;
        this.race = race;
        switch (race) {
            case 1:
                raceStr = "race1";
                break;
            case 2:
                raceStr = "race2";
                break;
            case 3:
                raceStr = "race3";
                break;
            default:
                raceStr = "race4";
                break;
        }
        this.secStatus = secStatus;
        this.description = description;
        this.corpID = corpID;
        this.corpName = corpName;
        this.corpMembers = corpMembers;
        this.corpLogoUrl = corpLogoUrl;
        this.corpTiker = corpTiker;
        this.shipName = shipName;
        this.shipid = shipid;
        this.shipItemID = shipItemID;
    }

    public CharDBClass(Cursor cursor) {
        this.accesToken = cursor.getString(cursor.getColumnIndex(ContentProvider.CHAR_ACS_TOKEN));
        this.accesCode = cursor.getString(cursor.getColumnIndex(ContentProvider.CHAR_ACS_CODE));
        this.refreshToken = cursor.getString(cursor.getColumnIndex(ContentProvider.CHAR_REFRESH_TOKEN));
        this.charID = cursor.getInt(cursor.getColumnIndex(ContentProvider.CHAR_CREST_ID));
        this.charName = cursor.getString(cursor.getColumnIndex(ContentProvider.CHAR_NAME));
        this.gender = cursor.getString(cursor.getColumnIndex(ContentProvider.CHAR_GENDER));
        this.birthday = cursor.getString(cursor.getColumnIndex(ContentProvider.CHAR_BIRTHDAY));
        this.race = cursor.getInt(cursor.getColumnIndex(ContentProvider.CHAR_RACE_INT));
        this.raceStr = cursor.getString(cursor.getColumnIndex(ContentProvider.CHAR_RACE_STR));
        this.secStatus = cursor.getInt(cursor.getColumnIndex(ContentProvider.CHAR_SEC_STATUS));
        this.description = cursor.getString(cursor.getColumnIndex(ContentProvider.CHAR_DESCRIPTION));
        this.corpID = cursor.getInt(cursor.getColumnIndex(ContentProvider.CHAR_CORP_ID));
        this.corpName = cursor.getString(cursor.getColumnIndex(ContentProvider.CHAR_CORP_NAME));
        this.corpMembers = cursor.getInt(cursor.getColumnIndex(ContentProvider.CHAR_CORP_MEMBERS));
        this.corpLogoUrl = cursor.getString(cursor.getColumnIndex(ContentProvider.CHAR_CORP_LOGO));
        this.corpTiker = cursor.getString(cursor.getColumnIndex(ContentProvider.CHAR_CORP_TIKER));
        this.shipName = cursor.getString(cursor.getColumnIndex(ContentProvider.CHAR_SHIP_NAME));
        this.shipid = cursor.getInt(cursor.getColumnIndex(ContentProvider.CHAR_SHIP_ID));
        this.shipItemID = cursor.getInt(cursor.getColumnIndex(ContentProvider.CHAR_SHIP_ITEM_ID));
    }

    public CharDBClass(Bundle bundle) {
        this.accesToken = bundle.getString(ContentProvider.CHAR_ACS_TOKEN);
        this.accesCode = bundle.getString(ContentProvider.CHAR_ACS_CODE);
        this.refreshToken = bundle.getString(ContentProvider.CHAR_REFRESH_TOKEN);
        this.charID = bundle.getInt(ContentProvider.CHAR_CREST_ID);
        this.charName = bundle.getString(ContentProvider.CHAR_NAME);
        this.gender = bundle.getString(ContentProvider.CHAR_GENDER);
        this.birthday = bundle.getString(ContentProvider.CHAR_BIRTHDAY);
        this.race = bundle.getInt(ContentProvider.CHAR_RACE_INT);
        this.raceStr = bundle.getString(ContentProvider.CHAR_RACE_STR);
        this.secStatus = bundle.getInt(ContentProvider.CHAR_SEC_STATUS);
        this.description = bundle.getString(ContentProvider.CHAR_DESCRIPTION);
        this.corpID = bundle.getInt(ContentProvider.CHAR_CORP_ID);
        this.corpName = bundle.getString(ContentProvider.CHAR_CORP_NAME);
        this.corpMembers = bundle.getInt(ContentProvider.CHAR_CORP_MEMBERS);
        this.corpLogoUrl = bundle.getString(ContentProvider.CHAR_CORP_LOGO);
        this.corpTiker = bundle.getString(ContentProvider.CHAR_CORP_TIKER);
        this.shipName = bundle.getString(ContentProvider.CHAR_SHIP_NAME);
        this.shipid = bundle.getInt(ContentProvider.CHAR_SHIP_ID);
        this.shipItemID = bundle.getInt(ContentProvider.CHAR_SHIP_ITEM_ID);
    }

    public ContentValues toContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(ContentProvider.CHAR_ACS_TOKEN,accesToken);
        cv.put(ContentProvider.CHAR_ACS_CODE,accesCode);
        cv.put(ContentProvider.CHAR_REFRESH_TOKEN,refreshToken);
        cv.put(ContentProvider.CHAR_CREST_ID,charID);
        cv.put(ContentProvider.CHAR_NAME,charName);
        cv.put(ContentProvider.CHAR_GENDER,gender);
        cv.put(ContentProvider.CHAR_BIRTHDAY,birthday);
        cv.put(ContentProvider.CHAR_RACE_INT,race);
        cv.put(ContentProvider.CHAR_RACE_STR,raceStr);
        cv.put(ContentProvider.CHAR_SEC_STATUS,secStatus);
        cv.put(ContentProvider.CHAR_DESCRIPTION,description);
        cv.put(ContentProvider.CHAR_CORP_ID,corpID);
        cv.put(ContentProvider.CHAR_CORP_NAME,corpName);
        cv.put(ContentProvider.CHAR_CORP_MEMBERS,corpMembers);
        cv.put(ContentProvider.CHAR_CORP_LOGO,corpLogoUrl);
        cv.put(ContentProvider.CHAR_CORP_TIKER,corpTiker);
        cv.put(ContentProvider.CHAR_SHIP_NAME,shipName);
        cv.put(ContentProvider.CHAR_SHIP_ID,shipid);
        cv.put(ContentProvider.CHAR_SHIP_ITEM_ID,shipItemID);
        return cv;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(ContentProvider.CHAR_ACS_TOKEN,accesToken);
        bundle.putString(ContentProvider.CHAR_ACS_CODE,accesCode);
        bundle.putString(ContentProvider.CHAR_REFRESH_TOKEN,refreshToken);
        bundle.putInt(ContentProvider.CHAR_CREST_ID,charID);
        bundle.putString(ContentProvider.CHAR_NAME,charName);
        bundle.putString(ContentProvider.CHAR_GENDER,gender);
        bundle.putString(ContentProvider.CHAR_BIRTHDAY,birthday);
        bundle.putInt(ContentProvider.CHAR_RACE_INT,race);
        bundle.putString(ContentProvider.CHAR_RACE_STR,raceStr);
        bundle.putInt(ContentProvider.CHAR_SEC_STATUS,secStatus);
        bundle.putString(ContentProvider.CHAR_DESCRIPTION,description);
        bundle.putInt(ContentProvider.CHAR_CORP_ID,corpID);
        bundle.putString(ContentProvider.CHAR_CORP_NAME,corpName);
        bundle.putInt(ContentProvider.CHAR_CORP_MEMBERS,corpMembers);
        bundle.putString(ContentProvider.CHAR_CORP_LOGO,corpLogoUrl);
        bundle.putString(ContentProvider.CHAR_CORP_TIKER,corpTiker);
        bundle.putString(ContentProvider.CHAR_SHIP_NAME,shipName);
        bundle.putInt(ContentProvider.CHAR_SHIP_ID,shipid);
        bundle.putInt(ContentProvider.CHAR_SHIP_ITEM_ID,shipItemID);
        return bundle;
    }

    public String getAccesToken() {
        return accesToken;
    }

    public void setAccesToken(String accesToken) {
        this.accesToken = accesToken;
    }

    public String getAccesCode() {
        return accesCode;
    }

    public void setAccesCode(String accesCode) {
        this.accesCode = accesCode;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public int getCharID() {
        return charID;
    }

    public void setCharID(int charID) {
        this.charID = charID;
    }

    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getRace() {
        return race;
    }

    public void setRace(int race) {
        this.race = race;
    }

    public String getRaceStr() {
        return raceStr;
    }

    public void setRaceStr(String raceStr) {
        this.raceStr = raceStr;
    }

    public int getSecStatus() {
        return secStatus;
    }

    public void setSecStatus(int secStatus) {
        this.secStatus = secStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCorpID() {
        return corpID;
    }

    public void setCorpID(int corpID) {
        this.corpID = corpID;
    }

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    public int getCorpMembers() {
        return corpMembers;
    }

    public void setCorpMembers(int corpMembers) {
        this.corpMembers = corpMembers;
    }

    public String getCorpLogoUrl() {
        return corpLogoUrl;
    }

    public void setCorpLogoUrl(String corpLogoUrl) {
        this.corpLogoUrl = corpLogoUrl;
    }

    public String getCorpTiker() {
        return corpTiker;
    }

    public void setCorpTiker(String corpTiker) {
        this.corpTiker = corpTiker;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public int getShipid() {
        return shipid;
    }

    public void setShipid(int shipid) {
        this.shipid = shipid;
    }

    public int getShipItemID() {
        return shipItemID;
    }

    public void setShipItemID(int shipItemID) {
        this.shipItemID = shipItemID;
    }


}