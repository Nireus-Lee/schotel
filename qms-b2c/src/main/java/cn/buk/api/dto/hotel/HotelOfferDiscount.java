/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto.hotel;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * User: yfdai
 * Date: 14-10-31
 * Time: 下午3:19
 */
public class HotelOfferDiscount {

    @XStreamAlias("NightsRequired")
    @XStreamAsAttribute
    private int nightsRequired;

    @XStreamAlias("NightsDiscounted")
    @XStreamAsAttribute
    private int nightsDiscounted;

    @XStreamAlias("DiscountPattern")
    @XStreamAsAttribute
    private String discountPattern;

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
}
