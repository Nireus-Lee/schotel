/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * User: yfdai
 * Date: 14-10-17
 * Time: 下午2:21
 */
@XStreamAlias("OriginalPriceDetail")
public class OriginalPriceDetail {

    @XStreamAlias("Currency")
    private String currency;

    /**
     * 携程卖价
     */
    @XStreamAlias("SalePrice")
    private int salePrice;

    /**
     * 航空公司卖价
     */
    @XStreamAlias("PublishPrice")
    private int publishPrice;

    /**
     * 航空公司结算价
     */
    @XStreamAlias("AccountPrice")
    private int accountPrice;

    @XStreamAlias("Tax")
    private int tax;

    @XStreamAlias("FuelSurCharge")
    private int fuelSurcharge;

    /**
     * 机票业务部和度假结算的价格，度假DIY
     */
    @XStreamAlias("SettlementPrice")
    private int settlementPrice;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(int salePrice) {
        this.salePrice = salePrice;
    }

    public int getPublishPrice() {
        return publishPrice;
    }

    public void setPublishPrice(int publishPrice) {
        this.publishPrice = publishPrice;
    }

    public int getAccountPrice() {
        return accountPrice;
    }

    public void setAccountPrice(int accountPrice) {
        this.accountPrice = accountPrice;
    }

    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

    public int getFuelSurcharge() {
        return fuelSurcharge;
    }

    public void setFuelSurcharge(int fuelSurcharge) {
        this.fuelSurcharge = fuelSurcharge;
    }

    public int getSettlementPrice() {
        return settlementPrice;
    }

    public void setSettlementPrice(int settlementPrice) {
        this.settlementPrice = settlementPrice;
    }
}
