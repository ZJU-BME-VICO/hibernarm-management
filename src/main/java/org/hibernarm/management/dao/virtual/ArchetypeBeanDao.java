package org.hibernarm.management.dao.virtual;

import java.util.List;

import org.hibernarm.management.model.ArchetypeBean;

public interface ArchetypeBeanDao {
	/*
	 * @author bleachzou according name select ArchetypeBean
	 */
	List<ArchetypeBean> matchProbableByName(String name);

	/*
	 * @author bleachzou according name select ArchetypeBean and implementing
	 * paging
	 */
	List<ArchetypeBean> matchProbableByNamePaging(String name,
			int sequenceOfPage, int perPageAmount);

	List<ArchetypeBean> matchProbableByNamePart(String name);

	List<ArchetypeBean> matchProbableByNamePagingPart(String name,
			int sequenceOfPage, int perPageAmount);
}
