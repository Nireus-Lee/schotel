/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.hotel.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * User: william
 * Date: 14-10-31
 * Time: 下午9:59
 */
@Entity
@Table(name="rateplan_rate_cancelpolicy")
public class HotelRatePlanRateCancelPolicy {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name="rateplan_rate_id")
    private HotelRatePlanRate hotelRatePlanRate;


    /**
     *  Start属性：开始时间,表示在这个时间段取消是需要扣除罚金，start表示了最迟的取消时间，在这个时间前取消不需要扣除罚
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;

    /**
     * End属性：结束时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;

    /**
     * 罚金金额=担保金额=当日房价
     */
    private float amount;

    private String currencyCode;

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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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
}
