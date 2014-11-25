/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.hotel.entity;

import javax.persistence.*;

/**
 * User: yfdai
 * Date: 14-10-29
 * Time: 下午4:05
 */
@Entity
@Table(name="hotel_award")
public class HotelAward {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name="hotel_id")
    private HotelInfo hotelInfo;

    //Award Rating="5" Provider="HotelStarRate"/>
    private float rating;

    @Column(length=20)
    private String provider;

    public HotelInfo getHotelInfo() {
        return hotelInfo;
    }

    public void setHotelInfo(HotelInfo hotelInfo) {
        this.hotelInfo = hotelInfo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }
}
