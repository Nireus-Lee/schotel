/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.hotel.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * User: william
 * Date: 14-11-3
 * Time: 下午11:33
 */
@Entity
@Table(name="rateplan_offer_rule")
public class HotelRatePlanOfferRule {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name="rateplan_offer_id")
    private HotelRatePlanOffer ratePlanOffer;

    @OneToMany(mappedBy = "ratePlanOfferRule", cascade = {CascadeType.ALL})
    private List<HotelRatePlanOfferRuleDateRestriction> ratePlanOfferRuleDateRestrictions = new ArrayList<HotelRatePlanOfferRuleDateRestriction>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HotelRatePlanOffer getRatePlanOffer() {
        return ratePlanOffer;
    }

    public void setRatePlanOffer(HotelRatePlanOffer ratePlanOffer) {
        this.ratePlanOffer = ratePlanOffer;
    }

    public List<HotelRatePlanOfferRuleDateRestriction> getRatePlanOfferRuleDateRestrictions() {
        return ratePlanOfferRuleDateRestrictions;
    }

    public void setRatePlanOfferRuleDateRestrictions(List<HotelRatePlanOfferRuleDateRestriction> ratePlanOfferRuleDateRestrictions) {
        this.ratePlanOfferRuleDateRestrictions = ratePlanOfferRuleDateRestrictions;
    }
}
