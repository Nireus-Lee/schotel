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
 * Time: 下午11:19
 */
public class RelativePosition {

    @XStreamAsAttribute
    @XStreamAlias("Name")
    private String name;

    @XStreamAsAttribute
    @XStreamAlias("UnitOfMeasureCode")
    private String unitOfMeasureCode;

    @XStreamAsAttribute
    @XStreamAlias("Distance")
    private float distance;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnitOfMeasureCode() {
        return unitOfMeasureCode;
    }

    public void setUnitOfMeasureCode(String unitOfMeasureCode) {
        this.unitOfMeasureCode = unitOfMeasureCode;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }
}
