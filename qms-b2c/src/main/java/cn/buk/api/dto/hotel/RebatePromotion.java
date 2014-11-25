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
 * Time: 下午2:25
 * 促销信息
 */
@XStreamAlias("RebatePromotion")
public class RebatePromotion {

    @XStreamAlias("StartPeriod")
    @XStreamAsAttribute
    private String startPeriod;

    @XStreamAlias("EndPeriod")
    @XStreamAsAttribute
    private String endPeriod;

    @XStreamAlias("ProgramName")
    @XStreamAsAttribute
    private String programName;

    @XStreamAlias("Amount")
    @XStreamAsAttribute
    private float amount;

    @XStreamAlias("CurrencyCode")
    @XStreamAsAttribute
    private String currencyCode;

    @XStreamAlias("Code")
    @XStreamAsAttribute
    private int code;

    @XStreamAlias("Description")
    private  Description description;

    public String getStartPeriod() {
        return startPeriod;
    }

    public void setStartPeriod(String startPeriod) {
        this.startPeriod = startPeriod;
    }

    public String getEndPeriod() {
        return endPeriod;
    }

    public void setEndPeriod(String endPeriod) {
        this.endPeriod = endPeriod;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }
}
