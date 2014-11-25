/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto.hotel;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.List;

/**
 * User: william
 * Date: 14-10-28
 * Time: 下午11:23
 */
public class TpaExtensions {

    @XStreamAlias("Zone")
    private List<ZoneType> zoneTypes;

    @XStreamAlias("ThemeCategory")
    private List<ThemeCategoryType> themeCategoryTypes;

    @XStreamAlias("CityImportantMessage")
    private List<CityImportantMessageType> cityImportantMessageTypes;

    public List<ZoneType> getZoneTypes() {
        return zoneTypes;
    }

    public void setZoneTypes(List<ZoneType> zoneTypes) {
        this.zoneTypes = zoneTypes;
    }

    public List<ThemeCategoryType> getThemeCategoryTypes() {
        return themeCategoryTypes;
    }

    public void setThemeCategoryTypes(List<ThemeCategoryType> themeCategoryTypes) {
        this.themeCategoryTypes = themeCategoryTypes;
    }

    public List<CityImportantMessageType> getCityImportantMessageTypes() {
        return cityImportantMessageTypes;
    }

    public void setCityImportantMessageTypes(List<CityImportantMessageType> cityImportantMessageTypes) {
        this.cityImportantMessageTypes = cityImportantMessageTypes;
    }
}
