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
 * Time: 下午1:00
 */
@XStreamAlias("ContactInfo")
public class HotelContactInfo {

    @XStreamAlias("Phones")
    private List<HotelPhone> hotelPhones;

    public List<HotelPhone> getHotelPhones() {
        return hotelPhones;
    }

    public void setHotelPhones(List<HotelPhone> hotelPhones) {
        this.hotelPhones = hotelPhones;
    }
}
