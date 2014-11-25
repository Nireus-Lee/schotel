/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.hotel.entity;

import javax.persistence.*;

/**
 * User: william
 * Date: 14-10-31
 * Time: 下午9:55
 */
@Entity
@Table(name="rateplan_rate_guarantee")
public class HotelRatePlanRateGuaranteePolicy {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name="rateplan_rate_id")
    private HotelRatePlanRate hotelRatePlanRate;

    /**
     * GuaranteeCode属性：担保类型代码，参考CodeList(RGC)
     */
    private int guaranteeCode;

    /**
     * HoldTime属性：在此时间之前不需要担保
     */
    private String holdTime;

    public int getGuaranteeCode() {
        return guaranteeCode;
    }

    public void setGuaranteeCode(int guaranteeCode) {
        this.guaranteeCode = guaranteeCode;
    }

    public String getHoldTime() {
        return holdTime;
    }

    public void setHoldTime(String holdTime) {
        this.holdTime = holdTime;
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
