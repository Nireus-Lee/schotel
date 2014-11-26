/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto.hotel;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * User: yfdai
 * Date: 14-10-30
 * Time: 下午12:51
 */
@XStreamAlias("ImageItem")
public class ImageItem {

    @XStreamAlias("Category")
    @XStreamAsAttribute
    private String category;

    @XStreamAlias("ImageFormat")
    private ImageFormat imageFormat;

    @XStreamAlias("Description")
    private ImageDescription imageDescription;

    @XStreamAlias("TPA_Extensions")
    private TpaExtensions tpaExtensions;

    public ImageFormat getImageFormat() {
        return imageFormat;
    }

    public void setImageFormat(ImageFormat imageFormat) {
        this.imageFormat = imageFormat;
    }

    public ImageDescription getImageDescription() {
        return imageDescription;
    }

    public void setImageDescription(ImageDescription imageDescription) {
        this.imageDescription = imageDescription;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public TpaExtensions getTpaExtensions() {
        return tpaExtensions;
    }

    public void setTpaExtensions(TpaExtensions tpaExtensions) {
        this.tpaExtensions = tpaExtensions;
    }
}
