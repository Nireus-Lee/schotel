/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto.hotel;

import com.sun.corba.se.pept.transport.ContactInfo;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import java.util.List;

/**
 * User: yfdai
 * Date: 14-10-30
 * Time: 上午11:22
 */
@XStreamAlias("HotelDescriptiveContent")
public class HotelDescriptiveContent {

    @XStreamAsAttribute
    @XStreamAlias("HotelId")
    private int hotelId;

    @XStreamAsAttribute
    @XStreamAlias("HotelCode")
    private String hotelCode;

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

    @XStreamAlias("HotelInfo")
    private HotelInfo hotelInfo;

    @XStreamAlias("FacilityInfo")
    private FacilityInfo facilityInfo;

    @XStreamAlias("Policies")
    private List<HotelPolicy> hotelPolicies;

    @XStreamAlias("AreaInfo")
    private AreaInfo areaInfo;

    @XStreamAlias("AffiliationInfo")
    private AffiliationInfo affiliationInfo;

    @XStreamAlias("MultimediaDescriptions")
    private List<MultimediaDescription> multimediaDescriptions;

    @XStreamAlias("ContactInfos")
    private List<HotelContactInfo> contactInfos;

    @XStreamAlias("TPA_Extensions")
    private TpaExtensions tpaExtensions;

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelCode() {
        return hotelCode;
    }

    public void setHotelCode(String hotelCode) {
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

    public HotelInfo getHotelInfo() {
        return hotelInfo;
    }

    public void setHotelInfo(HotelInfo hotelInfo) {
        this.hotelInfo = hotelInfo;
    }

    public FacilityInfo getFacilityInfo() {
        return facilityInfo;
    }

    public void setFacilityInfo(FacilityInfo facilityInfo) {
        this.facilityInfo = facilityInfo;
    }

    public List<HotelPolicy> getHotelPolicies() {
        return hotelPolicies;
    }

    public void setHotelPolicies(List<HotelPolicy> hotelPolicies) {
        this.hotelPolicies = hotelPolicies;
    }

    public AreaInfo getAreaInfo() {
        return areaInfo;
    }

    public void setAreaInfo(AreaInfo areaInfo) {
        this.areaInfo = areaInfo;
    }

    public AffiliationInfo getAffiliationInfo() {
        return affiliationInfo;
    }

    public void setAffiliationInfo(AffiliationInfo affiliationInfo) {
        this.affiliationInfo = affiliationInfo;
    }

    public List<MultimediaDescription> getMultimediaDescriptions() {
        return multimediaDescriptions;
    }

    public void setMultimediaDescriptions(List<MultimediaDescription> multimediaDescriptions) {
        this.multimediaDescriptions = multimediaDescriptions;
    }

    public List<HotelContactInfo> getContactInfos() {
        return contactInfos;
    }

    public void setContactInfos(List<HotelContactInfo> contactInfos) {
        this.contactInfos = contactInfos;
    }

    public TpaExtensions getTpaExtensions() {
        return tpaExtensions;
    }

    public void setTpaExtensions(TpaExtensions tpaExtensions) {
        this.tpaExtensions = tpaExtensions;
    }
}
