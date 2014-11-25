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
 * Time: 下午10:48
 */
@Entity
@Table(name="rateplan_rate_promotion")
public class HotelRatePlanRatePromotion {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name="rateplan_rate_id")
    private HotelRatePlanRate hotelRatePlanRate;

    /**
     *StartPeriod属性：开始时间
     */
    @Temporal(TemporalType.DATE)
    private Date startDate;

    /**
     * EndPeriod属性：结束时间
     */
    @Temporal(TemporalType.DATE)
    private Date endDate;

    /**
     * ProgramName属性：活动名称
     */
    private String programName;

    private int amount;

    private String currencyCode;

    /**
     * Code属性：活动类型，参考CodeList(RBP)
     */
    private int code;

    private  String description;

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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
