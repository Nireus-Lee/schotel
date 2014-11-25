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
 * Date: 14-11-2
 * Time: 上午9:49
 */
@Entity
@Table(name="CacheTime_ByHotel")
public class CacheHotel {


    @Id
    @GeneratedValue
    private int id;

    @Column(name="hotel_code", length=15)
    private String hotelCode;

    @Temporal(TemporalType.TIMESTAMP)
    private Date cacheTime= DateUtil.getCurDateTime();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(insertable = true, updatable = false)
    private Date createTime=DateUtil.getCurDateTime();

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
}
