/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto.hotel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * User: william
 * Date: 14-10-24
 * Time: 下午11:12
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class HotelRequestContent {

    @XmlElement(name="HotelRequestBody")
    private HotelRequestBody hotelRequestBody = new HotelRequestBody();

    public HotelRequestBody getHotelRequestBody() {
        return hotelRequestBody;
    }

    public void setHotelRequestBody(HotelRequestBody hotelRequestBody) {
        this.hotelRequestBody = hotelRequestBody;
    }
}
