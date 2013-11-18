package org.hibernarm.management.control.action;

import org.hibernarm.service.AQLExecute;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HibernarmControlAction {
	public String execute() {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml", HibernarmControlAction.class);
		AQLExecute client = (AQLExecute) context.getBean("wsclient");

		HibernarmControl control = new HibernarmControl();
		control.execute(client);

		return "success";
	}
}
