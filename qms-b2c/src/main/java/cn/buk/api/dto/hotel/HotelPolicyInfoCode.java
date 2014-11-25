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
 * Date: 14-10-30
 * Time: 下午12:24
 */
@XStreamAlias("PolicyInfoCode")
public class HotelPolicyInfoCode {

    @XStreamAsAttribute
    @XStreamAlias("Code")
    private String code;

    @XStreamImplicit(itemFieldName = "Description")
    private List<HotelPolicyInfoDescription> hotelPolicyInfoDescriptions;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<HotelPolicyInfoDescription> getHotelPolicyInfoDescriptions() {
        return hotelPolicyInfoDescriptions;
    }

    public void setHotelPolicyInfoDescriptions(List<HotelPolicyInfoDescription> hotelPolicyInfoDescriptions) {
        this.hotelPolicyInfoDescriptions = hotelPolicyInfoDescriptions;
    }
}
