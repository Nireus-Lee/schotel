/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto.hotel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * User: william
 * Date: 14-11-1
 * Time: 上午9:37
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class HotelCacheChangeRequest {

    @XmlAttribute(name="Version")
    private String version = "1.0";

    @XmlElement(name="CacheSearchCriteria", namespace = "http://www.opentravel.org/OTA/2003/05")
    private CacheSearchCriteria cacheSearchCriteria = new CacheSearchCriteria();

    public CacheSearchCriteria getCacheSearchCriteria() {
        return cacheSearchCriteria;
    }

    public void setCacheSearchCriteria(CacheSearchCriteria cacheSearchCriteria) {
        this.cacheSearchCriteria = cacheSearchCriteria;
    }
}
