package org.hibernarm.management.dao.virtual;

import java.util.List;

import org.hibernarm.management.model.ArchetypeBean;

public interface ArchetypeBeanDao {
  List<ArchetypeBean> matchProbaleByName();
}
