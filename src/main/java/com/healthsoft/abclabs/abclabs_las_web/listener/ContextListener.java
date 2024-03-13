package com.healthsoft.abclabs.abclabs_las_web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.healthsoft.abclabs.abclabs_las_web.util.AppConfigReaderUtil;

@WebListener
public class ContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext();
		context.setAttribute("BASE_URL", context.getContextPath() + "/");
		AppConfigReaderUtil readerUtil = new AppConfigReaderUtil();
		context.setAttribute("HOST",
				readerUtil.getProperty("app.web.host.domain") + ":" + readerUtil.getProperty("app.web.host.port"));
		System.out.println("ContextInitialized...");
		String baseUrl = (String) context.getAttribute("BASE_URL");
		String host = (String) context.getAttribute("HOST");

		System.out.println(baseUrl); // Assuming you have set BASE_URL somewhere else
		System.out.println(host);
		System.out.println("Application URL:= " + host + baseUrl);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}
}
