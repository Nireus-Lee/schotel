/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.hotel.entity;

import javax.persistence.*;

/**
 * Created by yfdai on 2014-11-17
 * 商业区 .
 */
@Entity
@Table(name="district_zone")
public class Zone {

    @Id
    @GeneratedValue
    private int id;

    @Column(name="zone_id")
    private int zoneId;

    @Column(name="city_id")
    private int cityId;

    private String name;

    private String englishName;

    @Column(length=500)
    private String description;

    private String mapUse;

    private String mapRange;

    private String mapPic;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getZoneId() {
        return zoneId;
    }

    public void setZoneId(int zoneId) {
        this.zoneId = zoneId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMapUse() {
        return mapUse;
    }

    public void setMapUse(String mapUse) {
        this.mapUse = mapUse;
    }

    public String getMapRange() {
        return mapRange;
    }

    public void setMapRange(String mapRange) {
        this.mapRange = mapRange;
    }

    public String getMapPic() {
        return mapPic;
    }

    public void setMapPic(String mapPic) {
        this.mapPic = mapPic;
    }
}
