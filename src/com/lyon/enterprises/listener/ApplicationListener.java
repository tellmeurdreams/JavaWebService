package com.lyon.enterprises.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.lyon.enterprises.dto.XmlReader;

@WebListener
public class ApplicationListener implements ServletContextListener{

	public void contextInitialized(ServletContextEvent servletContextEvent) {
		
		ServletContext ctx = servletContextEvent.getServletContext();
		String fileLocation = ctx.getInitParameter("FILE_LOCATION");
		XmlReader rdr = XmlReader.getInstance();
		rdr.XmlToJson(fileLocation);
		
		System.out.println("Hello ----------- contextInitialized");
	}
	
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		System.out.println("Bye -------------- contextDestroyed");
	}
}
