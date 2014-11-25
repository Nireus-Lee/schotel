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

/**
 * User: william
 * Date: 14-10-30
 * Time: 下午11:21
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class DateRange {

    /**
     *  Start属性：开始时间
     */
    @XmlAttribute(name="Start")
    @XStreamAlias("Start")
    @XStreamAsAttribute
    private String startDate;

    /**
     * End属性：结束时间
     */
    @XmlAttribute(name="End")
    @XStreamAlias("End")
    @XStreamAsAttribute
    private String endDate;

    /**
     * RestrictionDateCode ：501表示促销活动的时间区间，502表示活动在每天的开始和结束时间，一般作用于限时特价
     */
    @XmlAttribute(name="RestrictionDateCode")
    @XStreamAlias("RestrictionDateCode")
    @XStreamAsAttribute
    private String restrictionDateCode;


    /**
     * RestrictionTyp：booking可预订
     */
    @XmlAttribute(name="RestrictionType")
    @XStreamAlias("RestrictionType")
    @XStreamAsAttribute
    private String restrictionType;



    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getRestrictionDateCode() {
        return restrictionDateCode;
    }

    public void setRestrictionDateCode(String restrictionDateCode) {
        this.restrictionDateCode = restrictionDateCode;
    }

    public String getRestrictionType() {
        return restrictionType;
    }

    public void setRestrictionType(String restrictionType) {
        this.restrictionType = restrictionType;
    }
}
