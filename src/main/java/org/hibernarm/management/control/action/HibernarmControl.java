package org.hibernarm.management.control.action;

import java.util.ArrayList;
import java.util.List;

import org.hibernarm.management.dao.impl.ARMBeanDaoHibernateImpl;
import org.hibernarm.management.dao.impl.ArchetypeBeanDaoHibernateImpl;
import org.hibernarm.management.dao.virtual.ARMBeanDao;
import org.hibernarm.management.dao.virtual.ArchetypeBeanDao;
import org.hibernarm.management.model.ARMBean;
import org.hibernarm.management.model.ArchetypeBean;
import org.hibernarm.service.AQLExecute;

public class HibernarmControl {
	public void execute(AQLExecute client) {
		ArchetypeBeanDao archetypeBeanDao = new ArchetypeBeanDaoHibernateImpl();
		ARMBeanDao armBeanDao = new ARMBeanDaoHibernateImpl();
		List<ArchetypeBean> archetypes = archetypeBeanDao.selectAll();
		List<ARMBean> arms = armBeanDao.selectAll();
		client.stop();
		List<String> archetypesStrings = new ArrayList<String>();
		for (ArchetypeBean archetype : archetypes) {
			archetypesStrings.add(archetype.getContent());
		}
		List<String> armStrings = new ArrayList<String>();
		for (ARMBean arm : arms) {
			armStrings.add(arm.getContent());
		}
		client.reconfigure(archetypesStrings, armStrings);
		client.start();
	}
}
