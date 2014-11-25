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
 * Time: 下午1:57
 */
@XStreamAlias("Price")
public class Price {

    @XStreamAlias("PriceUnitID")
    private int priceUnitId;

    @XStreamAlias("RouteSearchToken")
    private String routeSearchToken;

    @XStreamAlias("FlyerFlag")
    private String flyerFlag;

    @XStreamAlias("BaggageList")
    private List<Baggage> baggageList;

    public int getPriceUnitId() {
        return priceUnitId;
    }

    public void setPriceUnitId(int priceUnitId) {
        this.priceUnitId = priceUnitId;
    }

    public String getRouteSearchToken() {
        return routeSearchToken;
    }

    public void setRouteSearchToken(String routeSearchToken) {
        this.routeSearchToken = routeSearchToken;
    }

    public String getFlyerFlag() {
        return flyerFlag;
    }

    public void setFlyerFlag(String flyerFlag) {
        this.flyerFlag = flyerFlag;
    }

    public List<Baggage> getBaggageList() {
        return baggageList;
    }

    public void setBaggageList(List<Baggage> baggageList) {
        this.baggageList = baggageList;
    }
}
