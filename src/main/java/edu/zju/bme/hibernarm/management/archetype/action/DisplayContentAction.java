package edu.zju.bme.hibernarm.management.archetype.action;

import edu.zju.bme.hibernarm.management.dao.ARMBeanDao;
import edu.zju.bme.hibernarm.management.dao.ARMBeanDaoImpl;
import edu.zju.bme.hibernarm.management.dao.ArchetypeBeanDao;
import edu.zju.bme.hibernarm.management.dao.ArchetypeBeanDaoImpl;
import edu.zju.bme.hibernarm.management.model.ARMBean;
import edu.zju.bme.hibernarm.management.model.ArchetypeBean;
import edu.zju.bme.hibernarm.management.util.HibernateUtil;

public class DisplayContentAction {
	private String keyName;
	private ArchetypeBean archetypeBean;
	private ARMBean armBean;
	private static ArchetypeBeanDao archetypeBeanDao = new ArchetypeBeanDaoImpl();
	private static ARMBeanDao armBeanDao = new ARMBeanDaoImpl();

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
