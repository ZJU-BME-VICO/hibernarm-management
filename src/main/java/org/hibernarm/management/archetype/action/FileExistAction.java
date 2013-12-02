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

public class FileExistAction {
	private String existStatus;
	private String existName;
	private File singleFile[];
	private String singleFileFileName[];
	private String originalFileName;
	private static ARMBeanDao armBeanDao = new ARMBeanDaoHibernateImpl();
	private static ArchetypeBeanDao archetypeBeanDao = new ArchetypeBeanDaoHibernateImpl();

	public String execute() {
		String result = "success";
		originalFileName=singleFileFileName[0];
		if (FileUtil.getFileType(singleFile[0]).compareToIgnoreCase("adl") == 0) {
			ArchetypeUtil archetypeUtil = new ArchetypeUtil(singleFile[0]);
			String archetypeId = archetypeUtil.getArchetypeId();
			String archetypeContent = archetypeUtil.getArchetypeContent();
			existName = archetypeId;
			if (!archetypeId.isEmpty() && !archetypeContent.isEmpty()) {
				ArchetypeBean archetypeBean = archetypeBeanDao
						.selectByName(archetypeId);
				if (archetypeBean != null) {
					if (archetypeBean.getContent().compareTo(archetypeContent) == 0) {
						existStatus = FileExistConstant.EXISTED;
					} else {
						existStatus = FileExistConstant.CHANGED;
					}
				} else {
					existStatus = FileExistConstant.NONE;
				}
			} else {
				existStatus = FileExistConstant.INVALID;
			}
		}

		if (FileUtil.getFileType(singleFile[0]).compareToIgnoreCase("xml") == 0) {
			ARMUtil armUtil = new ARMUtil(singleFile[0]);
			String archetypeId = armUtil.getArchetypeId();
			String armContent = armUtil.getARMContent();
			existName = archetypeId;
			if (!archetypeId.isEmpty() && !armContent.isEmpty()) {
				ARMBean armBean = armBeanDao.findByName(archetypeId);
				if (armBean != null) {
					if (armBean.getContent().compareTo(armContent) == 0) {
						existStatus = FileExistConstant.EXISTED;
					} else {
						existStatus = FileExistConstant.CHANGED;
					}
				} else {
					existStatus = FileExistConstant.NONE;
				}
			} else {
				existStatus = FileExistConstant.INVALID;
			}
		}

		return result;
	}

	public String getExistStatus() {
		return existStatus;
	}

	public void setExistStatus(String existStatus) {
		this.existStatus = existStatus;
	}

	public String getExistName() {
		return existName;
	}

	public void setExistName(String existName) {
		this.existName = existName;
	}

	public static ARMBeanDao getArmBeanDao() {
		return armBeanDao;
	}

	public static void setArmBeanDao(ARMBeanDao armBeanDao) {
		FileExistAction.armBeanDao = armBeanDao;
	}

	public static ArchetypeBeanDao getArchetypeBeanDao() {
		return archetypeBeanDao;
	}

	public static void setArchetypeBeanDao(ArchetypeBeanDao archetypeBeanDao) {
		FileExistAction.archetypeBeanDao = archetypeBeanDao;
	}

	public File[] getSingleFile() {
		return singleFile;
	}

	public void setSingleFile(File[] singleFile) {
		this.singleFile = singleFile;
	}

	public String[] getSingleFileFileName() {
		return singleFileFileName;
	}

	public void setSingleFileFileName(String[] singleFileFileName) {
		this.singleFileFileName = singleFileFileName;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}
	

}
