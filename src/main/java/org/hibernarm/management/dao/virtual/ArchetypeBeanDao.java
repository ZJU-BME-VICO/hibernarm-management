package org.hibernarm.management.dao.virtual;

import java.util.List;

import org.hibernarm.management.model.ArchetypeBean;
import org.hibernate.Session;

public interface ArchetypeBeanDao {
	List<ArchetypeBean> matchProbableByName(String name);

	List<ArchetypeBean> matchProbableByNamePaging(String name,
			int sequenceOfPage, int perPageAmount);

	List<ArchetypeBean> matchProbableByNamePart(String name);

	List<ArchetypeBean> matchProbableByNamePagingPart(String name,
			int sequenceOfPage, int perPageAmount);

	void saveOrUpdate(ArchetypeBean bean, Session seesion);

	ArchetypeBean selectByName(String name);

	List<ArchetypeBean> selectAll();

	void deleteAndRestore(ArchetypeBean archetypeBean, Session session);
}
