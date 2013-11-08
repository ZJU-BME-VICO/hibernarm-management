package org.hibernarm.management.dao.virtual;

import org.hibernarm.management.model.ARMBean;

public interface ARMBeanDao {
	void saveOrUpdate(ARMBean bean);
	ARMBean findByName(String name);

}
