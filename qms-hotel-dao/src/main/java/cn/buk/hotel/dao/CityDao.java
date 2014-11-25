/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.hotel.dao;

import cn.buk.hotel.entity.City;

import java.util.List;

/**
 * User: william
 * Date: 14-9-29
 * Time: 下午11:27
 */
public interface CityDao {

    public int create(City city);

    public City getCityByOpenApiId(int openApiId);

    public City getCityByCode(String cityCode);

    public List<City> getAllCity();
}
