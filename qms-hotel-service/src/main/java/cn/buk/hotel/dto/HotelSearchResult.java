/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.hotel.dto;

import java.util.List;

/**
 * User: yfdai
 * Date: 14-11-14
 * Time: 上午9:03
 */
public class HotelSearchResult {

    private int pageNo;

    private int pageTotal;

    private int rowCount;

    private List<HotelInfoDto> hotels;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(int pageTotal) {
        this.pageTotal = pageTotal;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public List<HotelInfoDto> getHotels() {
        return hotels;
    }

    public void setHotels(List<HotelInfoDto> hotels) {
        this.hotels = hotels;
    }
}
