/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto;

/**
 * User: yfdai
 * Date: 14-10-16
 * Time: 下午1:56
 */
public class PartitionSearchControl {
//    <!--是否需要分批查询-->
//    <NeedPartitionedSearchResults>true</NeedPartitionedSearchResults>
    private boolean needPartitionedSearchResults;
//    <!--分批查询Token，由Response输出
//    PartitionSearchToken使用场景：
//    每批的Token，第一次分批查询时为空，Aggregator会返回第一批结果，同时将所有的剩余分批Token（SearchResult.PartitionSearchTokenList）返回给调用方；然后调用方对每个剩余分批Token执行一次后续分批查询（将分批Token带入PartitionSearchToken参数）获取分批结果。有多个分批Token时可通过并行或串行方式执行后续分批查询，推荐使用并行方式，加快查询速度。如果第一次反批查询返回的分批Token列表为空，表示无后续分批查询。-->
//    <PartitionSearchToken></PartitionSearchToken>
    private String  PartitionSearchToken;
//    </PartitionSearchControl>
}
