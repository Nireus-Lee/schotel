package com.ctrip.openapi.java.utils;

public class ConfigData {
	public static String RequestType = "OTA_Ping";

    public static String OTA_FlightSearch_Request = "OTA_FlightSearch";
    public static String OTA_IntlFlightSearch_Request = "OAE_IntlFlightSearch";

    public static String OTA_UserUniqueID_Request = "OTA_UserUniqueID";

    public static String OTA_FltSaveOrder_Request = "OTA_FltSaveOrder";
    public static String OTA_FltViewOrder_Request = "OTA_FltViewOrder";
    public static String OTA_FltOrderList_Request = "OTA_FltOrderList";
    public static String OTA_FltCancelOrder_Request = "OTA_FltCancelOrder";

    public static String OTA_HotelSearch_Request =  "OTA_HotelSearch";
    public static String OTA_HotelDetail_Request =  "OTA_HotelDescriptiveInfo";
    public static String OTA_HotelRatePlan_Request = "OTA_HotelRatePlan";
    public static String OTA_HotelCacheChange_Request = "OTA_HotelCacheChange";


    public static String OPEN_API_URL = "http://openapi.ctrip.com";
    public static String OTA_FlightSearch_Url = OPEN_API_URL + "/Flight/DomesticFlight/OTA_FlightSearch.asmx";
    public static String OTA_IntlFlightSearch_Url = OPEN_API_URL + "/Flight/IntlFlight/OAE_IntlFlightSearch.asmx ";
    public static String OTA_FltSaveOrder_URL = OPEN_API_URL + "/Flight/DomesticFlight/OTA_FltSaveOrder.asmx";
    public static String OPEN_FLIGHT_PAYMENT_URL = OPEN_API_URL + "/Flight/PaymentEntry.aspx";

    public static String OTA_HotelSearch_Url = OPEN_API_URL + "/Hotel/OTA_HotelSearch.asmx";
    public static String OTA_HotelDetail_Url = OPEN_API_URL + "/Hotel/OTA_HotelDescriptiveInfo.asmx";
    public static String OTA_HotelRatePlan_Url = OPEN_API_URL + "/Hotel/OTA_HotelRatePlan.asmx";
    public static String OTA_HotelCacheChange_url = OPEN_API_URL + "/Hotel/OTA_HotelCacheChange.asmx";


    public static String XML_COUNTRY_FILE = "xml_country";
    public static String XML_PROVINCE_FILE = "xml_province";
    public static String XML_AIRPORT_FILE = "xml_airport";
    public static String XML_TERMINAL_FILE = "xml_terminal";
    public static String XML_CARRIER_FILE="xml_carrier";


    public static String HOTEL_CODES_WAITING_FOR_RATE_PLAN = "hotel_codes_waiting_for_rate_plan";


}
