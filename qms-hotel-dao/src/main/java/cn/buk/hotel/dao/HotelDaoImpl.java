/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.hotel.dao;

import cn.buk.hotel.entity.*;
import cn.buk.hotel.sc.HotelSearchCriteria;
import cn.buk.util.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HotelDaoImpl extends AbstractDao implements HotelDao {

    public static Logger logger = Logger.getLogger(HotelDaoImpl.class);


    @Override
    @Transactional
    public int createHotelInfo(HotelInfo hotelInfo) {
        int retCode = 0;
        EntityManager em = getEm();
        try {
            List<HotelInfo> hotelInfos = em.createQuery("select o from HotelInfo o where o.hotelCode = :hotelCode")
                    .setParameter("hotelCode", hotelInfo.getHotelCode())
                    .getResultList();
            if (hotelInfos.size() > 0) {
               retCode = 2;
            } else {
                em.persist(hotelInfo);
                retCode = 1;
            }
        } catch (Exception ex) {
            retCode = -1;
            logger.info("HotelCode: " + hotelInfo.getHotelCode() + ", HotelName: " + hotelInfo.getHotelName() + ": " + ex.getMessage());
        }
        return retCode;
    }

    @Override
    public HotelInfo getHotelInfoByHotelCode(String hotelCode) {

        HotelInfo hotelInfo = (HotelInfo) getEm().createQuery("select o from HotelInfo o where o.hotelCode = :hotelCode")
                .setParameter("hotelCode", hotelCode)
                .getSingleResult();


        return hotelInfo;
    }

    @Override
    //@Transactional
    public HotelInfo getHotelDetailInfoByHotelCode(String hotelCode) {
        HotelInfo hotelInfo = null;

        try {
            List<HotelInfo> hotelInfos = getEm().createQuery("select o from HotelInfo o   where o.hotelCode = :hotelCode ")
                    .setParameter("hotelCode", hotelCode)
                    .getResultList();
            if (hotelInfos.size() > 0) {
                hotelInfo = hotelInfos.get(0);

                hotelInfo.setHotelAddressZones(getEm().createQuery("select o from HotelAddressZone o where o.hotelInfo = :hotelInfo").setParameter("hotelInfo", hotelInfo).getResultList());


               // hotelInfo.getHotelServices().size();
                hotelInfo.setHotelServices(getEm().createQuery("select o from HotelServiceInfo o where o.hotelInfo = :hotelInfo").setParameter("hotelInfo", hotelInfo).getResultList());
                //hotelInfo.getHotelAwards().size();
                hotelInfo.setHotelAwards(getEm().createQuery("select o from HotelAward o where o.hotelInfo = :hotelInfo").setParameter("hotelInfo", hotelInfo).getResultList());
               // hotelInfo.getRelativePositions().size();
                hotelInfo.setRelativePositions(getEm().createQuery("select o from HotelRelativePosition o where o.hotelInfo = :hotelInfo").setParameter("hotelInfo", hotelInfo).getResultList());
                //hotelInfo.getGuestRooms().size();
                hotelInfo.setGuestRooms(getEm().createQuery("select o from HotelGuestRoom o where o.hotelInfo = :hotelInfo").setParameter("hotelInfo", hotelInfo).getResultList());
               // hotelInfo.getRefPoints().size();
                hotelInfo.setRefPoints(getEm().createQuery("select o from HotelRefPoint o where o.hotelInfo = :hotelInfo").setParameter("hotelInfo", hotelInfo).getResultList());
                //hotelInfo.getMedias().size();
                hotelInfo.setMedias(getEm().createQuery("select o from HotelMultimediaInfo o where o.hotelInfo = :hotelInfo").setParameter("hotelInfo", hotelInfo).getResultList());
            }
        } catch (PersistenceException e) {
            logger.error(e.getMessage());
        }

        return hotelInfo;
    }

    @Override
    @Transactional
    public int updateHotelInfo(HotelInfo hotelInfo) {
        int retCode = 0;
        EntityManager em = getEm();
        try {
            em.merge(hotelInfo);
            retCode = 1;
        } catch (Exception ex) {
            retCode = -1;
            logger.error("updateHotelInfo failed: (HotelCode: " + hotelInfo.getHotelCode() + ", HotelName: " + hotelInfo.getHotelName() + ")：" + ex.getMessage());
        }
        return retCode;
    }

    @Override
    @Transactional
    public int setHotelRatePlanStatusByHotelCode(String hotelCode, int ratePlanStatus) {
        int retCode = 0;

        retCode = getEm().createQuery("update HotelInfo o set o.ratePlanStatus = :ratePlanStatus where o.hotelCode = :hotelCode")
                .setParameter("ratePlanStatus", ratePlanStatus)
                .setParameter("hotelCode", hotelCode)
                .executeUpdate();

        return retCode;
    }

    @Override
    public List<HotelInfo> getAllHotelInfo() {
        List<HotelInfo> hotelInfos = new ArrayList<HotelInfo>();

        try {
            hotelInfos = getEm().createQuery("select o.hotelCode from HotelInfo o order by o.id").getResultList();;
        } catch (PersistenceException e) {
            logger.error(e.getMessage());
        }

        return  hotelInfos;
    }

    @Override
    public List<String> getAllHotelCodes() {
        List<String> hotelCodes = new ArrayList<String>();

        try {
            //body
            hotelCodes = getEm().createQuery("select o.hotelCode from HotelInfo o order by o.id").getResultList();
        } catch (PersistenceException e) {
            logger.error(e.getMessage());
        }

        return  hotelCodes;
    }

    @Override
    public List<String> getAllHotelCodesByCityId(int cityId) {
        List<String> hotelCodes = new ArrayList<String>();
        Date baseTime = DateUtil.getCurDateTime();

        try {
            //body
            hotelCodes = getEm().createQuery("select o.hotelCode from HotelInfo o where o.cityId = :cityId order by o.id")
                    .setParameter("cityId", cityId)
                    .getResultList();
        } catch (PersistenceException e) {
            logger.error(e.getMessage());
        }

        int span = DateUtil.getPastTime(baseTime);
        logger.info("get all hotel codes in city (" + cityId + ") elapsed: " + span + "ms.");

        return  hotelCodes;
    }

    @Override
    @Transactional
    public int createHotelRatePlan(HotelRatePlan hotelRatePlan) {
        int retCode = 0;
        EntityManager em = getEm();
        try {
            List<HotelRatePlan> ratePlans = em.createQuery("select o from HotelRatePlan o where o.ratePlanCode = :ratePlanCode and o.hotelInfo = :hotelInfo")
                    .setParameter("ratePlanCode", hotelRatePlan.getRatePlanCode())
                    .setParameter("hotelInfo", hotelRatePlan.getHotelInfo())
                    .getResultList();
            if (ratePlans.size() > 0) {
                retCode = 2;
                HotelRatePlan plan0 = ratePlans.get(0);
                //save rateplan_rate
                List<HotelRatePlanRate> rates0 = em.createQuery("select o from HotelRatePlanRate o where o.hotelRatePlan = :plan order by o.startDate")
                        .setParameter("plan", plan0)
                        .getResultList();
                for(HotelRatePlanRate rate0: rates0) {
                    for(HotelRatePlanRate rate: hotelRatePlan.getHotelRatePlanRates()) {
                        if (rate0.getStartDate().getTime() == rate.getStartDate().getTime()) {
                            em.remove(rate0);
                            break;
                        }
                    }
                }
                em.flush();
                for(HotelRatePlanRate rate: hotelRatePlan.getHotelRatePlanRates()) {
                    rate.setHotelRatePlan(plan0);
                    em.persist(rate);
                }
            } else {
                em.persist(hotelRatePlan);
                retCode = 1;
            }

        } catch (Exception ex) {
            retCode = -1;
            logger.error(ex.getMessage());
        }
        return retCode;
    }

    @Override
    public CacheCity getCacheCity(int cityId) {
        List<CacheCity> cacheCities = new ArrayList<CacheCity>();

        try {
            //body
            cacheCities = getEm().createQuery("select o from CacheCity o where o.cityId = :cityId")
                    .setParameter("cityId", cityId)
                    .getResultList();
        } catch (PersistenceException e) {
            logger.error(e.getMessage());
        }

        return cacheCities.size() > 0 ? cacheCities.get(0) : null;
    }

    @Override
    @Transactional
    public int createCacheCity(CacheCity cacheCity) {
        int retCode = 0;
        EntityManager em = getEm();
        try {
            em.persist(cacheCity);
                retCode = 1;
        } catch (Exception ex) {
            retCode = -1;
            logger.error(ex.getMessage());
        }
        return retCode;
    }

    @Override
    @Transactional
    public int updateCacheCity(CacheCity cacheCity) {
        int retCode = 0;
        EntityManager em = getEm();
        try {
            em.merge(cacheCity);
            retCode = 1;
        } catch (Exception ex) {
            retCode = -1;
        }
        return retCode;
    }

    @Override
    public CacheHotel getCacheHotel(String hotelCode) {
        List<CacheHotel> cacheHotels = new ArrayList<CacheHotel>();

        try {
            //body
            cacheHotels = getEm().createQuery("select o from CacheHotel o where o.hotelCode = :hotelCode")
                    .setParameter("hotelCode", hotelCode)
                    .getResultList();
        } catch (PersistenceException e) {
            logger.error(e.getMessage());
        }

        return cacheHotels.size() > 0 ? cacheHotels.get(0) : null;
    }

    @Override
    @Transactional
    public int createCacheHotel(CacheHotel cacheHotel) {
        int retCode = 0;
        EntityManager em = getEm();
        try {
            em.persist(cacheHotel);

            retCode = 1;
        } catch (Exception ex) {
            retCode = -1;
            logger.error(ex.getMessage());
        }
        return retCode;
    }

    @Override
    @Transactional
    public int updateCacheHotel(CacheHotel cacheHotel) {
        int retCode = 0;
        EntityManager em = getEm();
        try {
            em.merge(cacheHotel);
            retCode = 1;
        } catch (Exception ex) {
            retCode = -1;
            logger.error(ex.getMessage());
        }
        return retCode;
    }

    @Override
    public CacheRatePlan getCacheRatePlan(String hotelCode, int periodId) {
        CacheRatePlan cacheRatePlan = (CacheRatePlan) getEm().createQuery("select o from CacheRatePlan o where o.hotelCode = :hotelCode and o.periodId = :periodId")
                .setParameter("hotelCode", hotelCode)
                .setParameter("periodId", periodId)
                .getSingleResult();

        return cacheRatePlan;
    }

    @Override
    @Transactional
    public int createCacheRatePlan(CacheRatePlan cacheRatePlan) {
        int retCode = 0;
        EntityManager em = getEm();
        try {
            em.persist(cacheRatePlan);
            retCode = 1;
        } catch (Exception ex) {
            retCode = -1;
            logger.error(ex.getMessage());
        }
        return retCode;
    }

    @Override
    @Transactional
    public int updateCacheRatePlan(CacheRatePlan cacheRatePlan) {
        int retCode = 0;
        EntityManager em = getEm();
        try {
            em.merge(cacheRatePlan);

            retCode = 1;
        } catch (Exception ex) {
            retCode = -1;
            logger.error(ex.getMessage());
        }
        return retCode;
    }

    @Override
    public List<HotelInfo> searchAvailableHotel(HotelSearchCriteria sc) {

        List<HotelInfo> hotelInfos = null;


            try {
                //body
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

                hotelInfos = getEm().createQuery(cq).
                        setFirstResult(firstPosition).
                        setMaxResults(sc.getPage().getPageSize())
                        .getResultList();
            } catch (PersistenceException e) {
                logger.error(e.getMessage());
            }

        return  hotelInfos == null? new ArrayList<HotelInfo>() : hotelInfos;
    }

    @Override
    public List<HotelRatePlan> searchAvailableHotelRatePlan(int hotelId, Date checkInDate, Date checkOutDate) {
        List<HotelRatePlan> ratePlans=null;

        try {
            //body
            ratePlans = getEm().createQuery("select o from HotelRatePlan o where o.hotelInfo.id = :hotelId")
                    .setParameter("hotelId", hotelId)
                    .getResultList();
            for(HotelRatePlan rp: ratePlans) {
                List<HotelRatePlanRate> rates = getEm().createQuery("select o from HotelRatePlanRate o where o.startDate >= :startDate " +
                        " and o.endDate <= :endDate " +
                        " and o.hotelRatePlan.id = :id")
                        .setParameter("id", rp.getId())
                        .setParameter("startDate", checkInDate)
                        .setParameter("endDate", checkOutDate)
                        .getResultList();

                rp.setHotelRatePlanRates(rates);
            }
        } catch (PersistenceException e) {
            logger.error(e.getMessage());
        }

        return ratePlans;
    }

    @Override
    public HotelGuestRoom getHotelRoomInfo(int hotelId, String roomTypeCode) {
        List<HotelGuestRoom> rooms = null;

        try {
            //body
            rooms = getEm().createQuery("select o from HotelGuestRoom o where o.hotelInfo.id = :hotelId and o.roomTypeCode = :roomTypeCode")
                    .setParameter("hotelId", hotelId)
                    .setParameter("roomTypeCode", roomTypeCode)
                    .getResultList();
        } catch (PersistenceException e) {
            logger.error(e.getMessage());
        }

        return  rooms.size() > 0 ? rooms.get(0) : null;
    }

    @Override
    public void clearAllCache() {
        getEm().clear();
    }

    @Override
    @Transactional
    public int createDistrict(District district) {
        int retCode = 0;
        EntityManager em = getEm();
        try {
            em.persist(district);

            retCode = 1;
        } catch (Exception ex) {
            retCode = -1;
            logger.error(ex.getMessage());
        }
        return retCode;
    }

    @Override
    @Transactional
    public int createZone(Zone zone) {
        int retCode = 0;
        EntityManager em = getEm();
        try {
            em.persist(zone);
            retCode = 1;
        } catch (Exception ex) {
            retCode = -1;
            logger.error(ex.getMessage());
        }
        return retCode;
    }

    @Override
    public List<District> getDistrictByCityId(int cityId) {
        List<District> districts = null;

        try {
            //body
            districts = getEm().createQuery("select o from District o where o.cityId = :cityId")
                    .setParameter("cityId", cityId)
                    .getResultList();
        } catch (PersistenceException e) {
            logger.error(e.getMessage());
        }

        return districts;
    }

    @Override
    public List<Zone> getZoneByCityId(int cityId) {
        List<Zone> zones = null;

        try {
            //body
            zones = getEm().createQuery("select o from Zone o where o.cityId = :cityId")
                    .setParameter("cityId", cityId)
                    .getResultList();
        } catch (PersistenceException e) {
            logger.error(e.getMessage());
        }

        return zones;
    }

    @Override
    @Transactional
    public int createCacheHotelCacheChange(CacheHotelCacheChange cacheHotelCacheChange) {
        int retCode = 0;
        EntityManager em = getEm();
        try {
            em.persist(cacheHotelCacheChange);
            return  1;
        } catch (Exception ex) {
            retCode = -1;
            logger.error(ex.getMessage());
        }
        return 0;
    }

    @Override
    public List<String> getAllHotelCodes2() {
        List<String> hotelCodes = new ArrayList<String>();

        try {
            //body
            Date date = DateUtil.getCurDate();
            date = DateUtil.add(date, -7);

            hotelCodes = getEm().createQuery("select h.hotelCode from HotelInfo h where h.hotelCode not in (select o.hotelCode from CacheHotel o where o.cacheTime > :date)")
                    .setParameter("date", date)
                    .getResultList();
        } catch (PersistenceException e) {
            logger.error(e.getMessage());
        }

        return hotelCodes;
    }

    @Override
    public List<String> getHotelCodes4RatePlan(int periodId) {
        List<String> hotelCodes = new ArrayList<String>();

        try {
            //body
            Date date = DateUtil.getCurDate();
            date = DateUtil.add(date, -7);

            logger.info("[getHotelCodes4RatePlan] " + DateUtil.formatDate(date, "yyyy-MM-dd") + "," + periodId);

            Date baseTime = DateUtil.getCurDateTime();
            hotelCodes = getEm().createQuery("select h.hotelCode from HotelInfo h where h.ratePlanStatus != -1 and h.hotelCode not in (select o.hotelCode from CacheRatePlan o where o.cacheTime > :date and o.periodId = :periodId) order by h.id")
                    .setParameter("date", date)
                    .setParameter("periodId", periodId)
                    .setMaxResults(5000)
                    .getResultList();
            int span = DateUtil.getPastTime(baseTime);
            logger.info("[getHotelCodes4RatePlan] elapsed time: " + span + " ms, size: " + hotelCodes.size() + ", period id: " + periodId);
        } catch (PersistenceException e) {
            logger.error(e.getMessage());
        }

        return hotelCodes;
    }

    @Override
    @Transactional
    public void deleteExpiredRate() {
        Date date = DateUtil.getCurDate();
        date = DateUtil.add(date, -1);

        int count = getEm().createQuery("delete from HotelRatePlanRate r where r.endDate <= :endDate")
                .setParameter("endDate", date)
                .executeUpdate();

        logger.info("deleted " + count + " expired rates.");
    }

    @Override
    @Transactional
    public int setCacheRatePlanDone(String hotelCode, int periodId) {
        int retCode = 0;

        retCode = getEm().createQuery("update CacheRatePlan o set o.cacheTime = :cacheTime, o.endTime = :endTime where o.hotelCode = :hotelCode and o.periodId = :periodId")
                .setParameter("hotelCode", hotelCode)
                .setParameter("periodId", periodId)
                .setParameter("cacheTime", DateUtil.getCurDate())
                .setParameter("endTime", DateUtil.getCurDateTime())
                .executeUpdate();

        return retCode;
    }
}
