/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * User: yfdai
 * Date: 14-10-17
 * Time: 下午4:47
 */
public class DateRanges {

    @XStreamAlias("AllowedDateRange")
    private String allowedDateRange;

    @XStreamAlias("ForbiddenDateRange")
    private String forbiddenDateRange;


    public String getAllowedDateRange() {
        return allowedDateRange;
    }

    public void setAllowedDateRange(String allowedDateRange) {
        this.allowedDateRange = allowedDateRange;
    }

    public String getForbiddenDateRange() {
        return forbiddenDateRange;
    }

    public void setForbiddenDateRange(String forbiddenDateRange) {
        this.forbiddenDateRange = forbiddenDateRange;
    }
}
