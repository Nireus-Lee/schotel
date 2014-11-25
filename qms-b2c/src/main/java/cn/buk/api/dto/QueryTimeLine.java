/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * User: william
 * Date: 14-10-18
 * Time: 上午8:34
 */
@XStreamAlias("QueryTimeline")
public class QueryTimeLine {

    @XStreamAlias("EngineType")
    private String engineType;

    @XStreamAlias("QueryExecutionTime")
    private int queryExecutionTime;

    @XStreamAlias("NoResultReason")
    private String noResultReason;

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public int getQueryExecutionTime() {
        return queryExecutionTime;
    }

    public void setQueryExecutionTime(int queryExecutionTime) {
        this.queryExecutionTime = queryExecutionTime;
    }

    public String getNoResultReason() {
        return noResultReason;
    }

    public void setNoResultReason(String noResultReason) {
        this.noResultReason = noResultReason;
    }
}
