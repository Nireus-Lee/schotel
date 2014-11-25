/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.hotel.entity;

import javax.persistence.*;

/**
 * User: william
 * Date: 14-9-29
 * Time: 下午11:10
 */
@Entity
@Table(name="city")
public class City {

    @Id
    @GeneratedValue
    private int id;

    /**
     * 城市的三字代码
     */
    @Column(name="city_code")
    private String cityCode;

    @Column(name="name")
    private String cityName;

    @Column(name="nameEn")
    private String cityEnglishName;

    @Column(name="country_code")
    private String countryCode;

    @Column(name="province_id")
    private int provinceId;

    private String airport;

    @Column(name="open_api_id")
    private int openApiId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityEnglishName() {
        return cityEnglishName;
    }

    public void setCityEnglishName(String cityEnglishName) {
        this.cityEnglishName = cityEnglishName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    public int getOpenApiId() {
        return openApiId;
    }

    public void setOpenApiId(int openApiId) {
        this.openApiId = openApiId;
    }
}
