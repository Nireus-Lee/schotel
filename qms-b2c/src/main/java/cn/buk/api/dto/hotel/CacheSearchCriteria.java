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
 * Time: 上午9:38
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class CacheSearchCriteria {

    @XmlAttribute(name="CacheFromTimestamp", required = true)
    private String cacheFromTimestamp;

    @XmlElement(name="CacheSearchCriterion", namespace = "http://www.opentravel.org/OTA/2003/05")
    private CacheSearchCriterion cacheSearchCriterion = new CacheSearchCriterion();

    public String getCacheFromTimestamp() {
        return cacheFromTimestamp;
    }

    public void setCacheFromTimestamp(String cacheFromTimestamp) {
        this.cacheFromTimestamp = cacheFromTimestamp;
    }

    public CacheSearchCriterion getCacheSearchCriterion() {
        return cacheSearchCriterion;
    }

    public void setCacheSearchCriterion(CacheSearchCriterion cacheSearchCriterion) {
        this.cacheSearchCriterion = cacheSearchCriterion;
    }
}
