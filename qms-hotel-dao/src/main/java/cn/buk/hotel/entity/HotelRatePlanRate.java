/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.hotel.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * User: william
 * Date: 14-10-31
 * Time: 下午7:29
 */
@Entity
@Table(name="rateplan_rate")
public class HotelRatePlanRate {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name="rateplan_id")
    private HotelRatePlan hotelRatePlan;

    //<!-- Start属性：价格开始时间；End属性：价格结束时间；-->
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    private Date endDate;

    /**
     *  Status属性：open可售状态，onrequest 房源紧张,close表示不可售
     */
    private String status;

    /**
     * 是否即时确认
     */
    private int instantConfirm;

    /**
     *Breakfast属性：是否含早
     */
    private int breakfast;

    /**
     * NumberOfBreakfast属性：早餐的数量
     */
    private int numberOfBreakfast;

    /**
     * LaterReserveTime属性：此房间的最晚预订时间，超过这个时间预订不成功，有的酒店可能没有这个限制
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date laterReserveTime;

    @OneToMany(mappedBy = "hotelRatePlanRate", cascade = {CascadeType.ALL})
    private List<HotelRatePlanRateBaseByGuestAmount> hotelRatePlanRateBaseByGuestAmounts;

    @OneToMany(mappedBy = "hotelRatePlanRate", cascade = {CascadeType.ALL})
    private List<HotelRatePlanRateFee> hotelRatePlanRateFees;

    @OneToMany(mappedBy = "hotelRatePlanRate", cascade = {CascadeType.ALL})
    private List<HotelRatePlanRateGuaranteePolicy> hotelRatePlanRateGuaranteePolicies;

    @OneToMany(mappedBy = "hotelRatePlanRate", cascade = {CascadeType.ALL})
    private List<HotelRatePlanRateCancelPolicy> hotelRatePlanRateCancelPolicies;

    @OneToMany(mappedBy = "hotelRatePlanRate", cascade = {CascadeType.ALL})
    private List<HotelRatePlanRatePromotion> hotelRatePlanRatePromotions;

    public HotelRatePlan getHotelRatePlan() {
        return hotelRatePlan;
    }

    public void setHotelRatePlan(HotelRatePlan hotelRatePlan) {
        this.hotelRatePlan = hotelRatePlan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getInstantConfirm() {
        return instantConfirm;
    }

    public void setInstantConfirm(int instantConfirm) {
        this.instantConfirm = instantConfirm;
    }

    public int getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(int breakfast) {
        this.breakfast = breakfast;
    }

    public int getNumberOfBreakfast() {
        return numberOfBreakfast;
    }

    public void setNumberOfBreakfast(int numberOfBreakfast) {
        this.numberOfBreakfast = numberOfBreakfast;
    }

    public Date getLaterReserveTime() {
        return laterReserveTime;
    }

    public void setLaterReserveTime(Date laterReserveTime) {
        this.laterReserveTime = laterReserveTime;
    }

    public List<HotelRatePlanRateBaseByGuestAmount> getHotelRatePlanRateBaseByGuestAmounts() {
        return hotelRatePlanRateBaseByGuestAmounts;
    }

    public void setHotelRatePlanRateBaseByGuestAmounts(List<HotelRatePlanRateBaseByGuestAmount> hotelRatePlanRateBaseByGuestAmounts) {
        this.hotelRatePlanRateBaseByGuestAmounts = hotelRatePlanRateBaseByGuestAmounts;
    }

    public List<HotelRatePlanRateFee> getHotelRatePlanRateFees() {
        return hotelRatePlanRateFees;
    }

    public void setHotelRatePlanRateFees(List<HotelRatePlanRateFee> hotelRatePlanRateFees) {
        this.hotelRatePlanRateFees = hotelRatePlanRateFees;
    }

    public List<HotelRatePlanRateGuaranteePolicy> getHotelRatePlanRateGuaranteePolicies() {
        return hotelRatePlanRateGuaranteePolicies;
    }

    public void setHotelRatePlanRateGuaranteePolicies(List<HotelRatePlanRateGuaranteePolicy> hotelRatePlanRateGuaranteePolicies) {
        this.hotelRatePlanRateGuaranteePolicies = hotelRatePlanRateGuaranteePolicies;
    }

    public List<HotelRatePlanRateCancelPolicy> getHotelRatePlanRateCancelPolicies() {
        return hotelRatePlanRateCancelPolicies;
    }

    public void setHotelRatePlanRateCancelPolicies(List<HotelRatePlanRateCancelPolicy> hotelRatePlanRateCancelPolicies) {
        this.hotelRatePlanRateCancelPolicies = hotelRatePlanRateCancelPolicies;
    }

    public List<HotelRatePlanRatePromotion> getHotelRatePlanRatePromotions() {
        return hotelRatePlanRatePromotions;
    }

    public void setHotelRatePlanRatePromotions(List<HotelRatePlanRatePromotion> hotelRatePlanRatePromotions) {
        this.hotelRatePlanRatePromotions = hotelRatePlanRatePromotions;
    }
}
