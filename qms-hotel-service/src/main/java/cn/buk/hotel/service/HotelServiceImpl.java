/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.hotel.service;

import cn.buk.common.Page;
import cn.buk.hotel.dao.HotelDao;
import cn.buk.hotel.dto.*;
import cn.buk.hotel.entity.*;
import cn.buk.hotel.sc.HotelSearchCriteria;
import cn.buk.hotel.dao.CityDao;
import cn.buk.util.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author william dai(yunfeng2005@gmail.com)
 * @website http://www.buk.cn/
 * Date: 14-10-29
 * Time: 下午1:41
 */
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class HotelServiceImpl implements HotelService {

    private static Logger logger = Logger.getLogger(HotelServiceImpl.class);

    private HotelDao hotelDao;
    private CityDao cityDao;

    @Override
    public int createHotelInfo(HotelInfo hotelInfo) {
        return hotelDao.createHotelInfo(hotelInfo);
    }

    @Override
    public HotelSearchResult searchHotel(String cityCode, Date checkInDate, Date checkOutDate,int pageNo, String star, int districtId, int zoneId) {
        HotelSearchResult searchResult = new HotelSearchResult();
        Page page = new Page();
        page.setPageNo(pageNo);
        page.setPageSize(20);

        City city = cityDao.getCityByCode(cityCode);
        Date baseTime = DateUtil.getCurDateTime();
        int cityId = city.getOpenApiId();

        List<HotelInfoDto> dtos = new ArrayList<HotelInfoDto>();
        HotelSearchCriteria sc = new HotelSearchCriteria();
        sc.setCityId(cityId );
        sc.setPage(page);
        sc.setStar(star);
        sc.setDistrictId(districtId);
        sc.setZoneId(zoneId);
        List<HotelInfo> hotelInfos = hotelDao.searchAvailableHotel(sc);
        int span0 = DateUtil.getPastTime(baseTime);
        logger.info("elapsed time span0: " + span0 + " ms.");

        for(HotelInfo hotelInfo: hotelInfos) {
            //
            HotelInfoDto dto = new HotelInfoDto();
            dtos.add(dto);
            dto.setHotelCode(hotelInfo.getHotelCode());
            dto.setHotelName(hotelInfo.getHotelName());
            dto.setHotelAddress(hotelInfo.getAddress());
            dto.setHotelStarRate(hotelInfo.getHotelStarRate());
            dto.setHotelUserRate(hotelInfo.getHotelUserRate());

            //查找对应的价格计划
            Date baseTime1 = DateUtil.getCurDateTime();
            List<HotelRatePlan> ratePlans = hotelDao.searchAvailableHotelRatePlan(hotelInfo.getId(), checkInDate, checkOutDate);
            int span3 = DateUtil.getPastTime(baseTime1);
            logger.info("elapsed time span3: " + span3 + " ms.");
            for(HotelRatePlan rp: ratePlans) {
                if (rp.getHotelRatePlanRates() == null || rp.getHotelRatePlanRates().size() == 0) continue;

                HotelRatePlanDto rpDto = new HotelRatePlanDto();

                rpDto.setRatePlanCode(rp.getRatePlanCode());
                rpDto.setRatePlanName(rp.getName());

                if (rp.getHotelRatePlanSellableProducts() != null && rp.getHotelRatePlanSellableProducts().size() >0) {
                    if (rp.getHotelRatePlanSellableProducts().size()>1)
                        logger.warn("rateplan[" + rp.getId() + "] has too many sellable products.");

                    rpDto.setRoomTypeCode(rp.getHotelRatePlanSellableProducts().get(0).getInvCode());
                    HotelGuestRoom room = hotelDao.getHotelRoomInfo(rp.getHotelInfo().getId(), rpDto.getRoomTypeCode());
                    if (room == null)
                        logger.warn("Do not find room data: " + rp.getHotelInfo().getId() + "," + rpDto.getRoomTypeCode());
                    else {
                        rpDto.setRoomTypeName(room.getRoomTypeName());
                        if (room.getBedTypeCode() != null)
                            rpDto.setBedTypeCode(Integer.parseInt(room.getBedTypeCode()));

                        for(HotelGuestRoomAmenity amenity: room.getHotelGuestRoomAmenities()) {
                            HotelRoomAmenityDto amenityDto = new HotelRoomAmenityDto();
                            amenityDto.setAmenityCode(amenity.getCode());
                            amenityDto.setAmenityName(amenity.getDescription());

                            rpDto.getAmenities().add(amenityDto);
                        }
                    }

                } else {
                    logger.error("rateplan[" + rp.getId() + "] has not sellable products." );
                }

                //rpDto.setRoomTypeName(rp.getName());
                int price = 0;
                for(HotelRatePlanRate rate: rp.getHotelRatePlanRates()) {
                    price += rate.getHotelRatePlanRateBaseByGuestAmounts().get(0).getAmountBeforeTax();
                }
                rpDto.setPrice(price);
                rpDto.setBreakfast(rp.getHotelRatePlanRates().get(0).getBreakfast());

                dto.addRatePlan(rpDto);
            }
            int span4 = DateUtil.getPastTime(baseTime1);
            logger.info("elapsed time span4: " + span4 + " ms.");
        }
        int span = DateUtil.getPastTime(baseTime);
        logger.info("total elapsed time: " + span + " ms.");

        searchResult.setHotels(dtos);
        searchResult.setPageNo(page.getPageNo());
        searchResult.setPageTotal(page.getPageTotal());
        searchResult.setRowCount(page.getRowCount());

        return searchResult;
    }

    @Override
    public List<LocationDto> getLocations(String cityCode, String locationType) {
        City city = cityDao.getCityByCode(cityCode);
        int cityId = city.getOpenApiId();

        List<LocationDto> dtos = new ArrayList<LocationDto>();

        if (locationType.equalsIgnoreCase("district")) {
            List<District> districts = hotelDao.getDistrictByCityId(cityId);
            for(District district: districts) {
                LocationDto dto = new LocationDto();
                dtos.add(dto);

                dto.setId(district.getDistrictId());
                dto.setName(district.getName());
            }
        } else if (locationType.equalsIgnoreCase("zone")) {
            List<Zone> zones = hotelDao.getZoneByCityId(cityId);
            for(Zone zone: zones) {
                LocationDto dto = new LocationDto();
                dtos.add(dto);

                dto.setId(zone.getZoneId());
                dto.setName(zone.getName());
            }
        }

        return dtos;

    }

    public HotelDao getHotelDao() {
        return hotelDao;
    }

    public void setHotelDao(HotelDao hotelDao) {
        this.hotelDao = hotelDao;
    }

    public CityDao getCityDao() {
        return cityDao;
    }

    public void setCityDao(CityDao cityDao) {
        this.cityDao = cityDao;
    }
}
