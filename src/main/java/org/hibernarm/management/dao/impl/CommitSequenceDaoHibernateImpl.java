package org.hibernarm.management.dao.impl;

import org.hibernarm.management.dao.virtual.CommitSequenceDao;
import org.hibernarm.management.model.CommitSequence;
import org.hibernarm.management.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CommitSequenceDaoHibernateImpl implements CommitSequenceDao {
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
