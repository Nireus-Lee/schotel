/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.hotel.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Date;

/**
 * User: william
 * Date: 14-10-30
 * Time: 下午11:21
 */
@Embeddable
public class DateRange {
    //<ns:DateRange Start="2013-06-28" End="2013-06-29"/>
    //<DateRestriction End="2020-12-31 23:59:59" Start="2014-5-12 16:36:31" RestrictionDateCode="501" RestrictionType="booking"/>
    //<DateRestriction End="24:00" Start="00:00" RestrictionDateCode="502" RestrictionType="booking"/>
    //<!-- 活动时间限制规则-->
    //<!-- Start属性：开始时间；End属性：结束时间；RestrictionTyp：booking可预订；RestrictionDateCode ：501表示促销活动的时间区间，502表示活动在每天的开始和结束时间，一般作用于限时特价-->


    /**
     *  Start属性：开始时间
     */
    private Date startTime;

    /**
     * End属性：结束时间
     */
    private Date endTime;

    /**
     * RestrictionDateCode ：501表示促销活动的时间区间，502表示活动在每天的开始和结束时间，一般作用于限时特价
     */
    @Column(length=10)
    private String restrictionDateCode;


    /**
     * RestrictionTyp：booking可预订
     */
    @Column(length=50)
    private String restrictionType;





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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
