/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto.hotel;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.List;

/**
 * User: yfdai
 * Date: 14-10-30
 * Time: 下午12:20
 */
@XStreamAlias("Policy")
public class HotelPolicy {

    @XStreamAlias("PolicyInfo")
    private HotelPolicyInfo hotelPolicyInfo;

    @XStreamAlias("PolicyInfoCodes")
    private List<HotelPolicyInfoCode> hotelPolicyInfoCodes;

    public HotelPolicyInfo getHotelPolicyInfo() {
        return hotelPolicyInfo;
    }

    public void setHotelPolicyInfo(HotelPolicyInfo hotelPolicyInfo) {
        this.hotelPolicyInfo = hotelPolicyInfo;
    }

    public List<HotelPolicyInfoCode> getHotelPolicyInfoCodes() {
        return hotelPolicyInfoCodes;
    }

    public void setHotelPolicyInfoCodes(List<HotelPolicyInfoCode> hotelPolicyInfoCodes) {
        this.hotelPolicyInfoCodes = hotelPolicyInfoCodes;
    }
}
