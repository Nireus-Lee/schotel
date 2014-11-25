/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * User: yfdai
 * Date: 14-10-17
 * Time: 下午2:30
 */
@XStreamAlias("Baggage")
public class Baggage {

    @XStreamAlias("OrigDestSeqID")
    private int origDestSeqId;

    @XStreamAlias("RemarkID")
    private int remarkId;

    @XStreamAlias("TravelerCategoryCode")
    private String travelerCategoryCode;

    public int getOrigDestSeqId() {
        return origDestSeqId;
    }

    public void setOrigDestSeqId(int origDestSeqId) {
        this.origDestSeqId = origDestSeqId;
    }

    public int getRemarkId() {
        return remarkId;
    }

    public void setRemarkId(int remarkId) {
        this.remarkId = remarkId;
    }

    public String getTravelerCategoryCode() {
        return travelerCategoryCode;
    }

    public void setTravelerCategoryCode(String travelerCategoryCode) {
        this.travelerCategoryCode = travelerCategoryCode;
    }
}
