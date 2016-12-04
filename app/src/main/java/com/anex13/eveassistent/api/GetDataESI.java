package com.anex13.eveassistent.api;

import com.anex13.eveassistent.classesForApi.CharCorpHistory;
import com.anex13.eveassistent.classesForApi.CharPublicData;
import com.anex13.eveassistent.classesForApi.CharShipInfo;
import com.anex13.eveassistent.classesForApi.CorpInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface GetDataESI {

    @Headers({"Accept: application/json"})
    @GET("characters/{id}/?datasource=tranquility")
    Call<CharPublicData> getPublicData(@Path("id") int id);


    @Headers({"Accept: application/json"})
    @GET("characters/{charID}/ship/?datasource=tranquility")
    Call<CharShipInfo> getCharShipInfo(@Path("charID") int charid, @Header("Authorization") String barer);

    @Headers({"Accept: application/json"})
    @GET("corporations/{id}/?datasource=tranquility")
    Call<CorpInfo> getCorpInfo(@Path("id") int id);

    @Headers({"Accept: application/json"})
    @GET("c/characters/{character_id}/assets/?datasource=tranquility")
    Call<CharShipInfo> getCharAssets(@Path("character_id") int charid, @Header("Authorization") String barer);
    //todo call types

   // @Headers({"Accept: application/json"})
  //  @GET("/characters/{character_id}/bookmarks/?datasource=tranquility")
   // Call<CharShipInfo> getCharBookmarks(@Path("character_id") int charid, @Header("Authorization") String barer);

    //@Headers({"Accept: application/json"})
   // @GET("/characters/{character_id}/bookmarks/folders/?datasource=tranquility")
   // Call<CharShipInfo> getCharBookmarksFolders(@Path("character_id") int charid, @Header("Authorization") String barer);

    //@Headers({"Accept: application/json"})
    //@GET("/characters/{character_id}/calendar/?datasource=tranquility")
   // Call<CharShipInfo> getCharCalendar(@Path("character_id") int charid, @Header("Authorization") String barer);

    @Headers({"Accept: application/json"})
    @GET("/characters/{character_id}/corporationhistory/?datasource=tranquility")
    Call<List<CharCorpHistory>> getCharCorpHistory(@Path("character_id") int charid, @Header("Authorization") String barer);

    @Headers({"Accept: application/json"})
    @GET("/characters/{character_id}/killmails/recent/?datasource=tranquility")
    Call<CharShipInfo> getCharKillMails(@Path("character_id") int charid, @Header("Authorization") String barer);

    @Headers({"Accept: application/json"})
    @GET("/characters/{character_id}/location/?datasource=tranquility")
    Call<CharShipInfo> getCharLocation(@Path("character_id") int charid, @Header("Authorization") String barer);

    @Headers({"Accept: application/json"})
    @GET("/characters/{character_id}/skillqueue/?datasource=tranquility")
    Call<CharShipInfo> getCharSkillQueue(@Path("character_id") int charid, @Header("Authorization") String barer);

    @Headers({"Accept: application/json"})
    @GET("/characters/{character_id}/skills/?datasource=tranquility")
    Call<CharShipInfo> getCharSkills(@Path("character_id") int charid, @Header("Authorization") String barer);

    @Headers({"Accept: application/json"})
    @GET("/characters/{character_id}/wallets/?datasource=tranquility")
    Call<CharShipInfo> getCharwallets(@Path("character_id") int charid, @Header("Authorization") String barer);

    @Headers({"Accept: application/json"})
    @GET("/incursions/?datasource=tranquility")
    Call<CharShipInfo> getIncursions( @Header("Authorization") String barer);




}

//todo ПРОВЕРИТЬ УРЛЫ И ПАРАМЕТРЫ И БЭЙСУРЛЫ
//https://esi.tech.ccp.is/legacy/



/*
    GET /alliances/ //    List all alliances
    GET /alliances/names/ //    Get alliance names
    GET /alliances/{alliance_id}/  //    Get alliance information
    GET /alliances/{alliance_id}/corporations/  //    List alliance's corporations
    GET /alliances/{alliance_id}/icons/  //    Get alliance icon
    GET /characters/names/   //    Get character names
    GET /characters/{character_id}/calendar/{event_id}/  //    Get an event
    PUT /characters/{character_id}/calendar/{event_id}/  //    Respond to an event
    GET /characters/{character_id}/mail/  //    Return mail headers
    GET /characters/{character_id}/mail/labels/  //    Get mail labels
    GET /characters/{character_id}/mail/lists/  //    Return mailing list subscriptions
    GET /characters/{character_id}/mail/unread/  //    Return the number of unread mails
    GET /characters/{character_id}/mail/{mail_id}/  //    Return a mail
    GET /characters/{character_id}/portrait/  //    Get character portraits
    GET /characters/{character_id}/search/  //    Search on a string
    GET /corporations/names/  //    Get corporation names
    GET /corporations/{corporation_id}/alliancehistory/  //    Get alliance history
    GET /corporations/{corporation_id}/icons/  //    Get corporation icon
    GET /corporations/{corporation_id}/members/  //    Get corporation members
    GET /corporations/{corporation_id}/roles/  //    Get corporation member roles
    GET /killmails/{killmail_id}/{killmail_hash}/  //    Get a single killmail
    GET /markets/prices/  //    List market prices
    GET /markets/{region_id}/history/  //    List historical market statistics in a region
    GET /markets/{region_id}/orders/  //    List orders in a region
    GET /search/  //    Search on a string
    GET /sovereignty/campaigns/  //    List sovereignty campaigns
    GET /sovereignty/structures/  //    List sovereignty structures
    GET /universe/stations/{station_id}/  //    Get station information
    GET /universe/structures/  //    List all public structures
    GET /universe/structures/{structure_id}/  //    Get structure information
    GET /universe/systems/{system_id}/  //    Get solar system information
    GET /universe/types/{type_id}/  //    Get type information
 */