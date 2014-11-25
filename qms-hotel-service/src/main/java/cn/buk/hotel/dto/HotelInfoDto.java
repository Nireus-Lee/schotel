/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.hotel.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * User: yfdai
 * Date: 14-11-11
 * Time: 下午4:04
 */
public class HotelInfoDto {

    private String hotelCode;
    private String hotelName;
    private String hotelAddress;

    /**
     * 酒店星级
     */
    private int hotelStarRate;
    /**
     * 用户评星
     */
    private float hotelUserRate;

    private List<HotelRoomDto> rooms = new ArrayList<HotelRoomDto>();

    public void addRatePlan(HotelRatePlanDto rp) {
        boolean roomExisted = false;
        for(HotelRoomDto room: rooms) {
            if (room.getRoomTypeCode().equalsIgnoreCase(rp.getRoomTypeCode())) {
                roomExisted = true;
                room.getRatePlans().add(rp);
            }
        }

        if (roomExisted == false) {
            HotelRoomDto room = new HotelRoomDto();
            room.setRoomTypeCode(rp.getRoomTypeCode());
            room.setRoomTypeName(rp.getRoomTypeName());
            rooms.add(room);

            room.getRatePlans().add(rp);
        }
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    public int getHotelStarRate() {
        return hotelStarRate;
    }

    public void setHotelStarRate(int hotelStarRate) {
        this.hotelStarRate = hotelStarRate;
    }

    public float getHotelUserRate() {
        return hotelUserRate;
    }

    public void setHotelUserRate(float hotelUserRate) {
        this.hotelUserRate = hotelUserRate;
    }

    public String getHotelCode() {
        return hotelCode;
    }

    public void setHotelCode(String hotelCode) {
        this.hotelCode = hotelCode;
    }

    public List<HotelRoomDto> getRooms() {
        return rooms;
    }

    public void setRooms(List<HotelRoomDto> rooms) {
        this.rooms = rooms;
    }
}
