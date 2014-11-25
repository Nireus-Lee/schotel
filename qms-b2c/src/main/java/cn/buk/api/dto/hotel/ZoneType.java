/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto.hotel;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * User: william
 * Date: 14-10-28
 * Time: 下午11:24
 */
@XStreamAlias("ZoneType")
public class ZoneType {

    @XStreamAsAttribute
    @XStreamAlias("ZoneID")
    private int zoneId;

    @XStreamAsAttribute
    @XStreamAlias("ZoneName")
    private String zoneName;

    public int getZoneId() {
        return zoneId;
    }

    public void setZoneId(int zoneId) {
        this.zoneId = zoneId;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }
}
