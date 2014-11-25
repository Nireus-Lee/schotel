/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto.hotel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.List;

/**
 * User: william
 * Date: 14-10-30
 * Time: 下午11:17
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class RatePlanRequest {

    @XmlElement(name="DateRange", namespace = "http://www.opentravel.org/OTA/2003/05")
    private DateRange dateRange;

    @XmlElementWrapper(name="RatePlanCandidates", namespace = "http://www.opentravel.org/OTA/2003/05")
    @XmlElement(name = "RatePlanCandidate", namespace = "http://www.opentravel.org/OTA/2003/05")
    private List<RatePlanCandidate> hotelRatePlanCandidates = new ArrayList<RatePlanCandidate>();

    public List<RatePlanCandidate> getHotelRatePlanCandidates() {
        return hotelRatePlanCandidates;
    }

    public void setHotelRatePlanCandidates(List<RatePlanCandidate> hotelRatePlanCandidates) {
        this.hotelRatePlanCandidates = hotelRatePlanCandidates;
    }

    public DateRange getDateRange() {
        return dateRange;
    }

    public void setDateRange(DateRange dateRange) {
        this.dateRange = dateRange;
    }

}
