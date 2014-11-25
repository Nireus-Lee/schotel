/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto.hotel;

import com.ctrip.openapi.java.utils.ConfigData;
import com.ctrip.openapi.java.utils.SignatureUtils;

import javax.xml.bind.annotation.XmlAnyAttribute;


/**
 * User: william
 * Date: 14-10-24
 * Time: 下午11:10
 */
public class Header {

    @XmlAnyAttribute
    private String allianceID;

    @XmlAnyAttribute
    private String sid;

    @XmlAnyAttribute
    private String timeStamp;

    @XmlAnyAttribute
    private String signature;

    @XmlAnyAttribute
    private String requestType;

    public String getAllianceID() {
        return allianceID;
    }

    public void setAllianceID(String allianceID) {
        this.allianceID = allianceID;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }
}
