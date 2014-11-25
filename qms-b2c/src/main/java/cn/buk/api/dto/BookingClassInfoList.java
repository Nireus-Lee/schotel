/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * User: yfdai
 * Date: 14-10-17
 * Time: 下午2:37
 */
@XStreamAlias("BookingClassInfoList")
public class BookingClassInfoList {

    @XStreamImplicit(itemFieldName = "BookingClassInfo")
    private List<BookingClassInfo> bookingClassInfos;


    public List<BookingClassInfo> getBookingClassInfos() {
        return bookingClassInfos;
    }

    public void setBookingClassInfos(List<BookingClassInfo> bookingClassInfos) {
        this.bookingClassInfos = bookingClassInfos;
    }
}
