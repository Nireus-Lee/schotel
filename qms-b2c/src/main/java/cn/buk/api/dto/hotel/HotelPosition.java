/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto.hotel;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * User: william
 * Date: 14-10-26
 * Time: 下午11:01
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "http://www.opentravel.org/OTA/2003/05")
public class HotelPosition {


    @XmlAttribute(name="PositionTypeCode")
    @XStreamAsAttribute
    @XStreamAlias("PositionTypeCode")
    private String positionTypeCode;

    @XmlAttribute(name="Longitude")
    @XStreamAsAttribute
    @XStreamAlias("Longitude")
    private double longitude;

    @XmlAttribute(name="Latitude")
    @XStreamAsAttribute
    @XStreamAlias("Latitude")
    private double latitude;

    public String getPositionTypeCode() {
        return positionTypeCode;
    }

    public void setPositionTypeCode(String positionTypeCode) {
        this.positionTypeCode = positionTypeCode;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
