/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto.hotel;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * User: william
 * Date: 14-10-30
 * Time: 下午11:28
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class RatePlanCandidate {

    @XmlAttribute(name = "AvailRatesOnlyInd")
    private boolean availRatesOnlyInd;

    @XmlElementWrapper(name="HotelRefs", namespace = "http://www.opentravel.org/OTA/2003/05")
    @XmlElement(name = "HotelRef", namespace = "http://www.opentravel.org/OTA/2003/05")
    private List<RatePlanHotelRef> ratePlanHotelRefs = new ArrayList<RatePlanHotelRef>();

    public List<RatePlanHotelRef> getRatePlanHotelRefs() {
        return ratePlanHotelRefs;
    }

    public void setRatePlanHotelRefs(List<RatePlanHotelRef> ratePlanHotelRefs) {
        this.ratePlanHotelRefs = ratePlanHotelRefs;
    }

    public boolean isAvailRatesOnlyInd() {
        return availRatesOnlyInd;
    }

    public void setAvailRatesOnlyInd(boolean availRatesOnlyInd) {
        this.availRatesOnlyInd = availRatesOnlyInd;
    }

}
