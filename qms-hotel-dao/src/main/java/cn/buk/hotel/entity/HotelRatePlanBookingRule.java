/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.hotel.entity;

import javax.persistence.*;

/**
 * User: william
 * Date: 14-10-31
 * Time: 下午6:49
 */
@Entity
@Table(name="rateplan_bookingrule")
public class HotelRatePlanBookingRule {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name="rateplan_id")
    private HotelRatePlan hotelRatePlan;

    /**
     * 最少提前预定天数
     */
    private int minAdvancedBookingOffset;

    /**
     * 最大提前预定天数
     */
    private int maxAdvancedBookingOffset;

    /**
     * LaterReserveTime属性：此房间的最晚预订时间，超过这个时间预订不成功，有的酒店可能没有这个限制
     */
    private String laterReserveTime;

    /**
     *住几天以上促销，对应Offers 有详细描述-->
     *LengthOfStay：连住几晚以上的优惠促销-->
     */
    private int lengthOfStay;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HotelRatePlan getHotelRatePlan() {
        return hotelRatePlan;
    }

    public void setHotelRatePlan(HotelRatePlan hotelRatePlan) {
        this.hotelRatePlan = hotelRatePlan;
    }

    public int getMinAdvancedBookingOffset() {
        return minAdvancedBookingOffset;
    }

    public void setMinAdvancedBookingOffset(int minAdvancedBookingOffset) {
        this.minAdvancedBookingOffset = minAdvancedBookingOffset;
    }

    public int getMaxAdvancedBookingOffset() {
        return maxAdvancedBookingOffset;
    }

    public void setMaxAdvancedBookingOffset(int maxAdvancedBookingOffset) {
        this.maxAdvancedBookingOffset = maxAdvancedBookingOffset;
    }

    public String getLaterReserveTime() {
        return laterReserveTime;
    }

    public void setLaterReserveTime(String laterReserveTime) {
        this.laterReserveTime = laterReserveTime;
    }

    public int getLengthOfStay() {
        return lengthOfStay;
    }

    public void setLengthOfStay(int lengthOfStay) {
        this.lengthOfStay = lengthOfStay;
    }
}
