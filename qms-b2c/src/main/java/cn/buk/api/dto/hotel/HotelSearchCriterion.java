/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto.hotel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * User: william
 * Date: 14-10-26
 * Time: 下午10:56
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "http://www.opentravel.org/OTA/2003/05")
public class HotelSearchCriterion {

    @XmlElement(name="HotelRef")
    private HotelRef hotelRef = new HotelRef();

    @XmlElement(name="Position")
    private HotelPosition hotelPosition;

    @XmlElement(name="Award")
    private HotelAward hotelAward;

    public HotelRef getHotelRef() {
        return hotelRef;
    }

    public void setHotelRef(HotelRef hotelRef) {
        this.hotelRef = hotelRef;
    }

    public HotelPosition getHotelPosition() {
        return hotelPosition;
    }

    public void setHotelPosition(HotelPosition hotelPosition) {
        this.hotelPosition = hotelPosition;
    }

    public HotelAward getHotelAward() {
        return hotelAward;
    }

    public void setHotelAward(HotelAward hotelAward) {
        this.hotelAward = hotelAward;
    }
//    </ns:Criterion>
//    </ns:Criteria>
}
