/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto.hotel;

import javax.xml.bind.annotation.*;

/**
 * User: william
 * Date: 14-10-26
 * Time: 下午10:54
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "http://www.opentravel.org/OTA/2003/05")
public class HotelSearchCriteria {

    @XmlAttribute(name = "AvailableOnlyIndicator")
    private boolean AvailableOnlyIndicator = true;

    @XmlElement(name="Criterion")
    private HotelSearchCriterion criterion = new HotelSearchCriterion();


    public boolean isAvailableOnlyIndicator() {
        return AvailableOnlyIndicator;
    }

    public void setAvailableOnlyIndicator(boolean availableOnlyIndicator) {
        AvailableOnlyIndicator = availableOnlyIndicator;
    }

    public HotelSearchCriterion getCriterion() {
        return criterion;
    }

    public void setCriterion(HotelSearchCriterion criterion) {
        this.criterion = criterion;
    }
}
