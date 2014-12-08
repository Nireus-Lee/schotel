/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.service;

import cn.buk.api.dto.hotel.*;
import cn.buk.api.dto.hotel.DateRange;
import cn.buk.api.dto.hotel.HotelInfo;
import cn.buk.api.dto.hotel.HotelRatePlan;
import cn.buk.api.util.ConvertUtil;
import cn.buk.hotel.dao.CityDao;
import cn.buk.hotel.dao.HotelDao;
import cn.buk.hotel.dto.DocumentDto;
import cn.buk.hotel.entity.*;
import cn.buk.hotel.entity.Zone;
import cn.buk.qms.AppConfig;
import cn.buk.qms.util.PropertiesUtil;
import cn.buk.util.DateUtil;
import com.ctrip.openapi.java.base.HttpAccessAdapter;
import com.ctrip.openapi.java.utils.ConfigData;
import com.ctrip.openapi.java.utils.SignatureUtils;
import com.thoughtworks.xstream.XStream;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import org.apache.log4j.Logger;
import org.dom4j.*;
import org.dom4j.io.DocumentResult;
import org.dom4j.io.SAXReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.StringReader;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * User: yfdai
 * Date: 14-9-18
 * Time: 下午2:41
 */

public class CtripOpenApiServiceImpl implements CtripOpenApiService {

    private static final Logger logger = Logger.getLogger(CtripOpenApiServiceImpl.class);
    // --Commented out by Inspection (2014-12-8 22:38):private static int cacheHits;
    // --Commented out by Inspection (2014-12-8 22:39):private static int cacheMisses;

    private String allianceId;
    private String sid;
    private String secretKey;

    /**
     * 多长时间检查一次hotelCacheChange,单位（分钟);
     */
    private int intervalCacheChange;

    // 声明一个容量为50的缓存队列
    private final BlockingQueue<DocumentDto> ratePlanQueue = new LinkedBlockingQueue<DocumentDto>(50);

    private final BlockingQueue<DocumentDto> hotelCacheChangeQueue = new LinkedBlockingQueue<DocumentDto>(200);

    private final BlockingQueue<HotelRatePlanRequestDto> ratePlanRequestQueue = new LinkedBlockingQueue<HotelRatePlanRequestDto>(200);

    private static final CacheManager cacheManager = CacheManager.getInstance();

    private CityDao cityDao;
    private HotelDao hotelDao;

    private static Cache getCache() {
        Cache cache = cacheManager.getCache("openApiCache");
        if (cache == null) {
            cacheManager.addCache("openApiCache");
            cache = cacheManager.getCache("openApiCache");
        }

        return cache;
    }

    private static String execApiRequest(String requestContent, String urlString, String paraName) {
        Proxy proxy = null;
        String proxyUsed = (String)PropertiesUtil.pps.get(AppConfig.PROXY_USED);
        if (proxyUsed.equalsIgnoreCase("1")){
            String proxyHost = (String)PropertiesUtil.pps.get(AppConfig.PROXY_HOST);
            String proxyPort = (String)PropertiesUtil.pps.get(AppConfig.PROXY_PORT);
            int port = Integer.parseInt(proxyPort);
            SocketAddress address = new InetSocketAddress(proxyHost, port);

            proxy = new Proxy(Proxy.Type.HTTP, address);
        }

        return HttpAccessAdapter.SendRequestToUrl(requestContent, urlString, paraName, proxy);
    }

    private Element createRequestHeaderElement(Document document, String requestType) {
        Element returnElement = document.addElement("Request");
        Element element = returnElement.addElement("Header");
        element.addAttribute("AllianceID", this.allianceId);
        element.addAttribute("SID",this.sid);
        long timestamp = SignatureUtils.GetTimeStamp();
        String signature = null;
        try {
            signature = SignatureUtils.CalculationSignature(timestamp + "", this.allianceId, this.secretKey, this.sid, requestType);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        element.addAttribute("TimeStamp",timestamp+"");
        element.addAttribute("Signature",signature);
        element.addAttribute("RequestType", requestType);

        return returnElement;
    }

    @Override
    public String importCityInfo(String filename) {
        SAXReader reader = new SAXReader();

        File file = new File(filename);
        if (!file.exists()) return "not exist.";
        Document document;// 读取XML文件
        try {
            document = reader.read(file);
        } catch (DocumentException e) {
            e.printStackTrace();
            return "error";
        }

        List flightDataList = document.selectNodes("//CityDetails/*");
        List<City> cities = new ArrayList<City>();
        String temp;
        for (Iterator it = flightDataList.iterator(); it.hasNext(); ) {
            Element fltElement = (Element) it.next();

            City city = new City();
            cities.add(city);

            city.setCityCode(fltElement.element("CityCode").getText());
            city.setOpenApiId(Integer.parseInt(fltElement.element("City").getText()));
            city.setCityName(fltElement.element("CityName").getText());
            city.setCityEnglishName(fltElement.elementText("CityEName").trim());
            temp = fltElement.elementText("Country").trim();
            if (temp.equalsIgnoreCase("1"))
                city.setCountryCode("CN");
            else
                city.setCountryCode(temp);
            city.setProvinceId(Integer.parseInt(fltElement.elementText("Province")));
            city.setAirport(fltElement.element("Airport").getText());
        }

        int count = 0;
        for (City city : cities) {
            if (city.getCityCode()==null || city.getCityCode().trim().length()==0) continue;
            if (cityDao.create(city) == 1)
                count++;
        }

        return "create " + count + " record(s).";
    }

    @Override
    public String importDistrictInfo(String filename) {
        SAXReader reader = new SAXReader();

        File file = new File(filename);
        if (!file.exists()) return "not exist.";
        Document document;// 读取XML文件
        try {
            document = reader.read(file);
        } catch (DocumentException e) {
            e.printStackTrace();
            return "error";
        }

        List list = document.selectNodes("//LocationDetails/*");
        List<District> districts = new ArrayList<District>();
        for (Iterator it = list.iterator(); it.hasNext(); ) {
            Element element = (Element) it.next();

            District district = new District();
            districts.add(district);

            district.setName(element.element("LocationName").getText());
            district.setEnglishName(element.element("LocationEName").getText());
            district.setDistrictId(Integer.parseInt(element.element("Location").getText()));
            district.setCityId(Integer.parseInt(element.element("LocationCity").getText()));
        }

        int count = 0;
        for (District district : districts) {
            if (hotelDao.createDistrict(district) == 1)
                count++;
        }

        return "create " + count + " record(s).";
    }

    @Override
    public String importZoneInfo(String filename) {
        SAXReader reader = new SAXReader();

        File file = new File(filename);
        if (!file.exists()) return "not exist.";
        Document document;// 读取XML文件
        try {
            document = reader.read(file);
        } catch (DocumentException e) {
            e.printStackTrace();
            return "error";
        }

        List list = document.selectNodes("//ZoneDetails/*");
        List<Zone> zones = new ArrayList<Zone>();
        int maxLength = 0;
        for (Iterator it = list.iterator(); it.hasNext(); ) {
            Element element = (Element) it.next();

            Zone zone = new Zone();
            zones.add(zone);

            zone.setZoneId(Integer.parseInt(element.element("Zone").getText()));
            zone.setCityId(Integer.parseInt(element.element("City").getText()));
            zone.setName(element.element("ZoneName").getText());
            zone.setEnglishName(element.element("ZoneEName").getText());
            zone.setDescription(element.element("ZoneDesc").getText());
            if (zone.getDescription().length() > maxLength) maxLength = zone.getDescription().length();
            zone.setMapUse(element.element("ZoneMapuse").getText());
            zone.setMapRange(element.element("ZoneRange").getText());
            zone.setMapPic(element.element("ZoneMapPic").getText());
        }

        int count = 0;
        for (Zone zone : zones) {
            if (hotelDao.createZone(zone) == 1)
                count++;
        }
        System.out.println(maxLength);

        return "create " + count + " record(s).";
    }

    /**
     * 搜索指定城市的酒店信息
     * @param cityId 城市编号
     * @return 搜索的结果状态
     */
    @Override
    public String searchHotel(int cityId) {
        if (cityId <= 0) return "ER#CITYID IS " + cityId;

        //检查缓存，看看header节点中的API调用频率限制
        Cache cache = getCache();
        String cacheKey = ConfigData.OTA_HotelSearch_Request;
        net.sf.ehcache.Element cacheElement = cache.get(cacheKey);
        if (cacheElement != null) {
            Element headerElement = (Element)cacheElement.getValue();

            int accessCount = Integer.parseInt(headerElement.attributeValue("AccessCount"));
            int currentCount = Integer.parseInt(headerElement.attributeValue("CurrentCount")) + 1;
            logger.info("AccessCount=" + headerElement.attributeValue("AccessCount")
                    + ", CurrentCount=" + headerElement.attributeValue("CurrentCount")
                    //+ ", RecentlyTime=" + headerElement.attributeValue("RecentlyTime")
                    + ", ResetTime=" + headerElement.attributeValue("ResetTime"));
            if (currentCount >= accessCount) {
                try {
                    logger.info("Sleep for one minute.");
                    Thread.sleep(60000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }

        HotelRequestBody request = new HotelRequestBody();
        request.createHotelRequestRQ();
        request.getHotelRequestRQ().getCriteria().getCriterion().getHotelRef().setHotelCityCode(cityId);
        String xml;

        try {
            JAXBContext jc = JAXBContext.newInstance(HotelRequestBody.class);

            Marshaller m = jc.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.setProperty(Marshaller.JAXB_ENCODING, "utf-8");
            m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

            DocumentResult documentResult = new DocumentResult();
            m.marshal(request, documentResult);

            org.dom4j.Document document = documentResult.getDocument();
            org.dom4j.Element requestElement = document.getRootElement();
            Element ele = requestElement.element("OTA_HotelSearchRQ");
            ele.addNamespace("", "http://www.opentravel.org/OTA/2003/05");

            org.dom4j.Document doc1 = DocumentHelper.createDocument();

            org.dom4j.Element rootElement = createRequestHeaderElement(doc1, ConfigData.OTA_HotelSearch_Request);
            rootElement.add(requestElement);

            xml = doc1.asXML();
        } catch (JAXBException ex) {
            logger.error(ex.getMessage());
            return "ER";
        }

        String response = execApiRequest(xml, ConfigData.OTA_HotelSearch_Url, "requestXML");
        //处理结果
        SAXReader reader = new SAXReader();

        Document document;// 读取XML文件
        try {
            document = reader.read(new StringReader(response));
            response = processHotelSearchResponse(document);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return "ER";
        }

        return response;
    }



    private String processHotelSearchResponse(Document document) {
        if (document == null) return "ER#Document is null.";

        Element rootElement = document.getRootElement();
        Element headerElement = rootElement.element("Header");
        if (!headerElement.attribute("ResultCode").getValue().equalsIgnoreCase("Success")) {
            //logger.info("Header::ResultCode: " + headerElement.attribute("ResultCode").getValue());
            return "ER#ResultCode is not Success.";
        }

        //放置到缓存，用于记录header中的参数
        getCache().put(new net.sf.ehcache.Element(ConfigData.OTA_HotelSearch_Request, headerElement));

        List myNodes = document.selectNodes("/Response/HotelResponse/*");
        Element eleOtaHotelSearchRS = (Element) myNodes.get(0);

        XStream xs = new XStream();
        xs.alias("Response", HotelSearchResponse.class);
        xs.processAnnotations(HotelSearchResponse.class);

        Date date0 = DateUtil.getCurDateTime();
        String xml =  eleOtaHotelSearchRS.asXML();
        HotelSearchResponse response = (HotelSearchResponse)xs.fromXML(xml);
        int spanMilliSeconds = DateUtil.getPastTime(date0);
        logger.info("xml -> object: " + spanMilliSeconds + "ms.");
        int total = response.getHotelInfos().size();
        if (total == 0) {
            //logger.info(xml);
            if (response.getSuccess() != null) {
                logger.info("There is no hotel.");
                return "OK#0 hotel";
            }
        }


        // return response;
        String rs;
        int saveCount = 0;
        int count = 0;
        for(HotelInfo hotelInfo: response.getHotelInfos()) {
            cn.buk.hotel.entity.HotelInfo hotelInfo1 = ConvertUtil.convertHotelInfo(hotelInfo);
            int retCode = hotelDao.createHotelInfo(hotelInfo1);

            if ( retCode == 1 || retCode == 2) {
                saveCount++;
            }
            //else if (retCode == 2)
            //    logger.info("HotelCode: " + hotelInfo1.getHotelCode() + ", HotelName: " + hotelInfo1.getHotelName() + " is existed.");

            count++;
            if (count % 100 == 0) {
                logger.info("Progress: " + count + " / " + total);
            }
        }

       // rs += saveCount + " be saved.";
        if (saveCount > 0)
            rs = "OK#" + saveCount + " be saved";
        else
            rs = "ER";


        return rs;
    }


    private String processHotelDetailResponse(Document document) {
        if (document == null) return "ER#Document is null.";

        Element rootElement = document.getRootElement();
        Element headerElement = rootElement.element("Header");
        if (!headerElement.attribute("ResultCode").getValue().equalsIgnoreCase("Success")) {
            return "ER#ResultCode is not Success.";
        }

        //放置到缓存，用于记录header中的参数
        getCache().put(new net.sf.ehcache.Element(ConfigData.OTA_HotelDetail_Request, headerElement));

        //此处需要在指定的命名空间里面才能找到
        Map uris = new HashMap();
        uris.put("ns", "http://www.opentravel.org/OTA/2003/05");
        XPath xpath = document.createXPath("//ns:HotelDescriptiveContents/*");
        xpath.setNamespaceURIs(uris);
        List myNodes = xpath.selectNodes(document);

        String rs = "HAHA";

        if (myNodes.size()==0) {
            return "ER#There is nothing.";
        }

        XStream xs = new XStream();
        xs.alias("HotelDescriptiveContent", HotelDescriptiveContent.class);
        xs.processAnnotations(HotelDescriptiveContent.class);

        String xml;

        cn.buk.hotel.entity.HotelInfo hotelInfo1;
        String hotelCode, hotelName;
        for (Iterator it = myNodes.iterator(); it.hasNext(); ) {
            Element element = (Element) it.next();

            xml =  element.asXML();
            HotelDescriptiveContent response = (HotelDescriptiveContent)xs.fromXML(xml);

            hotelCode = response.getHotelCode();
            hotelName = response.getHotelName();
            hotelInfo1 = hotelDao.getHotelDetailInfoByHotelCode(hotelCode);

            if (hotelInfo1 == null) {
                rs = "ER#Not found hotelCode(" + hotelCode + ")";
                return  rs;
            } else {
                try {
                    if (response.getHotelInfo().getWhenBuilt() != null)
                        hotelInfo1.setBuildDate(DateUtil.convertToDate(response.getHotelInfo().getWhenBuilt()));
                } catch (Exception e) {
                    logger.error("WhenBuild: " + response.getHotelInfo().getWhenBuilt());
                    logger.error(e.getMessage());
                    return "ER#WhenBuild: " + response.getHotelInfo().getWhenBuilt();
                }

                if (response.getHotelInfo().getSegmentCategories() != null && response.getHotelInfo().getSegmentCategories().size()>0) {
                    int consumerLevel = Integer.parseInt(response.getHotelInfo().getSegmentCategories().get(0).getCode());
                    hotelInfo1.setConsumerLevel(consumerLevel);
                    if (response.getHotelInfo().getSegmentCategories().size()>1) {
                        logger.warn(hotelCode + "," + hotelName + ": SegmentCategory\'s size is " + response.getHotelInfo().getSegmentCategories().size());
                    }
                }

                if (response.getHotelInfo().getAddress().getZones()!=null) {
                    if (hotelInfo1.getHotelAddressZones() == null)
                        hotelInfo1.setHotelAddressZones(new ArrayList<HotelAddressZone>());
                    for(cn.buk.api.dto.hotel.Zone zone: response.getHotelInfo().getAddress().getZones()) {
                        if (zone.getZoneCode() > 0) {
                            HotelAddressZone zone1 = new HotelAddressZone();
                            zone1.setHotelInfo(hotelInfo1);

                            zone1.setZoneCode(zone.getZoneCode());
                            zone1.setZoneName(zone.getZoneName());

                            hotelInfo1.addAddressZone(zone1);
                        }
                    }
                }

                //SERVICE
                if (response.getHotelInfo().getHotelInfoServices() != null) {
                    if (hotelInfo1.getHotelServices() == null) hotelInfo1.setHotelServices(new ArrayList<HotelServiceInfo>());
                    for(HotelInfoService service: response.getHotelInfo().getHotelInfoServices()) {
                        HotelServiceInfo info = new HotelServiceInfo();
                        info.setHotelInfo(hotelInfo1);

                        info.setServiceCode(service.getCode());
                        info.setServiceId(service.getId());
                        info.setServiceDesc(service.getDescriptiveText());

                        hotelInfo1.addHotelService(info);
                    }
                }
                //GuestRoom
                if (response.getFacilityInfo().getGuestRooms() != null) {
                    if (hotelInfo1.getGuestRooms() == null) hotelInfo1.setGuestRooms(new ArrayList<HotelGuestRoom>());
                    for(GuestRoom guestRoom: response.getFacilityInfo().getGuestRooms()) {
                        HotelGuestRoom info = new HotelGuestRoom();
                        info.setHotelInfo(hotelInfo1);
                        //hotelInfo1.getGuestRooms().add(info);

                        info.setRoomTypeName(guestRoom.getRoomTypeName());
                        RoomType roomType = guestRoom.getRoomType();
                        info.setRoomTypeCode(roomType.getRoomTypeCod());
                        info.setStandardOccupancy(roomType.getStandardOccupancy());
                        info.setSize(roomType.getSize());
                        info.setFloor(roomType.getFloor());
                        info.setInvBlockCode(roomType.getInvBlockCode());
                        info.setBedTypeCode(roomType.getBedTypeCode());
                        if (roomType.isNonSmoking())
                            info.setNonSmoking(1);
                        else
                            info.setNonSmoking(0);
                        info.setHasWindow(roomType.getHasWindow());
                        info.setQuantity(roomType.getQuantity());
                        info.setRoomSize(roomType.getRoomSize());

                        if (guestRoom.getAmenities() != null) {
                            for(Amenity amenity: guestRoom.getAmenities()) {
                                HotelGuestRoomAmenity amenity1 = new HotelGuestRoomAmenity();
                                amenity1.setHotelGuestRoom(info);
                                if (info.getHotelGuestRoomAmenities()==null) info.setHotelGuestRoomAmenities(new ArrayList<HotelGuestRoomAmenity>());
                                info.getHotelGuestRoomAmenities().add(amenity1);

                                amenity1.setCode(amenity.getRoomAmenityCode());
                                amenity1.setDescription(amenity.getDescriptiveText());
                            }
                        }
                        hotelInfo1.addGuestRoom(info);
                    }
                }

                //ref points begin
                if (response.getAreaInfo()!= null && response.getAreaInfo().getRefPoints() != null) {
                    if (hotelInfo1.getRefPoints()==null) hotelInfo1.setRefPoints(new ArrayList<HotelRefPoint>());
                    for(RefPoint point: response.getAreaInfo().getRefPoints()) {
                        HotelRefPoint point1 = new HotelRefPoint();
                        point1.setHotelInfo(hotelInfo1);
                        hotelInfo1.getRefPoints().add(point1);

                        point1.setDistance(point.getDistance());
                        point1.setUnitOfMeasureCode(point.getUnitOfMeasureCode());
                        point1.setName(point.getName());
                        point1.setRefPointCategoryCode(Integer.parseInt(point.getRefPointCategoryCode()));
                        point1.setRefPointName(point.getRefPointName());
                        point1.setLatitude(point.getLatitude());
                        point1.setLongitude(point.getLongitude());
                        point1.setDescription(point.getDescriptiveText());
                    }
                }
                //ref points end

                //MultimediaDescriptions begin
                if (response.getMultimediaDescriptions() != null) {
                    if (hotelInfo1.getMedias()==null) hotelInfo1.setMedias(new ArrayList<HotelMultimediaInfo>());
                    for(MultimediaDescription md: response.getMultimediaDescriptions()) {
                        if (md.getImageItems() != null) {
                            for(ImageItem item: md.getImageItems()) {
                                HotelMultimediaInfo info = new HotelMultimediaInfo();
                                info.setHotelInfo(hotelInfo1);
                                hotelInfo1.getMedias().add(info);

                                info.setMediaType("image");
                                info.setCategory(Integer.parseInt(item.getCategory()));
                                info.setCaption(item.getImageDescription().getCaption());
                                info.setUrl(item.getImageFormat().getUrl());
                            }
                        }
                        if (md.getTextItems() != null) {
                            for(TextItem item: md.getTextItems()) {
                                HotelMultimediaInfo info = new HotelMultimediaInfo();
                                info.setHotelInfo(hotelInfo1);
                                hotelInfo1.getMedias().add(info);

                                info.setMediaType("text");
                                info.setCategory(Integer.parseInt(item.getCategory()));
                                info.setUrl(item.getUrl());
                                info.setDescription(item.getDescription());
                            }
                        }
                    }
                }


                //save
                int retCode = hotelDao.updateHotelInfo(hotelInfo1);


                if (retCode == 1) {
                    rs = "OK#Save OK";
                    //记录cache
                    CacheHotel cacheHotel = hotelDao.getCacheHotel(hotelCode);

                    if (cacheHotel == null) {
                        cacheHotel = new CacheHotel();
                        cacheHotel.setHotelCode(hotelCode);

                        hotelDao.createCacheHotel(cacheHotel);
                    } else {
                        cacheHotel.setCacheTime(DateUtil.getCurDateTime());
                        hotelDao.updateCacheHotel(cacheHotel);
                    }
                }  else {
                    logger.info(xml);
                    rs = "ER#Save Status is " + retCode + " for hotelCode[" + hotelCode + "]";
                    return rs;
                }
            }
        }  //end for

        return rs;
    }

    private void executeSaveRatePlanDocument(final Document document, final int periodId) {
        //此处需要在指定的命名空间里面才能找到
        Map uris = new HashMap();
        uris.put("ns", "http://www.opentravel.org/OTA/2003/05");
        XPath xpath = document.createXPath("//ns:OTA_HotelRatePlanRS/*");
        xpath.setNamespaceURIs(uris);
        List myNodes = xpath.selectNodes(document);

        if (myNodes.size() == 0) {
            logger.warn("myNodes.size() = 0");
            return;
        }

        Date baseTime0 = DateUtil.getCurDateTime();
        for (Iterator it = myNodes.iterator(); it.hasNext(); ) {
            Element element = (Element) it.next();
            if (!element.getName().equalsIgnoreCase("RatePlans")) continue;

            XStream xs = new XStream();
            xs.alias("RatePlans", HotelRatePlans.class);
            xs.processAnnotations(HotelRatePlans.class);

            String xml = element.asXML();
            HotelRatePlans response = (HotelRatePlans) xs.fromXML(xml);

            String hotelCode = response.getHotelCode();
            cn.buk.hotel.entity.HotelInfo hotelInfo1;

            if (response.getHotelRatePlans() == null || response.getHotelRatePlans().size() == 0) {
                if (periodId == 1) {
                    hotelDao.setHotelRatePlanStatusByHotelCode(hotelCode, -1);
                }
                logger.info("OK#Theres is no rates for {" + hotelCode + "," + periodId + "}.");
            } else {
                cn.buk.hotel.entity.HotelRatePlan hotelRatePlan;
                hotelInfo1 = hotelDao.getHotelInfoByHotelCode(hotelCode);
                for (HotelRatePlan dtoRatePlan : response.getHotelRatePlans()) {
                    try {
                        hotelRatePlan = ConvertUtil.convertHotelRatePlan(dtoRatePlan);
                        hotelRatePlan.setHotelInfo(hotelInfo1);
                        int retCode;

                        retCode = hotelDao.createHotelRatePlan(hotelRatePlan);
                        if (retCode != 1 && retCode != 2) {
                            logger.info(xml);
                            return;
                        }
                    } catch (Exception ex) {
                        logger.error(ex.getMessage());
                        return;
                    }
                }  // end for

                hotelDao.setHotelRatePlanStatusByHotelCode(hotelCode, 1);
            }

            //记录cache
            if (periodId > 0)
                hotelDao.setCacheRatePlanDone(hotelCode, periodId);
        }
        int spanTotal = DateUtil.getPastTime(baseTime0);
        logger.info(String.format("total time : %d ms", spanTotal));
    }


    /**
     * 处理HotelRatePlanResponse的结果
     * @param document 要处理的文档
     * @return 处理结果状态
     */
    private String processHotelRatePlanResponse(final Document document, final int periodId) {
        if (document == null) return "ER#Document is null.";

        Element rootElement = document.getRootElement();
        Element headerElement = rootElement.element("Header");
        if (!headerElement.attribute("ResultCode").getValue().equalsIgnoreCase("Success")) {
            logger.error("ER#ResultCode is not Success.");
            logger.error(document.asXML());
            return "ER#ResultCode is not Success.";
        }

        DocumentDto documentDto = new DocumentDto();
        documentDto.setDocument(document);
        documentDto.setPeriodId(periodId);

        try {
            logger.info("Current ratePlanQueue\'s size: " + ratePlanQueue.size());
            ratePlanQueue.put(documentDto);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "OK#Document has been added to Queue.";
    }


    private String processHotelCacheChangeResponse(final Document document) {
        if (document == null) return "ER#Document is null.";

        Element rootElement = document.getRootElement();
        Element headerElement = rootElement.element("Header");
        if (!headerElement.attribute("ResultCode").getValue().equalsIgnoreCase("Success")) {
            return "ER#ResultCode is not Success.";
        }

        //放置到缓存，用于记录header中的参数
        getCache().put(new net.sf.ehcache.Element(ConfigData.OTA_HotelCacheChange_Request, headerElement));

        DocumentDto documentDto = new DocumentDto();
        documentDto.setDocument(document);
        try {
            logger.debug("Current hotelCacheChangeQueue\'s size is " + hotelCacheChangeQueue.size());
            hotelCacheChangeQueue.put(documentDto);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        return "OK#HotelCacheChange document has been added into queue.";
    }
    @Override
    public String importHotelSearchRS(String filename) {
        String rs;
        SAXReader reader = new SAXReader();

               logger.info("filename: " + filename);

        File file = new File(filename);
        if (!file.exists()) return null;

        Document document;// 读取XML文件
        try {
            document = reader.read(file);
        } catch (DocumentException e) {
            e.printStackTrace();
            return "error";
        }

        //rs = "xml encoding: " + document.getXMLEncoding();
       // System.out.println(rs);

        rs = processHotelSearchResponse(document);

        return rs;
    }

    @Override
    public String searchHotelDetail(List<String> hotelCodes) {
        if (hotelCodes == null || hotelCodes.size()==0)
            return "ER#hotelcodes is null";

        //检查缓存，看看header节点中的API调用频率限制
        net.sf.ehcache.Element cacheElement = getCache().get(ConfigData.OTA_HotelDetail_Request);
        if (cacheElement != null) {
            Element headerElement = (Element)cacheElement.getValue();
            int accessCount = Integer.parseInt(headerElement.attributeValue("AccessCount"));
            int currentCount = Integer.parseInt(headerElement.attributeValue("CurrentCount"));
            logger.info(ConfigData.OTA_HotelDetail_Request + "AccessCount=" + headerElement.attributeValue("AccessCount")
                    + ", CurrentCount=" + headerElement.attributeValue("CurrentCount")
                    //+ ", RecentlyTime=" + headerElement.attributeValue("RecentlyTime")
                    + ", ResetTime=" + headerElement.attributeValue("ResetTime"));
            if (currentCount >= accessCount) {
                try {
                    logger.info("Sleep for one minute.");
                    Thread.sleep(60000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }

        HotelRequestBody request = new HotelRequestBody();
        request.createHotelDetailRequest();
        for(String hotelCode: hotelCodes) {
            HotelDescriptiveInfo hotelDescriptiveInfo = new HotelDescriptiveInfo();
            hotelDescriptiveInfo.setHotelCode(hotelCode);
            request.getHotelDetailRequest().getHotelDescriptiveInfos().add(hotelDescriptiveInfo);
        }

        String xml;

        try {
            JAXBContext jc = JAXBContext.newInstance(HotelRequestBody.class);

            Marshaller m = jc.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.setProperty(Marshaller.JAXB_ENCODING, "utf-8");
            m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

            DocumentResult documentResult = new DocumentResult();
            m.marshal(request, documentResult);

            org.dom4j.Document document = documentResult.getDocument();
            org.dom4j.Element requestElement = document.getRootElement();

            Element ele = requestElement.element("OTA_HotelDescriptiveInfoRQ");
            ele.addNamespace("", "http://www.opentravel.org/OTA/2003/05");

            org.dom4j.Document doc1 = DocumentHelper.createDocument();

            org.dom4j.Element rootElement = createRequestHeaderElement(doc1, ConfigData.OTA_HotelDetail_Request);
            rootElement.add(requestElement);

            xml = doc1.asXML();
        } catch (JAXBException e) {
            e.printStackTrace();
            return "ER#OTA_exception";
        }

        //logger.info(xml);
        Date date0 = DateUtil.getCurDateTime();
        String response = execApiRequest(xml, ConfigData.OTA_HotelDetail_Url, "requestXML");
        int apiElapsedTime = DateUtil.getPastTime(date0);
        logger.info(ConfigData.OTA_HotelDetail_Request + ": api耗时 " + apiElapsedTime + "ms");

        //处理结果
        String rs;
        SAXReader reader = new SAXReader();
        Document document;// 读取XML文件
        try {
            document = reader.read(new StringReader(response));
        } catch (DocumentException e) {
            logger.error(e.getMessage());
            return "ER#xml->document";
        }

        try {
            rs = processHotelDetailResponse(document);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            //logger.info(response);
            return "ER#processHotelDetailResponse";
        }

        int total = DateUtil.getPastTime(date0);
        logger.info(ConfigData.OTA_HotelDetail_Request + ": total " + total + "ms");

        return rs;
    }

    @Override
    public String importHotelDetailResponse(String filename) {
        String rs;
        SAXReader reader = new SAXReader();

        logger.info("filename: " + filename);

        File file = new File(filename);
        if (!file.exists()) {
            rs = "ER#File is not existed.";
            return rs;
        }

        Document document;// 读取XML文件
        try {
            document = reader.read(file);
        } catch (DocumentException e) {
            e.printStackTrace();
            return "ER#Read file error";
        }

        rs = processHotelDetailResponse(document);

        return rs;
    }

    @Override
    public String searchHotelRatePlan(List<String> hotelCodes, final int periodId, boolean returnXml) {
        if (hotelCodes == null || hotelCodes.size()==0)
            return "ER#hotelcodes is null or empty.";

        int minDay = (periodId - 1) * 28;
        int maxDay = periodId * 28;
        if (maxDay > 90) maxDay = 90;
        Date startDate = DateUtil.add(DateUtil.getCurDate(), minDay);
        Date endDate = DateUtil.add(DateUtil.getCurDate(), maxDay);

        String response =  execSearchHotelRatePlan(hotelCodes, startDate, endDate);

        //处理结果
        String rs;
        SAXReader reader = new SAXReader();
        Document document;// 读取XML文件
        try {
            document = reader.read(new StringReader(response));
        } catch (DocumentException e) {
            logger.error(e.getMessage());
            return "ER#xml->document";
        }

        try {
            rs = processHotelRatePlanResponse(document, periodId);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            logger.debug(response);
            return "ER#processHotelRatePlanResponse";
        }

        return rs;
    }

    @Override
    public String searchHotelRatePlan(final String hotelCode, Date startDate, Date endDate) {
        List<String> hotelCodes = new ArrayList<String>();
        hotelCodes.add(hotelCode);
        String response = execSearchHotelRatePlan(hotelCodes, startDate, endDate);

        //处理结果
        String rs="rs";
        SAXReader reader = new SAXReader();
        Document document;// 读取XML文件
        try {
            document = reader.read(new StringReader(response));
        } catch (DocumentException e) {
            logger.error(e.getMessage());
            return "ER#xml->document";
        }

        if (document == null) return "ER#Document is null.";

        Element rootElement = document.getRootElement();
        Element headerElement = rootElement.element("Header");
        if (!headerElement.attribute("ResultCode").getValue().equalsIgnoreCase("Success")) {
            logger.error("ER#ResultCode is not Success.");
            logger.error(document.asXML());
            return "ER#ResultCode is not Success.";
        }

        processHotelRatePlanResponse(document, 0);
        //processHotelRatePlanResponse


        return rs;
    }



    private static synchronized String doSearchHotelRatePlan(String requestXml) {

        logger.info("doSearchHotelRatePlan begin..." + Thread.currentThread().getName());

        //检查缓存，看看header节点中的API调用频率限制
        String cacheKey = ConfigData.OTA_HotelRatePlan_Request;
        net.sf.ehcache.Element cacheElement = getCache().get(cacheKey);
        if (cacheElement != null) {
            Element headerElement = (Element)cacheElement.getValue();
            int accessCount = Integer.parseInt(headerElement.attributeValue("AccessCount"));
            int currentCount = Integer.parseInt(headerElement.attributeValue("CurrentCount")) + 1;
            logger.info(ConfigData.OTA_HotelRatePlan_Request + " AccessCount=" + headerElement.attributeValue("AccessCount")
                    + ", CurrentCount=" + headerElement.attributeValue("CurrentCount")
                    + ", ResetTime=" + headerElement.attributeValue("ResetTime"));
            if (currentCount >= accessCount) {
                try {
                    logger.info("Sleep for one minute.");
                    Thread.sleep(60000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
        Date date0 = DateUtil.getCurDateTime();

        String response = execApiRequest(requestXml, ConfigData.OTA_HotelRatePlan_Url, "requestXML");

        int apiElapsedTime = DateUtil.getPastTime(date0);
        logger.info(ConfigData.OTA_HotelRatePlan_Request + ": api耗时 " + apiElapsedTime + "ms");

        //处理结果
        try {
            SAXReader reader = new SAXReader();
            Document document = reader.read(new StringReader(response));
            Element rootElement = document.getRootElement();
            Element headerElement = rootElement.element("Header");
            if (headerElement.attribute("ResultCode").getValue().equalsIgnoreCase("Success")) {
                //放置到缓存，用于记录header中的参数
                logger.debug("put headerElement to cache by key " + ConfigData.OTA_HotelRatePlan_Request);
                getCache().put(new net.sf.ehcache.Element(ConfigData.OTA_HotelRatePlan_Request, headerElement));
            } else {
                logger.error("ResultCode is not Success.");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        logger.info("doSearchHotelRatePlan end..." + Thread.currentThread().getName());

        return response;
    }

    private RatePlanRequest createRatePlanRequest(String hotelCode, Date startDate, Date endDate) {
        RatePlanHotelRef ratePlanHotelRef = new RatePlanHotelRef();
        ratePlanHotelRef.setHotelCode(hotelCode);
        RatePlanCandidate ratePlanCandidate = new RatePlanCandidate();
        ratePlanCandidate.getRatePlanHotelRefs().add(ratePlanHotelRef);

        DateRange ratePlanDateRange = new DateRange();
        ratePlanDateRange.setStartDate(DateUtil.formatDate(startDate, "yyyy-MM-dd"));
        ratePlanDateRange.setEndDate(DateUtil.formatDate(endDate, "yyyy-MM-dd"));

        RatePlanRequest ratePlanRequest = new RatePlanRequest();
        ratePlanRequest.getHotelRatePlanCandidates().add(ratePlanCandidate);
        ratePlanRequest.setDateRange(ratePlanDateRange);

        return ratePlanRequest;
    }

    private String execSearchHotelRatePlan(List<HotelRatePlanRequestDto> requestDtos) {
        HotelRequestBody request = new HotelRequestBody();
        request.createHotelRatePlanRequest();

        String hotelCode;
        Date startDate;
        Date endDate;
        for(HotelRatePlanRequestDto dto: requestDtos) {
            hotelCode = dto.getHotelCode();
            startDate = dto.getStartDate();
            endDate = dto.getEndDate();

            RatePlanRequest ratePlanRequest = createRatePlanRequest(hotelCode, startDate, endDate);

            request.getHotelRatePlaneRequest().getRatePlanRequests().add(ratePlanRequest);
        }

        String xml;

        try {
           xml = createXml4HotelRequestBody(request, ConfigData.OTA_HotelRatePlan_Request);
        } catch (Exception e) {
            e.printStackTrace();
            return "ER#request xml is wrong.";
        }
        logger.debug(xml);
        String response = doSearchHotelRatePlan(xml);
        logger.debug(response);

        return response;
    }

    private String createXml4HotelRequestBody(HotelRequestBody request, String requestType) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(HotelRequestBody.class);

        Marshaller m = jc.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        m.setProperty(Marshaller.JAXB_ENCODING, "utf-8");
        m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

        DocumentResult documentResult = new DocumentResult();
        m.marshal(request, documentResult);

        org.dom4j.Document document = documentResult.getDocument();
        org.dom4j.Element requestElement = document.getRootElement();

        org.dom4j.Document doc1 = DocumentHelper.createDocument();

        org.dom4j.Element rootElement = createRequestHeaderElement(doc1, requestType);
        rootElement.add(requestElement);

        return doc1.asXML();
    }

    private String execSearchHotelRatePlan(List<String> hotelCodes, Date startDate, Date endDate) {

        HotelRequestBody request = new HotelRequestBody();
        request.createHotelRatePlanRequest();

        for(String hotelCode: hotelCodes) {
            RatePlanRequest ratePlanRequest = createRatePlanRequest(hotelCode, startDate, endDate);
            request.getHotelRatePlaneRequest().getRatePlanRequests().add(ratePlanRequest);
        }

        String xml;

        try {
            xml = createXml4HotelRequestBody(request, ConfigData.OTA_HotelRatePlan_Request);
        } catch (Exception e) {
            e.printStackTrace();
            return "ER#request xml is wrong.";
        }
        logger.debug(xml);
        String response = doSearchHotelRatePlan(xml);
        logger.debug(response);

        return response;
    }

    @Override
    public String searchHotelRatePlan(List<String> hotelCodes, int periodId) {
        return searchHotelRatePlan(hotelCodes, periodId, false);
    }

    @Override
    public String importHotelRatePlanResponse(String filename) {
        String rs;
        SAXReader reader = new SAXReader();

        logger.info("filename: " + filename);

        File file = new File(filename);
        if (!file.exists()) return null;

        Document document;// 读取XML文件
        try {
            document = reader.read(file);
        } catch (DocumentException e) {
            e.printStackTrace();
            return "error";
        }

        //rs = "xml encoding: " + document.getXMLEncoding();
        // System.out.println(rs);

        rs = processHotelRatePlanResponse(document, 1);

        return rs;
    }

    private String searchHotelCacheChange(int cityId, Date fromTimeStamp) {
        //检查缓存，看看header节点中的API调用频率限制
        Cache cache = getCache();
        String cacheKey = ConfigData.OTA_HotelCacheChange_Request;
        net.sf.ehcache.Element cacheElement = cache.get(cacheKey);
        if (cacheElement != null) {
            Element headerElement = (Element)cacheElement.getValue();
            int accessCount = Integer.parseInt(headerElement.attributeValue("AccessCount"));
            int currentCount = Integer.parseInt(headerElement.attributeValue("CurrentCount"));
            logger.info("AccessCount=" + headerElement.attributeValue("AccessCount")
                    + ", CurrentCount=" + headerElement.attributeValue("CurrentCount")
                    + ", ResetTime=" + headerElement.attributeValue("ResetTime"));
            if (currentCount >= accessCount) {
                try {
                    logger.info("Sleep for one minute.");
                    Thread.sleep(60000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }

        HotelRequestBody request = new HotelRequestBody();
        request.createHotelCacheChangeRequest();

        String cacheFromTimeStamp = DateUtil.formatDate(fromTimeStamp, "yyyy-MM-dd'T'HH:mm:ss");
        request.getHotelCacheChangeRequest().getCacheSearchCriteria().setCacheFromTimestamp(cacheFromTimeStamp);
        request.getHotelCacheChangeRequest().getCacheSearchCriteria().getCacheSearchCriterion().setHotelCityCode(cityId);

        logger.info("OTA_HotelCacheChange: " + cacheFromTimeStamp + ", " + cityId);

        String xml;

        try {
            xml = createXml4HotelRequestBody(request, ConfigData.OTA_HotelCacheChange_Request);
        } catch (JAXBException e) {
            e.printStackTrace();
            return "<xml>" + e.getMessage() + "</xml>";
        }

        String paraName = "requestXML";
        Date basetime = DateUtil.getCurDateTime();
        String response = execApiRequest(xml, ConfigData.OTA_HotelCacheChange_url, paraName);
        int spantime = DateUtil.getPastTime(basetime);
        logger.info(ConfigData.OTA_HotelCacheChange_Request + ": api elapsed  " + spantime + "ms");


        return response;
    }

    @Override
    public String importHotelCacheChangeResponse(String filename) {
        String rs;
        SAXReader reader = new SAXReader();

        logger.info("filename: " + filename);

        File file = new File(filename);
        if (!file.exists()) return null;

        Document document;// 读取XML文件
        try {
            document = reader.read(file);
        } catch (DocumentException e) {
            e.printStackTrace();
            return "error";
        }

        rs = processHotelCacheChangeResponse(document);

        return rs;
    }

    /**
     * 更新所有的酒店基础信息
     * 每7天更新一次
     * 1. 获取城市id
     * 2.检查该城市最近更新时间
     * @return
     */
    @Override
    public String refreshAllHotelBasicInfo() {
        String rs = "";

        List<City> cities = cityDao.getAllCity();
        for(City city: cities) {
            CacheCity cacheCity = hotelDao.getCacheCity(city.getOpenApiId());
            if (cacheCity != null) {
                //判断上次更新到现在是否超过7天
                int spanDays = DateUtil.getPastDays(cacheCity.getCacheTime());
                if (spanDays < 7 ) continue;
            }

            String status = searchHotel(city.getOpenApiId());
            rs = status;
            if (status.substring(0, 2).equalsIgnoreCase("OK")) {
                if (cacheCity == null) {
                    cacheCity = new CacheCity();
                    cacheCity.setCityId(city.getOpenApiId());
                    cacheCity.setCreateTime(DateUtil.getCurDateTime());
                    cacheCity.setCacheTime(DateUtil.getCurDateTime());
                    hotelDao.createCacheCity(cacheCity);
                } else {
                    cacheCity.setCacheTime(DateUtil.getCurDateTime());
                    hotelDao.updateCacheCity(cacheCity);
                }
                //break;
            } else {
                //出错首先暂时退出
                logger.warn("refreshAllHotelBasicInfo：exit loop for (" + rs + ").");
                break;
            }
        }

        return rs;
    }

    /**
     * 更新酒店的房型等详细信息
     * 一周更新一次
     * @return
     */
    @Override
    public String refreshAllHotelDetail() {
        String rs = "HAHA";

        List<String> hotelCodes0 = hotelDao.getAllHotelCodes2();

        int count = 0;
        List<String> hotelCodes = new ArrayList<String>();
        for (String hotelCode : hotelCodes0) {
            CacheHotel cacheHotel = hotelDao.getCacheHotel(hotelCode);
            if (cacheHotel != null) {
                //判断上次更新到现在是否超过7天
                int spanDays = DateUtil.getPastDays(cacheHotel.getCacheTime());
                if (spanDays < 7) {
                    //logger.info(hotelCode + "\'s is already updated lately.");
                    continue;
                }
            }

            hotelCodes.add(hotelCode);
            count++;
            if (count < 10) continue;

            String status = searchHotelDetail(hotelCodes);
            rs = status;
            if (status.substring(0, 2).equalsIgnoreCase("OK")) {
                count = 0;
                hotelCodes.clear();
            } else {
                //出错首先暂时退出
                logger.warn("refreshAllHotelDetail：exit loop for (" + rs + ").");
                break;
            }
        }
        return rs;
    }

    private class RatePlanDaoThread implements Runnable {

        private volatile boolean stopRequested;
        private Thread runThread;

        @Override
        public void run() {
            runThread = Thread.currentThread();
            stopRequested = false;
            logger.info(runThread.getName() + "(RatePlanDaoThread) is running...");

            while ( !stopRequested || !ratePlanQueue.isEmpty() || !ratePlanRequestQueue.isEmpty() ) {


                try {
                    DocumentDto documentDto = ratePlanQueue.take();
                    Document document = documentDto.getDocument();
                    int periodId = documentDto.getPeriodId();

                    logger.info(runThread.getName() + " is running on " + periodId);

                    executeSaveRatePlanDocument(document, periodId);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    Thread.sleep(50);
                } catch ( InterruptedException x ) {
                    Thread.currentThread().interrupt(); // re-assert interrupt
                }
            }

            logger.info(runThread.getName() + "(RatePlanDaoThread) exit.");
        }

        public void stopRequest() {
            stopRequested = true;
        }
    }
    private class HotelCacheChangeDaoThread implements Runnable {

        private volatile boolean stopRequested;
        private Thread runThread;

        @Override
        public void run() {
            runThread = Thread.currentThread();
            stopRequested = false;

            logger.info(runThread.getName() + "(HotelCacheChangeDaoThread) is running...");

            while ( !stopRequested || !hotelCacheChangeQueue.isEmpty() ) {

                try {
                    DocumentDto documentDto = hotelCacheChangeQueue.take();
                    Document document = documentDto.getDocument();

                    executeSaveHotelCacheChangeDocument(document);

                    Thread.sleep(50);
                } catch ( InterruptedException x ) {
                    Thread.currentThread().interrupt(); // re-assert interrupt
                }
            }

            logger.info(runThread.getName() + "(HotelCacheChangeDaoThread) exit.");
        }

        public void stopRequest() {
            stopRequested = true;
        }
    }

    /**
     * 获取hotelCacheChange的详细信息
     */
    private class HotelCacheChangeDetailThread implements Runnable {

        private volatile boolean stopRequested;
        private Thread runThread;

        @Override
        public void run() {
            runThread = Thread.currentThread();
            stopRequested = false;

            logger.info(runThread.getName() + "(HotelCacheChangeDetailThread) is running...");

            //获取hotelCacheChange的原始数据
            List<HotelRatePlanRequestDto> requestDtos = new ArrayList<HotelRatePlanRequestDto>();

            SAXReader reader = new SAXReader();
            Document document;// 读取XML文件
            while ( !stopRequested || !ratePlanRequestQueue.isEmpty() ) {
                try {
                    if (ratePlanRequestQueue.size() == 0) Thread.sleep(1000);
                    int count = ratePlanRequestQueue.drainTo(requestDtos, 10);

                    logger.debug(runThread.getName() + " is running...");
                    if (count > 0 ) {
                        String response = execSearchHotelRatePlan(requestDtos);

                        try {
                            document = reader.read(new StringReader(response));
                            if (document != null) {

                                Element rootElement = document.getRootElement();
                                Element headerElement = rootElement.element("Header");
                                if (!headerElement.attribute("ResultCode").getValue().equalsIgnoreCase("Success")) {
                                    logger.error("ER#ResultCode is not Success.");
                                    logger.error(document.asXML());
                                }

                                processHotelRatePlanResponse(document, 0);
                            }
                        } catch (DocumentException e) {
                            logger.error(e.getMessage());
                       }
                    } else {
                        logger.info("drainTo is 0");
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    Thread.sleep(50);
                } catch ( InterruptedException x ) {
                    Thread.currentThread().interrupt(); // re-assert interrupt
                }
            }

            logger.info(runThread.getName() + "(HotelCacheChangeDetailThread) exit.");
        }



        public void stopRequest() {
            stopRequested = true;
        }
    }

    private void executeSaveHotelCacheChangeDocument(Document document) {

        //此处需要在指定的命名空间里面才能找到
        Map uris = new HashMap();
        uris.put("ns", "http://www.opentravel.org/OTA/2003/05");
        XPath xpath = document.createXPath("//ns:OTA_HotelCacheChangeRS/*");
        xpath.setNamespaceURIs(uris);
        List myNodes = xpath.selectNodes(document);


        List<CacheHotelCacheChange> hotelCacheChanges = new ArrayList<CacheHotelCacheChange>();

        XStream xs = new XStream();
        xs.alias("CacheChangeInfo", HotelCacheChangeInfo.class);
        xs.processAnnotations(HotelCacheChangeInfo.class);

        for (Iterator it = myNodes.iterator(); it.hasNext(); ) {
            Element element = (Element) it.next();

            if (!element.getName().equalsIgnoreCase("CacheChangeInfo")) {
                continue;
            }

            //String xml = element.asXML();
            HotelCacheChangeInfo hotelCacheChangeInfo = (HotelCacheChangeInfo) xs.fromXML(element.asXML());

            CacheHotelCacheChange cacheHotelCacheChange = new CacheHotelCacheChange();
            cacheHotelCacheChange.setHotelCode(hotelCacheChangeInfo.getHotelCode());
            cacheHotelCacheChange.setRatePlanCode(hotelCacheChangeInfo.getOtherInfo().getRatePlanCode());
            try {
                cacheHotelCacheChange.setStartDate(DateUtil.convertToDate(hotelCacheChangeInfo.getDateRange().getStartDate()));
                cacheHotelCacheChange.setEndDate(DateUtil.convertToDate(hotelCacheChangeInfo.getDateRange().getEndDate()));
            } catch (Exception ex) {
                logger.error(ex.getMessage());
                continue;
            }

            hotelCacheChanges.add(cacheHotelCacheChange);

        }//end for
        hotelDao.createCacheHotelCacheChanges(hotelCacheChanges);
    }

    @Override
    public String refreshAllRatePlan() {
        String rs = "";
        int count = 0;

        RatePlanDaoThread ratePlanDaoThread1 = new RatePlanDaoThread();
        Thread t = new Thread(ratePlanDaoThread1);
        t.setDaemon(true);
        t.start();

        RatePlanDaoThread ratePlanDaoThread2 = new RatePlanDaoThread();
        t = new Thread(ratePlanDaoThread2);
        t.setDaemon(true);
        t.start();

        RatePlanDaoThread ratePlanDaoThread3 = new RatePlanDaoThread();
        t = new Thread(ratePlanDaoThread3);
        t.setDaemon(true);
        t.start();

        cn.buk.hotel.entity.HotelInfo hotelInfo;
        boolean bBreak = false;
        for (int periodId = 1; periodId <= 4; periodId++) {
            List<String> hotelCodes1 = new ArrayList<String>();
            Date baseTime = DateUtil.getCurDateTime();
            List<String> hotelCodes = hotelDao.getHotelCodes4RatePlan(periodId);
            int span = DateUtil.getPastMinutes(baseTime);
            if (span >= 5) {
                logger.error("hotelDao.getHotelCodes4RatePlan(periodId) 耗时 " + span + ", 舍弃");
                break;
            }

            for (String hotelCode : hotelCodes) {
                //放置到缓存，用于记录header中的参数
                //timeToLiveSecond is the time in seconds from the point of creation in the cache till it expires (regardless of how frequently it is accessed in that time)
                //timeToIdleSeconds is the time in seconds before an object in the cache expires if it is not accessed.
                //getCache().put(new net.sf.ehcache.Element(ConfigData.HOTEL_CODES_WAITING_FOR_RATE_PLAN, copyHotelCodes, false, 1200000, 1800000));

                hotelInfo = hotelDao.getHotelInfoByHotelCode(hotelCode);
                if (hotelInfo == null || hotelInfo.getRatePlanStatus() == -1) {
                    logger.debug(hotelCode + " is null or rate plan status is -1.");
                    continue;
                }

                CacheRatePlan cacheRatePlan = hotelDao.getCacheRatePlan(hotelCode, periodId);
                if (cacheRatePlan != null) {
                    //判断上次更新到现在是否超过7天
                    int spanDays = DateUtil.getPastDays(cacheRatePlan.getCacheTime());
                    if (spanDays < 7)
                        continue;
                } else {
                    cacheRatePlan = new CacheRatePlan();
                    cacheRatePlan.setHotelCode(hotelCode);
                    cacheRatePlan.setPeriodId(periodId);
                    cacheRatePlan.setBeginTime(DateUtil.getCurDateTime());
                    hotelDao.createCacheRatePlan(cacheRatePlan);
                }

                hotelCodes1.add(hotelCode);
                count++;
                if (count < 10) continue;

                rs = searchHotelRatePlan(hotelCodes1, periodId);
                if (rs.substring(0, 2).equalsIgnoreCase("OK")) {
                    count = 0;
                    hotelCodes1.clear();
                } else {
                    logger.error("exit loop for (" + rs + ").");
                    bBreak = true;
                    break;
                }
            }
            if (bBreak) break;
        }

        ratePlanDaoThread1.stopRequest();
        ratePlanDaoThread2.stopRequest();
        ratePlanDaoThread3.stopRequest();

        return rs;
    }


    @Override
    public String refreshHotelCacheChange() {

        HotelCacheChangeDaoThread hotelCacheChangeDaoThread1 = new HotelCacheChangeDaoThread();
        Thread t = new Thread(hotelCacheChangeDaoThread1);
        t.setDaemon(true);
        t.start();

        HotelCacheChangeDaoThread hotelCacheChangeDaoThread2 = new HotelCacheChangeDaoThread();
        t = new Thread(hotelCacheChangeDaoThread2);
        t.setDaemon(true);
        t.start();

        HotelCacheChangeDaoThread hotelCacheChangeDaoThread3 = new HotelCacheChangeDaoThread();
        t = new Thread(hotelCacheChangeDaoThread3);
        t.setDaemon(true);
        t.start();


        List<City> cities = cityDao.getCityHotelGreaterThan100();
        int max = cities.size();
        int index = 0;

        Date timestamp1 = DateUtil.getCurDateTime();
        timestamp1 = DateUtil.addMinutes(timestamp1, -intervalCacheChange);
        timestamp1 = DateUtil.getDateOnMinute(timestamp1);
        String cacheFromTimeStamp = DateUtil.formatDate(timestamp1, "yyyy-MM-dd'T'HH:mm:ss");
        for(City city: cities) {
            if (city.getOpenApiId() == 0) continue;
            if (!city.getCountryCode().equalsIgnoreCase("CN")) continue;

            index++;
            logger.debug("refreshHotelCacheChange\'s process: " + index + "/" + max);
            logger.info("OTA_HotelCacheChange: " + cacheFromTimeStamp + ", " + city.getOpenApiId() + ", " + index);

            String xml = searchHotelCacheChange(city.getOpenApiId(),timestamp1);
            if (xml.length() == 0) break;
            processHotelCacheChange(xml);
        }

        hotelCacheChangeDaoThread1.stopRequest();
        hotelCacheChangeDaoThread2.stopRequest();
        hotelCacheChangeDaoThread3.stopRequest();

        return "OK";
    }

    @Override
    public String calculateSignature(String timestamp, String requestType) {
        try {
            return SignatureUtils.CalculationSignature(timestamp, this.allianceId, this.secretKey, this.sid, requestType);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private class HotelRatePlanRequestDto {
        private String hotelCode;
        private Date startDate;
        private Date endDate;

        public String getHotelCode() {
            return hotelCode;
        }

        public void setHotelCode(String hotelCode) {
            this.hotelCode = hotelCode;
        }

        public Date getStartDate() {
            return startDate;
        }

        public void setStartDate(Date startDate) {
            this.startDate = startDate;
        }

        public Date getEndDate() {
            return endDate;
        }

        public void setEndDate(Date endDate) {
            this.endDate = endDate;
        }
    }

    @Override
    public void retrieveHotelCacheChangeDetail() {
        HotelCacheChangeDetailThread cacheDetailThread1 = new HotelCacheChangeDetailThread();
        Thread t = new Thread(cacheDetailThread1);
        t.setDaemon(true);
        t.start();

        HotelCacheChangeDetailThread cacheDetailThread2 = new HotelCacheChangeDetailThread();
        t = new Thread(cacheDetailThread2);
        t.setDaemon(true);
        t.start();

        HotelCacheChangeDetailThread cacheDetailThread3 = new HotelCacheChangeDetailThread();
        t = new Thread(cacheDetailThread3);
        t.setDaemon(true);
        t.start();

        RatePlanDaoThread ratePlanDaoThread1 = new RatePlanDaoThread();
        t = new Thread(ratePlanDaoThread1);
        t.setDaemon(true);
        t.start();

        RatePlanDaoThread ratePlanDaoThread2 = new RatePlanDaoThread();
        t = new Thread(ratePlanDaoThread2);
        t.setDaemon(true);
        t.start();

        RatePlanDaoThread ratePlanDaoThread3 = new RatePlanDaoThread();
        t = new Thread(ratePlanDaoThread3);
        t.setDaemon(true);
        t.start();


        String hotelCode0 = null;
        Date startDate0 = null, endDate0 = null;
        List<CacheHotelCacheChange> cacheChanges = hotelDao.getHotelCacheChanges();

        for(CacheHotelCacheChange cacheHotelCacheChange: cacheChanges) {
            String hotelCode = cacheHotelCacheChange.getHotelCode();
            Date startDate = cacheHotelCacheChange.getStartDate();
            Date endDate = cacheHotelCacheChange.getEndDate();

            int spans = DateUtil.getPastDays(startDate, DateUtil.getCurDate());
            if (spans > 90) {
                logger.warn("HotelCacheChange\'s start date is " + DateUtil.formatDate(startDate, "yyyy-MM-dd") + ", " + Integer.toString(spans) + " > 90 days, ignored.");
                continue;
            }
            spans = DateUtil.getPastDays(endDate, DateUtil.getCurDate());
            if (spans > 90) {
                logger.warn("HotelCacheChange\'s end date is " + DateUtil.formatDate(endDate, "yyyy-MM-dd") + ", " + Integer.toString(spans) + " > 90 days, start date is " + DateUtil.formatDate(startDate, "yyyy-MM-dd") + ", ignored.");
                continue;
            }

            if (hotelCode0 == null) {
                hotelCode0 = hotelCode;
                startDate0 = startDate;
                endDate0 = endDate;

                continue;
            }

            if (hotelCode0.equalsIgnoreCase(hotelCode) && startDate0.getTime() == startDate.getTime() && endDate0.getTime() == endDate.getTime()) continue;

            HotelRatePlanRequestDto dto = new HotelRatePlanRequestDto();
            dto.setHotelCode(hotelCode0);
            dto.setStartDate(startDate0);
            dto.setEndDate(endDate0);
            try {
                logger.debug("current ratePlanRequestQueue\'s size is " + ratePlanRequestQueue.size());
                ratePlanRequestQueue.put(dto);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            hotelCode0 = hotelCode;
            startDate0 = startDate;
            endDate0 = endDate;


            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (hotelCode0 != null) {
            HotelRatePlanRequestDto dto = new HotelRatePlanRequestDto();
            dto.setHotelCode(hotelCode0);
            dto.setStartDate(startDate0);
            dto.setEndDate(endDate0);
            try {
                logger.debug("current request dot queue\'s size is " + ratePlanRequestQueue.size());
                ratePlanRequestQueue.put(dto);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        cacheDetailThread1.stopRequest();
        cacheDetailThread2.stopRequest();
        cacheDetailThread3.stopRequest();

        ratePlanDaoThread1.stopRequest();
        ratePlanDaoThread2.stopRequest();
        ratePlanDaoThread3.stopRequest();
    }

    private String processHotelCacheChange(String xml) {
        String retval;
        //处理结果
        SAXReader reader = new SAXReader();

        Document document;// 读取XML文件
        try {
            document = reader.read(new StringReader(xml));
            retval = processHotelCacheChangeResponse(document);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "ER";
        }

        return  retval;
    }


    public void setCityDao(CityDao cityDao) {
        this.cityDao = cityDao;
    }

// --Commented out by Inspection START (2014-12-8 22:39):
//    public HotelDao getHotelDao() {
//        return hotelDao;
//    }
// --Commented out by Inspection STOP (2014-12-8 22:39)

    public void setHotelDao(HotelDao hotelDao) {
        this.hotelDao = hotelDao;
    }

// --Commented out by Inspection START (2014-12-8 22:39):
//    public int getIntervalCacheChange() {
//        return intervalCacheChange;
//    }
// --Commented out by Inspection STOP (2014-12-8 22:39)

    public void setIntervalCacheChange(int intervalCacheChange) {
        this.intervalCacheChange = intervalCacheChange;
    }

// --Commented out by Inspection START (2014-12-8 22:39):
//    public String getAllianceId() {
//        return allianceId;
//    }
// --Commented out by Inspection STOP (2014-12-8 22:39)

    public void setAllianceId(String allianceId) {
        this.allianceId = allianceId;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
