/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto.hotel;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * User: yfdai
 * Date: 14-10-31
 * Time: 下午2:47
 */
@XStreamAlias("SellableProduct")
public class HotelSellableProduct {

    @XStreamAlias("InvCode")
    @XStreamAsAttribute
    private String invCode;

    public String getInvCode() {
        return invCode;
    }

    public void setInvCode(String invCode) {
        this.invCode = invCode;
    }
}
