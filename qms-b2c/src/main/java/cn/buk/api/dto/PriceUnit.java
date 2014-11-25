/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.List;

/**
 * User: yfdai
 * Date: 14-10-17
 * Time: 下午2:12
 */
@XStreamAlias("PriceUnit")
public class PriceUnit {

    @XStreamAlias("PriceUnitID")
    private int id;

    @XStreamAlias("FareInfoList")
    private List<FareInfo> fareInfos;

    @XStreamAlias("PriceInfoList")
    private List<PriceInfo> priceInfos;

    @XStreamAlias("BaggageList")
    private List<Baggage> baggages;

    @XStreamAlias("BookingClassInfoListList")
    private List<BookingClassInfoList> bookingClassInfoLists;

    @XStreamAlias("MajorCabinClassList")
    private CabinClassList cabinClassList;

    @XStreamAlias("EngineType")
    private String engineType;

    @XStreamAlias("PriceUnitMisc")
    private PriceUnitMisc priceUnitMisc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<FareInfo> getFareInfos() {
        return fareInfos;
    }

    public void setFareInfos(List<FareInfo> fareInfos) {
        this.fareInfos = fareInfos;
    }

    public List<PriceInfo> getPriceInfos() {
        return priceInfos;
    }

    public void setPriceInfos(List<PriceInfo> priceInfos) {
        this.priceInfos = priceInfos;
    }

    public List<Baggage> getBaggages() {
        return baggages;
    }

    public void setBaggages(List<Baggage> baggages) {
        this.baggages = baggages;
    }

    public List<BookingClassInfoList> getBookingClassInfoLists() {
        return bookingClassInfoLists;
    }

    public void setBookingClassInfoLists(List<BookingClassInfoList> bookingClassInfoLists) {
        this.bookingClassInfoLists = bookingClassInfoLists;
    }

    public CabinClassList getCabinClassList() {
        return cabinClassList;
    }

    public void setCabinClassList(CabinClassList cabinClassList) {
        this.cabinClassList = cabinClassList;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public PriceUnitMisc getPriceUnitMisc() {
        return priceUnitMisc;
    }

    public void setPriceUnitMisc(PriceUnitMisc priceUnitMisc) {
        this.priceUnitMisc = priceUnitMisc;
    }
}
