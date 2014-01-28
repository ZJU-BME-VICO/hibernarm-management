package edu.zju.bme.hibernarm.management.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import edu.zju.bme.hibernarm.management.model.CommitSequence;
import edu.zju.bme.hibernarm.management.util.HibernateUtil;

public class CommitSequenceDaoImpl implements CommitSequenceDao {
	public void saveCommitSequence(CommitSequence commitSequence) {
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.getTransaction();
		try {
			tx.begin();
			session.saveOrUpdate(commitSequence);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		}
		session.evict(commitSequence);
	}
}
