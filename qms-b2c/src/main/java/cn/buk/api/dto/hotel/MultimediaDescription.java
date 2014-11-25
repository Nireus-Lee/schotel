/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto.hotel;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.List;

/**
 * User: yfdai
 * Date: 14-10-30
 * Time: 下午12:49
 */
@XStreamAlias("MultimediaDescription")
public class MultimediaDescription {

    @XStreamAlias("ImageItems")
    private List<ImageItem> imageItems;

    @XStreamAlias("TextItems")
    private List<TextItem> textItems;

    public List<ImageItem> getImageItems() {
        return imageItems;
    }

    public void setImageItems(List<ImageItem> imageItems) {
        this.imageItems = imageItems;
    }

    public List<TextItem> getTextItems() {
        return textItems;
    }

    public void setTextItems(List<TextItem> textItems) {
        this.textItems = textItems;
    }
}
