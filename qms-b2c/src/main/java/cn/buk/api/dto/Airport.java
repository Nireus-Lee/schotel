/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * User: yfdai
 * Date: 14-10-17
 * Time: 下午5:17
 */
public class Airport {

    @XStreamAlias("Terminal")
    private String terminal;

    @XStreamAlias("PortCode")
    private String code;

    @XStreamAlias("PortName")
    private String name;

    @XStreamAlias("PortNameEN")
    private String nameEn;


    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }
}
