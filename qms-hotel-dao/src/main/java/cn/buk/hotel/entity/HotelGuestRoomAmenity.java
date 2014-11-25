/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.hotel.entity;



import javax.persistence.*;

/**
 * User: william
 * Date: 14-10-30
 * Time: 下午5:55
 */
@Entity
@Table(name="hotel_guestroom_amenity")
public class HotelGuestRoomAmenity {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name="hotel_guestroom_id")
    private HotelGuestRoom hotelGuestRoom;


    private int code;

    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HotelGuestRoom getHotelGuestRoom() {
        return hotelGuestRoom;
    }

    public void setHotelGuestRoom(HotelGuestRoom hotelGuestRoom) {
        this.hotelGuestRoom = hotelGuestRoom;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
