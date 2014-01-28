package edu.zju.bme.hibernarm.management.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import edu.zju.bme.hibernarm.management.model.ARMBean;
import edu.zju.bme.hibernarm.management.model.HistoriedARMBean;
import edu.zju.bme.hibernarm.management.util.CommitSequenceConstant;
import edu.zju.bme.hibernarm.management.util.HibernateUtil;

public class ARMBeanDaoImpl implements ARMBeanDao {
	public void saveOrUpdate(ARMBean bean, Session session) {
		HistoriedARMBean historiedARMBean = new HistoriedARMBean();
		historiedARMBean.setCommitSequence(bean.getCommitSequence());
		historiedARMBean.setContent(bean.getContent());
		historiedARMBean.setHistoriedTime(bean.getModifyTime());
		historiedARMBean.setName(bean.getName());
		session.save(historiedARMBean);
		ARMBean existBean = findByName(bean.getName());
		if (existBean != null) {
			existBean.setContent(bean.getContent());
			existBean.setModifyTime(bean.getModifyTime());
			existBean.setCommitSequence(bean.getCommitSequence());
		} else {
			session.save(bean);
		}
	}

	public ARMBean findByName(String name) {
		Session session = HibernateUtil.currentSession();
		Query query = session
				.createQuery("from ARMBean as arm where arm.name=:conditionname");
		query.setString("conditionname", name);
		ARMBean armBean = (ARMBean) query.uniqueResult();
		return armBean;
	}

	public List<ARMBean> selectAll() {
		Session session = HibernateUtil.currentSession();
		Query query = session.createQuery("from ARMBean");
		@SuppressWarnings("unchecked")
		List<ARMBean> arms = query.list();
		return arms;
	}

	public void deleteAndRestore(ARMBean armBean, Session session) {
		ARMBean armBeanRestored = findByName(armBean.getName());
		Query query = session
				.createQuery("from HistoriedARMBean as harm "
						+ "where harm.name=:conditionname and harm.commitSequence.commitValidation=:conditionCommitValidation "
						+ "order by harm.commitSequence.id desc");
		query.setString("conditionname", armBean.getName());
		query.setString("conditionCommitValidation",
				CommitSequenceConstant.VALID);
		HistoriedARMBean armBeanValidated = (HistoriedARMBean) query
				.uniqueResult();
		if (armBeanValidated != null) {
			armBeanRestored.setCommitSequence(armBeanValidated
					.getCommitSequence());
			armBeanRestored.setContent(armBeanValidated.getContent());
			armBeanRestored.setModifyTime(armBeanValidated.getHistoriedTime());
			armBeanRestored.setName(armBeanValidated.getName());
		} else {
			session.delete(armBeanRestored);
		}
	}
}
