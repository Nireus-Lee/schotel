/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.hotel.entity;

import cn.buk.util.DateUtil;

import javax.persistence.*;
import java.util.Date;

/**
 * User: william
 * Date: 14-11-3
 * Time: 下午2:27
 */
@Entity
@Table(name="CacheTime_RatePlan")
public class CacheRatePlan {

    @Id
    @GeneratedValue
    private int id;

    @Column(name="hotel_code", length=15)
    private String hotelCode;

    /**
     * 1-表示0-28天
     * 2-表示29-57天
     * 3-表示58-86天
     */
    @Column(name="period_id")
    private int periodId;

    @Temporal(TemporalType.DATE)
    private Date cacheTime= DateUtil.getCurDate();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(insertable = true, updatable = false)
    private Date createTime=DateUtil.getCurDateTime();

    @Temporal(TemporalType.TIMESTAMP)
    private Date beginTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHotelCode() {
        return hotelCode;
    }

    public void setHotelCode(String hotelCode) {
        this.hotelCode = hotelCode;
    }

    public Date getCacheTime() {
        return cacheTime;
    }

    public void setCacheTime(Date cacheTime) {
        this.cacheTime = cacheTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getPeriodId() {
        return periodId;
    }

    public void setPeriodId(int periodId) {
        this.periodId = periodId;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}