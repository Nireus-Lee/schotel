/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.service;

import cn.buk.api.dto.*;
import cn.buk.api.dto.Header;
import cn.buk.api.dto.hotel.*;
import cn.buk.api.dto.hotel.DateRange;
import cn.buk.api.dto.hotel.HotelInfo;
import cn.buk.api.dto.hotel.HotelRatePlan;
import cn.buk.api.util.ConvertUtil;
import cn.buk.hotel.dao.CityDao;
import cn.buk.hotel.dao.HotelDao;
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
import java.text.ParseException;
import java.util.*;

/**
 * User: yfdai
 * Date: 14-9-18
 * Time: 下午2:41
 */
public class CtripOpenApiServiceImpl implements CtripOpenApiService {

    private static Logger logger = Logger.getLogger(CtripOpenApiServiceImpl.class);
    private static int cacheHits;
    private static int cacheMisses;

    private String allianceId;
    private String sid;
    private String secretKey;

    /**
     * 多长时间检查一次hotelCacheChange,单位（分钟);
     */
    private int intervalCacheChange;

    private CacheManager cacheManager = CacheManager.getInstance();

    private CityDao cityDao;

    private HotelDao hotelDao;

    private Cache getCache() {
        Cache cache = cacheManager.getCache("openApiCache");
        if (cache == null) {
            cacheManager.addCache("openApiCache");
            cache = cacheManager.getCache("openApiCache");
        }

        return cache;
    }

    private String execApiRequest(String requestContent, String urlString, String paraName) {
        Proxy proxy = null;
        String proxyUsed = (String)PropertiesUtil.pps.get(AppConfig.PROXY_USED);
        if (proxyUsed.equalsIgnoreCase("1")){
            String proxyHost = (String)PropertiesUtil.pps.get(AppConfig.PROXY_HOST);
            String proxyPort = (String)PropertiesUtil.pps.get(AppConfig.PROXY_PORT);
            int port = Integer.parseInt(proxyPort);
            SocketAddress address = new InetSocketAddress(proxyHost, port);

            proxy = new Proxy(Proxy.Type.HTTP, address);
        }


        HttpAccessAdapter httpAccessAdapter = new HttpAccessAdapter();
        String response = httpAccessAdapter.SendRequestToUrl(requestContent, urlString, paraName, proxy);
        return response;
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
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
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
        if (file.exists() == false) return "not exist.";
        Document document = null;// 读取XML文件
        try {
            document = reader.read(file);
        } catch (DocumentException e) {
            e.printStackTrace();
            return "error";
        }
        Element rootElement = document.getRootElement();

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
        if (file.exists() == false) return "not exist.";
        Document document = null;// 读取XML文件
        try {
            document = reader.read(file);
        } catch (DocumentException e) {
            e.printStackTrace();
            return "error";
        }
        Element rootElement = document.getRootElement();

        List list = document.selectNodes("//LocationDetails/*");
        List<District> districts = new ArrayList<District>();
        String temp;
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
        if (file.exists() == false) return "not exist.";
        Document document = null;// 读取XML文件
        try {
            document = reader.read(file);
        } catch (DocumentException e) {
            e.printStackTrace();
            return "error";
        }
        Element rootElement = document.getRootElement();

        List list = document.selectNodes("//ZoneDetails/*");
        List<Zone> zones = new ArrayList<Zone>();
        String temp;
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

    private Response convertXml2Response(String xml) {
        XStream xs = new XStream();
        xs.alias("Response", Response.class);
        xs.aliasField("SearchResult", IntlFlightSearchResponse.class, "searchResult");
        xs.processAnnotations(Response.class);
        xs.processAnnotations(Header.class);
        xs.processAnnotations(IntlFlightSearchResponse.class);
        xs.processAnnotations(IntlFlightSearchResult.class);

        Response response = (Response)xs.fromXML(xml);

        return response;
    }


    /**
     * 搜索指定城市的酒店信息
     * @param cityId
     * @return
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
            //<Header ShouldRecordPerformanceTime="False" Timestamp="2013-06-25 14:49:04:54424" ReferenceID="bbfc4ced-446d-4374-a852-2e53511b8d72" RecentlyTime="2013-06-25 14:48:31"
            // AccessCount="30000" CurrentCount="622" ResetTime="2013-06-25 14:49:31" ResultCode="Success" />
            int accessCount = Integer.parseInt(headerElement.attributeValue("AccessCount"));
            int currentCount = Integer.parseInt(headerElement.attributeValue("CurrentCount"));
            logger.info("AccessCount=" + headerElement.attributeValue("AccessCount")
                    + ", CurrentCount=" + headerElement.attributeValue("CurrentCount")
                    + ", RecentlyTime=" + headerElement.attributeValue("RecentlyTime")
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
        String xml = "";

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
            ex.printStackTrace();
            return "ER";
        }

        logger.info(xml);

        String response = execApiRequest(xml, ConfigData.OTA_HotelSearch_Url, "requestXML");
        //处理结果
        SAXReader reader = new SAXReader();

        Document document = null;// 读取XML文件
        try {
            document = reader.read(new StringReader(response));
            response = processHotelSearchResponse(document);
        } catch (Exception ex) {
            ex.printStackTrace();
          return "ER";
        }

        return response;
    }

    private String processHotelSearchResponse(Document document) {
        if (document == null) return "ER#Document is null.";

        Element rootElement = document.getRootElement();
        Element headerElement = rootElement.element("Header");
        if (headerElement.attribute("ResultCode").getValue().equalsIgnoreCase("Success") == false) {
            //logger.info("Header::ResultCode: " + headerElement.attribute("ResultCode").getValue());
            return "ER#ResultCode is not Success.";
        }

        //放置到缓存，用于记录header中的参数
        getCache().put(new net.sf.ehcache.Element(ConfigData.OTA_HotelSearch_Request, headerElement));

        //logger.info(headerElement.attribute("ReferenceID").getValue());

        List myNodes = document.selectNodes("/Response/HotelResponse/*");
        //logger.info(myNodes.size());
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
            logger.info(xml);
            if (response.getSuccess() != null) {
                logger.info("There is no hotel.");
                return "OK#0 hotel";
            }
        }


        // return response;
        String rs = "OK," + response.getHotelInfos().size() + " hotels,";
        int saveCount = 0;
        int count = 0;
        for(HotelInfo hotelInfo: response.getHotelInfos()) {
            date0 = DateUtil.getCurDateTime();
            cn.buk.hotel.entity.HotelInfo hotelInfo1 = ConvertUtil.convertHotelInfo(hotelInfo);
            int spanMilliSeconds1 = DateUtil.getPastTime(date0);
            date0 = DateUtil.getCurDateTime();
            int retCode = hotelDao.createHotelInfo(hotelInfo1);
            int spanMilliSeconds2 = DateUtil.getPastTime(date0);

            if ( retCode == 1) {
                saveCount++;
            }
            else if (retCode == 2)
                logger.info("HotelCode: " + hotelInfo1.getHotelCode() + ", HotelName: " + hotelInfo1.getHotelName() + " is existed.");

            count++;
            if (count % 100 == 0) {
                logger.info("Progress: " + count + " / " + total);
            }
        }

        rs += saveCount + " be saved.";
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
        if (headerElement.attribute("ResultCode").getValue().equalsIgnoreCase("Success") == false) {
            return "ER#ResultCode is not Success.";
        }

        //放置到缓存，用于记录header中的参数
        getCache().put(new net.sf.ehcache.Element(ConfigData.OTA_HotelDetail_Request, headerElement));

        //此处需要在指定的命名空间里面才能找到
        Namespace ns = new Namespace("", "http://www.opentravel.org/OTA/2003/05");
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
        Date baseTime;
        int span1, span2, span3, span4, span5, span6, span7, span8;
        cn.buk.hotel.entity.HotelInfo hotelInfo1;
        String hotelCode, hotelName;
        for (Iterator it = myNodes.iterator(); it.hasNext(); ) {
            baseTime = DateUtil.getCurDateTime();
            Element element = (Element) it.next();

            xml =  element.asXML();
            HotelDescriptiveContent response = (HotelDescriptiveContent)xs.fromXML(xml);
            span1 = DateUtil.getPastTime(baseTime);

            rs += ";" + response.getHotelName();

            hotelCode = response.getHotelCode();
            hotelName = response.getHotelName();
            hotelInfo1 = hotelDao.getHotelInfoByHotelCode(hotelCode);
            span2 = DateUtil.getPastTime(baseTime);
            span3 = 0;
            span4 = 0;
            span5 = 0;
            span6 = 0;
            span7 = 0;
            span8 = 0;
            if (hotelInfo1 == null) {
                rs = "ER#Not found hotelCode(" + hotelCode + ")";
                return  rs;
            } else {
                try {
                    if (response.getHotelInfo().getWhenBuilt() != null)
                        hotelInfo1.setBuildDate(DateUtil.convertToDate(response.getHotelInfo().getWhenBuilt()));
                } catch (Exception e) {
                    logger.info("WhenBuild: " + response.getHotelInfo().getWhenBuilt());
                    e.printStackTrace();
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
                        point.setLatitude(point1.getLatitude());
                        point.setLongitude(point1.getLongitude());
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
                //MultimediaDescriptions
                span3 = DateUtil.getPastTime(baseTime);

                //save
                Date date0 = DateUtil.getCurDateTime();
                int retCode = hotelDao.updateHotelInfo(hotelInfo1);
                int spanTime3 = DateUtil.getPastTime(date0);
                logger.info("HotelCode[" + hotelCode + "]: 保存耗时　" + spanTime3 + "ms, retCode=" + retCode);
                span4 = DateUtil.getPastTime(baseTime);
                if (retCode == 1) {
                    rs = "OK#Save OK";
                    //记录cache
                    CacheHotel cacheHotel = hotelDao.getCacheHotel(hotelCode);
                    span6 = DateUtil.getPastTime(baseTime);
                    if (cacheHotel == null) {
                        cacheHotel = new CacheHotel();
                        cacheHotel.setHotelCode(hotelCode);
                        span7 = 0;
                        if (hotelDao.createCacheHotel(cacheHotel) == 1) {
                            logger.info("new cacheHotel is ok. -- " + cacheHotel.getHotelCode());
                            span7 = DateUtil.getPastTime(baseTime);
                        }
                    } else {
                        cacheHotel.setCacheTime(DateUtil.getCurDateTime());
                        hotelDao.updateCacheHotel(cacheHotel);
                        span7 = DateUtil.getPastTime(baseTime);
                    }

                }  else {
                    logger.info(xml);
                    rs = "ER#Save Status is " + retCode + " for hotelCode[" + hotelCode + "]";
                    return rs;
                }
            }
            int spanTotal = DateUtil.getPastTime(baseTime);
            logger.info(String.format("HotelCode[%s] total=%dms, span4=%d, span5=%d, span6=%d, span7=%d, span8=%d", hotelCode, spanTotal, span4, span5, span6, span7, span8));

        }  //end for

        return rs;
    }


    /**
     * 处理HotelRatePlanResponse的结果
     * @param document
     * @return
     */
    private String processHotelRatePlanResponse(Document document, int periodId) {
        if (document == null) return "ER#Document is null.";

        Element rootElement = document.getRootElement();
        Element headerElement = rootElement.element("Header");
        if (headerElement.attribute("ResultCode").getValue().equalsIgnoreCase("Success") == false) {
            return "ER#ResultCode is not Success.";
        }

        //放置到缓存，用于记录header中的参数
        getCache().put(new net.sf.ehcache.Element(ConfigData.OTA_HotelRatePlan_Request, headerElement));

        //此处需要在指定的命名空间里面才能找到
        Namespace ns = new Namespace("", "http://www.opentravel.org/OTA/2003/05");
        Map uris = new HashMap();
        uris.put("ns", "http://www.opentravel.org/OTA/2003/05");
        XPath xpath = document.createXPath("//ns:OTA_HotelRatePlanRS/*");
        xpath.setNamespaceURIs(uris);
        List myNodes = xpath.selectNodes(document);

        if (myNodes.size()==0) {
            logger.info("OK#There is nothing.");
            return "OK#There is nothing.";
        }


        String rs = "HAHA";

        int saveCount = 0, errorCount = 0;
        Date baseTime0 = DateUtil.getCurDateTime();
        for (Iterator it = myNodes.iterator(); it.hasNext(); ) {
            Date baseTime1 = DateUtil.getCurDateTime();
            Element element = (Element) it.next();

            if (element.getName().equalsIgnoreCase("RatePlans") == false) {
                logger.info("element.name: " + element.getName());
                continue;
            }

            XStream xs = new XStream();
            xs.alias("RatePlans", HotelRatePlans.class);
            xs.processAnnotations(HotelRatePlans.class);

            String xml =  element.asXML();
            HotelRatePlans response = (HotelRatePlans)xs.fromXML(xml);
            int span1 = DateUtil.getPastTime(baseTime1);

            rs += ";" + response.getHotelCode();
            String hotelCode = response.getHotelCode();
            cn.buk.hotel.entity.HotelInfo hotelInfo1 = hotelDao.getHotelInfoByHotelCode(hotelCode);

            if (response.getHotelRatePlans() == null || response.getHotelRatePlans().size() == 0) {
                if (periodId == 1) {
                    hotelInfo1.setRatePlanStatus(-1);
                    hotelDao.updateHotelInfo(hotelInfo1);
                }
                logger.info("OK#Theres is no rates for {" + hotelCode + "," + periodId + "}.");
                rs = "OK#Theres is no rates.";
            } else {

                cn.buk.hotel.entity.HotelRatePlan hotelRatePlan = null;
                for(HotelRatePlan dtoRatePlan: response.getHotelRatePlans()) {
                    try {
                        hotelRatePlan = ConvertUtil.convertHotelRatePlan(dtoRatePlan);
                        hotelRatePlan.setHotelInfo(hotelInfo1);
                        int retCode = hotelDao.createHotelRatePlan(hotelRatePlan);
                        if (retCode == 1) {
                            saveCount++;
                            rs = "OK#Save OK";
                        } else if (retCode == 2) {
                            rs = "OK#already exist.";
                            hotelRatePlan = null;
                        }
                        else {
                            errorCount++;
                            logger.info(xml);
                            return "ER#HotelCode is " + hotelCode + ", RatePlanCode is " + hotelRatePlan.getRatePlanCode() + ", rate plan save failed.";
                        }
                    } catch (Exception ex) {
                        errorCount++;
                        ex.printStackTrace();
                        return "ER#HotelCode is " + hotelCode + ", rate plan occur exception.";
                    }
                }  // end for
                hotelInfo1.setRatePlanStatus(1);
                hotelDao.updateHotelInfo(hotelInfo1);
            }

            int span2 = DateUtil.getPastTime(baseTime1);
            //记录cache
            CacheRatePlan cacheRatePlan = hotelDao.getCacheRatePlan(hotelCode, periodId);
            if (cacheRatePlan == null) {
                cacheRatePlan = new CacheRatePlan();
                cacheRatePlan.setHotelCode(hotelCode);
                cacheRatePlan.setPeriodId(periodId);
                hotelDao.createCacheRatePlan(cacheRatePlan);
            } else {
                cacheRatePlan.setCacheTime(DateUtil.getCurDateTime());
                cacheRatePlan.setEndTime(DateUtil.getCurDateTime());
                hotelDao.updateCacheRatePlan(cacheRatePlan);
            }

            int span3 = DateUtil.getPastTime(baseTime1);
            logger.info(String.format("hotel code[%s]: span1=%dms, span2=%dms, span3=%dms", hotelCode, span1, span2, span3));
        }
        int spanTotal = DateUtil.getPastTime(baseTime0);
        logger.info(String.format("total time : %d ms", spanTotal));
        if (spanTotal > 15000) {
            hotelDao.clearAllCache();
            logger.warn(String.format("total time(%d ms)>15s, clear hibernate cache.", spanTotal));

        }

        rs = "OK#" + saveCount + " OK";


        return rs;
    }


    private String processHotelCacheChangeResponse(Document document) {
        if (document == null) return "ER#Document is null.";

        Element rootElement = document.getRootElement();
        Element headerElement = rootElement.element("Header");
        if (headerElement.attribute("ResultCode").getValue().equalsIgnoreCase("Success") == false) {
            //logger.info("Header::ResultCode: " + headerElement.attribute("ResultCode").getValue());
            return "ER#ResultCode is not Success.";
        }

        //放置到缓存，用于记录header中的参数
        getCache().put(new net.sf.ehcache.Element(ConfigData.OTA_HotelCacheChange_Request, headerElement));


        //此处需要在指定的命名空间里面才能找到
        Namespace ns = new Namespace("", "http://www.opentravel.org/OTA/2003/05");
        Map uris = new HashMap();
        uris.put("ns", "http://www.opentravel.org/OTA/2003/05");
        XPath xpath = document.createXPath("//ns:OTA_HotelCacheChangeRS/*");
        xpath.setNamespaceURIs(uris);
        List myNodes = xpath.selectNodes(document);

        String rs = "NA";
        Date basetime = DateUtil.getCurDateTime();
        int saveCount = 0;
        for (Iterator it = myNodes.iterator(); it.hasNext(); ) {
            Element element = (Element) it.next();

            if (element.getName().equalsIgnoreCase("CacheChangeInfo") == false) {
                //logger.info("element.name: " + element.getName());
                continue;
            }

            XStream xs = new XStream();
            xs.alias("CacheChangeInfo", HotelCacheChangeInfo.class);
            xs.processAnnotations(HotelCacheChangeInfo.class);

            String xml =  element.asXML();
            HotelCacheChangeInfo hotelCacheChangeInfo = (HotelCacheChangeInfo)xs.fromXML(xml);

            CacheHotelCacheChange cacheHotelCacheChange = new CacheHotelCacheChange();
            cacheHotelCacheChange.setHotelCode(hotelCacheChangeInfo.getHotelCode());
            cacheHotelCacheChange.setRatePlanCode(hotelCacheChangeInfo.getOtherInfo().getRatePlanCode());
            try {
                cacheHotelCacheChange.setStartDate(DateUtil.convertToDate(hotelCacheChangeInfo.getDateRange().getStartDate()));
                cacheHotelCacheChange.setEndDate(DateUtil.convertToDate(hotelCacheChangeInfo.getDateRange().getEndDate()));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            int retCode = hotelDao.createCacheHotelCacheChange(cacheHotelCacheChange);
            if (retCode != 1) {
                rs = "ER#" + cacheHotelCacheChange.getHotelCode() + "," + cacheHotelCacheChange.getRatePlanCode();
                break;
            } else {
                saveCount++;
            }
        }

        int spantime = DateUtil.getPastTime(basetime);
        if (spantime > 2000) {
           logger.info("processHotelCacheChangeResponse\'s time is " + spantime + " ms, clean EntityManager.");
            hotelDao.clearAllCache();
        }

        return rs;
    }
    @Override
    public String importHotelSearchRS(String filename) {
        String rs = "HAHA";
        SAXReader reader = new SAXReader();

               logger.info("filename: " + filename);

        File file = new File(filename);
        if (file.exists() == false) return null;

        Document document = null;// 读取XML文件
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
            logger.info("AccessCount=" + headerElement.attributeValue("AccessCount")
                    + ", CurrentCount=" + headerElement.attributeValue("CurrentCount")
                    + ", RecentlyTime=" + headerElement.attributeValue("RecentlyTime")
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

        String xml = "";

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
        Document document = null;// 读取XML文件
        try {
            document = reader.read(new StringReader(response));
        } catch (DocumentException e) {
            e.printStackTrace();
            return "ER#xml->document";
        }

        try {
            rs = processHotelDetailResponse(document);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            logger.info(response);
            return "ER#processHotelDetailResponse";
        }

        return rs;
    }

    @Override
    public String importHotelDetailResponse(String filename) {
        String rs;
        SAXReader reader = new SAXReader();

        logger.info("filename: " + filename);

        File file = new File(filename);
        if (file.exists() == false) {
            rs = "ER#File is not existed.";
            return rs;
        }

        Document document = null;// 读取XML文件
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
    public String searchHotelRatePlan(List<String> hotelCodes, int periodId, boolean returnXml) {
        if (hotelCodes == null || hotelCodes.size()==0)
            return "ER#hotelcodes is null or empty.";

        //检查缓存，看看header节点中的API调用频率限制
        Cache cache = getCache();
        String cacheKey = ConfigData.OTA_HotelRatePlan_Request;
        net.sf.ehcache.Element cacheElement = cache.get(cacheKey);
        if (cacheElement != null) {
            Element headerElement = (Element)cacheElement.getValue();
            int accessCount = Integer.parseInt(headerElement.attributeValue("AccessCount"));
            int currentCount = Integer.parseInt(headerElement.attributeValue("CurrentCount"));
            logger.info("AccessCount=" + headerElement.attributeValue("AccessCount")
                    + ", CurrentCount=" + headerElement.attributeValue("CurrentCount")
                    + ", RecentlyTime=" + headerElement.attributeValue("RecentlyTime")
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
        request.createHotelRatePlanRequest();

        for(String hotelCode: hotelCodes) {
            RatePlanHotelRef ratePlanHotelRef = new RatePlanHotelRef();
            ratePlanHotelRef.setHotelCode(hotelCode);
            RatePlanCandidate ratePlanCandidate = new RatePlanCandidate();
            ratePlanCandidate.getRatePlanHotelRefs().add(ratePlanHotelRef);

            DateRange ratePlanDateRange = new DateRange();
            int minDay = (periodId - 1) * 28;
            int maxDay = periodId * 28;
            if (maxDay > 90) maxDay = 90;
            ratePlanDateRange.setStartDate(DateUtil.formatDate(DateUtil.add(DateUtil.getCurDate(), minDay), "yyyy-MM-dd"));
            ratePlanDateRange.setEndDate(DateUtil.formatDate(DateUtil.add(DateUtil.getCurDate(), maxDay), "yyyy-MM-dd"));

            RatePlanRequest ratePlanRequest = new RatePlanRequest();
            ratePlanRequest.getHotelRatePlanCandidates().add(ratePlanCandidate);
            ratePlanRequest.setDateRange(ratePlanDateRange);

            request.getHotelRatePlaneRequest().getRatePlanRequests().add(ratePlanRequest);
        }

        String xml = "";

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

            org.dom4j.Document doc1 = DocumentHelper.createDocument();

            org.dom4j.Element rootElement = createRequestHeaderElement(doc1, ConfigData.OTA_HotelRatePlan_Request);
            rootElement.add(requestElement);

            xml = doc1.asXML();

        } catch (Exception e) {
            e.printStackTrace();
            return "ER#request xml is wrong.";
        }
        logger.debug(xml);
        String paraName = "requestXML";

        //logger.info(xml);
        Date date0 = DateUtil.getCurDateTime();
        String response = execApiRequest(xml, ConfigData.OTA_HotelRatePlan_Url, paraName);
        int apiElapsedTime = DateUtil.getPastTime(date0);
        logger.info(ConfigData.OTA_HotelRatePlan_Request + ": api耗时 " + apiElapsedTime + "ms");

        if (returnXml) return response;

        //处理结果
        String rs;
        SAXReader reader = new SAXReader();
        Document document = null;// 读取XML文件
        try {
            document = reader.read(new StringReader(response));
        } catch (DocumentException e) {
            e.printStackTrace();
            return "ER#xml->document";
        }

        try {
            rs = processHotelRatePlanResponse(document, periodId);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info(response);
            return "ER#processHotelRatePlanResponse";
        }

        return rs;
    }

    @Override
    public String searchHotelRatePlan(List<String> hotelCodes, int periodId) {
        return searchHotelRatePlan(hotelCodes, periodId, false);
    }

    @Override
    public String importHotelRatePlanResponse(String filename) {
        String rs = "HAHA";
        SAXReader reader = new SAXReader();

        logger.info("filename: " + filename);

        File file = new File(filename);
        if (file.exists() == false) return null;

        Document document = null;// 读取XML文件
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

    private String searchHotelCacheChange(int cityId) {
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
                    + ", RecentlyTime=" + headerElement.attributeValue("RecentlyTime")
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

        Date timestamp1 = DateUtil.getCurDateTime();
        timestamp1 = DateUtil.addMinutes(timestamp1, -intervalCacheChange);
        timestamp1 = DateUtil.getDateOnTheHour(timestamp1);

        String cacheFromTimeStamp = DateUtil.formatDate(timestamp1, "yyyy-MM-dd'T'HH:mm:ss");
        request.getHotelCacheChangeRequest().getCacheSearchCriteria().setCacheFromTimestamp(cacheFromTimeStamp);
        request.getHotelCacheChangeRequest().getCacheSearchCriteria().getCacheSearchCriterion().setHotelCityCode(cityId);

        String xml = "";

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

            org.dom4j.Document doc1 = DocumentHelper.createDocument();

            org.dom4j.Element rootElement = createRequestHeaderElement(doc1, ConfigData.OTA_HotelCacheChange_Request);
            rootElement.add(requestElement);

            xml = doc1.asXML();
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
    public String searchHotelCacheChange() {
        return searchHotelCacheChange(2);
    }

    @Override
    public String importHotelCacheChangeResponse(String filename) {
        String rs = "HAHA";
        SAXReader reader = new SAXReader();

        logger.info("filename: " + filename);

        File file = new File(filename);
        if (file.exists() == false) return null;

        Document document = null;// 读取XML文件
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

        List<String> hotelCodes0 = hotelDao.getAllHotelCodes();

        int count = 0;
        List<String> hotelCodes = new ArrayList<String>();
        for (String hotelCode : hotelCodes0) {
            CacheHotel cacheHotel = hotelDao.getCacheHotel(hotelCode);
            if (cacheHotel != null) {
                //判断上次更新到现在是否超过7天
                int spanDays = DateUtil.getPastDays(cacheHotel.getCacheTime());
                if (spanDays < 7) {
                    logger.info(hotelCode + "\'s is already updated lately.");
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

    @Override
    public String refreshAllRatePlan() {

        List<String> hotelCodes0;
        if (getCache().get(ConfigData.HOTEL_CODES_WAITING_FOR_RATE_PLAN) == null) {
            hotelCodes0 = hotelDao.getAllHotelCodes();
        }  else {
            hotelCodes0 = (List<String>) getCache().get(ConfigData.HOTEL_CODES_WAITING_FOR_RATE_PLAN).getObjectValue();
        }



        return refreshRatePlans(hotelCodes0);

    }

    @Override
    public String refreshRatePlans(List<String> hotelCodes) {
        String rs = "";
        int count = 0, cachedCount = 0, loopCount = 0;
        List<String> hotelCodes1 = new ArrayList<String>();

        cn.buk.hotel.entity.HotelInfo hotelInfo;
        logger.info("hotel total for refreshing rate plan: " + hotelCodes.size());

        boolean bBreak = false;
        int idx = 0;
        for (String hotelCode : hotelCodes) {
            idx++;

            if (loopCount > 100) {
                List<String> copyHotelCodes = hotelCodes.subList(idx - 1, hotelCodes.size());
                //放置到缓存，用于记录header中的参数
                //timeToLiveSecond is the time in seconds from the point of creation in the cache till it expires (regardless of how frequently it is accessed in that time)
                //timeToIdleSeconds is the time in seconds before an object in the cache expires if it is not accessed.
                getCache().put(new net.sf.ehcache.Element(ConfigData.HOTEL_CODES_WAITING_FOR_RATE_PLAN, copyHotelCodes, false, 1200000, 1800000));
                //bBreak = true;

                break;
            }


            hotelInfo = hotelDao.getHotelInfoByHotelCode(hotelCode);
            if (hotelInfo == null || hotelInfo.getRatePlanStatus() == -1) {
                logger.info(hotelCode + " skipped for rate plan status is -1.");
                continue;
            }

            CacheRatePlan cacheRatePlan = hotelDao.getCacheRatePlan(hotelCode, 1);
            if (cacheRatePlan != null) {
                //判断上次更新到现在是否超过7天
                int spanDays = DateUtil.getPastDays(cacheRatePlan.getCacheTime());

                if (spanDays < 7) {
                    cachedCount++;
                    logger.info(hotelCode + " skipped for cached time is short.");
                    continue;
                }
            } else {
                cacheRatePlan = new CacheRatePlan();
                cacheRatePlan.setHotelCode(hotelCode);
                cacheRatePlan.setPeriodId(1);
                cacheRatePlan.setBeginTime(DateUtil.getCurDateTime());
                if (hotelDao.createCacheRatePlan(cacheRatePlan) == 1) {

                }
            }

            hotelCodes1.add(hotelCode);
            count++;
            if (count < 10) continue;

            loopCount++;

            for (int periodId = 1; periodId <= 4; periodId++) {
                rs = searchHotelRatePlan(hotelCodes1, periodId);
                if (rs.substring(0, 2).equalsIgnoreCase("OK")) {
                    if (periodId == 4) {
                        count = 0;
                        hotelCodes1.clear();
                    }
                } else {
                    //出错首先暂时退出
                    logger.warn("exit loop for (" + rs + ").");
                    bBreak = true;
                    break;
                }
            }

            if (bBreak) break;
        }

        logger.info("cached rate plan: " + cachedCount);

        return rs;
    }

    @Override
    public String refreshRatePlansByCityId(int cityId) {
        List<String> hotelCodes0 = hotelDao.getAllHotelCodesByCityId(cityId);
        return refreshRatePlans(hotelCodes0);
    }

    @Override
    public String refreshHotelCacheChange() {
        List<City> cities = cityDao.getAllCity();
        int max = cities.size();
        int index = 0;
        for(City city: cities) {
            if (city.getOpenApiId() == 0) continue;
            if (city.getCityCode().equalsIgnoreCase("CN")==false) continue;

            index++;
            logger.info("refreshHotelCacheChange\'s process: " + index + "/" + max);

            String xml = searchHotelCacheChange(city.getOpenApiId());
            if (xml.length() == 0) break;
            processHotelCacheChange(xml);
        }

        return null;
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

    private String processHotelCacheChange(String xml) {
        String retval = "NA";
        //处理结果
        SAXReader reader = new SAXReader();

        Document document = null;// 读取XML文件
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

    public HotelDao getHotelDao() {
        return hotelDao;
    }

    public void setHotelDao(HotelDao hotelDao) {
        this.hotelDao = hotelDao;
    }

    public int getIntervalCacheChange() {
        return intervalCacheChange;
    }

    public void setIntervalCacheChange(int intervalCacheChange) {
        this.intervalCacheChange = intervalCacheChange;
    }

    public String getAllianceId() {
        return allianceId;
    }

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
