package org.hibernarm.management.archetype.action;

import java.io.File;

import org.hibernarm.management.dao.impl.ARMBeanDaoHibernateImpl;
import org.hibernarm.management.dao.impl.ArchetypeBeanDaoHibernateImpl;
import org.hibernarm.management.dao.virtual.ARMBeanDao;
import org.hibernarm.management.dao.virtual.ArchetypeBeanDao;
import org.hibernarm.management.model.ARMBean;
import org.hibernarm.management.model.ArchetypeBean;
import org.hibernarm.management.util.ARMUtil;
import org.hibernarm.management.util.ArchetypeUtil;
import org.hibernarm.management.util.FileUtil;
import org.hibernarm.management.util.FileExistConstant;

import com.google.gson.Gson;

public class ExamExistAction {
	private String examName;
	private String flagExisted;
	private File singleFile[];
	private static ARMBeanDao armBeanDao = new ARMBeanDaoHibernateImpl();
	private static ArchetypeBeanDao archetypeBeanDao = new ArchetypeBeanDaoHibernateImpl();

	private class FileExist {
		private String status = "";
		private String name = "";
	}

	public String execute() {
		String result = "success";

		FileExist fe = new FileExist();

		if (FileUtil.getFileType(singleFile[0]).compareToIgnoreCase("adl") == 0) {
			ArchetypeUtil archetypeUtil = new ArchetypeUtil(singleFile[0]);
			String archetypeId = archetypeUtil.getArchetypeId();
			String archetypeContent = archetypeUtil.getArchetypeContent();
			fe.name = archetypeId;
			if (!archetypeId.isEmpty() && !archetypeContent.isEmpty()) {
				ArchetypeBean archetypeBean = archetypeBeanDao
						.selectByName(archetypeId);
				if (archetypeBean != null) {
					if (archetypeBean.getContent().compareTo(archetypeContent) == 0) {
						fe.status = FileExistConstant.EXISTED;
					} else {
						fe.status = FileExistConstant.CHANGED;
					}
				} else {
					fe.status = FileExistConstant.NONE;
				}
			} else {
				fe.status = FileExistConstant.INVALID;
			}
		}

		if (FileUtil.getFileType(singleFile[0]).compareToIgnoreCase("xml") == 0) {
			ARMUtil armUtil = new ARMUtil(singleFile[0]);
			String archetypeId = armUtil.getArchetypeId();
			String armContent = armUtil.getARMContent();
			fe.name = archetypeId;
			if (!archetypeId.isEmpty() && !armContent.isEmpty()) {
				ARMBean armBean = armBeanDao.findByName(archetypeId);
				if (armBean != null) {
					if (armBean.getContent().compareTo(armContent) == 0) {
						fe.status = FileExistConstant.EXISTED;
					} else {
						fe.status = FileExistConstant.CHANGED;
					}
				} else {
					fe.status = FileExistConstant.NONE;
				}
			} else {
				fe.status = FileExistConstant.INVALID;
			}
		}

		Gson g = new Gson();
		flagExisted = g.toJson(fe);

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
