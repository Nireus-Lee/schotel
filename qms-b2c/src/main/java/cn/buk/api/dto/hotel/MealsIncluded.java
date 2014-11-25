/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto.hotel;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * User: yfdai
 * Date: 14-10-31
 * Time: 下午2:22
 */
public class MealsIncluded {

    @XStreamAlias("Breakfast")
    private boolean breakfast;

    @XStreamAlias("NumberOfBreakfast")
    private int numberOfBreakfast;

    public boolean isBreakfast() {
        return breakfast;
    }

    public void setBreakfast(boolean breakfast) {
        this.breakfast = breakfast;
    }

    public int getNumberOfBreakfast() {
        return numberOfBreakfast;
    }

    public void setNumberOfBreakfast(int numberOfBreakfast) {
        this.numberOfBreakfast = numberOfBreakfast;
    }
}
