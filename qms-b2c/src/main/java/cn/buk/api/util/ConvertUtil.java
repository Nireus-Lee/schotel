/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.util;

import cn.buk.api.dto.hotel.*;
import cn.buk.api.dto.hotel.DateRange;
import cn.buk.api.dto.hotel.HotelAward;
import cn.buk.api.dto.hotel.HotelInfo;
import cn.buk.api.dto.hotel.HotelRatePlan;
import cn.buk.hotel.entity.*;
import cn.buk.util.DateUtil;
import org.apache.log4j.Logger;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

/**
 * User: yfdai
 * Date: 14-9-18
 * Time: 下午3:27
 */
public class ConvertUtil {

    private static Logger logger = Logger.getLogger(ConvertUtil.class);


    public static cn.buk.hotel.entity.HotelInfo convertHotelInfo(HotelInfo hotelInfo) {
        cn.buk.hotel.entity.HotelInfo hotelInfo1 = new cn.buk.hotel.entity.HotelInfo();

        hotelInfo1.setSource("ctrip");
        hotelInfo1.setHotelId(hotelInfo.getHotelId());
        hotelInfo1.setHotelCode(hotelInfo.getHotelCode() + "");
        hotelInfo1.setHotelName(hotelInfo.getHotelName());
        hotelInfo1.setCityId(hotelInfo.getHotelCityCode());
        hotelInfo1.setAreaId(hotelInfo.getAreaId());
        hotelInfo1.setBrandCode(hotelInfo.getBrandCode()+"");

        hotelInfo1.setPositionTypeCode(hotelInfo.getHotelPosition().getPositionTypeCode());
        hotelInfo1.setLongitude(hotelInfo.getHotelPosition().getLongitude());
        hotelInfo1.setLatitude(hotelInfo.getHotelPosition().getLatitude());

        hotelInfo1.setCityName(hotelInfo.getAddress().getCityName());
        hotelInfo1.setAddress(hotelInfo.getAddress().getAddressLine());
        hotelInfo1.setPostalCode(hotelInfo.getAddress().getPostalCode());
        //hotel award
        for(HotelAward hotelAward: hotelInfo.getAwards()) {
            cn.buk.hotel.entity.HotelAward hotelAward1 = new cn.buk.hotel.entity.HotelAward();
            hotelAward1.setProvider(hotelAward.getProvider());
            try {
                hotelAward1.setRating(Float.parseFloat(hotelAward.getRating()));
            }   catch (Exception ex) {
                System.out.print("hotelAward.Rating");
                ex.printStackTrace();
            }
            hotelAward1.setHotelInfo(hotelInfo1);
            hotelInfo1.getHotelAwards().add(hotelAward1);
        }
        //relative position
        if (hotelInfo.getRelativePositions() == null) {
            System.out.println(hotelInfo.getHotelCode() + "\'s relative position is null.");
        } else {
            for(RelativePosition rp: hotelInfo.getRelativePositions()) {
                HotelRelativePosition rp1 = new cn.buk.hotel.entity.HotelRelativePosition();

                rp1.setName(rp.getName());
                rp1.setDistance(rp.getDistance());
                try {
                    rp1.setUnitOfMeasureCode(Integer.parseInt(rp.getUnitOfMeasureCode()));
                }   catch (Exception ex) {
                    System.out.print("UnitOfMeasureCode");
                    ex.printStackTrace();
                }
                rp1.setHotelInfo(hotelInfo1);
                hotelInfo1.getRelativePositions().add(rp1);
            }
        }

        return hotelInfo1;
    }

    public static cn.buk.hotel.entity.HotelRatePlan convertHotelRatePlan(HotelRatePlan dtoRatePlan) {
        cn.buk.hotel.entity.HotelRatePlan ratePlan = new cn.buk.hotel.entity.HotelRatePlan();

        //logger.info("rate plan code: " + dtoRatePlan.getRatePlanCode());
        ratePlan.setRatePlanCode(dtoRatePlan.getRatePlanCode());
        ratePlan.setRatePlanCategory(dtoRatePlan.getRatePlanCategory());
        ratePlan.setMarketCode(dtoRatePlan.getMarketCode());
        ratePlan.setName(dtoRatePlan.getDescription().getName());

        //booking rule begin
        if (dtoRatePlan.getBookingRules() != null && dtoRatePlan.getBookingRules().size() > 0) {
            ratePlan.setHotelRatePlanBookingRules(new ArrayList<HotelRatePlanBookingRule>());
            for(BookingRule bookingRule: dtoRatePlan.getBookingRules()) {
                HotelRatePlanBookingRule bookingRule1 = new HotelRatePlanBookingRule();
                bookingRule1.setHotelRatePlan(ratePlan);
                ratePlan.getHotelRatePlanBookingRules().add(bookingRule1);

                bookingRule1.setMinAdvancedBookingOffset(bookingRule.getMinAdvancedBookingOffset());
                bookingRule1.setMaxAdvancedBookingOffset(bookingRule.getMaxAdvancedBookingOffset());
                bookingRule1.setLaterReserveTime(bookingRule.getLaterReserveTime());

                if (bookingRule.getLengthOfStays() != null && bookingRule.getLengthOfStays().size()>0) {
                   bookingRule1.setLengthOfStay(bookingRule.getLengthOfStays().get(0).getTime());
                    if (bookingRule.getLengthOfStays().size()>1)
                        logger.warn("lengthOfStays = " + bookingRule.getLengthOfStays().size());
                }
            }
        }
        //booking rule end

        //rates begin
        ratePlan.setHotelRatePlanRates(new ArrayList<HotelRatePlanRate>());
        for(HotelRate rate: dtoRatePlan.getHotelRates()) {
            HotelRatePlanRate rate1 = new HotelRatePlanRate();
            rate1.setHotelRatePlan(ratePlan);
            ratePlan.getHotelRatePlanRates().add(rate1);

            try {
                rate1.setStartDate(DateUtil.convertToDate(rate.getStart()));
                rate1.setEndDate(DateUtil.convertToDate(rate.getEnd()));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            rate1.setStatus(rate.getStatus());

            if (rate.isInstantConfirm())
                rate1.setInstantConfirm(1);
            else
                rate1.setInstantConfirm(0);

            if (rate.getMealsIncluded() != null) {
                if (rate.getMealsIncluded().isBreakfast())
                    rate1.setBreakfast(1);
                else
                    rate1.setBreakfast(0);
                rate1.setNumberOfBreakfast(rate.getMealsIncluded().getNumberOfBreakfast());
            }

            //baseByGuestNumber
            rate1.setHotelRatePlanRateBaseByGuestAmounts(new ArrayList<HotelRatePlanRateBaseByGuestAmount>());
            for(HotelRateBaseByGuestAmount ga: rate.getHotelRateBaseByGuestAmount()) {
                HotelRatePlanRateBaseByGuestAmount ga1 = new HotelRatePlanRateBaseByGuestAmount();
                ga1.setHotelRatePlanRate(rate1);
                rate1.getHotelRatePlanRateBaseByGuestAmounts().add(ga1);

                ga1.setCurrencyCode(ga.getCurrencyCode());
                ga1.setListPrice(ga.getListPrice());
                ga1.setAmountBeforeTax(ga.getAmountBeforeTax());
                ga1.setNumberOfGuest(ga.getNumberOfGuests());
            }

            //fees
            rate1.setHotelRatePlanRateFees(new ArrayList<HotelRatePlanRateFee>());
            for(HotelRateFee fee: rate.getHotelRateFees()) {
                HotelRatePlanRateFee fee1 = new HotelRatePlanRateFee();
                fee1.setHotelRatePlanRate(rate1);
                rate1.getHotelRatePlanRateFees().add(fee1);

                fee1.setCode(fee.getCode());
                fee1.setAmount(fee.getAmount());
                fee1.setCurrencyCode(fee.getCurrencyCode());
                fee1.setChargeUnit(fee.getChargeUnit());
                fee1.setDescription(fee.getDescription().getText());
            }
            //guarantee
            if (rate.getHotelRateGuaranteePolicies() != null) {
                rate1.setHotelRatePlanRateGuaranteePolicies(new ArrayList<HotelRatePlanRateGuaranteePolicy>());
                for(HotelRateGuaranteePolicy p: rate.getHotelRateGuaranteePolicies()) {
                    HotelRatePlanRateGuaranteePolicy p1 = new HotelRatePlanRateGuaranteePolicy();
                    p1.setHotelRatePlanRate(rate1);
                    rate1.getHotelRatePlanRateGuaranteePolicies().add(p1);

                    p1.setGuaranteeCode(p.getGuaranteeCode());
                    p1.setHoldTime(p.getHoldTime());
                }
            }
            //cancel policy
            if (rate.getHotelRateCancelPolicies() != null) {
                rate1.setHotelRatePlanRateCancelPolicies(new ArrayList<HotelRatePlanRateCancelPolicy>());
                for(HotelRateCancelPolicy p: rate.getHotelRateCancelPolicies()) {
                    HotelRatePlanRateCancelPolicy p1 = new HotelRatePlanRateCancelPolicy();
                    p1.setHotelRatePlanRate(rate1);
                    rate1.getHotelRatePlanRateCancelPolicies().add(p1);

                    try {
                        p1.setStartTime(DateUtil.convertToDateTime(p.getStart()));
                        p1.setEndTime(DateUtil.convertToDateTime(p.getEnd()));
                    } catch (ParseException e) {
                        logger.error("cancel policy: date convert error.");
                        e.printStackTrace();
                    }
                    p1.setAmount(p.getAmountPercent().getAmount());
                    p1.setCurrencyCode(p.getAmountPercent().getCurrencyCode());
                }
            }
            //promotion
            rate1.setHotelRatePlanRatePromotions(new ArrayList<HotelRatePlanRatePromotion>());
            if (rate.getRebatePromotions() != null) {
                for(RebatePromotion p: rate.getRebatePromotions()) {
                    HotelRatePlanRatePromotion p1 = new HotelRatePlanRatePromotion();
                    p1.setHotelRatePlanRate(rate1);
                    rate1.getHotelRatePlanRatePromotions().add(p1);

                    try {
                        p1.setStartDate(DateUtil.convertToDate(p.getStartPeriod()));
                        p1.setEndDate(DateUtil.convertToDate(p.getEndPeriod()));
                    } catch (ParseException e) {
                        logger.error("promotion: date convert error." + p.getStartPeriod() + "," + p.getEndPeriod());
                        e.printStackTrace();
                    }

                    p1.setProgramName(p.getProgramName());
                    p1.setAmount((int)p.getAmount());
                    p1.setCurrencyCode(p.getCurrencyCode());
                    p1.setCode(p.getCode());
                    p1.setDescription(p.getDescription().getText());
                }
            }

            //booking rule
            if (rate.getBookingRules() != null && rate.getBookingRules().size() > 0) {
                String laterReserveTime = rate.getBookingRules().get(0).getLaterReserveTime();
                try {
                    rate1.setLaterReserveTime(DateUtil.convertToDate(laterReserveTime,"yyyy-MM-dd'T'HH:mm:ss"));
                    //logger.info(laterReserveTime + " ->" + rate1.getLaterReserveTime());
                } catch (ParseException e) {
                    logger.error("booking rule: date convert error, " + laterReserveTime);
                    e.printStackTrace();
                }
            }
        }
        //rates end

        //offer begin
        if (dtoRatePlan.getHotelRateOffers() != null && dtoRatePlan.getHotelRateOffers().size() > 0) {
            ratePlan.setHotelRatePlanOffers(new ArrayList<HotelRatePlanOffer>());
            for(HotelRateOffer dtoRateOffer : dtoRatePlan.getHotelRateOffers()) {
                HotelRatePlanOffer ratePlanOffer = new HotelRatePlanOffer();
                ratePlanOffer.setHotelRatePlan(ratePlan);
                ratePlan.getHotelRatePlanOffers().add(ratePlanOffer);

                String startDate = "", endDate = "";

                try {
                    if (dtoRateOffer.getHotelRateOfferRules() != null && dtoRateOffer.getHotelRateOfferRules().size() > 0)  {
                        ratePlanOffer.setRatePlanOfferRules(new ArrayList<HotelRatePlanOfferRule>());
                        for(HotelRateOfferRule dtoRule: dtoRateOffer.getHotelRateOfferRules()) {
                            HotelRatePlanOfferRule rule = new HotelRatePlanOfferRule();
                            rule.setRatePlanOffer(ratePlanOffer);
                            ratePlanOffer.getRatePlanOfferRules().add(rule);

                            for(DateRange dtoRange: dtoRule.getDateRestrictions()) {
                                HotelRatePlanOfferRuleDateRestriction range = new HotelRatePlanOfferRuleDateRestriction();
                                range.setRatePlanOfferRule(rule);
                                rule.getRatePlanOfferRuleDateRestrictions().add(range);

                                range.getDateRange().setRestrictionDateCode(dtoRange.getRestrictionDateCode());
                                range.getDateRange().setRestrictionType(dtoRange.getRestrictionType());
                                try {
                                    if (dtoRange.getRestrictionDateCode() == null) {
                                        Date start = DateUtil.convertToDate(dtoRange.getStartDate(), "yyyy-MM-dd HH:mm:ss");
                                        range.getDateRange().setStartTime(start);
                                        Date end =  DateUtil.convertToDate(dtoRange.getEndDate(), "yyyy-MM-dd HH:mm:ss");
                                        range.getDateRange().setEndTime(end);
                                    } else if (dtoRange.getRestrictionDateCode().equalsIgnoreCase("501")) {
                                        Date start = DateUtil.convertToDate(dtoRange.getStartDate(), "yyyy-MM-dd HH:mm:ss");
                                        range.getDateRange().setStartTime(start);
                                        Date end =  DateUtil.convertToDate(dtoRange.getEndDate(), "yyyy-MM-dd HH:mm:ss");
                                        range.getDateRange().setEndTime(end);
                                    } else if (dtoRange.getRestrictionDateCode().equalsIgnoreCase("502")) {
                                        Date start = DateUtil.convertToDate(dtoRange.getStartDate(), "HH:mm");
                                        range.getDateRange().setStartTime(start);
                                        Date end =  DateUtil.convertToDate(dtoRange.getEndDate(), "HH:mm");
                                        range.getDateRange().setEndTime(end);
                                    }

                                } catch (Exception ex) {
                                    logger.warn("exception：" + dtoRange.getStartDate() + ", " + dtoRange.getEndDate());
                                    ex.printStackTrace();
                                }

                            }
                        }

                    }
                } catch (Exception e) {
                    logger.error("offer: date convert error." + startDate + "," + endDate);
                    e.printStackTrace();
                }

                ratePlanOffer.setOfferCode(dtoRateOffer.getOfferCode());

                if (dtoRateOffer.getHotelOfferDiscount() != null) {
                    ratePlanOffer.setNightsRequired(dtoRateOffer.getHotelOfferDiscount().getNightsRequired());
                    ratePlanOffer.setNightsDiscounted(dtoRateOffer.getHotelOfferDiscount().getNightsDiscounted());
                    ratePlanOffer.setDiscountPattern(dtoRateOffer.getHotelOfferDiscount().getDiscountPattern());
                }

                if (dtoRateOffer.getDescription() != null)
                    ratePlanOffer.setDescription(dtoRateOffer.getDescription().getText());
            }
        }
        //offer end

        //SellableProducts begin
        ratePlan.setHotelRatePlanSellableProducts(new ArrayList<HotelRatePlanSellableProduct>());
        for(HotelSellableProduct s: dtoRatePlan.getHotelSellableProducts()) {
            HotelRatePlanSellableProduct s1 = new HotelRatePlanSellableProduct();
            s1.setHotelRatePlan(ratePlan);
            ratePlan.getHotelRatePlanSellableProducts().add(s1);

            s1.setInvCode(s.getInvCode());
        }
        //SellableProducts end

        return ratePlan;
    }
}
