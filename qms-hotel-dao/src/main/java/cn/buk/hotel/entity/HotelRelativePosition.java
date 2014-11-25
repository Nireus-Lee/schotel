/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.hotel.entity;

import javax.persistence.*;

/**
 * User: yfdai
 * Date: 14-10-29
 * Time: 下午4:20
 */
@Entity
@Table(name="hotel_relative_position")
public class HotelRelativePosition {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name="hotel_id")
    private HotelInfo hotelInfo;

    @Column(length=50)
    private String name;

    @Column(name="unit_measure_code")
    private int unitOfMeasureCode;

    private float distance;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUnitOfMeasureCode() {
        return unitOfMeasureCode;
    }

    public void setUnitOfMeasureCode(int unitOfMeasureCode) {
        this.unitOfMeasureCode = unitOfMeasureCode;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }
}
