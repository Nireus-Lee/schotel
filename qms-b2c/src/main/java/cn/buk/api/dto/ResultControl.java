/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * User: yfdai
 * Date: 14-10-21
 * Time: 上午8:47
 */
public class ResultControl {
    //    <!--指定请问返回结果数目-->
//    <MaxResultCount>10</MaxResultCount>
    @XStreamAlias("MaxResultCount")
    private int maxResultCount = 0;
//    <!--结果排序参数-->
//    <SortInstruction>
    @XStreamAlias("SortInstruction")
    private SortInstruction sortInstruction = new SortInstruction();
//    <!--中转类型：0 – 返回所有数据
//    1 – 仅返回直飞数据，即往返程查询返回：（直飞，直飞）；单程查询返回：直飞
//    2 – 仅返回非直飞数据，即往返程查询返回：（非直飞，非直飞）；单程查询返回：非直飞
//    4 – 仅在往返程查询下起作用，返回：（直飞，非直飞）和（非直飞，直飞）；单程查询这个参数不起作用
//    5 – 仅返回一程中转，即往返程查询返回：（一程中转，一程中转）；单程查询返回：一程中转
//    6 – 往返程查询返回：（直飞，一程中转）和（一程中转，直飞）；单程查询返回：直飞或一程中转
//    7 – 往返程查询返回：（直飞，直飞）、（一程中转，一程中转）、（直飞，一程中转）和（一程中转，直飞），即1,5,6的并集；单程查询返回：直飞或一程中转，在单程查询中，6和7的意义相同
//    8 – 往返程查询返回：（非“直飞或一程中转”，非“直飞或一程中转”）、（直飞，非“直飞或一程中转”）、（一程中转，非“直飞或一程中转”）、（非“直飞或一程中转”，直飞）和（非“直飞或一程中转”、一程中转）；单程查询返回：非“直飞或一程中转”-->
//    <TransferType>
//    1
//    </TransferType>
    @XStreamAlias("TransferType")
    private int transferType = 1;

    public SortInstruction getSortInstruction() {
        return sortInstruction;
    }

    public void setSortInstruction(SortInstruction sortInstruction) {
        this.sortInstruction = sortInstruction;
    }

    public int getMaxResultCount() {
        return maxResultCount;
    }

    public void setMaxResultCount(int maxResultCount) {
        this.maxResultCount = maxResultCount;
    }

    public int getTransferType() {
        return transferType;
    }

    public void setTransferType(int transferType) {
        this.transferType = transferType;
    }


//    <!--是否只返回每个PU下的默认票台价格的PriceInfo-->
//    <DefaultPriceOnly>true</DefaultPriceOnly>
//    <!--是否只返回每个FlightProductGroup下的最低价格的Price-->
//    <LowestPriceOnly>true</LowestPriceOnly>

}
