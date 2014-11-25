/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto.hotel;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import java.util.List;

/**
 * User: yfdai
 * Date: 14-10-31
 * Time: 上午11:11
 * 额外费用信息
 */
@XStreamAlias("Fee")
public class HotelRateFee {

    /**
     * code属性：费用类型代码，参考CodeList(FTT)
     */
    @XStreamAlias("Code")
    @XStreamAsAttribute
    private int code;

    @XStreamAlias("Amount")
    @XStreamAsAttribute
    private float amount;

    /**
     *属性：币种
     */
    @XStreamAlias("CurrencyCode")
    @XStreamAsAttribute
    private String currencyCode;

    /**
     * ChargeUnit属性：扣款单位:如每房每晚/每人
     */
    @XStreamAlias("ChargeUnit")
    @XStreamAsAttribute
    private int chargeUnit;

    @XStreamAlias("Description")
    private Description description;

    @XStreamAlias("TPA_Extensions")
    private List<HotelRateOtherCurrency> hotelRateOtherCurrencies;

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public int getChargeUnit() {
        return chargeUnit;
    }

    public void setChargeUnit(int chargeUnit) {
        this.chargeUnit = chargeUnit;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<HotelRateOtherCurrency> getHotelRateOtherCurrencies() {
        return hotelRateOtherCurrencies;
    }

    public void setHotelRateOtherCurrencies(List<HotelRateOtherCurrency> hotelRateOtherCurrencies) {
        this.hotelRateOtherCurrencies = hotelRateOtherCurrencies;
    }
}
