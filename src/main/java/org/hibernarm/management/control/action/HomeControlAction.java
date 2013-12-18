package org.hibernarm.management.control.action;

import java.util.List;

import org.hibernarm.service.AQLExecute;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HomeControlAction {
	private List<String> archetypeIdList;
	
	public String execute() {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml", HibernarmControlAction.class);
		AQLExecute client = (AQLExecute) context.getBean("wsclient");
		setArchetypeIdList(client.getArchetypeIds());
		return "success";
	}
	
	public List<String> getArchetypeIdList() {
		return archetypeIdList;
	}
	
	public void setArchetypeIdList(List<String> archetypeIdList) {
		this.archetypeIdList = archetypeIdList;
	}
}
