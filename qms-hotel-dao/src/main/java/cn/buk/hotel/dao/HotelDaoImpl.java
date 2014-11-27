/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.hotel.dao;

import cn.buk.hotel.entity.*;
import cn.buk.hotel.sc.HotelSearchCriteria;
import cn.buk.hotel.util.EntityUtil;
import cn.buk.util.DateUtil;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HotelDaoImpl extends AbstractDao implements HotelDao {

    public static Logger logger = Logger.getLogger(HotelDaoImpl.class);


    @Override
    public int createHotelInfo(HotelInfo hotelInfo) {
        int retCode = 0;
        EntityManager em = createEntityManager();
        try {
            List<HotelInfo> hotelInfos = em.createQuery("select o from HotelInfo o where o.hotelCode = :hotelCode")
                    .setParameter("hotelCode", hotelInfo.getHotelCode())
                    .getResultList();
            if (hotelInfos.size() > 0) {
               retCode = 2;
            } else {
                em.getTransaction().begin();
                em.persist(hotelInfo);
                em.getTransaction().commit();

                retCode = 1;
            }
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            retCode = -1;
            logger.info("HotelCode: " + hotelInfo.getHotelCode() + ", HotelName: " + hotelInfo.getHotelName());
            logger.warn(ex.getMessage());
        } finally {
            em.close();
        }
        return retCode;
    }

    @Override
    public HotelInfo getHotelInfoByHotelCode(String hotelCode) {
        List<HotelInfo> hotelInfos = getEm().createQuery("select o from HotelInfo o where o.hotelCode = :hotelCode")
                .setParameter("hotelCode", hotelCode)
                .getResultList();
        return hotelInfos.size() > 0 ? hotelInfos.get(0): null;
    }

    @Override
    public int updateHotelInfo(HotelInfo hotelInfo) {
        int retCode = 0;
        EntityManager em = createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(hotelInfo);
            em.getTransaction().commit();

            retCode = 1;
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            retCode = -1;
            logger.error("updateHotelInfo failed: (HotelCode: " + hotelInfo.getHotelCode() + ", HotelName: " + hotelInfo.getHotelName() + ")");
            logger.error(ex.getMessage());
        }finally {
            em.close();
        }
        return retCode;
    }

    @Override
    public List<HotelInfo> getAllHotelInfo() {
        List<HotelInfo> hotelInfos = getEm().createQuery("select o.hotelCode from HotelInfo o order by o.id").getResultList();
        return  hotelInfos;
    }

    @Override
    public List<String> getAllHotelCodes() {
        List<String> hotelCodes = getEm().createQuery("select o.hotelCode from HotelInfo o order by o.id").getResultList();
        return  hotelCodes;
    }

    @Override
    public List<String> getAllHotelCodesByCityId(int cityId) {
        Date baseTime = DateUtil.getCurDateTime();
        List<String> hotelCodes = getEm().createQuery("select o.hotelCode from HotelInfo o where o.cityId = :cityId order by o.id")
                .setParameter("cityId", cityId)
                .getResultList();
        int span = DateUtil.getPastTime(baseTime);
        logger.info("get all hotel codes in city (" + cityId + ") elapsed: " + span + "ms.");

        return  hotelCodes;
    }

    @Override
    public int createHotelRatePlan(HotelRatePlan hotelRatePlan) {
        int retCode = 0;
        EntityManager em = createEntityManager();
        try {
            List<HotelRatePlan> ratePlans = em.createQuery("select o from HotelRatePlan o where o.ratePlanCode = :ratePlanCode and o.hotelInfo = :hotelInfo")
                    .setParameter("ratePlanCode", hotelRatePlan.getRatePlanCode())
                    .setParameter("hotelInfo", hotelRatePlan.getHotelInfo())
                    .getResultList();
            if (ratePlans.size() > 0) {
                retCode = 2;
            } else {
                em.getTransaction().begin();
                em.persist(hotelRatePlan);
                em.getTransaction().commit();

                retCode = 1;
            }

        } catch (Exception ex) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            retCode = -1;
            ex.printStackTrace();
    }            finally {
            em.close();
        }
        return retCode;
    }

    @Override
    public CacheCity getCacheCity(int cityId) {
        List<CacheCity> cacheCities = getEm().createQuery("select o from CacheCity o where o.cityId = :cityId")
                .setParameter("cityId", cityId)
                .getResultList();

        return cacheCities.size() > 0 ? cacheCities.get(0) : null;
    }

    @Override
    public int createCacheCity(CacheCity cacheCity) {
        int retCode = 0;
        EntityManager em = createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(cacheCity);
            em.getTransaction().commit();

                retCode = 1;
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            retCode = -1;
        } finally {
            em.close();
        }
        return retCode;
    }

    @Override
    public int updateCacheCity(CacheCity cacheCity) {
        int retCode = 0;
        EntityManager em = createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(cacheCity);
            em.getTransaction().commit();

            retCode = 1;
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            retCode = -1;
        }
        return retCode;
    }

    @Override
    public CacheHotel getCacheHotel(String hotelCode) {
        List<CacheHotel> cacheHotels = getEm().createQuery("select o from CacheHotel o where o.hotelCode = :hotelCode")
                .setParameter("hotelCode", hotelCode)
                .getResultList();

        return cacheHotels.size() > 0 ? cacheHotels.get(0) : null;
    }

    @Override
    public int createCacheHotel(CacheHotel cacheHotel) {
        int retCode = 0;
        EntityManager em = createEntityManager();
        try {

            em.getTransaction().begin();
            em.persist(cacheHotel);
            em.getTransaction().commit();


            retCode = 1;
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            retCode = -1;
        }finally {
            em.close();
        }
        return retCode;
    }

    @Override
    public int updateCacheHotel(CacheHotel cacheHotel) {
        int retCode = 0;
        EntityManager em = createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(cacheHotel);
            em.getTransaction().commit();

            retCode = 1;
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            retCode = -1;
        }  finally {
            em.close();
        }
        return retCode;
    }

    @Override
    public CacheRatePlan getCacheRatePlan(String hotelCode, int periodId) {
        List<CacheRatePlan> cacheRatePlans = getEm().createQuery("select o from CacheRatePlan o where o.hotelCode = :hotelCode and o.periodId = :periodId")
                .setParameter("hotelCode", hotelCode)
                .setParameter("periodId", periodId)
                .getResultList();

        return cacheRatePlans.size() > 0 ? cacheRatePlans.get(0) : null;
    }

    @Override
    public int createCacheRatePlan(CacheRatePlan cacheRatePlan) {
        int retCode = 0;
        EntityManager em = createEntityManager();
        try {

            em.getTransaction().begin();
            em.persist(cacheRatePlan);
            em.getTransaction().commit();

            retCode = 1;
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            retCode = -1;
        } finally {
            em.close();
        }
        return retCode;
    }

    @Override
    public int updateCacheRatePlan(CacheRatePlan cacheRatePlan) {
        int retCode = 0;
        EntityManager em = createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(cacheRatePlan);
            em.getTransaction().commit();

            retCode = 1;
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            retCode = -1;
        }  finally {
            em.close();
        }
        return retCode;
    }

    @Override
    public List<HotelInfo> searchAvailableHotel(HotelSearchCriteria sc) {
        CriteriaBuilder cb = getEm().getCriteriaBuilder();

        CriteriaQuery<HotelInfo> cq = cb.createQuery(HotelInfo.class);
        Root<HotelInfo> root = cq.from(HotelInfo.class);
        root.alias("h");

        List<Predicate> predicates = new ArrayList<Predicate>();

        Predicate predicate = cb.equal(root.get(HotelInfo_.cityId), sc.getCityId());
        predicates.add(predicate);

        if (sc.getDistrictId() > 0) {
            predicate = cb.equal(root.get(HotelInfo_.areaId), sc.getDistrictId());
            predicates.add(predicate);
        }

        if (sc.getStar() != null && sc.getStar().length() > 0) {
            Join<HotelInfo, HotelAward> hotelAward = root.join("hotelAwards", JoinType.LEFT);
            hotelAward.alias("ha");

            predicates.add(cb.equal(hotelAward.get("provider"),"HotelStarRate"));

            String[] stars = sc.getStar().split(",");
            Predicate p0 = cb.disjunction();
            for (String star:stars) {
                if (star.length() == 0) continue;
                int starLevel = Integer.parseInt(star);
                if (starLevel == 2)
                    p0 = cb.or(p0, cb.le(hotelAward.get(HotelAward_.rating), starLevel));
                else
                    p0 = cb.or(p0, cb.equal(hotelAward.get("rating"), starLevel));
            }
            predicates.add(p0);
        }

        if (sc.getZoneId() > 0) {
          Join<HotelInfo, HotelAddressZone> hotelZone = root.join(HotelInfo_.hotelAddressZones, JoinType.LEFT);
            hotelZone.alias("hz");

            predicate = cb.equal(hotelZone.get(HotelAddressZone_.zoneCode), sc.getZoneId());
            predicates.add(predicate);
        }

        // count items
        CriteriaQuery<Long> cq0 = cb.createQuery(Long.class);
        Root<HotelInfo> root0 = cq0.from(HotelInfo.class);
        root0.alias("h");
        if (sc.getStar() != null && sc.getStar().length() > 0 ) {
            Join<HotelInfo, HotelAward> hotelAward0 = root0.join("hotelAwards", JoinType.LEFT);
            hotelAward0.alias("ha");
        }
        if (sc.getZoneId() > 0) {
            Join<HotelInfo, HotelAddressZone> hotelZone0 = root0.join(HotelInfo_.hotelAddressZones, JoinType.LEFT);
            hotelZone0.alias("hz");
        }
        cq0.select(cb.count(root0)).where(predicates.toArray(new Predicate[0]));
        Long count = getEm().createQuery(cq0).getSingleResult();

        sc.getPage().setRowCount(count.intValue());


        int firstPosition = (sc.getPage().getPageNo()-1) * sc.getPage().getPageSize();
        cq.select(root).where(predicates.toArray(new Predicate[0]));

        List<HotelInfo> hotelInfos = getEm().createQuery(cq).
                setFirstResult(firstPosition).
                setMaxResults(sc.getPage().getPageSize())
                .getResultList();

        return  hotelInfos;
    }

    @Override
    public List<HotelRatePlan> searchAvailableHotelRatePlan(int hotelId, Date checkInDate, Date checkOutDate) {
        List<HotelRatePlan> ratePlans = getEm().createQuery("select o from HotelRatePlan o where o.hotelInfo.id = :hotelId")
                .setParameter("hotelId", hotelId)
                .getResultList();
        for(HotelRatePlan rp: ratePlans) {
            List<HotelRatePlanRate> rates = getEm().createQuery("select o from HotelRatePlanRate o where o.startDate >= :startDate " +
                    " and o.endDate <= :endDate " +
                    " and o.hotelRatePlan.id = :id")
                    .setParameter("id",rp.getId())
                    .setParameter("startDate", checkInDate)
                    .setParameter("endDate", checkOutDate)
                    .getResultList();

            rp.setHotelRatePlanRates(rates);

        }

        return ratePlans;
    }

    @Override
    public HotelGuestRoom getHotelRoomInfo(int hotelId, String roomTypeCode) {
        List<HotelGuestRoom> rooms = getEm().createQuery("select o from HotelGuestRoom o where o.hotelInfo.id = :hotelId and o.invBlockCode = :roomTypeCode")
                .setParameter("hotelId", hotelId)
                .setParameter("roomTypeCode", roomTypeCode)
                .getResultList();

        return  rooms.size() > 0 ? rooms.get(0) : null;
    }

    @Override
    public void clearAllCache() {
        getEm().clear();
    }

    @Override
    public int createDistrict(District district) {
        int retCode = 0;
        EntityManager em = createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(district);
            em.getTransaction().commit();

            retCode = 1;
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            retCode = -1;
        } finally {
            em.close();
        }
        return retCode;
    }

    @Override
    public int createZone(Zone zone) {
        int retCode = 0;
        EntityManager em = createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(zone);
            em.getTransaction().commit();

            retCode = 1;
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            retCode = -1;
        } finally {
            em.close();
        }
        return retCode;
    }

    @Override
    public List<District> getDistrictByCityId(int cityId) {
        return getEm().createQuery("select o from District o where o.cityId = :cityId")
                .setParameter("cityId", cityId)
                .getResultList();
    }

    @Override
    public List<Zone> getZoneByCityId(int cityId) {
        return getEm().createQuery("select o from Zone o where o.cityId = :cityId")
                .setParameter("cityId", cityId)
                .getResultList();
    }

    @Override
    public int createCacheHotelCacheChange(CacheHotelCacheChange cacheHotelCacheChange) {
        int retCode = 0;
        EntityManager em = createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(cacheHotelCacheChange);
            em.getTransaction().commit();
            return  1;
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            retCode = -1;
        } finally {
            em.close();
        }
        return 0;
    }
}
