/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.qms.listener;

import cn.buk.qms.util.PropertiesUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.FileInputStream;
import java.util.Properties;

public class WebConfigListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		PropertiesUtil.pps = new Properties();

		try{

			String path = (getClass().getClassLoader().getResource("").toURI()).getPath();

			FileInputStream fis = new FileInputStream(path + "config.properties");

			PropertiesUtil.pps.load(fis);

		}catch (Exception e) {

			e.printStackTrace();

		}

	}

}
