/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto.hotel;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.List;

/**
 * User: yfdai
 * Date: 14-10-30
 * Time: 下午12:37
 */
public class AreaInfo {

    @XStreamAlias("RefPoints")
    private List<RefPoint> refPoints;

    public List<RefPoint> getRefPoints() {
        return refPoints;
    }

    public void setRefPoints(List<RefPoint> refPoints) {
        this.refPoints = refPoints;
    }
}
