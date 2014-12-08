/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.qms.task;

import cn.buk.api.service.CtripOpenApiService;
import cn.buk.util.DateUtil;
import org.apache.log4j.Logger;

import java.util.Date;

/**
 * User: yfdai
 * Date: 14-11-6
 * Time: 下午3:29
 */
public class RatePlanTask {

    private CtripOpenApiService ctripOpenApiService;
    private Logger logger = Logger.getLogger(RatePlanTask.class);

    public void refreshRatePlan() {
        logger.debug("[refreshRatePlan] begin");
        Date baseTime = DateUtil.getCurDateTime();

        ctripOpenApiService.refreshAllRatePlan();

        int total = DateUtil.getPastTime(baseTime);
        logger.info("task [refresh rate plan] totally elapsed: " + total + " ms.");
        logger.debug("[refreshRatePlan] end.");
    }

    public void refreshHotelCacheChange() {
        logger.debug("[refreshHotelCacheChange] begin");
        Date baseTime = DateUtil.getCurDateTime();

        ctripOpenApiService.refreshHotelCacheChange();

        int total = DateUtil.getPastTime(baseTime);
        logger.info("task [refresh hotel cache change] totally elapsed: " + total + " ms.");
        logger.debug("[refreshHotelCacheChange] end.");

        //TODO: 触发线程去更新响应的rateplan
        ctripOpenApiService.retrieveHotelCacheChangeDetail();
    }

    public void refreshHotelInfo() {
        logger.debug("[refreshHotelInfo] begin");
        Date baseTime = DateUtil.getCurDateTime();

        ctripOpenApiService.refreshAllHotelBasicInfo();
        ctripOpenApiService.refreshAllHotelDetail();

        int total = DateUtil.getPastTime(baseTime);
        logger.info("task [refresh hotel info] totally elapsed: " + total + " ms.");
        logger.debug("[refreshHotelInfo] end.");
    }

    public CtripOpenApiService getCtripOpenApiService() {
        return ctripOpenApiService;
    }

    public void setCtripOpenApiService(CtripOpenApiService ctripOpenApiService) {
        this.ctripOpenApiService = ctripOpenApiService;
    }

}
