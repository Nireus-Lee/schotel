/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto.hotel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * User: william
 * Date: 14-10-29
 * Time: 下午8:23
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class HotelDescriptiveInfo {

    @XmlAttribute(name="HotelCode")
    private String hotelCode;

    @XmlAttribute(name="PositionTypeCode")
    private String positionTypeCode;


//    <!-- SendData属性：是否请求酒店类信息；bool类型，True表示需要发送酒店类信息；可空 -->
//    <HotelInfo SendData="true"/>
    @XmlElement(name="HotelInfo", namespace = "http://www.opentravel.org/OTA/2003/05")
    private HotelInfoSendData hotelInfoSendData = new HotelInfoSendData();

//    <!--设施类信息（客房、会议室、餐饮、娱乐），这里仅限请求酒店客房设施-->
//    <!-- SendGuestRooms属性：是否发送客房信息；bool类型，True表示需要发送；可空 -->
//    <FacilityInfo SendGuestRooms="true"/>
    @XmlElement(name="FacilityInfo", namespace = "http://www.opentravel.org/OTA/2003/05")
    private HotelInfoFacilityInfo hotelInfoFacilityInfo = new HotelInfoFacilityInfo();
//    <!--位置区域类信息-->
//    <!-- SendAttractions属性：是否发送景点地标信息；bool类型，True表示需要发送；可空 -->
//    <!-- SendRecreations属性：是否发送娱乐区域信息；bool类型，True表示需要发送；可空 -->
//    <AreaInfo SendAttractions="true" SendRecreations="true"/>
    @XmlElement(name="AreaInfo", namespace = "http://www.opentravel.org/OTA/2003/05")
    private HotelInfoAreaInfo hotelInfoAreaInfo = new HotelInfoAreaInfo();
//    <!--联系方式类信息-->
//    <!-- SendData属性：是否发送联系方式类数据，bool类型，True表示需要发送；可空 -->
//    <ContactInfo SendData="true"/>
    @XmlElement(name="ContactInfo", namespace = "http://www.opentravel.org/OTA/2003/05")
    private HotelInfoContactInfo hotelInfoContactInfo = new HotelInfoContactInfo();
//    <!--多媒体信息-->
//    <!-- SendData属性：是否发送多媒体数据，bool类型，True表示需要发送；可空 -->
//    <MultimediaObjects SendData="true"/>
    @XmlElement(name="MultimediaObjects", namespace = "http://www.opentravel.org/OTA/2003/05")
    private HotelInfoMultimedia hotelInfoMultimedia = new HotelInfoMultimedia();


    public String getHotelCode() {
        return hotelCode;
    }

    public void setHotelCode(String hotelCode) {
        this.hotelCode = hotelCode;
    }

    public String getPositionTypeCode() {
        return positionTypeCode;
    }

    public void setPositionTypeCode(String positionTypeCode) {
        this.positionTypeCode = positionTypeCode;
    }





}
