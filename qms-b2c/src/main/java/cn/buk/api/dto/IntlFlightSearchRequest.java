/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

/**
 * 国际航班查询请求
 * User: yfdai
 * Date: 14-10-16
 * Time: 上午10:42
 */
public class IntlFlightSearchRequest {

    //<!--最终用户直接设定的参数-->
//    <SearchCriteria>
    @XStreamAlias("SearchCriteria")
    private SearchCriteria searchCriteria = new SearchCriteria();

//    <!--结果控制-->
//    <ResultControl>
    @XStreamAlias("ResultControl")
    private ResultControl resultControl = new ResultControl();

//    <!--查询情景信息-->
    @XStreamAlias("SearchContext")
    @XStreamOmitField
    private SearchContext searchContext = new SearchContext();


    public SearchCriteria getSearchCriteria() {
        return searchCriteria;
    }

    public void setSearchCriteria(SearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    public SearchContext getSearchContext() {
        return searchContext;
    }

    public void setSearchContext(SearchContext searchContext) {
        this.searchContext = searchContext;
    }

    public ResultControl getResultControl() {
        return resultControl;
    }

    public void setResultControl(ResultControl resultControl) {
        this.resultControl = resultControl;
    }
}


