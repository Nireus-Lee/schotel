/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/

package cn.buk.api.dto.hotel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * User: william
 * Date: 14-10-24
 * Time: 下午11:25
 */
@XmlRootElement(name="RequestBody")
@XmlAccessorType(XmlAccessType.FIELD)
public class HotelRequestBody {

    @XmlElement(name="OTA_HotelSearchRQ", namespace = "http://www.opentravel.org/OTA/2003/05")
    private HotelRequestRQ hotelRequestRQ;


    @XmlElement(name="OTA_HotelDescriptiveInfoRQ")
    private HotelDetailRequest hotelDetailRequest;

    @XmlElement(name="OTA_HotelRatePlanRQ", namespace = "http://www.opentravel.org/OTA/2003/05")
    private HotelRatePlanRequest hotelRatePlaneRequest;

    @XmlElement(name="OTA_HotelCacheChangeRQ", namespace = "http://www.opentravel.org/OTA/2003/05")
    private HotelCacheChangeRequest hotelCacheChangeRequest;

    public void createHotelRequestRQ() {
        this.hotelRequestRQ = new HotelRequestRQ();
    }

    public void createHotelDetailRequest() {
        this.hotelDetailRequest = new HotelDetailRequest();
    }

    public  void createHotelRatePlanRequest() {
        this.hotelRatePlaneRequest = new HotelRatePlanRequest();
    }

    public void createHotelCacheChangeRequest() {
        this.hotelCacheChangeRequest = new HotelCacheChangeRequest();
    }

    public HotelRequestRQ getHotelRequestRQ() {
        return hotelRequestRQ;
    }

    public void setHotelRequestRQ(HotelRequestRQ hotelRequestRQ) {
        this.hotelRequestRQ = hotelRequestRQ;
    }

    public HotelDetailRequest getHotelDetailRequest() {
        return hotelDetailRequest;
    }

    public void setHotelDetailRequest(HotelDetailRequest hotelDetailRequest) {
        this.hotelDetailRequest = hotelDetailRequest;
    }

    public HotelRatePlanRequest getHotelRatePlaneRequest() {
        return hotelRatePlaneRequest;
    }

    public void setHotelRatePlaneRequest(HotelRatePlanRequest hotelRatePlaneRequest) {
        this.hotelRatePlaneRequest = hotelRatePlaneRequest;
    }

    public HotelCacheChangeRequest getHotelCacheChangeRequest() {
        return hotelCacheChangeRequest;
    }

    public void setHotelCacheChangeRequest(HotelCacheChangeRequest hotelCacheChangeRequest) {
        this.hotelCacheChangeRequest = hotelCacheChangeRequest;
    }
}
