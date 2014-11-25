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
 * Date: 14-10-29
 * Time: 下午8:19
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class HotelDetailRequest {

    @XmlAttribute(name="Version")
    private String version = "1.0";

    @XmlElementWrapper(name="HotelDescriptiveInfos", namespace = "http://www.opentravel.org/OTA/2003/05")
    @XmlElement(name = "HotelDescriptiveInfo", namespace = "http://www.opentravel.org/OTA/2003/05")
    private List<HotelDescriptiveInfo> hotelDescriptiveInfos = new ArrayList<HotelDescriptiveInfo>();

    public List<HotelDescriptiveInfo> getHotelDescriptiveInfos() {
        return hotelDescriptiveInfos;
    }

    public void setHotelDescriptiveInfos(List<HotelDescriptiveInfo> hotelDescriptiveInfos) {
        this.hotelDescriptiveInfos = hotelDescriptiveInfos;
    }




}
