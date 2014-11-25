/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto;

/**
 * User: yfdai
 * Date: 14-10-16
 * Time: 上午11:21
 */
public class RouteSearchControl {
    //    <!--
//    路由查询使用场景：
//    用户点击“预订”按钮时的路由查询：
//      当用户点击”预订“按钮时，说明航班/舱位/价格等信息都已经确定，这个时候客户端只需要发送一个新的路由查询请求
//      （包含该航班/舱位/价格对应的RouteSearchToken）给Aggregator，即可获取该航班/舱位/价格的详细信息。
//      这种情况路由查询返回的结果只包括一个产品和价格，又称反向查询。请求报文应添加如下节点：
//          <SearchContext>
//              <RouteSearchControl>
//                  <RouteSearchMode>AllOrigDestsStrict</RouteSearchMode>
//                  <RouteSearchToken>{Ctrip--1#}{HO1309,1,Y,M|HO1306,2,Y,M}{1/2[SHA-HO-M-HKG(NOA,N41492714G,S6978,C2468)]+1/2[HKG-HO-M-SHA(NOA,N41492714G,S6978,C2468)]}</RouteSearchToken>
//              </RouteSearchControl>
//          </SearchContext>
//    用户点击“返程”按钮时的路由查询：
//      当用户点击”返程“按钮时（往返查询），说明去程航班已经确定，这个时候客户端只需要发送一个新的路由查询请求
//      （包含该航班/舱位/价格对应的RouteSearchToken）给Aggregator，即可获取与用户选择的去程航班相匹配的所有结果。
//      这种情况路由查询返回的结果通常包括多个产品和价格。请求报文应添加如下节点：
//          <SearchContext>
//              <RouteSearchControl>
//                  <RouteSearchMode>AllOrigDests</RouteSearchMode>
//                  <RouteSearchToken>{Ctrip--1#}{HO1309,1,Y,M|HO1306,2,Y,M}{1/2[SHA-HO-M-HKG(NOA,N41492714G,S6978,C2468)]+1/2[HKG-HO-M-SHA(NOA,N41492714G,S6978,C2468)]}</RouteSearchToken>
//                  <OrigDestSeqID>1</OrigDestSeqID>
//              </RouteSearchControl>
//          </SearchContext>
//      其中，RouteSearchMode的值为AllOrigDests，表示不需要严格匹配；OrigDestSeqID的值为1，表示第一个行程（去程）已经选中。
//    路由查询方式：
//      None：不使用基于路由（航班/舱位）的查询
//      NextOrigDest：返回与RouteSearchToken的航班相匹配的，包含下一个（由OrigDestSeqID指定）行程航班的结果
//      AllOrigDests：返回与RouteSearchToken的前OrigDestSeqID个行程的航班相匹配的，包含所有行程航班的结果。路由（航班/舱位）匹配的最大行程序号 0表示无匹配 N（N>0）表示需要匹配前N个行程的路由（航班/舱位）其它情况，该字段不使用。
//      AllOrigDestsStrict：返回与RouteSearchToken的所有行程的航班和舱位严格匹配的，包含所有行程航班的结果

//    <RouteSearchMode>AllOrigDestsStrict</RouteSearchMode>
    private String  routeSearchMode;
//    <!--选定的产品和价格对应的完整路由（航班/舱位）-->
    //<RouteSearchToken>{Ctrip--0#}{HO1305,1,Y,S}{SHA-HO-S-HKG(NOA,N40850638,SFare,C2468)}</RouteSearchToken>
    private String routeSearchToken;
    //<!--航程序号(单程为1，往返程为1-2，联程类推)-->
    //<OrigDestSeqID>1</OrigDestSeqID>
    private int origDestSeqID;
}
