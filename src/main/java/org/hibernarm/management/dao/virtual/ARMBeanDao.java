package org.hibernarm.management.dao.virtual;

import java.util.List;

import org.hibernarm.management.model.ARMBean;

public interface ARMBeanDao {
	void saveOrUpdate(ARMBean bean);
	
	ARMBean findByName(String name);

	List<ARMBean> selectAll();
}
