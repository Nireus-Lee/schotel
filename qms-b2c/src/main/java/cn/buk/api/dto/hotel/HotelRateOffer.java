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
 * Time: 下午2:38
 */
@XStreamAlias("Offer")
public class HotelRateOffer {

    @XStreamAlias("OfferCode")
    @XStreamAsAttribute
    private int offerCode;

    @XStreamAlias("Discount")
    private HotelOfferDiscount hotelOfferDiscount;

    @XStreamAlias("OfferRules")
    private List<HotelRateOfferRule> hotelRateOfferRules;


    /**
     *  礼盒描述
     */
    @XStreamAlias("OfferDescription")
    private Description description;

    public int getOfferCode() {
        return offerCode;
    }

    public void setOfferCode(int offerCode) {
        this.offerCode = offerCode;
    }

    public List<HotelRateOfferRule> getHotelRateOfferRules() {
        return hotelRateOfferRules;
    }

    public void setHotelRateOfferRules(List<HotelRateOfferRule> hotelRateOfferRules) {
        this.hotelRateOfferRules = hotelRateOfferRules;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public HotelOfferDiscount getHotelOfferDiscount() {
        return hotelOfferDiscount;
    }

    public void setHotelOfferDiscount(HotelOfferDiscount hotelOfferDiscount) {
        this.hotelOfferDiscount = hotelOfferDiscount;
    }
//    <!--打折(住几送几)-->
//    <!-- NightsRequired属性：满足要求的房夜数；NightsDiscounted属性：免费送的房夜数；DiscountPattern属性：收费/免费房夜的数据格式-->
//    < Discount />
//    <Discount NightsRequired="3"—住2送1，合计3天  NightsDiscounted="1"---送多少天 DiscountPattern="001" />

}
