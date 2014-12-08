/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.action;

import cn.buk.api.service.CtripOpenApiService;
import cn.buk.qms.util.ResponseUtil;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: yfdai
 * Date: 14-10-24
 * Time: 下午3:02
 */
public class CtripHotelAction extends ActionSupport {

    private String hotelCode;
    private Date startDate;
    private Date endDate;

    private CtripOpenApiService ctripOpenApiService;

    private void writeXmlResponse(String rs) {
        HttpServletResponse response = ServletActionContext.getResponse();
        ResponseUtil.writeXmlResponse(rs, response);
    }

    private void writeResponse(String rs) {
        HttpServletResponse response = ServletActionContext.getResponse();
        ResponseUtil.writeResponse(rs, response);
    }

    public void searchHotel() {
        //Date departureDate = DateUtil.addDays(DateUtil.getCurDate(), 30);
        //Date returnDate = DateUtil.addDays(departureDate, 180);
        int cityId = 2;
        String xml = ctripOpenApiService.searchHotel(cityId);
        writeXmlResponse(xml);
    }

    public void importHotelSearchRS() {
        String xml_file = "d:\\searchHotelResponse.xml";
        String response = ctripOpenApiService.importHotelSearchRS(xml_file);
        System.out.println(response);
        writeResponse(response);
    }


    public void importHotelDetailResponse() {
        String xml_file = "d:\\searchHotelDetailResponse.xml";
        String response = ctripOpenApiService.importHotelDetailResponse(xml_file);
        System.out.println(response);
        writeResponse(response);
    }


    public void importHotelCacheChangeResponse() {
        String xml_file = "d:\\searchHotelCacheChangeResponse.xml";
        String response = ctripOpenApiService.importHotelCacheChangeResponse(xml_file);
        System.out.println(response);
        writeResponse(response);
    }

    public void searchHotelCacheChange() {
        ctripOpenApiService.refreshHotelCacheChange();
        ctripOpenApiService.retrieveHotelCacheChangeDetail();
        writeResponse("OK");
    }

    public void searchHotelDetail() {
        String hotelCode = "";
        List<String> hotelCodes = new ArrayList<String>();
        String xml = ctripOpenApiService.searchHotelDetail(hotelCodes);
        writeXmlResponse(xml);
    }

    public void searchHotelRatePlan()  {
        String xml = ctripOpenApiService.searchHotelRatePlan(hotelCode, startDate, endDate);
        writeXmlResponse(xml);
    }

    public void importHotelRatePlanResponse() {
        String xml_file = "d:\\searchHotelRatePlanResponse.xml";
        String response = ctripOpenApiService.importHotelRatePlanResponse(xml_file);
        System.out.println(response);
        writeResponse(response);
    }

    public void refreshAllHotelBasicInfo() {
        String xml = ctripOpenApiService.refreshAllHotelBasicInfo();
        writeXmlResponse(xml);
    }

    public void refreshAllHotelDetail() {
        String xml = ctripOpenApiService.refreshAllHotelDetail();
        writeXmlResponse(xml);
    }

    public void refreshHotelCacheChangeDetail() {
        ctripOpenApiService.retrieveHotelCacheChangeDetail();
        writeXmlResponse("OK");
    }

    public void refreshAllRatePlan() {
        String xml = ctripOpenApiService.refreshAllRatePlan();
        writeResponse(xml);
    }


    public void setCtripOpenApiService(CtripOpenApiService ctripOpenApiService) {
        this.ctripOpenApiService = ctripOpenApiService;
    }

    public String getHotelCode() {
        return hotelCode;
    }

    public void setHotelCode(String hotelCode) {
        this.hotelCode = hotelCode;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
