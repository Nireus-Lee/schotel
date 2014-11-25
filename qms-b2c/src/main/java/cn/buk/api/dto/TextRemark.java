/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * User: william
 * Date: 14-10-17
 * Time: 下午9:45
 */
@XStreamAlias("TextRemark")
public class TextRemark {

    @XStreamAlias("RemarkID")
    private int id;

    @XStreamAlias("TextCN")
    private String textCn;

    @XStreamAlias("TextEN")
    private String textEn;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTextCn() {
        return textCn;
    }

    public void setTextCn(String textCn) {
        this.textCn = textCn;
    }

    public String getTextEn() {
        return textEn;
    }

    public void setTextEn(String textEn) {
        this.textEn = textEn;
    }
}
