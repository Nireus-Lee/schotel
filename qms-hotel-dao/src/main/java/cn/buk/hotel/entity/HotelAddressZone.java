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
 * Time: 下午12:54
 */

@Entity
@Table(name="hotel_address_zone")
public class HotelAddressZone {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name="hotel_id")
    private HotelInfo hotelInfo;

        /**
     * 商业区代码
     */
    private Integer zoneCode;

    /**
     * 商业区名称
     */
    private String zoneName;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(insertable=true, updatable = false)
    private Date createTime = DateUtil.getCurDateTime();

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate = DateUtil.getCurDateTime();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HotelInfo getHotelInfo() {
        return hotelInfo;
    }

    public void setHotelInfo(HotelInfo hotelInfo) {
        this.hotelInfo = hotelInfo;
    }

    public int getZoneCode() {
        if (zoneCode == null)
            return 0;
        else
            return zoneCode.intValue();
    }

    public void setZoneCode(int zoneCode) {
        this.zoneCode = zoneCode;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
