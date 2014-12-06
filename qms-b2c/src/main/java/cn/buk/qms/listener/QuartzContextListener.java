/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.qms.listener;

import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * User: yfdai
 * Date: 14-9-1
 * Time: 下午3:54
 */
public class QuartzContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        WebApplicationContext webApplicationContext = (WebApplicationContext) arg0
                .getServletContext()
                .getAttribute(
                        WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);

        Object obj = webApplicationContext.getBean("startQuartz");
        //System.out.println(obj.getClass().getName());
        org.quartz.impl.StdScheduler startQuartz = (org.quartz.impl.StdScheduler) obj;
        if(startQuartz != null) {
            //System.out.println("startQuertz.shutdown();");
            startQuartz.shutdown();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
