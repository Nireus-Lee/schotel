/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto.hotel;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * User: yfdai
 * Date: 14-10-28
 * Time: 下午4:09
 */

@XStreamAlias("Property")
public class HotelInfo {

    @XStreamAsAttribute
    @XStreamAlias("HotelId")
    private int hotelId;

    @XStreamAsAttribute
    @XStreamAlias("HotelCode")
    private int hotelCode;

    @XStreamAsAttribute
    @XStreamAlias("BrandCode")
    private int brandCode;

    @XStreamAsAttribute
    @XStreamAlias("AreaID")
    private int areaId;

    @XStreamAsAttribute
    @XStreamAlias("HotelCityCode")
    private int hotelCityCode;

    @XStreamAsAttribute
    @XStreamAlias("HotelName")
    private String hotelName;

    @XStreamAsAttribute
    @XStreamAlias("WhenBuilt")
    private String whenBuilt;

    @XStreamAsAttribute
    @XStreamAlias("LastUpdated")
    private String whenUpdated;


    @XStreamAlias("VendorMessages")
    private List<VendorMessage> vendorMessages;

    @XStreamAlias("Position")
    private HotelPosition hotelPosition;

    @XStreamAlias("Address")
    private Address address;

    @XStreamImplicit(itemFieldName = "Award")
    private List<HotelAward> awards;

    @XStreamImplicit(itemFieldName = "RelativePosition")
    private List<RelativePosition> relativePositions;

    @XStreamAlias("TPA_Extensions")
    private TpaExtensions tpaExtensions;


    @XStreamAlias("CategoryCodes")
    private List<SegmentCategory> segmentCategories;

    @XStreamAlias("Services")
    private List<HotelInfoService> hotelInfoServices;

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public int getHotelCode() {
        return hotelCode;
    }

    public void setHotelCode(int hotelCode) {
        this.hotelCode = hotelCode;
    }

    public int getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(int brandCode) {
        this.brandCode = brandCode;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public int getHotelCityCode() {
        return hotelCityCode;
    }

    public void setHotelCityCode(int hotelCityCode) {
        this.hotelCityCode = hotelCityCode;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public List<VendorMessage> getVendorMessages() {
        return vendorMessages;
    }

    public void setVendorMessages(List<VendorMessage> vendorMessages) {
        this.vendorMessages = vendorMessages;
    }

    public HotelPosition getHotelPosition() {
        return hotelPosition;
    }

    public void setHotelPosition(HotelPosition hotelPosition) {
        this.hotelPosition = hotelPosition;
    }

    public List<RelativePosition> getRelativePositions() {
        return relativePositions;
    }

    public void setRelativePositions(List<RelativePosition> relativePositions) {
        this.relativePositions = relativePositions;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<HotelAward> getAwards() {
        return awards;
    }

    public void setAwards(List<HotelAward> awards) {
        this.awards = awards;
    }

    public TpaExtensions getTpaExtensions() {
        return tpaExtensions;
    }

    public void setTpaExtensions(TpaExtensions tpaExtensions) {
        this.tpaExtensions = tpaExtensions;
    }

    public List<SegmentCategory> getSegmentCategories() {
        return segmentCategories;
    }

    public void setSegmentCategories(List<SegmentCategory> segmentCategories) {
        this.segmentCategories = segmentCategories;
    }

    public List<HotelInfoService> getHotelInfoServices() {
        return hotelInfoServices;
    }

    public void setHotelInfoServices(List<HotelInfoService> hotelInfoServices) {
        this.hotelInfoServices = hotelInfoServices;
    }

    public String getWhenBuilt() {
        return whenBuilt;
    }

    public void setWhenBuilt(String whenBuilt) {
        this.whenBuilt = whenBuilt;
    }

    public String getWhenUpdated() {
        return whenUpdated;
    }

    public void setWhenUpdated(String whenUpdated) {
        this.whenUpdated = whenUpdated;
    }
}
