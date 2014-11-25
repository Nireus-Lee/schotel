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
 * Time: 下午12:47
 */
public class AffiliationInfo {

    @XStreamAlias("Awards")
    private List<HotelAward> hotelAwards;

    public List<HotelAward> getHotelAwards() {
        return hotelAwards;
    }

    public void setHotelAwards(List<HotelAward> hotelAwards) {
        this.hotelAwards = hotelAwards;
    }
}
