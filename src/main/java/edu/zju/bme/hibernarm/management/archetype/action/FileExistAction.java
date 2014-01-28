package edu.zju.bme.hibernarm.management.archetype.action;

import java.io.File;

import org.apache.log4j.Logger;

import edu.zju.bme.hibernarm.management.dao.ARMBeanDao;
import edu.zju.bme.hibernarm.management.dao.ARMBeanDaoImpl;
import edu.zju.bme.hibernarm.management.dao.ArchetypeBeanDao;
import edu.zju.bme.hibernarm.management.dao.ArchetypeBeanDaoImpl;
import edu.zju.bme.hibernarm.management.model.ARMBean;
import edu.zju.bme.hibernarm.management.model.ArchetypeBean;
import edu.zju.bme.hibernarm.management.util.ARMUtil;
import edu.zju.bme.hibernarm.management.util.ArchetypeUtil;
import edu.zju.bme.hibernarm.management.util.FileExistConstant;
import edu.zju.bme.hibernarm.management.util.FileUtil;

public class FileExistAction {
	private static Logger logger = Logger.getLogger(FileExistAction.class
			.getName());
	
	private String existStatus;
	private String existName;
	private File singleFile[];
	private String singleFileFileName[];
	private String originalFileName;
	private static ARMBeanDao armBeanDao = new ARMBeanDaoImpl();
	private static ArchetypeBeanDao archetypeBeanDao = new ArchetypeBeanDaoImpl();

	public String execute() {
		String result = "success";
		logger.info(singleFile[0].getPath() + " size: " + singleFile[0].length());
		originalFileName = singleFileFileName[0];
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
