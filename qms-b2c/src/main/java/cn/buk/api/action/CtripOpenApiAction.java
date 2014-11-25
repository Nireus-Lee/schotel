/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.action;

import cn.buk.api.service.CtripOpenApiService;
import cn.buk.qms.AppConfig;
import cn.buk.qms.util.PropertiesUtil;
import cn.buk.qms.util.ResponseUtil;
import com.ctrip.openapi.java.base.HttpAccessAdapter;
import com.ctrip.openapi.java.utils.ConfigData;
import com.ctrip.openapi.java.utils.SignatureUtils;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.config.entities.Parameterizable;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Map;

@SuppressWarnings("serial")
public class CtripOpenApiAction extends ActionSupport implements Parameterizable {


    private String depCity;
    private String depCityName;
    private String arrCity;
    private String arrCityName;
    private Date depDate;
    private Date returnDate;



    private String openApiTempOrderId;

    private String payUrl;
    private String showUrl;
    private String returnUrl;

    private String orderDesc;
    private String paymentDesc;


    private CtripOpenApiService ctripOpenApiService;

	Map<String, String> params;

    /**
     * 支付返回结果用到的参数
     */
    private String errorMsg;
    private String errorCode;

    /**
     * 客户端流水号, 支付处理中的唯一号
     */
    private String transactionID;
    /**
     *支付的总金额
     */
    private String amount;
    /**
     *币种,人民币：CNY
     */
    private String currencyCode;
    /**
     *订单号
     */
    private String orderID;
    /**
     *支付状态, 0：信息收集失败 1：信息收集成功
     */
    private String status;

    /**
     * 开发测试时返回xml，看看生成的xml是否正确
     */
    private String xml;

    public static Logger log = Logger.getLogger(CtripOpenApiAction.class);

    @Override
    public void addParam(String s, String s2) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setParams(Map<String, String> params) {
		this.params = params;
	}

    @Override
    public Map<String, String> getParams() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    private void writeResponse(String rs) {
        HttpServletResponse response = ServletActionContext.getResponse();
        ResponseUtil.writeResponse(rs, response);
    }

    private void writeXmlResponse(String rs) {
        HttpServletResponse response = ServletActionContext.getResponse();
        ResponseUtil.writeXmlResponse(rs, response);
    }


    public void importCityInfo() throws FileNotFoundException {
        String xml_file = "d:\\city.xml";
        String response = ctripOpenApiService.importCityInfo(xml_file);
        System.out.println(response);
        writeResponse(response);
    }


    public void importDistrictInfo() throws FileNotFoundException {
        String xml_file = "d:\\region.xml";
        String response = ctripOpenApiService.importDistrictInfo(xml_file);
        System.out.println(response);
        writeResponse(response);
    }
    public void importZoneInfo() throws FileNotFoundException {
        String xml_file = "d:\\zone.xml";
        String response = ctripOpenApiService.importZoneInfo(xml_file);
        System.out.println(response);
        writeResponse(response);
    }


    public void setCtripOpenApiService(CtripOpenApiService ctripOpenApiService) {
        this.ctripOpenApiService = ctripOpenApiService;
    }

    public String getOpenApiTempOrderId() {
        return openApiTempOrderId;
    }

    public void setOpenApiTempOrderId(String openApiTempOrderId) {
        this.openApiTempOrderId = openApiTempOrderId;
    }

    public String getPayUrl() {
        return payUrl;
    }

    public void setPayUrl(String payUrl) {
        this.payUrl = payUrl;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }

    public String getPaymentDesc() {
        return paymentDesc;
    }

    public void setPaymentDesc(String paymentDesc) {
        this.paymentDesc = paymentDesc;
    }


    public String getErrorMsg() {
        return errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getShowUrl() {
        return showUrl;
    }

    public void setShowUrl(String showUrl) {
        this.showUrl = showUrl;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public String getDepCity() {
        return depCity;
    }

    public void setDepCity(String depCity) {
        this.depCity = depCity;
    }

    public String getDepCityName() {
        return depCityName;
    }

    public void setDepCityName(String depCityName) {
        this.depCityName = depCityName;
    }

    public String getArrCity() {
        return arrCity;
    }

    public void setArrCity(String arrCity) {
        this.arrCity = arrCity;
    }

    public String getArrCityName() {
        return arrCityName;
    }

    public void setArrCityName(String arrCityName) {
        this.arrCityName = arrCityName;
    }

    public Date getDepDate() {
        return depDate;
    }

    public void setDepDate(Date depDate) {
        this.depDate = depDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
