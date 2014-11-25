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
 * Time: 下午2:09
 */
public class IntlFlightSearchResult {

    @XStreamAlias("FlightProductGroupList")
    private List<FlightProductGroup> flightProductGroups;

    @XStreamAlias("PriceUnitList")
    private List<PriceUnit> priceUnits;

    @XStreamAlias("FlightList")
    private List<Flight> flights;

    @XStreamAlias("FareList")
    private List<Fare> fares;

    @XStreamAlias("TextRemarkList")
    private List<TextRemark> textRemarks;

    public List<FlightProductGroup> getFlightProductGroups() {
        return flightProductGroups;
    }

    public void setFlightProductGroups(List<FlightProductGroup> flightProductGroups) {
        this.flightProductGroups = flightProductGroups;
    }

    public List<PriceUnit> getPriceUnits() {
        return priceUnits;
    }

    public void setPriceUnits(List<PriceUnit> priceUnits) {
        this.priceUnits = priceUnits;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public List<Fare> getFares() {
        return fares;
    }

    public void setFares(List<Fare> fares) {
        this.fares = fares;
    }

    public List<TextRemark> getTextRemarks() {
        return textRemarks;
    }

    public void setTextRemarks(List<TextRemark> textRemarks) {
        this.textRemarks = textRemarks;
    }
}
