/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.qms.util;



import javax.servlet.http.HttpServletRequest;

public class Common {
	static public String getIpAddr(HttpServletRequest request) {
		return request.getRemoteAddr();
		
	}

}

