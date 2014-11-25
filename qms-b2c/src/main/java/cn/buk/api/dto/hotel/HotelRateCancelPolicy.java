/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto.hotel;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yfdai
 * Date: 14-10-31
 * Time: 上午11:29
 * 取消制度
 */
@XStreamAlias("CancelPenalty")
public class HotelRateCancelPolicy {

    /**
     *  Start属性：开始时间,表示在这个时间段取消是需要扣除罚金，start表示了最迟的取消时间，在这个时间前取消不需要扣除罚
     */
    @XStreamAlias("Start")
    @XStreamAsAttribute
    private String start;

    /**
     * End属性：结束时间
     */
    @XStreamAlias("End")
    @XStreamAsAttribute
    private String end;

    /**
     * 罚金金额=担保金额=当日房价
     */
    @XStreamAlias("AmountPercent")
    private AmountPercent amountPercent;

    @XStreamAlias("TPA_Extensions")
    private List<HotelRateOtherCurrency> hotelRateOtherCurrencies;

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public AmountPercent getAmountPercent() {
        return amountPercent;
    }

    public void setAmountPercent(AmountPercent amountPercent) {
        this.amountPercent = amountPercent;
    }

    public List<HotelRateOtherCurrency> getHotelRateOtherCurrencies() {
        return hotelRateOtherCurrencies;
    }

    public void setHotelRateOtherCurrencies(List<HotelRateOtherCurrency> hotelRateOtherCurrencies) {
        this.hotelRateOtherCurrencies = hotelRateOtherCurrencies;
    }
}
