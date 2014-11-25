/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto.hotel;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * User: william
 * Date: 14-10-30
 * Time: 下午11:10
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class HotelRatePlanRequest {

    @XmlAttribute(name="Version")
    private String version = "1.0";

    @XmlElementWrapper(name="RatePlans", namespace = "http://www.opentravel.org/OTA/2003/05")
    @XmlElement(name = "RatePlan", namespace = "http://www.opentravel.org/OTA/2003/05")
    private List<RatePlanRequest> ratePlanRequests = new ArrayList<RatePlanRequest>();

    public List<RatePlanRequest> getRatePlanRequests() {
        return ratePlanRequests;
    }

    public void setRatePlanRequests(List<RatePlanRequest> ratePlanRequests) {
        this.ratePlanRequests = ratePlanRequests;
    }
}
