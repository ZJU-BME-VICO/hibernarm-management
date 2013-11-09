package org.hibernarm.management.util;

import java.io.FileInputStream;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.PropertyConfigurator;

public class Log4jInit extends HttpServlet{
	public Log4jInit(){
		
	}
	public void init(ServletConfig config){
		String prefix=config.getServletContext().getRealPath("/");
		String file=config.getInitParameter("log4j");
		String filePath=prefix+file;
		System.out.println(prefix);
		Properties props=new Properties();
	    try{
	    	FileInputStream iStream=new FileInputStream(filePath);
	    	props.load(iStream);
	    	String logFile=prefix+props.getProperty("log4j.appender.R.File");
	    	props.setProperty("log4j.appender.R.File", logFile);
	    	PropertyConfigurator.configure(props);
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	}

}
