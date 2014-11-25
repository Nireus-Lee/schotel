/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto.hotel;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * User: yfdai
 * Date: 14-10-30
 * Time: 下午12:52
 */
@XStreamAlias("ImageFormat")
public class ImageFormat {

    @XStreamAlias("URL")
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
