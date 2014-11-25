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
 * Time: 下午12:01
 *房间设施，是否可加床、免费宽带、付费宽带。
 * RoomAmenityCode：房间设施代码，参考CodeList (RMA)
 */
@XStreamAlias("Amenity")
public class Amenity {
//    <Amenity RoomAmenityCode="4">
//    <!--设施类型描述性文字-->
//    <DescriptiveText>有线宽带</DescriptiveText>
//    </Amenity>

    @XStreamAsAttribute
    @XStreamAlias("RoomAmenityCode")
    private int roomAmenityCode;

    @XStreamAlias("DescriptiveText")
    private String descriptiveText;



    public String getDescriptiveText() {
        return descriptiveText;
    }

    public void setDescriptiveText(String descriptiveText) {
        this.descriptiveText = descriptiveText;
    }

    public int getRoomAmenityCode() {
        return roomAmenityCode;
    }

    public void setRoomAmenityCode(int roomAmenityCode) {
        this.roomAmenityCode = roomAmenityCode;
    }
}
