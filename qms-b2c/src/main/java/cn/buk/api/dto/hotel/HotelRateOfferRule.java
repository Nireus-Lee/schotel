/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto.hotel;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * User: yfdai
 * Date: 14-10-31
 * Time: 下午2:41
 */
@XStreamAlias("OfferRule")
public class HotelRateOfferRule {

    @XStreamImplicit(itemFieldName = "DateRestriction")
    private List<DateRange> dateRestrictions;

    public List<DateRange> getDateRestrictions() {
        return dateRestrictions;
    }

    public void setDateRestrictions(List<DateRange> dateRestrictions) {
        this.dateRestrictions = dateRestrictions;
    }
}
