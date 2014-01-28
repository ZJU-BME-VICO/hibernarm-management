package edu.zju.bme.hibernarm.management.control.action;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.zju.bme.hibernarm.service.AQLExecute;

public class HibernarmReconfigureAction {	
	public String execute() {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"/beans.xml", HibernarmReconfigureAction.class);
		AQLExecute client = (AQLExecute) context.getBean("wsclient");

		HibernarmControl control = new HibernarmControl();
		control.execute(client);

		return "success";
	}
}
