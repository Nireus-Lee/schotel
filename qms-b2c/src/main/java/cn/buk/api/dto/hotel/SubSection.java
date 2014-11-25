/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto.hotel;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * User: william
 * Date: 14-10-28
 * Time: 下午11:04
 */
public class SubSection {

    @XStreamAlias("Paragraph")
    private Paragraph paragraph;

    public Paragraph getParagraph() {
        return paragraph;
    }

    public void setParagraph(Paragraph paragraph) {
        this.paragraph = paragraph;
    }
}
