/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.hotel.entity;

import javax.persistence.*;

/**
 * User: william
 * Date: 14-10-31
 * Time: 下午9:10
 */
@Entity
@Table(name="rateplan_rate_fee")
public class HotelRatePlanRateFee {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name="rateplan_rate_id")
    private HotelRatePlanRate hotelRatePlanRate;

    /**
     * code属性：费用类型代码，参考CodeList(FTT)
     */
    private int code;

    private float amount;

    /**
     *属性：币种
     */
    private String currencyCode;

    /**
     * ChargeUnit属性：扣款单位:如每房每晚/每人
     */
    private int chargeUnit;

    private String description;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public int getChargeUnit() {
        return chargeUnit;
    }

    public void setChargeUnit(int chargeUnit) {
        this.chargeUnit = chargeUnit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HotelRatePlanRate getHotelRatePlanRate() {
        return hotelRatePlanRate;
    }

    public void setHotelRatePlanRate(HotelRatePlanRate hotelRatePlanRate) {
        this.hotelRatePlanRate = hotelRatePlanRate;
    }
}
