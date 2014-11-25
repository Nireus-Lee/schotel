/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.List;

/**
 * User: william
 * Date: 14-10-17
 * Time: 下午10:49
 */

public class IntlFlightSearchResponse {

    @XStreamAlias("SearchResult")
    private IntlFlightSearchResult searchResult;

    @XStreamAlias("RedirectURI")
    private String redirectUrl;

    @XStreamAlias("QueryTimelineList")
    private List<QueryTimeLine> queryTimeLines;

    public IntlFlightSearchResult getSearchResult() {
        return searchResult;
    }

    public void setSearchResult(IntlFlightSearchResult searchResult) {
        this.searchResult = searchResult;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
}
