/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * User: yfdai
 * Date: 14-10-17
 * Time: 下午2:18
 */
@XStreamAlias("PriceInfo")
public class PriceInfo {

    @XStreamAlias("IsDefault")
    private boolean isDefault;

    @XStreamAlias("Expression")
    private String expression;

    @XStreamAlias("TravelerCategoryCode")
    private String travelerCategoryCode;

    @XStreamAlias("Agency")
    private Agency agency;

    /**
     * 底价扣率
     */
    @XStreamAlias("CommissionRate")
    private double commissionRate;

    /**
     * 实价扣率
     */
    @XStreamAlias("SalesRate")
    private double salesRate;

    @XStreamAlias("Exchange")
    private int exchange;

    /**
     * 利润类型，0=扣率，1=保险费
     */
    @XStreamAlias("ProfitType")
    private int profitType;

    @XStreamAlias("OriginalPriceDetail")
    private OriginalPriceDetail originalPriceDetail;

    @XStreamAlias("OfficeNo")
    private String officeNo;

    @XStreamAlias("TicketRemark")
    private String ticketRemark;

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getTravelerCategoryCode() {
        return travelerCategoryCode;
    }

    public void setTravelerCategoryCode(String travelerCategoryCode) {
        this.travelerCategoryCode = travelerCategoryCode;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    public double getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(double commissionRate) {
        this.commissionRate = commissionRate;
    }

    public double getSalesRate() {
        return salesRate;
    }

    public void setSalesRate(double salesRate) {
        this.salesRate = salesRate;
    }

    public int getExchange() {
        return exchange;
    }

    public void setExchange(int exchange) {
        this.exchange = exchange;
    }

    public int getProfitType() {
        return profitType;
    }

    public void setProfitType(int profitType) {
        this.profitType = profitType;
    }

    public OriginalPriceDetail getOriginalPriceDetail() {
        return originalPriceDetail;
    }

    public void setOriginalPriceDetail(OriginalPriceDetail originalPriceDetail) {
        this.originalPriceDetail = originalPriceDetail;
    }

    public String getOfficeNo() {
        return officeNo;
    }

    public void setOfficeNo(String officeNo) {
        this.officeNo = officeNo;
    }

    public String getTicketRemark() {
        return ticketRemark;
    }

    public void setTicketRemark(String ticketRemark) {
        this.ticketRemark = ticketRemark;
    }
}
