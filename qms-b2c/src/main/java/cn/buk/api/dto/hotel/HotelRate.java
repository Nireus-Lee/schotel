/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto.hotel;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import java.util.List;

/**
 * User: yfdai
 * Date: 14-10-31
 * Time: 上午10:32
 */
@XStreamAlias("Rate")
public class HotelRate {

    @XStreamAlias("Start")
    @XStreamAsAttribute
    private String start;

    @XStreamAlias("End")
    @XStreamAsAttribute
    private String end;

    @XStreamAlias("Status")
    @XStreamAsAttribute
    private String status;

    @XStreamAlias("IsInstantConfirm")
    @XStreamAsAttribute
    private boolean instantConfirm;

    /**
     * 按客人数量的基础价格列表
     */
    @XStreamAlias("BaseByGuestAmts")
    private List<HotelRateBaseByGuestAmount> hotelRateBaseByGuestAmount;

    /**
     * 额外费用列表：比如宽带费/加床费/加早餐费
     */
    @XStreamAlias("Fees")
    private List<HotelRateFee> hotelRateFees;

    /**
     * 担保制度列表，担保金额等于当日房价
     */
    @XStreamAlias("GuaranteePolicies")
    private List<HotelRateGuaranteePolicy> hotelRateGuaranteePolicies;

    /**
     * 取消制度列表
     */
    @XStreamAlias("CancelPolicies")
    private List<HotelRateCancelPolicy> hotelRateCancelPolicies;


    /**
     * 餐食信息
     */
    @XStreamAlias("MealsIncluded")
    private MealsIncluded mealsIncluded;


    /**
     * 附加信息
     */
    @XStreamAlias("TPA_Extensions")
    private List<RebatePromotion> rebatePromotions;

    /**
     * 酒店预订时间限制
     */
    @XStreamAlias("BookingRules")
    private List<BookingRule> bookingRules;



    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<HotelRateBaseByGuestAmount> getHotelRateBaseByGuestAmount() {
        return hotelRateBaseByGuestAmount;
    }

    public void setHotelRateBaseByGuestAmount(List<HotelRateBaseByGuestAmount> hotelRateBaseByGuestAmount) {
        this.hotelRateBaseByGuestAmount = hotelRateBaseByGuestAmount;
    }

    public List<HotelRateFee> getHotelRateFees() {
        return hotelRateFees;
    }

    public void setHotelRateFees(List<HotelRateFee> hotelRateFees) {
        this.hotelRateFees = hotelRateFees;
    }

    public List<HotelRateGuaranteePolicy> getHotelRateGuaranteePolicies() {
        return hotelRateGuaranteePolicies;
    }

    public void setHotelRateGuaranteePolicies(List<HotelRateGuaranteePolicy> hotelRateGuaranteePolicies) {
        this.hotelRateGuaranteePolicies = hotelRateGuaranteePolicies;
    }

    public List<HotelRateCancelPolicy> getHotelRateCancelPolicies() {
        return hotelRateCancelPolicies;
    }

    public void setHotelRateCancelPolicies(List<HotelRateCancelPolicy> hotelRateCancelPolicies) {
        this.hotelRateCancelPolicies = hotelRateCancelPolicies;
    }

    public MealsIncluded getMealsIncluded() {
        return mealsIncluded;
    }

    public void setMealsIncluded(MealsIncluded mealsIncluded) {
        this.mealsIncluded = mealsIncluded;
    }

    public List<RebatePromotion> getRebatePromotions() {
        return rebatePromotions;
    }

    public void setRebatePromotions(List<RebatePromotion> rebatePromotions) {
        this.rebatePromotions = rebatePromotions;
    }

    public List<BookingRule> getBookingRules() {
        return bookingRules;
    }

    public void setBookingRules(List<BookingRule> bookingRules) {
        this.bookingRules = bookingRules;
    }

    public boolean isInstantConfirm() {
        return instantConfirm;
    }

    public void setInstantConfirm(boolean instantConfirm) {
        this.instantConfirm = instantConfirm;
    }
}
