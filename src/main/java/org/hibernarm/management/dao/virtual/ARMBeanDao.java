package org.hibernarm.management.dao.virtual;

import java.util.List;

import org.hibernarm.management.model.ARMBean;
import org.hibernate.Session;

public interface ARMBeanDao {
	void saveOrUpdate(ARMBean bean, Session session);

	ARMBean findByName(String name);

	List<ARMBean> selectAll();

	void deleteAndRestore(ARMBean armBean, Session session);
}
