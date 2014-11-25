/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto.hotel;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * User: yfdai
 * Date: 14-10-31
 * Time: 下午3:01
 */
@XStreamAlias("OtherCurrency")
public class HotelRateOtherCurrency {

    @XStreamAlias("AmountPercentType")
    private AmountPercent amountPercent;

    public AmountPercent getAmountPercent() {
        return amountPercent;
    }

    public void setAmountPercent(AmountPercent amountPercent) {
        this.amountPercent = amountPercent;
    }
}
