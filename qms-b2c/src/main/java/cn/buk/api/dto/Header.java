/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto;

import com.ctrip.openapi.java.utils.ConfigData;
import com.ctrip.openapi.java.utils.SignatureUtils;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import org.dom4j.Element;

/**
 * User: yfdai
 * Date: 14-10-16
 * Time: 下午3:48
 */
public class Header {

    @XStreamAlias("AllianceID")
    @XStreamAsAttribute
    private String allianceID;

    @XStreamAsAttribute
    @XStreamAlias("SID")
    private String sid;

    @XStreamAsAttribute
    @XStreamAlias("TimeStamp")
    private String timeStamp;

    @XStreamAsAttribute
    @XStreamAlias("Signature")
    private String signature;

    @XStreamAsAttribute
    @XStreamAlias("RequestType")
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

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }
}
