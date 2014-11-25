/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto;

import cn.buk.api.util.xstream.MyDateConverter;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

import java.util.Date;

/**
 * User: yfdai
 * Date: 14-10-17
 * Time: 下午4:30
 */
public class PriceUnitMisc {

    @XStreamAlias("Channel")
    private String channel;

    @XStreamAlias("SourceFareIDs")
    private String sourceFareIds;

    @XStreamAlias("TicketingCarrier")
    private String ticketingCarrier;

    @XStreamAlias("IsAddOnJoined")
    private boolean addOnJoined;

    @XStreamAlias("TicketType")
    private String ticketType;

    @XStreamAlias("TravelerEligibility")
    private String travelerEligibility;

    @XStreamAlias("RecommendLevel")
    private int recommendLevel;

    @XStreamAlias("MinPassengerCount")
    private int minPassengerCount;

    @XStreamAlias("MaxPassengerCount")
    private int maxPassengerCount;

    @XStreamAlias("MinStay")
    private String minStay;

    @XStreamAlias("MaxStay")
    private String maxStay;

    @XStreamAlias("OutboundDateRanges")
    private DateRanges outboundDateRanges;

    @XStreamAlias("InboundDateRanges")
    private DateRanges inboundDateRanges;

    @XStreamAlias("SalesDateRanges")
    private String salesDateRange;

    @XStreamAlias("ForbiddenFlightsRemarkID")
    private int forbiddenFlightsRemarkId;

    @XStreamAlias("AllowedFlightsRemarkID")
    private int allowedFlightsRemarkId;

    @XStreamAlias("IsImmediateBooking")
    private boolean immediateBooking;

    @XStreamAlias("BookingDeadline")
    private BookingDeadline bookingDeadline;

    @XStreamAlias("IsOpenJawAllowed")
    private boolean openJawAllowed;

    @XStreamAlias("IsKAllowed")
    private boolean kallowed;

    @XStreamAlias("FareRemarkID")
    private String fareRemarkId;

    @XStreamAlias("RemarkFareID")
    private String remarkFareId;

    @XStreamAlias("PUFareType")
    private String puFareType;

    @XStreamAlias("InventoryType")
    private String inventoryType;

    @XStreamAlias("TicketingDeadline")
    @XStreamConverter(value=MyDateConverter.class, strings={"yyyy-MM-dd'T'HH:mm:ss"})
    private Date ticketingDeadline;

    @XStreamAlias("CarrierTicketingDeadline")
    @XStreamConverter(value=MyDateConverter.class, strings={"yyyy-MM-dd'T'HH:mm:ss"})
    private Date carrierTicketingDeadline;

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getSourceFareIds() {
        return sourceFareIds;
    }

    public void setSourceFareIds(String sourceFareIds) {
        this.sourceFareIds = sourceFareIds;
    }

    public String getTicketingCarrier() {
        return ticketingCarrier;
    }

    public void setTicketingCarrier(String ticketingCarrier) {
        this.ticketingCarrier = ticketingCarrier;
    }

    public boolean isAddOnJoined() {
        return addOnJoined;
    }

    public void setAddOnJoined(boolean addOnJoined) {
        this.addOnJoined = addOnJoined;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public String getTravelerEligibility() {
        return travelerEligibility;
    }

    public void setTravelerEligibility(String travelerEligibility) {
        this.travelerEligibility = travelerEligibility;
    }

    public int getRecommendLevel() {
        return recommendLevel;
    }

    public void setRecommendLevel(int recommendLevel) {
        this.recommendLevel = recommendLevel;
    }

    public int getMinPassengerCount() {
        return minPassengerCount;
    }

    public void setMinPassengerCount(int minPassengerCount) {
        this.minPassengerCount = minPassengerCount;
    }

    public int getMaxPassengerCount() {
        return maxPassengerCount;
    }

    public void setMaxPassengerCount(int maxPassengerCount) {
        this.maxPassengerCount = maxPassengerCount;
    }

    public String getMinStay() {
        return minStay;
    }

    public void setMinStay(String minStay) {
        this.minStay = minStay;
    }

    public String getMaxStay() {
        return maxStay;
    }

    public void setMaxStay(String maxStay) {
        this.maxStay = maxStay;
    }

    public DateRanges getOutboundDateRanges() {
        return outboundDateRanges;
    }

    public void setOutboundDateRanges(DateRanges outboundDateRanges) {
        this.outboundDateRanges = outboundDateRanges;
    }

    public DateRanges getInboundDateRanges() {
        return inboundDateRanges;
    }

    public void setInboundDateRanges(DateRanges inboundDateRanges) {
        this.inboundDateRanges = inboundDateRanges;
    }

    public String getSalesDateRange() {
        return salesDateRange;
    }

    public void setSalesDateRange(String salesDateRange) {
        this.salesDateRange = salesDateRange;
    }

    public int getForbiddenFlightsRemarkId() {
        return forbiddenFlightsRemarkId;
    }

    public void setForbiddenFlightsRemarkId(int forbiddenFlightsRemarkId) {
        this.forbiddenFlightsRemarkId = forbiddenFlightsRemarkId;
    }

    public int getAllowedFlightsRemarkId() {
        return allowedFlightsRemarkId;
    }

    public void setAllowedFlightsRemarkId(int allowedFlightsRemarkId) {
        this.allowedFlightsRemarkId = allowedFlightsRemarkId;
    }

    public boolean isImmediateBooking() {
        return immediateBooking;
    }

    public void setImmediateBooking(boolean immediateBooking) {
        this.immediateBooking = immediateBooking;
    }

    public BookingDeadline getBookingDeadline() {
        return bookingDeadline;
    }

    public void setBookingDeadline(BookingDeadline bookingDeadline) {
        this.bookingDeadline = bookingDeadline;
    }

    public boolean isOpenJawAllowed() {
        return openJawAllowed;
    }

    public void setOpenJawAllowed(boolean openJawAllowed) {
        this.openJawAllowed = openJawAllowed;
    }

    public boolean isKallowed() {
        return kallowed;
    }

    public void setKallowed(boolean kallowed) {
        this.kallowed = kallowed;
    }

    public String getRemarkFareId() {
        return remarkFareId;
    }

    public void setRemarkFareId(String remarkFareId) {
        this.remarkFareId = remarkFareId;
    }

    public String getPuFareType() {
        return puFareType;
    }

    public void setPuFareType(String puFareType) {
        this.puFareType = puFareType;
    }

    public String getInventoryType() {
        return inventoryType;
    }

    public void setInventoryType(String inventoryType) {
        this.inventoryType = inventoryType;
    }

    public Date getTicketingDeadline() {
        return ticketingDeadline;
    }

    public void setTicketingDeadline(Date ticketingDeadline) {
        this.ticketingDeadline = ticketingDeadline;
    }

    public Date getCarrierTicketingDeadline() {
        return carrierTicketingDeadline;
    }

    public void setCarrierTicketingDeadline(Date carrierTicketingDeadline) {
        this.carrierTicketingDeadline = carrierTicketingDeadline;
    }
}
