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
 * Date: 14-11-1
 * Time: 上午9:42
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class CacheSearchCriterion {

    @XmlAttribute(name="HotelCityCode", required = true)
    private int hotelCityCode;

    @XmlAttribute(name="HotelCode")
    private String hotelCode;

    public int getHotelCityCode() {
        return hotelCityCode;
    }

    public void setHotelCityCode(int hotelCityCode) {
        this.hotelCityCode = hotelCityCode;
    }

    public String getHotelCode() {
        return hotelCode;
    }

    public void setHotelCode(String hotelCode) {
        this.hotelCode = hotelCode;
    }
}
