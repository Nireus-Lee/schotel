/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.hotel.entity;

import javax.persistence.*;

/**
 * User: william
 * Date: 14-10-31
 * Time: 下午11:11
 */
@Entity
@Table(name="rateplan_roomtype")
public class HotelRatePlanSellableProduct {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name="rateplan_id")
    private HotelRatePlan hotelRatePlan;

    private String invCode;

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

    public String getInvCode() {
        return invCode;
    }

    public void setInvCode(String invCode) {
        this.invCode = invCode;
    }
}
