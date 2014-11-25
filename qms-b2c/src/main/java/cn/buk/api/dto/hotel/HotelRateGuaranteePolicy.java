/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto.hotel;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import java.util.List;

/**
 * User: yfdai
 * Date: 14-10-31
 * Time: 上午11:18
 * 担保制度
 */
@XStreamAlias("GuaranteePolicy")
public class HotelRateGuaranteePolicy {

    /**
     * GuaranteeCode属性：担保类型代码，参考CodeList(RGC)
     */
    @XStreamAlias("GuaranteeCode")
    @XStreamAsAttribute
    private int guaranteeCode;

    /**
     * HoldTime属性：在此时间之前不需要担保
     */
    @XStreamAlias("HoldTime")
    @XStreamAsAttribute
    private String holdTime;



    public int getGuaranteeCode() {
        return guaranteeCode;
    }

    public void setGuaranteeCode(int guaranteeCode) {
        this.guaranteeCode = guaranteeCode;
    }

    public String getHoldTime() {
        return holdTime;
    }

    public void setHoldTime(String holdTime) {
        this.holdTime = holdTime;
    }

}
