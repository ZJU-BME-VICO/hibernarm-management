package org.hibernarm.management.test;

import java.util.List;

import org.hibernarm.management.dao.impl.ArchetypeBeanDaoHibernateImpl;
import org.hibernarm.management.dao.virtual.ArchetypeBeanDao;
import org.hibernarm.management.model.ArchetypeBean;
import org.junit.Test;

public class TestArchetypeBeanDaoHibernateImpl {
	@Test
	public void testmatchProbaleByName(){
		ArchetypeBeanDao dao=new ArchetypeBeanDaoHibernateImpl();
		List<ArchetypeBean> list=dao.matchProbableByName("erwe");
//		List<ArchetypeBean>list=dao.matchProbaleByNamePart("openEHR-EHR-OBSERVATION.blood_pressure.v1");
		System.out.println(list);
		System.out.println(list.size());
		System.out.println(list.get(0).getContent());
		System.out.println(list.get(0).getName());
		System.out.println(list.get(0).getDescription());
	}

}
