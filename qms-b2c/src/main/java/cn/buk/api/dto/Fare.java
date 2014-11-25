/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * User: william
 * Date: 14-10-17
 * Time: 下午9:41
 */
@XStreamAlias("Fare")
public class Fare {

    @XStreamAlias("FareID")
    private int id;

    @XStreamAlias("IsAddOn")
    private boolean addOn;

    @XStreamAlias("FareSource")
    private int fareSource;

    @XStreamAlias("CabinClass")
    private String cabinClass;

    @XStreamAlias("BookingClass")
    private String bookingClass;

    @XStreamAlias("TicketingCarrier")
    private String ticketingCarrier;

    @XStreamAlias("OperatingCarrier")
    private String operatingCarrier;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAddOn() {
        return addOn;
    }

    public void setAddOn(boolean addOn) {
        this.addOn = addOn;
    }

    public int getFareSource() {
        return fareSource;
    }

    public void setFareSource(int fareSource) {
        this.fareSource = fareSource;
    }

    public String getCabinClass() {
        return cabinClass;
    }

    public void setCabinClass(String cabinClass) {
        this.cabinClass = cabinClass;
    }

    public String getBookingClass() {
        return bookingClass;
    }

    public void setBookingClass(String bookingClass) {
        this.bookingClass = bookingClass;
    }

    public String getTicketingCarrier() {
        return ticketingCarrier;
    }

    public void setTicketingCarrier(String ticketingCarrier) {
        this.ticketingCarrier = ticketingCarrier;
    }

    public String getOperatingCarrier() {
        return operatingCarrier;
    }

    public void setOperatingCarrier(String operatingCarrier) {
        this.operatingCarrier = operatingCarrier;
    }
}
