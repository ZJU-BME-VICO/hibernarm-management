package edu.zju.bme.hibernarm.management.control.action;

import java.util.ArrayList;
import java.util.List;

import edu.zju.bme.hibernarm.management.dao.ARMBeanDao;
import edu.zju.bme.hibernarm.management.dao.ARMBeanDaoImpl;
import edu.zju.bme.hibernarm.management.dao.ArchetypeBeanDao;
import edu.zju.bme.hibernarm.management.dao.ArchetypeBeanDaoImpl;
import edu.zju.bme.hibernarm.management.model.ARMBean;
import edu.zju.bme.hibernarm.management.model.ArchetypeBean;
import edu.zju.bme.hibernarm.service.AQLExecute;

public class HibernarmControl {
	public void execute(AQLExecute client) {
		ArchetypeBeanDao archetypeBeanDao = new ArchetypeBeanDaoImpl();
		ARMBeanDao armBeanDao = new ARMBeanDaoImpl();
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
