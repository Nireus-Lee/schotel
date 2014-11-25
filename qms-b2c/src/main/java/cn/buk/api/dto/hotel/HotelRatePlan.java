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
 * Time: 上午9:26
 * 价格计划
 */
public class HotelRatePlan {

    /**
     *  RatePlanCode属性：价格计划代码；
     */
    @XStreamAsAttribute
    @XStreamAlias("RatePlanCode")
    private int ratePlanCode;

    /**
     * RatePlanCategory属性：价格计划类型代码，参考CodeList (RTC)
     */
    @XStreamAsAttribute
    @XStreamAlias("RatePlanCategory")
    private int ratePlanCategory;

    /**
     *MarketCode属性：市场代码，参考CodeList (MKC)
     */
    @XStreamAsAttribute
    @XStreamAlias("MarketCode")
    private int marketCode;

    /**
     * 描述该计划的名称
     */
    @XStreamAlias("Description")
    private Description description;

    /**
     * 预订规则列表，注意目前接口下单支持提前预定促销、住几天以上促销、住几送几促销，这三种促销，可以有下面的规则中任意一个也可为空
     */
    @XStreamAlias("BookingRules")
    private List<BookingRule> bookingRules;

    /**
     * 特定价格计划范围内的每日价列表
     */
    @XStreamAlias("Rates")
    private List<HotelRate> hotelRates;

    /**
     * 特定价格计划范围内的礼品礼盒促销等Offer信息
     */
    @XStreamAlias("Offers")
    private List<HotelRateOffer> hotelRateOffers;

    /**
     * 特定价格计划范围内的酒店可卖产品描述列表，这里特指房型描述
     */
    @XStreamAlias("SellableProducts")
    private List<HotelSellableProduct> hotelSellableProducts;

    public List<HotelRateOffer> getHotelRateOffers() {
        return hotelRateOffers;
    }

    public void setHotelRateOffers(List<HotelRateOffer> hotelRateOffers) {
        this.hotelRateOffers = hotelRateOffers;
    }

    public List<HotelSellableProduct> getHotelSellableProducts() {
        return hotelSellableProducts;
    }

    public void setHotelSellableProducts(List<HotelSellableProduct> hotelSellableProducts) {
        this.hotelSellableProducts = hotelSellableProducts;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public int getRatePlanCode() {
        return ratePlanCode;
    }

    public void setRatePlanCode(int ratePlanCode) {
        this.ratePlanCode = ratePlanCode;
    }

    public int getRatePlanCategory() {
        return ratePlanCategory;
    }

    public void setRatePlanCategory(int ratePlanCategory) {
        this.ratePlanCategory = ratePlanCategory;
    }

    public int getMarketCode() {
        return marketCode;
    }

    public void setMarketCode(int marketCode) {
        this.marketCode = marketCode;
    }

    public List<BookingRule> getBookingRules() {
        return bookingRules;
    }

    public void setBookingRules(List<BookingRule> bookingRules) {
        this.bookingRules = bookingRules;
    }

    public List<HotelRate> getHotelRates() {
        return hotelRates;
    }

    public void setHotelRates(List<HotelRate> hotelRates) {
        this.hotelRates = hotelRates;
    }
}
