/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * User: yfdai
 * Date: 14-10-21
 * Time: 上午8:50
 */
public class SortInstruction {

    //    <!--Price,DepartureTime,ArrivalTime,FlightDuration.-->
//    <SortingField>Price</SortingField>
    @XStreamAlias("SortingField")
    private String sorttingField = "DepartureTime";

//    <!--ASC,DESC-->
//    <Direction>DESC</Direction>
    @XStreamAlias("Direction")
    private String direction = "ASC";

    public String getSorttingField() {
        return sorttingField;
    }

    public void setSorttingField(String sorttingField) {
        this.sorttingField = sorttingField;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
//    <!--结果排序参数-->
//    </SortInstruction>
}
