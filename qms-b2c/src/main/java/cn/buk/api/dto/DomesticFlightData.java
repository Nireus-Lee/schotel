/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto;

import java.util.Date;

/**
 * User: yfdai
 * Date: 14-9-16
 * Time: 下午4:52
 */
public class DomesticFlightData {

//    <!--出发机场三字码-->
//    <DepartCityCode>SHA</DepartCityCode>
    private String departCityCode;
//    <!--到达机场三字码-->
//    <ArriveCityCode>BJS</ArriveCityCode>
    private String arriveCityCode;
//    <!--起飞时间：yyyy-MM-ddThh:mm:ss-->
//    <TakeOffTime>2013-05-20T07:55:00</TakeOffTime>
    private Date takeOffTime;
//    <!--抵达时间：yyyy-MM-ddThh:mm:ss-->
//    <ArriveTime>2013-05-20T10:15:00</ArriveTime>
    private Date arriveTime;
//    <!--航班号-->
//    <Flight>CA1858</Flight>
    private String flightNo;
//    <!--机型-->
//    <CraftType>33A</CraftType>
    private String craftType;
//    <!--航空公司代码-->
//    <AirlineCode>CA</AirlineCode>
    private String airlineCode;
//    <!--舱位等级：对应航班查询结果的FlightClass 字段-->
//    <Class>Y</Class>
    private String cabinClass;
//    <!--子舱位-->
//    <SubClass>B</SubClass>
    private String subClass;
//    <!--显示用舱位-->
//    <DisplaySubclass>B</DisplaySubclass>
    private String displaySubclass;
//    <!--航班扣率-->
//    <Rate>0.90</Rate>
    private float rate;
//    <!--航班票价-->
//    <Price>1020</Price>
    private float price;
//    <!--标准价-->
//    <StandardPrice>1130.0000</StandardPrice>
    private float standardPrice;
//    <!--儿童标准价-->
//    <ChildStandardPrice>570</ChildStandardPrice>
    private float childStandardPrice;
//    <!--婴儿标准价-->
//    <BabyStandardPrice>110</BabyStandardPrice>
    private float babyStandardPrice;
//    <!--餐食类型-->
//    <MealType>B</MealType>
    private String mealType;
//    <!--成人税-->
//    <AdultTax>50</AdultTax>
    private int adultTax;
//    <!--婴儿税-->
//    <BabyTax>0</BabyTax>
    private int babyTax;
//    <!--儿童税-->
//    <ChildTax>0</ChildTax>
    private int childTax;
//    <!--成人燃油费用-->
//    <AdultOilFee>110.0000</AdultOilFee>
    private float adultOilFee;
//    <!--婴儿燃油费用-->
//    <BabyOilFee>0</BabyOilFee>
    private float babyOilFee;
//    <!--儿童燃油费用-->
//    <ChildOilFee>50.0000</ChildOilFee>
    private float childOilFee;
//    <!--出发机场代码-->
//    <DPortCode>SHA</DPortCode>
    private String dportCode;
//    <!--抵达机场代码-->
//    <APortCode>PEK</APortCode>
    private String aportCode;
//    <!--出发机场航站楼ID-->
//    <DPortBuildingID>35</DPortBuildingID>
    private String dportBuildingId;
    private int aportBuildingCheckInTime;
    private int dportBuildingCheckInTime;
//    <!--到达机场航站楼ID -->
//    <APortBuildingID>3</APortBuildingID>
    private String aportBuildingId;
//    <!--经停次数-->
//    <StopTimes>0</StopTimes>
    private int stopTimes;
//    <!--更标示-->
//    <Nonrer>H</Nonrer>
    private String nonrer;
//    <!--转签标示-->
//    <Nonend>T</Nonend>
    private String nonend;
//    <!--退票标示-->
//    <Nonref>H</Nonref>
    private String nonref;
//    <!--更改说明 （中文 或 英文）中文、英文 的显示取决于请求Header 节Culture 值-->
//    <Rernote>起飞（含）前同等舱位更改需收取票面价10％的更改费，起飞后需收取票面价20％的更改费，改期费与升舱费同时发生时，则需同时收取改期费和升舱差额。收费更改请到国航直属售票处办理。</Rernote>
    private String rernote;
//    <!--转签说明 （中文 或 英文）中文、英文 的显示取决于请求Header 节Culture 值-->
//    <Endnote>不得签转。</Endnote>
    private String endnote;
//    <!--退票说明 （中文 或 英文）
//    中文、英文 的显示取决于请求Header 节Culture 值-->
//    <Refnote>起飞（含）前需收取票面价20％的退票费，起飞后需收取票面价30％的退票费。</Refnote>
    private String refnote;
//    <!--备注 （中文或英文）中文、英文 的显示取决于请求Header 节Culture 值-->
//    <Remarks>儿童加CHD</Remarks>
    private String remarks;
//    <!--票种,中文、英文 的显示取决于请求Header 节Culture 值-->
//    <TicketType>1111</TicketType>
    private String ticketType;
//    <!--提前预定天数-->
//    <BeforeFlyDate>0</BeforeFlyDate>
    private int beforeFlyDate;
//    <!--剩余票量Int32 中文、英文 的显示取决于请求Header 节Culture 值-->
//    <Quantity>6</Quantity>
    private int quantity;
//    <!--价格类型 NormalPrice ：普通,SingleTripPrice：提前预售特价,CZSpecialPrice：南航特价-->
//    <PriceType>NormalPrice</PriceType>
    private String priceType;
//    <!--机票产品类型:Normal,YOUNGMAN,OLDMAN-->
//    <ProductType>Normal</ProductType>
    private String productType;
//    <!--机票产品来源:1携程机票频道,2共享平台,3两者共有,4直连产品-->
//    <ProductSource>1</ProductSource>
    private String productSource;
//    <!--库存类型:FIX：定额   FAV：见舱-->
//    <InventoryType>Fav</InventoryType>
    private String inventoryType;
//    <!--航程索引-->
//    <RouteIndex>0</RouteIndex>
    private int routeIndex;
//    <!--是否K位-->
//    <NeedApplyString>F</NeedApplyString>
    private String needApplyString;
//    <!--推荐等级-->
//    <Recommend>2</Recommend>
    private int recommend;
//    <!--退票费公式ID-->
//    <RefundFeeFormulaID>16</RefundFeeFormulaID>
    private int refundFeeFormulaId;
//    <!--是否可升舱-->
//    <CanUpGrade>true</CanUpGrade>
    private boolean canUpGrade;
//    <!--是否可单独销售-->
//    <CanSeparateSale />
//    <!--是否随订随售-->
//    <CanNoDefer>false</CanNoDefer>
    private boolean canNoDefer;
//    <!--是否飞人航班-->
//    <IsFlyMan>false</IsFlyMan>
    private boolean flyMan;
//    <!--是否政策限本地-->
//    <OnlyOwnCity>false</OnlyOwnCity>
    private boolean onlyOwnCity;
//    <!--是否最低价-->
//    <IsLowestPrice>true</IsLowestPrice>
    private boolean lowestPrice;
//    <!--是否南航最低价-->
//    <IsLowestCZSpecialPrice>false</IsLowestCZSpecialPrice>
    private boolean lowestCZSpecialPrice;
//    <!--参考准点率-->
//    <PunctualityRate>0</PunctualityRate>
//    <!--政策ID-->
//    <PolicyID>496006</PolicyID>
    private int policyId;
//    <!--原政策可出票种:对应航班查询结果中的DeliverTicketType 字段-->
//    <AllowCPType>1111</AllowCPType>
    private String allowCpType;
//    <!--是否超出邮寄时间-->
//    <OutOfPostTime>false</OutOfPostTime>
//    <!--是否在送票取票时间之外-->
//    <OutOfSendGetTime>false</OutOfSendGetTime>
//    <!--是否在柜台取票时间之外-->
//    <OutOfAirlineCounterTime>false</OutOfAirlineCounterTime>
//    <!--是否可邮寄-->
//    <CanPost>true</CanPost>
//    <!--是否可航空公司柜台取消-->
//    <CanAirlineCounter>true</CanAirlineCounter>
//    <!--是否可市内送票取票-->
//    <CanSendGet>true</CanSendGet>
//    <!--是否返现-->
//    <IsRebate>true</IsRebate>
    private boolean rebate;
//    <!--返现金额-->
//    <RebateAmount>50</RebateAmount>
    private int rebateAmount;

    public int getRebateAmount() {
        return rebateAmount;
    }

    public void setRebateAmount(int rebateAmount) {
        this.rebateAmount = rebateAmount;
    }

    public boolean isRebate() {
        return rebate;
    }

    public void setRebate(boolean rebate) {
        this.rebate = rebate;
    }

    public boolean isLowestPrice() {
        return lowestPrice;
    }

    public void setLowestPrice(boolean lowestPrice) {
        this.lowestPrice = lowestPrice;
    }

    public boolean isFlyMan() {
        return flyMan;
    }

    public void setFlyMan(boolean flyMan) {
        this.flyMan = flyMan;
    }

    public boolean isCanUpGrade() {
        return canUpGrade;
    }

    public void setCanUpGrade(boolean canUpGrade) {
        this.canUpGrade = canUpGrade;
    }

    public int getRefundFeeFormulaId() {
        return refundFeeFormulaId;
    }

    public void setRefundFeeFormulaId(int refundFeeFormulaId) {
        this.refundFeeFormulaId = refundFeeFormulaId;
    }

    public int getRecommend() {
        return recommend;
    }

    public void setRecommend(int recommend) {
        this.recommend = recommend;
    }

    public String getNeedApplyString() {
        return needApplyString;
    }

    public void setNeedApplyString(String needApplyString) {
        this.needApplyString = needApplyString;
    }

    public int getRouteIndex() {
        return routeIndex;
    }

    public void setRouteIndex(int routeIndex) {
        this.routeIndex = routeIndex;
    }

    public String getInventoryType() {
        return inventoryType;
    }

    public void setInventoryType(String inventoryType) {
        this.inventoryType = inventoryType;
    }

    public String getProductSource() {
        return productSource;
    }

    public void setProductSource(String productSource) {
        this.productSource = productSource;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getEndnote() {
        return endnote;
    }

    public void setEndnote(String endnote) {
        this.endnote = endnote;
    }

    public String getRernote() {
        return rernote;
    }

    public void setRernote(String rernote) {
        this.rernote = rernote;
    }

    public String getNonref() {
        return nonref;
    }

    public void setNonref(String nonref) {
        this.nonref = nonref;
    }

    public String getNonend() {
        return nonend;
    }

    public void setNonend(String nonend) {
        this.nonend = nonend;
    }

    public String getNonrer() {
        return nonrer;
    }

    public void setNonrer(String nonrer) {
        this.nonrer = nonrer;
    }

    public int getStopTimes() {
        return stopTimes;
    }

    public void setStopTimes(int stopTimes) {
        this.stopTimes = stopTimes;
    }

    public String getDportBuildingId() {
        return dportBuildingId;
    }

    public void setDportBuildingId(String dportBuildingId) {
        this.dportBuildingId = dportBuildingId;
    }

    public String getAportCode() {
        return aportCode;
    }

    public void setAportCode(String aportCode) {
        this.aportCode = aportCode;
    }

    public float getChildOilFee() {
        return childOilFee;
    }

    public void setChildOilFee(float childOilFee) {
        this.childOilFee = childOilFee;
    }

    public float getBabyOilFee() {
        return babyOilFee;
    }

    public void setBabyOilFee(float babyOilFee) {
        this.babyOilFee = babyOilFee;
    }

    public float getAdultOilFee() {
        return adultOilFee;
    }

    public void setAdultOilFee(float adultOilFee) {
        this.adultOilFee = adultOilFee;
    }

    public int getChildTax() {
        return childTax;
    }

    public void setChildTax(int childTax) {
        this.childTax = childTax;
    }

    public int getBabyTax() {
        return babyTax;
    }

    public void setBabyTax(int babyTax) {
        this.babyTax = babyTax;
    }

    public int getAdultTax() {
        return adultTax;
    }

    public void setAdultTax(int adultTax) {
        this.adultTax = adultTax;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public float getBabyStandardPrice() {
        return babyStandardPrice;
    }

    public void setBabyStandardPrice(float babyStandardPrice) {
        this.babyStandardPrice = babyStandardPrice;
    }

    public float getChildStandardPrice() {
        return childStandardPrice;
    }

    public void setChildStandardPrice(float childStandardPrice) {
        this.childStandardPrice = childStandardPrice;
    }

    public float getStandardPrice() {
        return standardPrice;
    }

    public void setStandardPrice(float standardPrice) {
        this.standardPrice = standardPrice;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getDisplaySubclass() {
        return displaySubclass;
    }

    public void setDisplaySubclass(String displaySubclass) {
        this.displaySubclass = displaySubclass;
    }

    public String getSubClass() {
        return subClass;
    }

    public void setSubClass(String subClass) {
        this.subClass = subClass;
    }

    public String getCabinClass() {
        return cabinClass;
    }

    public void setCabinClass(String cabinClass) {
        this.cabinClass = cabinClass;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public String getCraftType() {
        return craftType;
    }

    public void setCraftType(String craftType) {
        this.craftType = craftType;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public Date getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(Date arriveTime) {
        this.arriveTime = arriveTime;
    }

    public Date getTakeOffTime() {
        return takeOffTime;
    }

    public void setTakeOffTime(Date takeOffTime) {
        this.takeOffTime = takeOffTime;
    }

    public String getArriveCityCode() {
        return arriveCityCode;
    }

    public void setArriveCityCode(String arriveCityCode) {
        this.arriveCityCode = arriveCityCode;
    }

    public String getDepartCityCode() {
        return departCityCode;
    }

    public void setDepartCityCode(String departCityCode) {
        this.departCityCode = departCityCode;
    }

    public int getBeforeFlyDate() {
        return beforeFlyDate;
    }

    public void setBeforeFlyDate(int beforeFlyDate) {
        this.beforeFlyDate = beforeFlyDate;
    }

    public String getRefnote() {
        return refnote;
    }

    public void setRefnote(String refnote) {
        this.refnote = refnote;
    }

    public String getAportBuildingId() {
        return aportBuildingId;
    }

    public void setAportBuildingId(String aportBuildingId) {
        this.aportBuildingId = aportBuildingId;
    }

    public String getDportCode() {
        return dportCode;
    }

    public void setDportCode(String dportCode) {
        this.dportCode = dportCode;
    }


    public boolean isCanNoDefer() {
        return canNoDefer;
    }

    public void setCanNoDefer(boolean canNoDefer) {
        this.canNoDefer = canNoDefer;
    }

    public boolean isOnlyOwnCity() {
        return onlyOwnCity;
    }

    public void setOnlyOwnCity(boolean onlyOwnCity) {
        this.onlyOwnCity = onlyOwnCity;
    }

    public boolean isLowestCZSpecialPrice() {
        return lowestCZSpecialPrice;
    }

    public void setLowestCZSpecialPrice(boolean lowestCZSpecialPrice) {
        this.lowestCZSpecialPrice = lowestCZSpecialPrice;
    }

    public int getPolicyId() {
        return policyId;
    }

    public void setPolicyId(int policyId) {
        this.policyId = policyId;
    }

    public String getAllowCpType() {
        return allowCpType;
    }

    public void setAllowCpType(String allowCpType) {
        this.allowCpType = allowCpType;
    }

    public int getAportBuildingCheckInTime() {
        return aportBuildingCheckInTime;
    }

    public void setAportBuildingCheckInTime(int aportBuildingCheckInTime) {
        this.aportBuildingCheckInTime = aportBuildingCheckInTime;
    }

    public int getDportBuildingCheckInTime() {
        return dportBuildingCheckInTime;
    }

    public void setDportBuildingCheckInTime(int dportBuildingCheckInTime) {
        this.dportBuildingCheckInTime = dportBuildingCheckInTime;
    }
//    <RebateCPCity />
}
