/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * User: yfdai
 * Date: 14-10-17
 * Time: 下午2:34
 */
@XStreamAlias("BookingClassInfo")
public class BookingClassInfo {

    @XStreamAlias("BookingClass")
    private String subClass;

    @XStreamAlias("CabinClass")
    private String cabinClass;

    public String getSubClass() {
        return subClass;
    }

    public void setSubClass(String subClass) {
        this.subClass = subClass;
    }

    public String getCabinClass() {
        return cabinClass;
    }

    public void setCabinClass(String cabinClass) {
        this.cabinClass = cabinClass;
    }
}
