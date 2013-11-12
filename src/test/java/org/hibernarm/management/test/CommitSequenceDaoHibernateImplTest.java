package org.hibernarm.management.test;

import java.util.Date;

import org.hibernarm.management.dao.impl.CommitSequenceDaoHibernateImpl;
import org.hibernarm.management.model.CommitSequence;
import org.hibernarm.management.util.HibernateUtil;
import org.junit.Test;

public class CommitSequenceDaoHibernateImplTest {
	@Test
    public void testSaveCommitSequence(){
    	CommitSequence cs=new CommitSequence();
    	cs.setCommitTime(new Date(System.currentTimeMillis()));
    	CommitSequenceDaoHibernateImpl dao=new CommitSequenceDaoHibernateImpl();
    	dao.saveCommitSequence(cs);
    	HibernateUtil.closeSession();
    	System.out.println(cs.getId());
    }
}
