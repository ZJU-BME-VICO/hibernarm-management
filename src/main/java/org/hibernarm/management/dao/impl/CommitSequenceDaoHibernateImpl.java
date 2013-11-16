package org.hibernarm.management.dao.impl;

import org.apache.log4j.Logger;
import org.hibernarm.management.dao.virtual.CommitSequenceDao;
import org.hibernarm.management.model.CommitSequence;
import org.hibernarm.management.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;



public class CommitSequenceDaoHibernateImpl implements CommitSequenceDao {
    private static Logger logger=Logger.getLogger(CommitSequenceDaoHibernateImpl.class.getName());
	public void saveCommitSequence(CommitSequence commitSequence) {
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.getTransaction();
		try {
			tx.begin();
			session.saveOrUpdate(commitSequence);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
            logger.error("error when save CommitSequence:"+e.getMessage());
            throw new RuntimeException("error when save CommitSequence:"+e.getMessage());
		}
		session.evict(commitSequence);
		// TODO Auto-generated method stub

	}

}
