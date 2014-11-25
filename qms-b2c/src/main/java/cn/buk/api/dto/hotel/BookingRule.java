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
 * Time: 上午9:32
 */
@XStreamAlias("BookingRule")
public class BookingRule {

    //<!-- MaxAdvancedBookingOffset属性：最大提前预订时间差 -->
    //<!-- MinAdvancedBookingOffset：提前多少天预订会优惠-->

    @XStreamAlias("MaxAdvancedBookingOffset")
    @XStreamAsAttribute
    private int minAdvancedBookingOffset;

    @XStreamAlias("MinAdvancedBookingOffset")
    @XStreamAsAttribute
    private int maxAdvancedBookingOffset;

    /**
     * LaterReserveTime属性：此房间的最晚预订时间，超过这个时间预订不成功，有的酒店可能没有这个限制
     */
    @XStreamAlias("LaterReserveTime")
    @XStreamAsAttribute
    private String laterReserveTime;

    /**
     *住几天以上促销，对应Offers 有详细描述-->
     *LengthOfStay：连住几晚以上的优惠促销-->
     */
    @XStreamAlias("LengthsOfStay")
    private List<LengthOfStay> lengthOfStays;

    @XStreamAlias("Viewerships")
    private List<Viewership> viewerships;

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

    public List<LengthOfStay> getLengthOfStays() {
        return lengthOfStays;
    }

    public void setLengthOfStays(List<LengthOfStay> lengthOfStays) {
        this.lengthOfStays = lengthOfStays;
    }

    public String getLaterReserveTime() {
        return laterReserveTime;
    }

    public void setLaterReserveTime(String laterReserveTime) {
        this.laterReserveTime = laterReserveTime;
    }

    public List<Viewership> getViewerships() {
        return viewerships;
    }

    public void setViewerships(List<Viewership> viewerships) {
        this.viewerships = viewerships;
    }
}
