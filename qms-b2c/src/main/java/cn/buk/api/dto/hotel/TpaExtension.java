/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto.hotel;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * User: yfdai
 * Date: 14-10-30
 * Time: 下午12:07
 */
@XStreamAlias("TPA_Extension")
public class TpaExtension {

    @XStreamAlias("FacilityName")
    private String facilityName;

    @XStreamAlias("FTypeName")
    private String typeName;

    @XStreamAlias("FacilityValue")
    private String facilityValue;
}
