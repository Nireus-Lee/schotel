/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.hotel.dto;

/**
 * User: yfdai
 * Date: 14-11-14
 * Time: 下午7:41
 * 房间设施
 */
public class HotelRoomAmenityDto {

    /**
     * 设施代码
     */
    private int amenityCode;

    /**
     * 设施名称
     */
    private String amenityName;

    public int getAmenityCode() {
        return amenityCode;
    }

    public void setAmenityCode(int amenityCode) {
        this.amenityCode = amenityCode;
    }

    public String getAmenityName() {
        return amenityName;
    }

    public void setAmenityName(String amenityName) {
        this.amenityName = amenityName;
    }
}
