/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto.hotel;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by william on 2014-11-16.
 */
@XStreamAlias("Profile")
public class Profile {

    @XStreamAlias("Customer")
    private DtoCustomer dtoCustomer;

    public DtoCustomer getDtoCustomer() {
        return dtoCustomer;
    }

    public void setDtoCustomer(DtoCustomer dtoCustomer) {
        this.dtoCustomer = dtoCustomer;
    }
}
