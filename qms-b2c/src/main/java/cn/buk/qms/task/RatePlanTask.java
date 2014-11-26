/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.qms.task;

import cn.buk.api.service.CtripOpenApiService;
import org.apache.log4j.Logger;

/**
 * User: yfdai
 * Date: 14-11-6
 * Time: 下午3:29
 */
public class RatePlanTask {

    private CtripOpenApiService ctripOpenApiService;
    private Logger logger = Logger.getLogger(RatePlanTask.class);

    public void refreshRatePlan() {
        logger.info("[refreshRatePlan] begin");
        ctripOpenApiService.refreshAllRatePlan();
        logger.info("[refreshRatePlan] end.");
    }

    public void refreshHotelCacheChange() {
        logger.info("[refreshHotelCacheChange] begin");
        ctripOpenApiService.refreshHotelCacheChange();
        logger.info("[refreshHotelCacheChange] end.");
    }

    public void refreshHotelInfo() {
        logger.info("[refreshHotelInfo] begin");
        ctripOpenApiService.refreshAllHotelBasicInfo();
        ctripOpenApiService.refreshAllHotelDetail();
        logger.info("[refreshHotelInfo] end.");
    }

    public CtripOpenApiService getCtripOpenApiService() {
        return ctripOpenApiService;
    }

    public void setCtripOpenApiService(CtripOpenApiService ctripOpenApiService) {
        this.ctripOpenApiService = ctripOpenApiService;
    }
}
