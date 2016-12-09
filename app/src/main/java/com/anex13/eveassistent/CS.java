package com.anex13.eveassistent;

/**
 * Created by it.zavod on 27.11.2016.
 */

public class CS {
    //img server routes
    public static final String BASE_URL_IMG = "https://imageserver.eveonline.com/";
    public static final String ALI_URL_IMG = "Alliance/{allianceID}_{width}.png";
    public static final String CORP_URL_IMG = "Corporation/";
    public static final String CHAR_URL_IMG = "Character/";
    public static final String TYPE_URL_IMG = "Type/{typeID}_{width}.png";
    public static final String RENDER_URL_IMG = "Render/{typeID}_{width}.png";
    public static final String IMG_SIZE_32 = "_32";
    public static final String IMG_SIZE_64 = "_64";
    public static final String IMG_SIZE_128 = "_128";
    public static final String IMG_SIZE_256 = "_256";
    public static final String IMG_SIZE_512 = "_512";
    public static final String IMG_SIZE_1024 = "_1024";


    public static final String BASE_URL_AUTH = "https://login.eveonline.com/";
    //public static final String BASE_URL_AUTH = "https://sisilogin.testeveonline.com/";    //testserv login
    public static final String BASE_URL_DATA = "https://crest-tq.eveonline.com/";
    // public static final String BASE_URL_DATA = "https://crest-tq.eveonline.com/";      test serv data crest
    public static final String BASE_URL_ESI = "https://esi.tech.ccp.is/legacy/";
    public static final String AUTH_PREF = "auth prefs";
    public static final String AUTH_CODE_TAG = "acces code";
    public static final String AUTH_TOKEN_TAG = "acces token";
    public static final String AUTH_REFRESH_TOKEN_TAG = "refresh token";
    public static final String AUTH_BASIC = "MGYzMTM2Mjc2MGM5NGM5ZjkyODBkZDI5MzQ3ZTljMjQ6QjEwQXBaMkJoc3lKUTVhcVNwSGV6Z2gxS1JWRGVVTlZqSlBFNVpOZA==";
    public static final String LOGIN_URL = BASE_URL_AUTH + "oauth/authorize/?";
    public static final String LOGIN_RESP_TYPE = "response_type=code&";
    public static final String LOGIN_REDIR_URI = "redirect_uri=anexevetest://auth/&";
    public static final String LOGIN_CLIENT_ID = "client_id=0f31362760c94c9f9280dd29347e9c24&";
    public static final String LOGIN_STATE = "state=trustmeiamingener";
    public static final String LOGIN_SCOPES = "scope="
           /* + "characterAccountRead "//: Read your account subscription status.
            + "characterAssetsRead "//: Read your asset list.
            + "characterBookmarksRead "// List your bookmarks and their coordinates.
            + "characterCalendarRead "//: Read your calendar events and attendees.
            + "characterChatChannelsRead  "//: List chat channels you own or operate.
            + "characterClonesRead "// List your jump clones, implants, attributes, and jump fatigue timer.
            + "characterContactsRead "//: Allows access to reading your characters contacts.
            + "characterContactsWrite "//: Allows applications to add, modify, and delete contacts for your character.
            + "characterContractsRead "//: Read your contracts.
            + "characterFactionalWarfareRead "//: Read your factional warfare statistics.
            + "characterFittingsRead "// Allows an application to view all of your character's saved fits.
            + "characterFittingsWrite "//: Allows an application to create and delete the saved fits for your character.
            + "characterIndustryJobsRead "//: List your industry jobs.
            + "characterKillsRead "//: Read your kill mails.
            + "characterLocationRead "//: Allows an application to read your characters real time location in EVE.
            + "characterLoyaltyPointsRead "//: List loyalty points your character has for the different corporations.
            + "characterMailRead "//: Read your EVE Mail.
            + "characterMarketOrdersRead "//: Read your market orders.
            + "characterMedalsRead "//: List your public and private medals.
            + "characterNavigationWrite "//: Allows an application to set your ships autopilot destination.
            + "characterNotificationsRead "//: Receive in-game notifications.
            + "characterOpportunitiesRead "//: List the opportunities your character has completed.
            + "characterResearchRead "//: List your research agents working for you and research progress.
            + "characterSkillsRead "//: Read your skills and skill queue.
            + "characterStatsRead "//: Yearly aggregated stats about your character.
            + "characterWalletRead "//: Read your wallet status, transaction, and journal history.
            + "corporationAssetsRead "//: Read your corporation's asset list.
            + "corporationBookmarksRead "//: List your corporation's bookmarks and their coordinates.
            + "corporationContactsRead "//: Read your corporation’s contact list and standings
            + "corporationContractsRead "//: List your corporation's contracts.
            + "corporationFactionalWarfareRead "//: Read your corporation's factional warfare statistics.
            + "corporationIndustryJobsRead "//: List your corporation's industry jobs.
            + "corporationKillsRead "//: Read your corporation's kill mails.
            + "corporationMarketOrdersRead "//: List your corporation's market orders.
            + "corporationMedalsRead "//: List your corporation's issued medals.
            + "corporationMembersRead "//: List your corporation's members, their titles, and roles.
            + "corporationShareholdersRead "//: List your corporation's shareholders and their shares.
            + "corporationStructuresRead "//: List your corporation's structures, outposts, and starbases.
            + "corporationWalletRead "//: Read your corporation's wallet status, transaction, and journal history.
            + "fleetRead "//: Allows real time reading of your fleet information (members, ship types, etc.) if you're the boss of the fleet.
            + "fleetWrite "//: Allows the ability to invite, kick, and update fleet information if you're the boss of the fleet.
            + "structureVulnUpdate "//: Allows updating your structures' vulnerability timers.
            + "remoteClientUI "//: Allows applications to control the UI of your EVE Online client.
            + "publicData "//: Allows access to public data.*/
            + "esi-fleets.write_fleet.v1 "//: Allows manipulating fleets
            + "esi-planets.manage_planets.v1 "//: Allows reading a list of a characters planetary colonies, and the details of those colonies
            + "esi-ui.open_window.v1 "//: Allows open window in game client remotely
            + "esi-assets.read_assets.v1 "//: Allows reading a list of assets that the character owns
            + "esi-calendar.read_calendar_events.v1 "//: Allows reading a character's calendar, including corporation events
            + "esi-bookmarks.read_character_bookmarks.v1 "//: Allows reading of a character's bookmarks and bookmark folders
            + "esi-wallet.read_character_wallet.v1 "//: Allows reading of a character's wallet, journal and transaction history.
            + "esi-clones.read_clones.v1 "//: Allows reading the locations of a character's jump clones and their implants.
            + "esi-characters.read_contacts.v1 "//: Allows reading of a characters contacts list, and calculation of CSPA charges
            + "esi-corporations.read_corporation_membership.v1 "//: Allows reading a list of the ID's and roles of a character's fellow corporation members
            + "esi-fleets.read_fleet.v1 "//: Allows reading information about fleets
            + "esi-killmails.read_killmails.v1 "//: Allows reading of a character's kills and losses
            + "esi-location.read_location.v1 "//: Allows reading of a character's active ship location
            + "esi-location.read_ship_type.v1 "//: Allows reading of a character's active ship class
            + "esi-skills.read_skillqueue.v1 "//: Allows reading of a character's currently training skill queue.
            + "esi-skills.read_skills.v1 "//: Allows reading of a character's currently known skills.
            + "esi-universe.read_structures.v1 "//: Allows querying the location and type of structures that the character has docking access at.
            + "esi-calendar.respond_calendar_events.v1 "//: Allows updating of a character's calendar event responses
            + "esi-search.search_structures.v1 "//: Allows searching over all structures that a character can see in the structure browser.
            +" esi-mail.read_mail.v1 "
            +"esi-mail.send_mail.v1 "
            +"esi-mail.organize_mail.v1 "
            + "esi-ui.write_waypoint.v1"//: Allows manipulating waypoints in game client remotely
            + "&";
    public static final String CHAR_NAME = "char name";
    public static final String CHAR_ID = "car id ";
    public static final String SPREF_DEF_CHAR = "default char";

    //https://login.eveonline.com/Account/LogOn?ReturnUrl=/oauth/authorize?
    // response_type=token&redirect_uri=https://esi.tech.ccp.is/latest/o2c.html
    // &realm=ESI
    // &client_id=eadab3ac00814cf6938445ef59987b49
    // &scope=esi-assets.read_assets.v1
    // esi-bookmarks.read_character_bookmarks.v1
    // esi-calendar.read_calendar_events.v1
    // esi-calendar.respond_calendar_events.v1
    // esi-characters.read_contacts.v1
    // esi-clones.read_clones.v1
    // esi-corporations.read_corporation_membership.v1
    // esi-killmails.read_killmails.v1
    // esi-location.read_location.v1
    // esi-location.read_ship_type.v1
    // esi-mail.organize_mail.v1
    // esi-mail.read_mail.v1
    // esi-mail.send_mail.v1
    // esi-search.search_structures.v1
    // esi-skills.read_skillqueue.v1
    // esi-skills.read_skills.v1
    // esi-universe.read_structures.v1
    // esi-wallet.read_character_wallet.v1
    // &state=evesso
    // &response_type=token
    // &redirect_uri=https://esi.tech.ccp.is/latest/o2c.html
    // &realm=ESI
    // &client_id=eadab3ac00814cf6938445ef59987b49
    // &scope=esi-assets.read_assets.v1 esi-bookmarks.read_character_bookmarks.v1 esi-calendar.read_calendar_events.v1 esi-calendar.respond_calendar_events.v1 esi-characters.read_contacts.v1 esi-clones.read_clones.v1 esi-corporations.read_corporation_membership.v1 esi-killmails.read_killmails.v1 esi-location.read_location.v1 esi-location.read_ship_type.v1 esi-mail.organize_mail.v1 esi-mail.read_mail.v1 esi-mail.send_mail.v1 esi-search.search_structures.v1 esi-skills.read_skillqueue.v1 esi-skills.read_skills.v1 esi-universe.read_structures.v1 esi-wallet.read_character_wallet.v1&state=evesso


    //routes crest !!!--------------------------------------------------------------------------------------------------------------------------------


    /*
"constellations": {"href": "https://crest-tq.eveonline.com/constellations/"},
 "itemGroups": {"href": "https://crest-tq.eveonline.com/inventory/groups/"},
 "corporations": {"href": "https://crest-tq.eveonline.com/corporations/"},
 "alliances": {"href": "https://crest-tq.eveonline.com/alliances/"},
 "itemTypes": {"href": "https://crest-tq.eveonline.com/inventory/types/"},
 "userCount": 7751,
 "decode": {"href": "https://crest-tq.eveonline.com/decode/"},
 "marketPrices": {"href": "https://crest-tq.eveonline.com/market/prices/"},
 "opportunities": {"tasks": {"href": "https://crest-tq.eveonline.com/opportunities/tasks/"},
 "groups": {"href": "https://crest-tq.eveonline.com/opportunities/groups/"}},
 "itemCategories": {"href": "https://crest-tq.eveonline.com/inventory/categories/"},
 "regions": {"href": "https://crest-tq.eveonline.com/regions/"},
 "bloodlines": {"href": "https://crest-tq.eveonline.com/bloodlines/"},
 "marketGroups": {"href": "https://crest-tq.eveonline.com/market/groups/"},
 "systems": {"href": "https://crest-tq.eveonline.com/solarsystems/"},
 "sovereignty": {"campaigns": {"href": "https://crest-tq.eveonline.com/sovereignty/campaigns/"},
 				"structures": {"href": "https://crest-tq.eveonline.com/sovereignty/structures/"}},
 "tournaments": {"href": "https://crest-tq.eveonline.com/tournaments/"},
 "virtualGoodStore": {"href": "https://vgs-tq.eveonline.com/"},
 "serverVersion": "EVE-TRANQUILITY 14.1.1102122.1102122",
 "userCount_str": "7751",
 "wars": {"href": "https://crest-tq.eveonline.com/wars/"},
 "incursions": {"href": "https://crest-tq.eveonline.com/incursions/"},
 "dogma": {"attributes": {"href": "https://crest-tq.eveonline.com/dogma/attributes/"},
 			"effects": {"href": "https://crest-tq.eveonline.com/dogma/effects/"}},
 "races": {"href": "https://crest-tq.eveonline.com/races/"},
 "insurancePrices": {"href": "https://crest-tq.eveonline.com/insuranceprices/"},
 "authEndpoint": {"href": "https://login-tq.eveonline.com/oauth/token/"},
 "serviceStatus": "online",
 "industry": {"facilities": {"href": "https://crest-tq.eveonline.com/industry/facilities/"},
 				"systems": {"href": "https://crest-tq.eveonline.com/industry/systems/"}},
 "npcCorporations": {"href": "https://crest-tq.eveonline.com/corporations/npccorps/"},
 "time": {"href": "https://crest-tq.eveonline.com/time/"},
 "marketTypes": {"href": "https://crest-tq.eveonline.com/market/types/"},
 "serverName": "TRANQUILITY"

     */


}
