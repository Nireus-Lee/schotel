/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.hotel.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * User: yfdai
 * Date: 14-11-13
 * Time: 下午2:52
 */
public class HotelRoomDto {

    /**
     * 房型代码
     */
    private String roomTypeCode;
    /**
     * 房型名称
     */
    private String roomTypeName;



    private List<HotelRatePlanDto> ratePlans = new ArrayList<HotelRatePlanDto>();

    public String getRoomTypeCode() {
        return roomTypeCode;
    }

    public void setRoomTypeCode(String roomTypeCode) {
        this.roomTypeCode = roomTypeCode;
    }

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    public List<HotelRatePlanDto> getRatePlans() {
        return ratePlans;
    }

    public void setRatePlans(List<HotelRatePlanDto> ratePlans) {
        this.ratePlans = ratePlans;
    }
}
