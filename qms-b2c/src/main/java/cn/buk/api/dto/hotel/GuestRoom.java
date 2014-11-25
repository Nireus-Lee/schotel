/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto.hotel;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import java.util.List;

/**
 * User: yfdai
 * Date: 14-10-30
 * Time: 上午11:49
 */
@XStreamAlias("GuestRoom")
public class GuestRoom {

    /**
     * RoomTypeName属性：房型名称
     */
    @XStreamAsAttribute
    @XStreamAlias("RoomTypeName")
    private String roomTypeName;

    /**
     * 客房属性
     */
    @XStreamAlias("TypeRoom")
    private RoomType roomType;

    /**
     * 设施设备列表
     */
    @XStreamAlias("Amenities")
    private List<Amenity> amenities;

    @XStreamAlias("TPA_Extensions")
    private List<TpaExtension> tpaExtensions;

    @XStreamAlias("Features")
    private List<HotelFeature> hotelFeatures;

    @XStreamAlias("DescriptiveText")
    private String descriptiveText;

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public List<Amenity> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<Amenity> amenities) {
        this.amenities = amenities;
    }

    public List<TpaExtension> getTpaExtensions() {
        return tpaExtensions;
    }

    public void setTpaExtensions(List<TpaExtension> tpaExtensions) {
        this.tpaExtensions = tpaExtensions;
    }

    public List<HotelFeature> getHotelFeatures() {
        return hotelFeatures;
    }

    public void setHotelFeatures(List<HotelFeature> hotelFeatures) {
        this.hotelFeatures = hotelFeatures;
    }

    public String getDescriptiveText() {
        return descriptiveText;
    }

    public void setDescriptiveText(String descriptiveText) {
        this.descriptiveText = descriptiveText;
    }
}
