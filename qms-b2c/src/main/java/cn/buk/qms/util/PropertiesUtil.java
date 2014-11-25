/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.qms.util;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.LocalizedTextUtil;

public class PropertiesUtil {

	public static java.util.Properties pps;
	
	public static String getProperty(String key) {
	
		return LocalizedTextUtil.findDefaultText(key, ActionContext.getContext().getLocale());
	}
}
