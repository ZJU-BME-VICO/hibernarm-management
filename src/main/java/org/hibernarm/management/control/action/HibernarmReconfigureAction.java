package org.hibernarm.management.control.action;

import org.hibernarm.service.AQLExecute;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HibernarmReconfigureAction {	
	public String execute() {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml", HibernarmReconfigureAction.class);
		AQLExecute client = (AQLExecute) context.getBean("wsclient");

		HibernarmControl control = new HibernarmControl();
		control.execute(client);

		return "success";
	}
}
