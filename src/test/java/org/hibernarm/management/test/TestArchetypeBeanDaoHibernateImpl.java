package org.hibernarm.management.test;

import java.util.List;

import org.hibernarm.management.dao.impl.ArchetypeBeanDaoHibernateImpl;
import org.hibernarm.management.dao.virtual.ArchetypeBeanDao;
import org.hibernarm.management.model.ArchetypeBean;
import org.hibernarm.management.util.HibernateUtil;
import org.junit.Test;

public class TestArchetypeBeanDaoHibernateImpl {
//	@Test
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
	//@Test
	public void testSaveOrUpdate(){
		ArchetypeBean archetypeBean=new ArchetypeBean();
		archetypeBean.setContent("修改测试5");
		archetypeBean.setName("openEHR-EHR-OBSERVATION.blood_pressure.v1");
		ArchetypeBeanDao dao=new ArchetypeBeanDaoHibernateImpl();
		dao.saveOrUpdate(archetypeBean);
		HibernateUtil.closeSession();
	}
	//@Test
	public void testSaveOrUpdateBrach(){
		ArchetypeBean archetypeBean=new ArchetypeBean();
		archetypeBean.setContent("修改测试5");
		archetypeBean.setName("openEHR-EHR-OBSERVATION.blood_pressure.v3");
		ArchetypeBeanDao dao=new ArchetypeBeanDaoHibernateImpl();
		dao.saveOrUpdate(archetypeBean);
	}
	@Test
	public void testSelectByName(){
		ArchetypeBeanDao archetypeBeanDao=new ArchetypeBeanDaoHibernateImpl();
		ArchetypeBean archetypeBean=archetypeBeanDao.selectByName("openEHR-EHR-OBSERVATION.blood_pressure.v4");
		System.out.println(archetypeBean);
	}

	@Test
	public void testSelectAll(){
		ArchetypeBeanDao archetypeBeanDao = new ArchetypeBeanDaoHibernateImpl();
		List<ArchetypeBean> archetypes = archetypeBeanDao.selectAll();
		for (ArchetypeBean archetypeBean : archetypes) {
			System.out.println(archetypeBean);			
		}
	}
}
