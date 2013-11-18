package org.hibernarm.management.test;

import java.io.IOException;
import java.text.ParseException;
import org.hibernarm.management.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

public class TestArchetypeBean extends ArchetypeTestBase{
	@Test
	public void testSaveArchetypeBean() throws ParseException, IOException{
//		ArchetypeBean bean=new ArchetypeBean();
//		bean.setContent("��һ��");
//		bean.setName("��һ��");
//		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date date=format.parse("2013-12-23 12:20:20");
//		bean.setModifyTime(date);
		initial();
		Session session=HibernateUtil.currentSession();
		Transaction tx=session.getTransaction();
		tx.begin();
		session.save(archetypes.get("openEHR-EHR-OBSERVATION.blood_pressure.v2"));
		tx.commit();
		HibernateUtil.closeSession();
		
	}
	

}
