/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto.hotel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * User: william
 * Date: 14-10-26
 * Time: 下午10:58
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "http://www.opentravel.org/OTA/2003/05")
public class HotelRef {

    @XmlAttribute(name="HotelCityCode")
    private int hotelCityCode = 2;

    @XmlAttribute(name="AreaID")
    private Integer areaId ;

    @XmlAttribute(name="HotelName")
    private String HotelName;

    public int getHotelCityCode() {
        return hotelCityCode;
    }

    public void setHotelCityCode(int hotelCityCode) {
        this.hotelCityCode = hotelCityCode;
    }

    public int getAreaId() {
        if (this.areaId == null)
            return 0;
        else
            return areaId.intValue();
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public String getHotelName() {
        return HotelName;
    }

    public void setHotelName(String hotelName) {
        HotelName = hotelName;
    }
}
