/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.hotel.entity;

import javax.persistence.*;
import java.util.List;

/**
 * User: yfdai
 * Date: 14-10-31
 * Time: 下午3:36
 */
@Entity
@Table(name="rateplan")
public class HotelRatePlan {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name="hotel_id")
    private HotelInfo hotelInfo;

    private String name;

    /**
     *  RatePlanCode属性：价格计划代码；
     */
    private int ratePlanCode;

    /**
     * RatePlanCategory属性：价格计划类型代码，参考CodeList (RTC)
     */
    private int ratePlanCategory;

    /**
     *MarketCode属性：市场代码，参考CodeList (MKC)
     */
    private int marketCode;



    @OneToMany(mappedBy = "hotelRatePlan", cascade = {CascadeType.ALL})
    private List<HotelRatePlanBookingRule> hotelRatePlanBookingRules;

    @OneToMany(mappedBy = "hotelRatePlan", cascade = {CascadeType.ALL})
    private List<HotelRatePlanRate> hotelRatePlanRates;

    /**
     * 特定价格计划范围内的礼品礼盒促销等Offer信息
     */
    @OneToMany(mappedBy = "hotelRatePlan", cascade = {CascadeType.ALL})
    private List<HotelRatePlanOffer> hotelRatePlanOffers;

    /**
     * 特定价格计划范围内的酒店可卖产品描述列表，这里特指房型描述
     */
    @OneToMany(mappedBy = "hotelRatePlan", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<HotelRatePlanSellableProduct> hotelRatePlanSellableProducts;


    public HotelInfo getHotelInfo() {
        return hotelInfo;
    }

    public void setHotelInfo(HotelInfo hotelInfo) {
        this.hotelInfo = hotelInfo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<HotelRatePlanBookingRule> getHotelRatePlanBookingRules() {
        return hotelRatePlanBookingRules;
    }

    public void setHotelRatePlanBookingRules(List<HotelRatePlanBookingRule> hotelRatePlanBookingRules) {
        this.hotelRatePlanBookingRules = hotelRatePlanBookingRules;
    }

    public List<HotelRatePlanRate> getHotelRatePlanRates() {
        return hotelRatePlanRates;
    }

    public void setHotelRatePlanRates(List<HotelRatePlanRate> hotelRatePlanRates) {
        this.hotelRatePlanRates = hotelRatePlanRates;
    }

    public List<HotelRatePlanOffer> getHotelRatePlanOffers() {
        return hotelRatePlanOffers;
    }

    public void setHotelRatePlanOffers(List<HotelRatePlanOffer> hotelRatePlanOffers) {
        this.hotelRatePlanOffers = hotelRatePlanOffers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<HotelRatePlanSellableProduct> getHotelRatePlanSellableProducts() {
        return hotelRatePlanSellableProducts;
    }

    public void setHotelRatePlanSellableProducts(List<HotelRatePlanSellableProduct> hotelRatePlanSellableProducts) {
        this.hotelRatePlanSellableProducts = hotelRatePlanSellableProducts;
    }
}
