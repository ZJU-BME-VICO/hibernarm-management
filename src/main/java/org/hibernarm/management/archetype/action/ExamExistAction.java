package org.hibernarm.management.archetype.action;

import java.io.File;

import org.hibernarm.management.dao.impl.ARMBeanDaoHibernateImpl;
import org.hibernarm.management.dao.impl.ArchetypeBeanDaoHibernateImpl;
import org.hibernarm.management.dao.virtual.ARMBeanDao;
import org.hibernarm.management.dao.virtual.ArchetypeBeanDao;
import org.hibernarm.management.model.ARMBean;
import org.hibernarm.management.model.ArchetypeBean;
import org.hibernarm.management.util.HibernateUtil;
import org.hibernarm.management.util.UtilConstant;

public class ExamExistAction {
	private String examName;
	private String flagExisted;
	private File singleFile[];
	private static ARMBeanDao armBeanDao = new ARMBeanDaoHibernateImpl();
	private static ArchetypeBeanDao archetypeBeanDao = new ArchetypeBeanDaoHibernateImpl();

	public String execute() {
		String result = "success";
		ARMBean armBean = null;
		ArchetypeBean archetypeBean = null;
		flagExisted = UtilConstant.EXISTED;
//		String prefix = examName.substring(0, examName.lastIndexOf("."));
//		String suf = examName.substring(examName.lastIndexOf(".") + 1);
//		flagExisted = UtilConstant.NONE;
//		try {
//			if ("arm".equalsIgnoreCase(suf)) {
//				armBean = armBeanDao.findByName(prefix);
//			} else if ("adl".equalsIgnoreCase(suf)) {
//				archetypeBean = archetypeBeanDao.selectByName(prefix);
//			}
//			if (archetypeBean != null || armBean != null) {
//				flagExisted = UtilConstant.EXISTED;
//			}
//		} catch (Exception e) {
//			result = "fail";
//		} finally {
//			HibernateUtil.closeSession();
//		}
		return result;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public String getFlagExisted() {
		return flagExisted;
	}

	public void setFlagExisted(String flagExisted) {
		this.flagExisted = flagExisted;
	}

	public static ARMBeanDao getArmBeanDao() {
		return armBeanDao;
	}

	public static void setArmBeanDao(ARMBeanDao armBeanDao) {
		ExamExistAction.armBeanDao = armBeanDao;
	}

	public static ArchetypeBeanDao getArchetypeBeanDao() {
		return archetypeBeanDao;
	}

	public static void setArchetypeBeanDao(ArchetypeBeanDao archetypeBeanDao) {
		ExamExistAction.archetypeBeanDao = archetypeBeanDao;
	}

	public File[] getSingleFile() {
		return singleFile;
	}

	public void setSingleFile(File[] singleFile) {
		this.singleFile = singleFile;
	}
   
}
