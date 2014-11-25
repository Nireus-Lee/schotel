/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.qms.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ResponseUtil {
	
	public static void writeResponse(String rs, HttpServletResponse response) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        
        PrintWriter pw;
		try {
			pw = response.getWriter();
			pw.print(rs); 
			pw.flush();
		} catch (IOException e) {
		}
	}

    public static void writeXmlResponse(String rs, HttpServletResponse response) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/xml");
        //response.setHeader("Cache-Control" ,  "no-cache" );

        PrintWriter pw;
        try {
            pw = response.getWriter();
            pw.print(rs);
            pw.flush();
        } catch (IOException e) {
        }
    }


}
