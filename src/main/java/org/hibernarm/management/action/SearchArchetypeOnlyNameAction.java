package org.hibernarm.management.action;

import java.util.List;

public class SearchArchetypeOnlyNameAction {
	private String condition;
	private List<String> nameList;
	public String execute(){
		
		return "success";
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public List<String> getNameList() {
		return nameList;
	}
	public void setNameList(List<String> nameList) {
		this.nameList = nameList;
	}
	

}
