/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.hotel.dao;

import cn.buk.hotel.entity.*;
import cn.buk.hotel.sc.HotelSearchCriteria;

import java.util.Date;
import java.util.List;


public interface HotelDao {

    public void flush();

	public int createHotelInfo(HotelInfo hotelInfo);
    public void clearHotelInfoFromCache(HotelInfo hotelInfo);

    public HotelInfo getHotelInfoByHotelCode(String hotelCode);

    public int updateHotelInfo(HotelInfo hotelInfo);

    public List<HotelInfo> getAllHotelInfo();
    public List<String> getAllHotelCodes();
    public List<String> getAllHotelCodesByCityId(int cityId);


    public int createHotelRatePlan(HotelRatePlan hotelRatePlan);
    public void clearHotelRatePlanFromCache(HotelRatePlan hotelRatePlan);


    public CacheCity getCacheCity(int cityId);
    public int createCacheCity(CacheCity cacheCity);
    public int updateCacheCity(CacheCity cacheCity);

    public CacheHotel getCacheHotel(String hotelCode);
    public int createCacheHotel(CacheHotel cacheHotel);
    public int updateCacheHotel(CacheHotel cacheHotel);
    public void clearCacheHotelFromCache(CacheHotel cacheHotel);

    public CacheRatePlan getCacheRatePlan(String hotelCode, int periodId);

    public void clearCacheRatePlanFromCache(CacheRatePlan cacheRatePlan);

    public int createCacheRatePlan(CacheRatePlan cacheRatePlan);

    public int updateCacheRatePlan(CacheRatePlan cacheRatePlan);

    public List<HotelInfo> searchAvailableHotel(HotelSearchCriteria sc);

    /**
     * 查找指定酒店可以用的运价
     * @param hotelId
     * @param checkInDate
     * @param checkOutDate
     * @return
     */
    public List<HotelRatePlan> searchAvailableHotelRatePlan(int hotelId, Date checkInDate, Date checkOutDate);

    public HotelGuestRoom getHotelRoomInfo(int hotelId, String roomTypeCode);

    public void clearAllCache();

    public int createDistrict(District district);

    public int createZone(Zone zone);

    public List<District> getDistrictByCityId(int cityId);

    public List<Zone> getZoneByCityId(int cityId);

    public int createCacheHotelCacheChange(CacheHotelCacheChange cacheHotelCacheChange);
}
