package org.hibernarm.management.archetype.action;

import org.hibernarm.management.dao.impl.ARMBeanDaoHibernateImpl;
import org.hibernarm.management.dao.impl.ArchetypeBeanDaoHibernateImpl;
import org.hibernarm.management.dao.virtual.ARMBeanDao;
import org.hibernarm.management.dao.virtual.ArchetypeBeanDao;
import org.hibernarm.management.model.ARMBean;
import org.hibernarm.management.model.ArchetypeBean;
import org.hibernarm.management.util.HibernateUtil;

public class DisplayContentAction {
	private String keyName;
	private ArchetypeBean archetypeBean;
	private ARMBean armBean;
	private static ArchetypeBeanDao archetypeBeanDao = new ArchetypeBeanDaoHibernateImpl();
	private static ARMBeanDao armBeanDao = new ARMBeanDaoHibernateImpl();

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public ArchetypeBean getArchetypeBean() {
		return archetypeBean;
	}

	public void setArchetypeBean(ArchetypeBean archetypeBean) {
		this.archetypeBean = archetypeBean;
	}

	public ARMBean getArmBean() {
		return armBean;
	}

	public void setArmBean(ARMBean armBean) {
		this.armBean = armBean;
	}

	public String execute() {
		String result = "success";
		try {
			archetypeBean = archetypeBeanDao.selectByName(keyName);
			armBean = armBeanDao.findByName(keyName);
		} catch (Exception e) {
			result = "fail";
		} finally {
			HibernateUtil.closeSession();
		}
		return result;
	}
}
