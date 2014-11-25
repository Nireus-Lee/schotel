/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto.hotel;

import javax.xml.bind.annotation.*;

/**
 * User: william
 * Date: 14-10-26
 * Time: 下午10:54
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "http://www.opentravel.org/OTA/2003/05")
public class HotelRequestRQ {

    @XmlAttribute(name="Version")
    private String version = "1.0";

    @XmlAttribute(name="PrimaryLangID")
    private String primaryLangID="zh";

    @XmlElement(name="Criteria")
    private HotelSearchCriteria criteria = new HotelSearchCriteria();


    public HotelSearchCriteria getCriteria() {
        return criteria;
    }

    public void setCriteria(HotelSearchCriteria criteria) {
        this.criteria = criteria;
    }
}
