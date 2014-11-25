/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * User: yfdai
 * Date: 14-10-17
 * Time: 下午2:14
 */
@XStreamAlias("FareInfo")
public class FareInfo {

    @XStreamAlias("FareID")
    private int id;

    @XStreamAlias("OrigDestSeqID")
    private int origDestSeqId;

    @XStreamAlias("Schedule")
    private String schedule;

    @XStreamAlias("FareBasis")
    private String fareBasis;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrigDestSeqId() {
        return origDestSeqId;
    }

    public void setOrigDestSeqId(int origDestSeqId) {
        this.origDestSeqId = origDestSeqId;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getFareBasis() {
        return fareBasis;
    }

    public void setFareBasis(String fareBasis) {
        this.fareBasis = fareBasis;
    }
}
