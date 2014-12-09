/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.qms.action;

import cn.buk.hotel.dto.HotelInfoDto;
import cn.buk.hotel.dto.HotelSearchResult;
import cn.buk.hotel.dto.LocationDto;
import cn.buk.hotel.service.HotelService;
import cn.buk.util.DateUtil;
import com.opensymphony.xwork2.ActionSupport;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: yfdai
 * Date: 14-11-6
 * Time: 下午2:43
 */
public class HotelAction extends ActionSupport {

    private int pageNo;
    private String cityCode;
    private String locationType;

    private String cityName;
    private String checkInDate;
    private String checkOutDate;
    private String hotelName;
    /**
     * 按照酒店星级过滤
     */
    private String star;

    private int districtId;

    private int zoneId;

    private HotelSearchResult hotelSearchResult;

    private List<LocationDto> locations;

    private HotelService hotelService;

    public String search() {
        Date inDate=DateUtil.getCurDate(), outDate = DateUtil.getCurDate();
        try {
            inDate = DateUtil.convertToDate(checkInDate, "yyyy-MM-dd");
            outDate = DateUtil.convertToDate(checkOutDate, "yyyy-MM-dd");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        hotelSearchResult = hotelService.searchHotel(cityCode, inDate, outDate, hotelName, pageNo, star, districtId, zoneId);
        return SUCCESS;
    }

    public String getLocationList() {

        if (locationType == null || locationType.length() == 0) {
            locations = new ArrayList<LocationDto>();
        } else {

            locations = hotelService.getLocations(cityCode, locationType);
        }

        return SUCCESS;
    }

    public HotelService getHotelService() {
        return hotelService;
    }

    public void setHotelService(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public HotelSearchResult getHotelSearchResult() {
        return hotelSearchResult;
    }

    public void setHotelSearchResult(HotelSearchResult hotelSearchResult) {
        this.hotelSearchResult = hotelSearchResult;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public List<LocationDto> getLocations() {
        return locations;
    }

    public void setLocations(List<LocationDto> locations) {
        this.locations = locations;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public int getZoneId() {
        return zoneId;
    }

    public void setZoneId(int zoneId) {
        this.zoneId = zoneId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }
}
