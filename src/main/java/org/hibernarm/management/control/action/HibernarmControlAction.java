package org.hibernarm.management.control.action;

import java.util.List;

import org.hibernarm.management.dao.impl.ARMBeanDaoHibernateImpl;
import org.hibernarm.management.dao.impl.ArchetypeBeanDaoHibernateImpl;
import org.hibernarm.management.dao.virtual.ARMBeanDao;
import org.hibernarm.management.dao.virtual.ArchetypeBeanDao;
import org.hibernarm.management.model.ARMBean;
import org.hibernarm.management.model.ArchetypeBean;
import org.hibernarm.service.AQLExecute;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HibernarmControlAction {
	public String execute() {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml", HibernarmControlAction.class);
		AQLExecute client = (AQLExecute) context.getBean("wsclient");
		ArchetypeBeanDao archetypeBeanDao = new ArchetypeBeanDaoHibernateImpl();
		ARMBeanDao armBeanDao = new ARMBeanDaoHibernateImpl();
		List<ArchetypeBean> archetypes = archetypeBeanDao.selectAll();
		List<ARMBean> arms = armBeanDao.selectAll();
		client.stop();
		for (ArchetypeBean archetype : archetypes) {
			ARMBean arm = getArm(archetype.getName(), arms);
			if (arm != null) {
				client.registerArchetype(
					archetype.getName(), archetype.getContent(), arm.getContent());
			}
		}
		client.reconfigure();
		client.start();
		return "success";
	}

	protected ARMBean getArm(String archetypeId, List<ARMBean> arms) {
		for (ARMBean arm : arms) {
			if (arm.getName().compareTo(archetypeId) == 0) {
				return arm;
			}
		}

		return null;
	}
}
