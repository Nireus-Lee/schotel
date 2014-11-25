/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.hotel.entity;

import javax.persistence.*;
import java.util.List;

/**
 * User: william
 * Date: 14-10-31
 * Time: 下午11:01
 */
@Entity
@Table(name="rateplan_offer")
public class HotelRatePlanOffer {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name="rateplan_id")
    private HotelRatePlan hotelRatePlan;


    /**
     * OfferCode属性：礼盒代码
     */
    private int offerCode;

    /**
     * 打折(住几送几)
     *  NightsRequired属性：满足要求的房夜数；NightsDiscounted属性：免费送的房夜数；DiscountPattern属性：收费/免费房夜的数据格
     */
    private int nightsRequired;

    private int nightsDiscounted;

    private String discountPattern;


    @OneToMany(mappedBy = "ratePlanOffer", cascade = {CascadeType.ALL})
    private List<HotelRatePlanOfferRule> ratePlanOfferRules;


    /**
     *  礼盒描述
     */
    @Column(length=8000)
    private String description;

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

    public int getOfferCode() {
        return offerCode;
    }

    public void setOfferCode(int offerCode) {
        this.offerCode = offerCode;
    }

    public int getNightsRequired() {
        return nightsRequired;
    }

    public void setNightsRequired(int nightsRequired) {
        this.nightsRequired = nightsRequired;
    }

    public int getNightsDiscounted() {
        return nightsDiscounted;
    }

    public void setNightsDiscounted(int nightsDiscounted) {
        this.nightsDiscounted = nightsDiscounted;
    }

    public String getDiscountPattern() {
        return discountPattern;
    }

    public void setDiscountPattern(String discountPattern) {
        this.discountPattern = discountPattern;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<HotelRatePlanOfferRule> getRatePlanOfferRules() {
        return ratePlanOfferRules;
    }

    public void setRatePlanOfferRules(List<HotelRatePlanOfferRule> ratePlanOfferRules) {
        this.ratePlanOfferRules = ratePlanOfferRules;
    }
}
