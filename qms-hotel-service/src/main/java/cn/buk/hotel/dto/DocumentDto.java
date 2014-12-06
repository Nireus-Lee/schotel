/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.hotel.dto;

import org.dom4j.Document;

/**
 * Created by william on 2014-12-5.
 */
public class DocumentDto {

    private Document document;

    private int periodId;

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public int getPeriodId() {
        return periodId;
    }

    public void setPeriodId(int periodId) {
        this.periodId = periodId;
    }
}
