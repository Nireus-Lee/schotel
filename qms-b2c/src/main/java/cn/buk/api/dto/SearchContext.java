/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto;

/**
 * User: yfdai
 * Date: 14-10-16
 * Time: 上午10:50
 */
public class SearchContext {
    //<!--全舱查询Token（暂时只有TSK使用）-->
    private String allClassesSearchToken;
//    <!--基于路由的分段/反查控制-->
//    <RouteSearchControl>
    private RouteSearchControl routeSearchControl;

//    <!--分批查询控制-->
//    <PartitionSearchControl>
    private PartitionSearchControl partitionSearchControl;

}
