/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * User: yfdai
 * Date: 14-10-17
 * Time: 下午5:04
 */
public class BookingDeadline {

    @XStreamAlias("AdvReservationFrom")
    private String advReservationFrom;

    @XStreamAlias("AdvReservationTo")
    private String advReservationTo;

    @XStreamAlias("IsImmediateBooking")
    private boolean immediateBooking;

    @XStreamAlias("BeforeDeparture")
    private String beforeDeparture;

    @XStreamAlias("AfterReservation")
    private String afterReservation;

    public String getAdvReservationFrom() {
        return advReservationFrom;
    }

    public void setAdvReservationFrom(String advReservationFrom) {
        this.advReservationFrom = advReservationFrom;
    }

    public String getAdvReservationTo() {
        return advReservationTo;
    }

    public void setAdvReservationTo(String advReservationTo) {
        this.advReservationTo = advReservationTo;
    }

    public boolean isImmediateBooking() {
        return immediateBooking;
    }

    public void setImmediateBooking(boolean immediateBooking) {
        this.immediateBooking = immediateBooking;
    }

    public String getBeforeDeparture() {
        return beforeDeparture;
    }

    public void setBeforeDeparture(String beforeDeparture) {
        this.beforeDeparture = beforeDeparture;
    }

    public String getAfterReservation() {
        return afterReservation;
    }

    public void setAfterReservation(String afterReservation) {
        this.afterReservation = afterReservation;
    }
}
