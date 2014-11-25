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
 * Time: 下午4:25
 */
public class HotelRatePlanDto {

    /**
     * 价格计划代码
     */
    private int ratePlanCode;

    /**
     * 价格计划名称
     */
    private String ratePlanName;

    /**
     * 房价
     */
    private int price;

    /**
     * 房型代码
     */
    private String roomTypeCode;
    /**
     * 房型名称
     */
    private String roomTypeName;

    private int breakfast;

    private int bedTypeCode;

    private String bedTypeName;

    /**
     * 固定宽带
     * 0-无
     * 1-收费
     * 2-免费
     */
    private int fixedBroadBand;

    /**
     * 无线宽带
     * ０－无
     * １－收费
     * ２－免费
     */
    private int wifi;

//    Room Amenity Type	RMA
//    3	免费有线宽带
//    4	收费有线宽带
//    5	免费无线宽带
//    6	收费无线宽带
//    7	可加床
//    8	加中式早餐
//    9	加西式早餐
//    10	加自助早餐
//    11	免费加床
//    12	收费加床

    private List<HotelRoomAmenityDto> amenities = new ArrayList<HotelRoomAmenityDto>();



    public int getRatePlanCode() {
        return ratePlanCode;
    }

    public void setRatePlanCode(int ratePlanCode) {
        this.ratePlanCode = ratePlanCode;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getRatePlanName() {
        return ratePlanName;
    }

    public void setRatePlanName(String ratePlanName) {
        this.ratePlanName = ratePlanName;
    }

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

    public int getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(int breakfast) {
        this.breakfast = breakfast;
    }

    public int getBedTypeCode() {
        return bedTypeCode;
    }

    public void setBedTypeCode(int bedTypeCode) {
        this.bedTypeCode = bedTypeCode;
        convertToBedTypeName();
    }

    private void convertToBedTypeName() {
        switch (this.bedTypeCode)  {
            case 1: this.bedTypeName = "双床";break;
            case 2: this.bedTypeName = "Futon"; break;
            case 3: this.bedTypeName = "大床";  break;
            case 4: this.bedTypeName = "Murphy bed"; break;
            case 5: this.bedTypeName = "Queen";    break;
            case 6: this.bedTypeName = "Sofa bed";   break;
            case 7: this.bedTypeName = "Tatami mats";   break;
            case 8: this.bedTypeName = "2张单人床";break;
            case 9: this.bedTypeName = "单人床";    break;
            case 10: this.bedTypeName = "Full";     break;
            case 11: this.bedTypeName = "Run of the house";  break;
            case 12: this.bedTypeName = "Dorm bed";   break;
            case 501: this.bedTypeName = "大床或双床";break;
            case 502: this.bedTypeName = "大床或单床";   break;
            case 503: this.bedTypeName = "大床或单床";   break;
        }


    }

    public String getBedTypeName() {
        return bedTypeName;
    }

    public void setBedTypeName(String bedTypeName) {
        this.bedTypeName = bedTypeName;
    }

    public List<HotelRoomAmenityDto> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<HotelRoomAmenityDto> amenities) {
        this.amenities = amenities;
    }

    public int getFixedBroadBand() {
        if(fixedBroadBand == 0) {
            //    3	免费有线宽带
//    4	收费有线宽带
            for(HotelRoomAmenityDto dto: amenities) {
                if (dto.getAmenityCode() == 3) {
                    this.fixedBroadBand = 2;
                    break;
                } else if (dto.getAmenityCode() == 4) {
                    this.fixedBroadBand = 1;
                    break;
                }
            }
        }
        return fixedBroadBand;
    }

    public void setFixedBroadBand(int fixedBroadBand) {
        this.fixedBroadBand = fixedBroadBand;
    }

    public int getWifi() {
        if (wifi == 0) {
           for(HotelRoomAmenityDto dto: amenities) {
//    5	免费无线宽带
//    6	收费无线宽带
               if (dto.getAmenityCode() == 5) {
                   this.wifi = 2;
                   break;
               } else if (dto.getAmenityCode() == 6) {
                   this.wifi = 1;
                   break;
               }
           }
        }
        return wifi;
    }

    public void setWifi(int wifi) {
        this.wifi = wifi;
    }
}
