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
 * Time: 上午11:07
 * 按客人数量的基础价格
 */
@XStreamAlias("BaseByGuestAmt")
public class HotelRateBaseByGuestAmount {

    @XStreamAlias("AmountBeforeTax")
    @XStreamAsAttribute
    private float amountBeforeTax;

    @XStreamAlias("CurrencyCode")
    @XStreamAsAttribute
    private String currencyCode;

    @XStreamAlias("NumberOfGuests")
    @XStreamAsAttribute
    private int numberOfGuests;

    @XStreamAlias("ListPrice")
    @XStreamAsAttribute
    private float listPrice;

//    TPA_Extensions><OtherCurrency><AmountPercentType Amount="3082.00" CurrencyCode="CNY"/></OtherCurrency></TPA_Extensions>
    @XStreamAlias("TPA_Extensions")
    private List<HotelRateOtherCurrency> hotelRateOtherCurrencies;


    public float getAmountBeforeTax() {
        return amountBeforeTax;
    }

    public void setAmountBeforeTax(float amountBeforeTax) {
        this.amountBeforeTax = amountBeforeTax;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public float getListPrice() {
        return listPrice;
    }

    public void setListPrice(float listPrice) {
        this.listPrice = listPrice;
    }

    public List<HotelRateOtherCurrency> getHotelRateOtherCurrencies() {
        return hotelRateOtherCurrencies;
    }

    public void setHotelRateOtherCurrencies(List<HotelRateOtherCurrency> hotelRateOtherCurrencies) {
        this.hotelRateOtherCurrencies = hotelRateOtherCurrencies;
    }
}
