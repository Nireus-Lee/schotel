/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto.hotel;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * User: william
 * Date: 14-10-28
 * Time: 下午11:00
 */
@XStreamAlias("VendorMessage")
public class VendorMessage {

    @XStreamAsAttribute
    @XStreamAlias("InfoType")
    private int infoType;

    @XStreamAlias("SubSection")
    private SubSection subSection;

    public int getInfoType() {
        return infoType;
    }

    public void setInfoType(int infoType) {
        this.infoType = infoType;
    }

    public SubSection getSubSection() {
        return subSection;
    }

    public void setSubSection(SubSection subSection) {
        this.subSection = subSection;
    }
}
