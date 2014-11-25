/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.ArrayList;
import java.util.List;

/**
 * User: yfdai
 * Date: 14-10-16
 * Time: 上午10:48
 */
public class SearchCriteria {

    @XStreamAlias("OrigDestRequestList")
    private List<OrigDestRequest> origDestRequestList = new ArrayList<OrigDestRequest>();

    //<!--乘客信息-->
    @XStreamAlias("TravelerRequestList")
    private List<TravelerRequest> travelerRequestList = new ArrayList<TravelerRequest>();

//    <!--舱等：经济舱Economy/超级经济舱Premium/商务舱Business/头等舱First-->
    @XStreamAlias("CabinClass")
    private String cabinClass;
//    <!--为true时结果只返回用户指定舱位等级-->
    @XStreamAlias("RequestedCabinClassOnly")
    private boolean  requestedCabinClassOnly;
//    <!--机票配送城市ID-->
    @XStreamAlias("TicketDeliveryCityID")
    private int  ticketDeliveryCityID;

    public List<OrigDestRequest> getOrigDestRequestList() {
        return origDestRequestList;
    }

    public void setOrigDestRequestList(List<OrigDestRequest> origDestRequestList) {
        this.origDestRequestList = origDestRequestList;
    }

    public List<TravelerRequest> getTravelerRequestList() {
        return travelerRequestList;
    }

    public void setTravelerRequestList(List<TravelerRequest> travelerRequestList) {
        this.travelerRequestList = travelerRequestList;
    }

    public String getCabinClass() {
        return cabinClass;
    }

    public void setCabinClass(String cabinClass) {
        this.cabinClass = cabinClass;
    }

    public boolean isRequestedCabinClassOnly() {
        return requestedCabinClassOnly;
    }

    public void setRequestedCabinClassOnly(boolean requestedCabinClassOnly) {
        this.requestedCabinClassOnly = requestedCabinClassOnly;
    }

    public int getTicketDeliveryCityID() {
        return ticketDeliveryCityID;
    }

    public void setTicketDeliveryCityID(int ticketDeliveryCityID) {
        this.ticketDeliveryCityID = ticketDeliveryCityID;
    }
}
