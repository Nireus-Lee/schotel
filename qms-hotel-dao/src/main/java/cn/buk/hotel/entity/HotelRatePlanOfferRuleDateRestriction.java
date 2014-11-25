/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.hotel.entity;

import javax.persistence.*;

/**
 * User: william
 * Date: 14-11-3
 * Time: 下午11:41
 */
@Entity
@Table(name="rateplan_offer_rule_date_restriction")
public class HotelRatePlanOfferRuleDateRestriction {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name="rateplan_offer_rule_id")
    private HotelRatePlanOfferRule ratePlanOfferRule;

    @Embedded
    private DateRange dateRange = new DateRange();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HotelRatePlanOfferRule getRatePlanOfferRule() {
        return ratePlanOfferRule;
    }

    public void setRatePlanOfferRule(HotelRatePlanOfferRule ratePlanOfferRule) {
        this.ratePlanOfferRule = ratePlanOfferRule;
    }


    public DateRange getDateRange() {
        return dateRange;
    }

    public void setDateRange(DateRange dateRange) {
        this.dateRange = dateRange;
    }
}
