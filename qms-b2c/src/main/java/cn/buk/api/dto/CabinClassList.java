/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * User: yfdai
 * Date: 14-10-17
 * Time: 下午2:55
 */
public class CabinClassList {

    @XStreamImplicit(itemFieldName = "CabinClass")
    private List<String> cabinClasses;

    public List<String> getCabinClasses() {
        return cabinClasses;
    }

    public void setCabinClasses(List<String> cabinClasses) {
        this.cabinClasses = cabinClasses;
    }
}
