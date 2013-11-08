package org.hibernarm.management.archetype.action;

import java.util.List;

import org.hibernarm.management.dao.impl.ArchetypeBeanDaoHibernateImpl;
import org.hibernarm.management.dao.virtual.ArchetypeBeanDao;
import org.hibernarm.management.model.ArchetypeBean;
import org.hibernarm.management.util.HibernateUtil;

public class SearchArchetypeOnlyNameAction {
	private String condition;
	private List<ArchetypeBean> archetypeBeanList;
	private static ArchetypeBeanDao archetypeBeanDao = new ArchetypeBeanDaoHibernateImpl();

	public String execute() {
		String result = "SUCEESS";
		try {
			archetypeBeanList = archetypeBeanDao
					.matchProbableByNamePart(condition);
			HibernateUtil.closeSession();
		} catch (Exception e) {
            result="FAIL";
		} finally {
			HibernateUtil.closeSession();
		}
		return result;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public List<ArchetypeBean> getArchetypeBeanList() {
		return archetypeBeanList;
	}

	public void setArchetypeBeanList(List<ArchetypeBean> archetypeBeanList) {
		this.archetypeBeanList = archetypeBeanList;
	}

}
