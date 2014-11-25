/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.util.xstream;

import com.thoughtworks.xstream.converters.basic.DateConverter;

import java.util.TimeZone;

/**
 * User: yfdai
 * Date: 14-10-17
 * Time: 上午9:48
 */
public class MyDateConverter  extends DateConverter {
    public MyDateConverter(String dateFormat) {
       super(dateFormat, new String[] { dateFormat }, TimeZone.getDefault());

        System.out.println(dateFormat);
        TimeZone tz = TimeZone.getDefault();
        System.out.println(tz.getDisplayName());
    }
}
