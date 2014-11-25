/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * User: william
 * Date: 14-10-18
 * Time: 上午12:15
 */
@XStreamAlias("Stop")
public class Stop {

    @XStreamAlias("Airport")
    private String airport;

    @XStreamAlias("Duration")
    private int duration;

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
