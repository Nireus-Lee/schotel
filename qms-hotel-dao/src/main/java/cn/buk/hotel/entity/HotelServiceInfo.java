/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.hotel.entity;

import javax.persistence.*;

/**
 * User: yfdai
 * Date: 14-10-30
 * Time: 下午4:19
 */
@Entity
@Table(name="hotel_service")
public class HotelServiceInfo {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name="hotel_id")
    private HotelInfo hotelInfo;

    /**
     *ID属性：设施设备类别，例如：客房设施和服务、宾馆餐饮设施等。
     */
    @Column(length=10)
    private String serviceId;

    /**
     * Code属性：设施设备代码, 参考CodeList (HAC)
     */
    private int serviceCode;

    private String serviceDesc;



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

    public int getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(int serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getServiceDesc() {
        return serviceDesc;
    }

    public void setServiceDesc(String serviceDesc) {
        this.serviceDesc = serviceDesc;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }
}
