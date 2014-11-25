/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto.hotel;

import cn.buk.api.dto.hotel.HotelInfo;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * User: yfdai
 * Date: 14-10-28
 * Time: 下午4:08
 */
@XStreamAlias("OTA_HotelSearchRS")
public class HotelSearchResponse {

    @XStreamAlias("Properties")
    private List<HotelInfo> hotelInfos;

    @XStreamAlias("Success")
    private Success success;

    public List<HotelInfo> getHotelInfos() {
        return hotelInfos;
    }

    public void setHotelInfos(List<HotelInfo> hotelInfos) {
        this.hotelInfos = hotelInfos;
    }

    public Success getSuccess() {
        return success;
    }

    public void setSuccess(Success success) {
        this.success = success;
    }
}
