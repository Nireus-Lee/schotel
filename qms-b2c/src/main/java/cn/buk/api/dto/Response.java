/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

/**
 * User: william
 * Date: 14-10-17
 * Time: 下午10:34
 */
public class Response {

    @XStreamAlias("Header")
    private Header header;

    @XStreamAlias("IntlFlightSearchResponse")
    private IntlFlightSearchResponse responseBody;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public IntlFlightSearchResponse getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(IntlFlightSearchResponse responseBody) {
        this.responseBody = responseBody;
    }
}
