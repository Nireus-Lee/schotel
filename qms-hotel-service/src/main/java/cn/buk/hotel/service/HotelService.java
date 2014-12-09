/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.hotel.service;

import cn.buk.hotel.dto.HotelSearchResult;
import cn.buk.hotel.dto.LocationDto;
import cn.buk.hotel.entity.HotelInfo;

import java.util.Date;
import java.util.List;

/**
 * User: yfdai
 * Date: 14-10-29
 * Time: 下午1:40
 */
public interface HotelService {

    public int createHotelInfo(HotelInfo hotelInfo);

    public HotelSearchResult searchHotel(String cityCode, Date checkInDate, Date checkOutDate, String hotelName, int pageNo, String star, int districtId, int zoneId);

    public List<LocationDto> getLocations(String cityCode, String locationType);
}
