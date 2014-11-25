/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto.hotel;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * User: yfdai
 * Date: 14-10-30
 * Time: 下午12:38
 */
@XStreamAlias("RefPoint")
public class RefPoint {

    @XStreamAsAttribute
    @XStreamAlias("Name")
    private String name;

    @XStreamAsAttribute
    @XStreamAlias("Distance")
    private double distance;

    @XStreamAsAttribute
    @XStreamAlias("UnitOfMeasureCode")
    private int unitOfMeasureCode;

    @XStreamAsAttribute
    @XStreamAlias("RefPointCategoryCode")
    private String refPointCategoryCode;

    @XStreamAsAttribute
    @XStreamAlias("RefPointName")
    private String refPointName;

    @XStreamAsAttribute
    @XStreamAlias("Latitude")
    private double latitude;

    @XStreamAsAttribute
    @XStreamAlias("Longitude")
    private double longitude;

    @XStreamAlias("DescriptiveText")
    private String descriptiveText;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }


    public String getRefPointCategoryCode() {
        return refPointCategoryCode;
    }

    public void setRefPointCategoryCode(String refPointCategoryCode) {
        this.refPointCategoryCode = refPointCategoryCode;
    }

    public String getRefPointName() {
        return refPointName;
    }

    public void setRefPointName(String refPointName) {
        this.refPointName = refPointName;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getDescriptiveText() {
        return descriptiveText;
    }

    public void setDescriptiveText(String descriptiveText) {
        this.descriptiveText = descriptiveText;
    }

    public int getUnitOfMeasureCode() {
        return unitOfMeasureCode;
    }

    public void setUnitOfMeasureCode(int unitOfMeasureCode) {
        this.unitOfMeasureCode = unitOfMeasureCode;
    }
}
