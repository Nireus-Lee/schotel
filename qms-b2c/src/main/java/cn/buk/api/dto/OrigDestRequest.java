/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto;

import cn.buk.api.util.xstream.MyDateConverter;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

import java.util.Date;

/**
 * 行程信息
 * User: yfdai
 * Date: 14-10-16
 * Time: 下午1:47
 */
public class OrigDestRequest {

    //<!--出发日期-->
    @XStreamConverter(value=MyDateConverter.class, strings={"yyyy/MM/dd"})
    @XStreamAlias("Date")
    private Date date;

    //<!--出发城市-->
    @XStreamAlias("ORIG")
    private String orig;

    //<!--到达城市-->
    @XStreamAlias("DEST")
    private String dest;


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getOrig() {
        return orig;
    }

    public void setOrig(String orig) {
        this.orig = orig;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }
}
