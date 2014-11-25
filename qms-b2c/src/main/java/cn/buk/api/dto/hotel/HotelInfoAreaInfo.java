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
 * Date: 14-10-29
 * Time: 下午11:40
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class HotelInfoAreaInfo {

    @XmlAttribute(name="SendAttractions")
    private boolean sendAttractions=true;

    @XmlAttribute(name="SendRecreations")
    private boolean sendRecreations=true;
}
