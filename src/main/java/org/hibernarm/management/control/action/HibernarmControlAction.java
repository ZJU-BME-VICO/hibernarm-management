package org.hibernarm.management.control.action;

import org.hibernarm.service.AQLExecute;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HibernarmControlAction {
	public String execute() {
	    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml", HibernarmControlAction.class);		
	    AQLExecute client = (AQLExecute)context.getBean("wsclient");
	    client.stop();
	    client.reconfigure();
	    client.start();
		return "success";
	}
}
