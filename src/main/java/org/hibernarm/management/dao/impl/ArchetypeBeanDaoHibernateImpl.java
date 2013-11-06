package org.hibernarm.management.dao.impl;

import java.util.List;

import org.hibernarm.management.dao.virtual.ArchetypeBeanDao;
import org.hibernarm.management.model.ArchetypeBean;
import org.hibernarm.management.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

public class ArchetypeBeanDaoHibernateImpl implements ArchetypeBeanDao {

	public List<ArchetypeBean> matchProbableByName(String name) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.currentSession();
		Query query = session
				.createQuery("from ArchetypeBean as atb where atb.name like :conditionname");
		query.setString("conditionname", "%" + name + "%");
		List<ArchetypeBean> list = query.list();
		HibernateUtil.closeSession();
		return list;
	}

	public List<ArchetypeBean> matchProbableByNamePaging(String name,
			int sequenceOfPage, int perPageAmount) {
		Session session = HibernateUtil.currentSession();
		Query query = session
				.createQuery("from ArchetypeBean as atb where atb.name like :conditionname");
		query.setString("conditionname", "%" + name + "%");
		query.setFirstResult((sequenceOfPage - 1) * perPageAmount);
		query.setMaxResults(perPageAmount);
		List<ArchetypeBean> list = query.list();
		HibernateUtil.closeSession();
		return list;
	}

	public List<ArchetypeBean> matchProbableByNamePart(String name) {
		Session session = HibernateUtil.currentSession();
		Query query = session
				.createQuery("select new ArchetypeBean(atb.name,atb.description) from ArchetypeBean as atb where atb.name like :conditionname");
		query.setString("conditionname", "%" + name + "%");
		List<ArchetypeBean> list = query.list();
		HibernateUtil.closeSession();
		return list;
	}

	public List<ArchetypeBean> matchProbableByNamePagingPart(String name,
			int sequenceOfPage, int perPageAmount) {
		Session session = HibernateUtil.currentSession();
		Query query = session
				.createQuery("select new ArchetypeBean(atb.name,atb.description) from ArchetypeBean as atb where atb.name like :conditionname");
		query.setString("conditionname", "%" + name + "%");
		query.setFirstResult((sequenceOfPage - 1) * perPageAmount);
		query.setMaxResults(perPageAmount);
		List<ArchetypeBean> list = query.list();
		HibernateUtil.closeSession();
		return list;

	}

}
