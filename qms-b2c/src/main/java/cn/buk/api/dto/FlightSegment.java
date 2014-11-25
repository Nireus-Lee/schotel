/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * User: yfdai
 * Date: 14-10-17
 * Time: 下午2:02
 */
@XStreamAlias("FlightSegment")
public class FlightSegment {

    /**
     * 行程序号，例如。去程1，返程2
     */
    @XStreamAlias("OrigDestSeqID")
    private int origDestSeqId;

    @XStreamAlias("SegmentSeqID")
    private int segmentSeqId;

    @XStreamAlias("IsAddOn")
    private boolean addOn;

    @XStreamAlias("FlightID")
    private int flightId;

    @XStreamAlias("GroupID")
    private String groupId;

    public int getOrigDestSeqId() {
        return origDestSeqId;
    }

    public void setOrigDestSeqId(int origDestSeqId) {
        this.origDestSeqId = origDestSeqId;
    }

    public int getSegmentSeqId() {
        return segmentSeqId;
    }

    public void setSegmentSeqId(int segmentSeqId) {
        this.segmentSeqId = segmentSeqId;
    }

    public boolean isAddOn() {
        return addOn;
    }

    public void setAddOn(boolean addOn) {
        this.addOn = addOn;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
