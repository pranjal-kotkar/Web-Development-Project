package com.example;

import jakarta.servlet.ServletContextEvent;

import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener 
public class MyServletContextListener implements ServletContextListener 
{ @Override
	public void contextInitialized(ServletContextEvent sce) 
{ String configFilePath = sce.getServletContext().getRealPath("/WEB-INF/resources/jaas.config");
System.setProperty("java.security.auth.login.config", configFilePath); 
System.out.println("JAAS configuration loaded: " + System.getProperty("java.security.auth.login.config")); }
@Override 
public void contextDestroyed(ServletContextEvent sce) { // Cleanup code if needed 
	
}
}

