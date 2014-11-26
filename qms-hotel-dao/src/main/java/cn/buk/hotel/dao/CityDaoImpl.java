/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.hotel.dao;

import cn.buk.hotel.entity.City;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import java.util.List;


/**
 * User: william
 * Date: 14-9-29
 * Time: 下午11:27
 */
public class CityDaoImpl extends AbstractDao implements CityDao {

    private static Logger logger = Logger.getLogger(CityDaoImpl.class);

    @Override
    public int create(City city) {
        int retStatus = 0;
        EntityManager em = createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(city);
            em.getTransaction().commit();
            retStatus = 1;
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            retStatus = -1;
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return retStatus;
    }

    @Override
    public City getCityByOpenApiId(int openApiId) {
        List<City> cities = getEm().createQuery("select o from City o where o.openApiId = :openApiId")
                .setParameter("openApiId", openApiId)
                .getResultList();
        if (cities == null || cities.size() == 0) return null;

        return cities.get(0);
    }

    @Override
    public City getCityByCode(String cityCode) {
        List<City> cities = getEm().createQuery("select o from City o where o.cityCode = :cityCode")
                .setParameter("cityCode", cityCode)
                .getResultList();
        if (cities == null || cities.size() == 0) return null;

        return cities.get(0);
    }


    @Override
    public List<City> getAllCity() {
        List<City> cities = getEm().createQuery("select o from City o order by o.openApiId")
                .getResultList();
        return cities;
    }
}
