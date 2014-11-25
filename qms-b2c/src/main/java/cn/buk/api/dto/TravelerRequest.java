/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * User: yfdai
 * Date: 14-10-16
 * Time: 下午1:50
 */
@XStreamAlias("TravelerRequest")
public class TravelerRequest {

    //<!--乘客类别：成人Adult/儿童Child/婴儿（不占座）InfantInLap/婴儿（占座）InfantInSeat/老人Senior-->
//    <TravelerCategoryCode>
//    Adult
//            </>
    @XStreamAlias("TravelerCategoryCode")
    private String  travelerCategoryCode;
//    <!--乘客人数-->
//    <TravelerCount>
//    1
//    </>
    @XStreamAlias("TravelerCount")
    private int travelerCount;

//    <!--乘客资质：ALL 全部 ADT 普通成人 CHD 儿童 EMI 移民 LAB 劳务人员 NEW 新移民 SEA 海员 SRC 老年人 STU 学生 TAI 台商 VFR 探亲访友 WOM 女士 YTH 青年-->
//    <TravelerEligibilityCode>
//    ADT
//            </>
@XStreamAlias("TravelerEligibilityCode")
    private String travelerEligibilityCode;

    public String getTravelerCategoryCode() {
        return travelerCategoryCode;
    }

    public void setTravelerCategoryCode(String travelerCategoryCode) {
        this.travelerCategoryCode = travelerCategoryCode;
    }

    public int getTravelerCount() {
        return travelerCount;
    }

    public void setTravelerCount(int travelerCount) {
        this.travelerCount = travelerCount;
    }

    public String getTravelerEligibilityCode() {
        return travelerEligibilityCode;
    }

    public void setTravelerEligibilityCode(String travelerEligibilityCode) {
        this.travelerEligibilityCode = travelerEligibilityCode;
    }
}
