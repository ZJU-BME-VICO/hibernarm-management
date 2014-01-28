package edu.zju.bme.hibernarm.management.dao.virtual;

import java.util.List;

import org.hibernate.Session;

import edu.zju.bme.hibernarm.management.model.ARMBean;

public interface ARMBeanDao {
	void saveOrUpdate(ARMBean bean, Session session);

	ARMBean findByName(String name);

	List<ARMBean> selectAll();

	void deleteAndRestore(ARMBean armBean, Session session);
}
