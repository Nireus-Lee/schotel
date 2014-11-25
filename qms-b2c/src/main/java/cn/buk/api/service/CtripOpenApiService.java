/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.service;

import cn.buk.api.dto.DomesticFlightData;
import cn.buk.api.dto.Response;

import java.util.Date;
import java.util.List;

/**
 * User: yfdai
 * Date: 14-9-18
 * Time: 下午2:38
 */
public interface CtripOpenApiService {

    public String importCityInfo(String filename);
    public String importDistrictInfo(String filename);
    public String importZoneInfo(String filename);


    //hotel
    public String searchHotel(int cityId);
    public String importHotelSearchRS(String filename);

    public String searchHotelDetail(List<String> hotelCodes);
    public String importHotelDetailResponse(String filename);

    public String searchHotelRatePlan(List<String> hotelCodes, int periodId);

    /**
     * 根据returnXml的值，来决定是否直接返回API的结果XML给客户端
     * @param hotelCodes
     * @param returnXml
     * @return
     */
    public String searchHotelRatePlan(List<String> hotelCodes, int periodId, boolean returnXml);
    public String importHotelRatePlanResponse(String filename);

    public String searchHotelCacheChange();
    public String importHotelCacheChangeResponse(String filename);

    /**
     * 刷新所有酒店的基础信息
     * @return
     */
    public String refreshAllHotelBasicInfo();
    public String refreshAllHotelDetail();
    public String refreshAllRatePlan();
    public String refreshRatePlans(List<String> hotelCodes);
    public String refreshRatePlansByCityId(int cityId);

    /**
     *价格缓存变化查询
     * @return
     */
    public String refreshHotelCacheChange();

    public String calculateSignature(String timestamp, String requestType);
}
