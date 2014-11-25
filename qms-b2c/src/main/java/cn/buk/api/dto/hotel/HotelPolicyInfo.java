/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto.hotel;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * User: yfdai
 * Date: 14-10-30
 * Time: 下午12:21
 */
public class HotelPolicyInfo {

    @XStreamAsAttribute
    @XStreamAlias("CheckInTime")
    private String checkInTime;

    @XStreamAsAttribute
    @XStreamAlias("CheckOutTime")
    private String checkOutTime;

    public String getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(String checkInTime) {
        this.checkInTime = checkInTime;
    }

    public String getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(String checkOutTime) {
        this.checkOutTime = checkOutTime;
    }
}
