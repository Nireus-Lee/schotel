/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto.hotel;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * User: yfdai
 * Date: 14-10-31
 * Time: 上午9:24
 * 价格计划列表（对应Ctrip子房型）
 */
public class HotelRatePlans {

    @XStreamAlias("HotelCode")
    @XStreamAsAttribute
    private String hotelCode;

    @XStreamImplicit(itemFieldName = "RatePlan")
    private List<HotelRatePlan> hotelRatePlans;

    public String getHotelCode() {
        return hotelCode;
    }

    public void setHotelCode(String hotelCode) {
        this.hotelCode = hotelCode;
    }

    public List<HotelRatePlan> getHotelRatePlans() {
        return hotelRatePlans;
    }

    public void setHotelRatePlans(List<HotelRatePlan> hotelRatePlans) {
        this.hotelRatePlans = hotelRatePlans;
    }
}
