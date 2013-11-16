package org.hibernarm.management.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernarm.management.dao.virtual.ARMBeanDao;
import org.hibernarm.management.model.ARMBean;
import org.hibernarm.management.model.HistoriedARMBean;
import org.hibernarm.management.util.CommitSequenceConstant;
import org.hibernarm.management.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

public class ARMBeanDaoHibernateImpl implements ARMBeanDao {
	private static Logger logger = Logger
			.getLogger(ARMBeanDaoHibernateImpl.class.getName());

	public void saveOrUpdate(ARMBean bean, Session session) {
		try {
			Query query = session
					.createQuery("from ARMBean as arm where arm.name=:conditionname");
			query.setString("conditionname", bean.getName());
			ARMBean existBean = (ARMBean) query.uniqueResult();
			if (existBean != null) {
				HistoriedARMBean historiedARMBean = new HistoriedARMBean();
				historiedARMBean.setCommitSequence(existBean
						.getCommitSequence());
				historiedARMBean.setContent(existBean.getContent());
				historiedARMBean.setHistoriedTime(bean.getModifyTime());
				historiedARMBean.setName(existBean.getName());
				session.save(historiedARMBean);
				existBean.setContent(bean.getContent());
				existBean.setModifyTime(bean.getModifyTime());
			} else {
				session.save(bean);
			}
		} catch (Exception e) {
			logger.error("error when save or update ARMBean" + e.getMessage());
			throw new RuntimeException("error when save or update ARMBean"
					+ e.getMessage());
		}

	}

	public ARMBean findByName(String name) {
		Session session = HibernateUtil.currentSession();
		Query query = session
				.createQuery("from ARMBean as arm where arm.name=:conditionname");
		query.setString("conditionname", name);
		ARMBean armBean = (ARMBean) query.uniqueResult();
		if (armBean != null) {
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

	public void deleteAndRestore(ARMBean armBean, Session session) {
		// create a historiedArmBean for armBean will be deleted
		HistoriedARMBean armBeanReadyRemoved = new HistoriedARMBean();
		armBeanReadyRemoved.setCommitSequence(armBean.getCommitSequence());
		armBeanReadyRemoved.setContent(armBean.getContent());
		armBeanReadyRemoved.setHistoriedTime(new Date(System
				.currentTimeMillis()));
		armBeanReadyRemoved.setName(armBean.getName());
		session.save(armBeanReadyRemoved);
		session.delete(armBean);
		Query query = session
				.createQuery("select harm from HistoriedARMBean as harm left join  CommitSequence as csq "
						+ "on harm.commitSequence=csq.id where harm.name=:conditionname and  csq.commitValidation=:conditionCommitValidation "
						+ "order by csq.id desc");
		query.setString("conditionname", armBean.getName());
		query.setInteger("conditionCommitValidation", CommitSequenceConstant.VALIDATION_SUCCESS);
		HistoriedARMBean readyForResoredARMBean=(HistoriedARMBean)query.uniqueResult();
		if(readyForResoredARMBean!=null){
			ARMBean armBeanResored=new ARMBean();
			armBean.setCommitSequence(readyForResoredARMBean.getCommitSequence());
			armBean.setContent(readyForResoredARMBean.getContent());
			armBean.setModifyTime(readyForResoredARMBean.getHistoriedTime());
			armBean.setName(readyForResoredARMBean.getName());
			session.save(armBeanResored);
			session.delete(readyForResoredARMBean);
			
		}

	}
}
