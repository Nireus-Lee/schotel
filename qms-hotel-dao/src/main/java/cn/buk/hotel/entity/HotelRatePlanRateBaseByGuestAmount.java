/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.hotel.entity;

import javax.persistence.*;

/**
 * User: william
 * Date: 14-10-31
 * Time: 下午8:08
 */
@Entity
@Table(name="rateplan_rate_baseByGuestAmount")
public class HotelRatePlanRateBaseByGuestAmount {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name="rateplan_rate_id")
    private HotelRatePlanRate hotelRatePlanRate;

    private String currencyCode;
    private float listPrice;
    private float amountBeforeTax;
    private int numberOfGuest;

    public HotelRatePlanRate getHotelRatePlanRate() {
        return hotelRatePlanRate;
    }

    public void setHotelRatePlanRate(HotelRatePlanRate hotelRatePlanRate) {
        this.hotelRatePlanRate = hotelRatePlanRate;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public float getListPrice() {
        return listPrice;
    }

    public void setListPrice(float listPrice) {
        this.listPrice = listPrice;
    }

    public float getAmountBeforeTax() {
        return amountBeforeTax;
    }

    public void setAmountBeforeTax(float amountBeforeTax) {
        this.amountBeforeTax = amountBeforeTax;
    }

    public int getNumberOfGuest() {
        return numberOfGuest;
    }

    public void setNumberOfGuest(int numberOfGuest) {
        this.numberOfGuest = numberOfGuest;
    }
}
