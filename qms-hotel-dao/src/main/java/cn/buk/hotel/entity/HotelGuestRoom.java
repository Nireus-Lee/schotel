/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.hotel.entity;

import javax.persistence.*;
import java.util.List;

/**
 * User: yfdai
 * Date: 14-10-30
 * Time: 下午4:49
 */
@Entity
@Table(name="hotel_guestroom")
public class HotelGuestRoom {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name="hotel_id")
    private HotelInfo hotelInfo;

    /**
     * RoomTypeCode属性：房型代码，对应Ctrip基础房型
     */
    @Column(length=10)
    private String roomTypeCode;

    /**
     * RoomTypeName属性：房型名称
     */
    private String roomTypeName;


    private int standardOccupancy;

    /**
     * 床的尺寸
     */
    private String size;

    private String floor;

    private String invBlockCode;

    private String bedTypeCode;

    private int nonSmoking;

    private int hasWindow;

    private int quantity;

    private String roomSize;

    /**
     * 房间设施
     */
    @OneToMany(mappedBy = "hotelGuestRoom", cascade = {CascadeType.ALL})
    private List<HotelGuestRoomAmenity> hotelGuestRoomAmenities;

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

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    public String getRoomTypeCode() {
        return roomTypeCode;
    }

    public void setRoomTypeCode(String roomTypeCode) {
        this.roomTypeCode = roomTypeCode;
    }

    public int getStandardOccupancy() {
        return standardOccupancy;
    }

    public void setStandardOccupancy(int standardOccupancy) {
        this.standardOccupancy = standardOccupancy;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getInvBlockCode() {
        return invBlockCode;
    }

    public void setInvBlockCode(String invBlockCode) {
        this.invBlockCode = invBlockCode;
    }

    public String getBedTypeCode() {
        return bedTypeCode;
    }

    public void setBedTypeCode(String bedTypeCode) {
        this.bedTypeCode = bedTypeCode;
    }

    public int getNonSmoking() {
        return nonSmoking;
    }

    public void setNonSmoking(int nonSmoking) {
        this.nonSmoking = nonSmoking;
    }

    public int getHasWindow() {
        return hasWindow;
    }

    public void setHasWindow(int hasWindow) {
        this.hasWindow = hasWindow;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<HotelGuestRoomAmenity> getHotelGuestRoomAmenities() {
        return hotelGuestRoomAmenities;
    }

    public void setHotelGuestRoomAmenities(List<HotelGuestRoomAmenity> hotelGuestRoomAmenities) {
        this.hotelGuestRoomAmenities = hotelGuestRoomAmenities;
    }

    public String getRoomSize() {
        return roomSize;
    }

    public void setRoomSize(String roomSize) {
        this.roomSize = roomSize;
    }
}
