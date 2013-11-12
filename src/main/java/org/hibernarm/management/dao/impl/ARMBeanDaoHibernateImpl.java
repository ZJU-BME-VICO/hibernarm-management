package org.hibernarm.management.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernarm.management.dao.virtual.ARMBeanDao;
import org.hibernarm.management.model.ARMBean;
import org.hibernarm.management.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;



public class ARMBeanDaoHibernateImpl implements ARMBeanDao{
    private static Logger logger=Logger.getLogger(ARMBeanDaoHibernateImpl.class.getName());
	public void saveOrUpdate(ARMBean bean) {
		Session session=HibernateUtil.currentSession();
		Transaction tx=session.getTransaction();
		try{
		tx.begin();
		Query query=session.createQuery("from ARMBean as arm where arm.name=:conditionname");
		query.setString("conditionname", bean.getName());
		ARMBean existBean=(ARMBean)query.uniqueResult();
		if(existBean!=null){
			existBean.setContent(bean.getContent());
			existBean.setModifyTime(bean.getModifyTime());
			} else {
				session.save(bean);
			}
			tx.commit();
		}catch(Exception e){
			tx.rollback();
			logger.error("error when save or update ARMBean"+e.getMessage());
			throw new RuntimeException("error when save or update ARMBean"+e.getMessage());
		}
		
	}

	public ARMBean findByName(String name) {
		Session session=HibernateUtil.currentSession();
		Query query=session.createQuery("from ARMBean as arm where arm.name=:conditionname");
		query.setString("conditionname", name);
		ARMBean armBean=(ARMBean)query.uniqueResult();
		if(armBean!=null){
			session.evict(armBean);
		}
		return armBean;
	}

	public List<ARMBean> selectAll() {
		Session session = HibernateUtil.currentSession();
		Query query = session.createQuery("from ARMBean");
		List<ARMBean> arms = query.list();
		return arms;
	}
}
