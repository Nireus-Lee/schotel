/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto.hotel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * User: william
 * Date: 14-10-30
 * Time: 下午11:33
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class RatePlanHotelRef {

    @XmlAttribute(name="HotelCode")
    private String hotelCode;

    public String getHotelCode() {
        return hotelCode;
    }

    public void setHotelCode(String hotelCode) {
        this.hotelCode = hotelCode;
    }
}
