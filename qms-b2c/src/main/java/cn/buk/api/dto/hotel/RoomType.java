/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto.hotel;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * User: yfdai
 * Date: 14-10-30
 * Time: 上午11:53
 */
public class RoomType {

    @XStreamAsAttribute
    @XStreamAlias("StandardOccupancy")
    private int standardOccupancy;

    @XStreamAsAttribute
    @XStreamAlias("Size")
    private String size;

    @XStreamAsAttribute
    @XStreamAlias("Name")
    private String name;

    @XStreamAsAttribute
    @XStreamAlias("RoomTypeCode")
    private String roomTypeCod;

    @XStreamAsAttribute
    @XStreamAlias("Floor")
    private String floor;

    @XStreamAsAttribute
    @XStreamAlias("InvBlockCode")
    private String invBlockCode;

    @XStreamAsAttribute
    @XStreamAlias("BedTypeCode")
    private String bedTypeCode;

    @XStreamAsAttribute
    @XStreamAlias("NonSmoking")
    private boolean nonSmoking;

    @XStreamAsAttribute
    @XStreamAlias("HasWindow")
    private int hasWindow;

    @XStreamAsAttribute
    @XStreamAlias("Quantity")
    private int quantity;

    @XStreamAsAttribute
    @XStreamAlias("RoomSize")
    private String roomSize;

    public int getStandardOccupancy() {
        return standardOccupancy;
    }

    public void setStandardOccupancy(int standardOccupancy) {
        this.standardOccupancy = standardOccupancy;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoomTypeCod() {
        return roomTypeCod;
    }

    public void setRoomTypeCod(String roomTypeCod) {
        this.roomTypeCod = roomTypeCod;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getInvBlockCode() {
        return invBlockCode;
    }

    public void setInvBlockCode(String invBlockCode) {
        this.invBlockCode = invBlockCode;
    }

    public String getBedTypeCode() {
        return bedTypeCode;
    }

    public void setBedTypeCode(String bedTypeCode) {
        this.bedTypeCode = bedTypeCode;
    }

    public boolean isNonSmoking() {
        return nonSmoking;
    }

    public void setNonSmoking(boolean nonSmoking) {
        this.nonSmoking = nonSmoking;
    }

    public int getHasWindow() {
        return hasWindow;
    }

    public void setHasWindow(int hasWindow) {
        this.hasWindow = hasWindow;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getRoomSize() {
        return roomSize;
    }

    public void setRoomSize(String roomSize) {
        this.roomSize = roomSize;
    }
}
