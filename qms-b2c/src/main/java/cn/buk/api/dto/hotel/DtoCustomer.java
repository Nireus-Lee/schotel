/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto.hotel;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * Created by william on 2014-11-16.
 */
public class DtoCustomer {

    @XStreamAlias("CustomerValue")
    @XStreamAsAttribute
    private int customerValue;

    public int getCustomerValue() {
        return customerValue;
    }

    public void setCustomerValue(int customerValue) {
        this.customerValue = customerValue;
    }
}
