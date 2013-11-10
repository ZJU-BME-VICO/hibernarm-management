package org.hibernarm.management.archetype.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernarm.management.dao.impl.ARMBeanDaoHibernateImpl;
import org.hibernarm.management.dao.impl.ArchetypeBeanDaoHibernateImpl;
import org.hibernarm.management.dao.virtual.ARMBeanDao;
import org.hibernarm.management.dao.virtual.ArchetypeBeanDao;
import org.hibernarm.management.model.ARMBean;
import org.hibernarm.management.model.ArchetypeBean;
import org.hibernarm.management.util.FileUtil;
import org.hibernarm.management.util.HibernateUtil;

import com.opensymphony.xwork2.ActionSupport;


public class FileUploadAction extends ActionSupport {
	private File[] upload;
	private String[] uploadFileName;
	private String[] uploadContentType;
	private static ArchetypeBeanDao archetypeBeanDao = new ArchetypeBeanDaoHibernateImpl();
	private static ARMBeanDao armBeanDao = new ARMBeanDaoHibernateImpl();
	private static Logger logger=Logger.getLogger(FileUploadAction.class.getName());
			

	public File[] getUpload() {
		return upload;
	}

	public void setUpload(File[] upload) {
		this.upload = upload;
	}

	public String[] getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String[] uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String[] getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String[] uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	private static List<ArchetypeBean> constructArchetypeBeans(File[] upload,
			String[] uploadFileName, String[] uploadContentType)
			throws Exception {
		List<ArchetypeBean> list = new ArrayList<ArchetypeBean>();
		Date currentDate = new Date(System.currentTimeMillis());
		for (int i = 0; i < upload.length; i++) {
			if (uploadFileName[i].endsWith("adl")) {
				ArchetypeBean archetypeBean = new ArchetypeBean();
				archetypeBean.setModifyTime(currentDate);
				archetypeBean.setContent(FileUtil.extractContent(upload[i]));
				archetypeBean.setName(uploadFileName[i].substring(0, uploadFileName[i].lastIndexOf(".")));
				list.add(archetypeBean);
			}
		}
		return list;
	}

	private static List<ARMBean> constructArmBeans(File[] upload,
			String[] uploadFileName, String[] uploadContentType)
			throws Exception {
		List<ARMBean> list = new ArrayList<ARMBean>();
		Date currentDate = new Date(System.currentTimeMillis());
		for (int i = 0; i < uploadFileName.length; i++) {
			if (uploadFileName[i].endsWith("arm")) {
				ARMBean armBean = new ARMBean();
				armBean.setContent(FileUtil.extractContent(upload[i]));
				armBean.setModifyTime(currentDate);
				armBean.setName(uploadFileName[i].substring(0, uploadFileName[i].lastIndexOf(".")));
				list.add(armBean);
			}
		}
		return list;
	}

	public String execute() {
		String result = "success";
		try {
			List<ArchetypeBean> listArchetypeBeans = constructArchetypeBeans(
					upload, uploadFileName, uploadContentType);
			List<ARMBean> listArmBeans = constructArmBeans(upload,
					uploadFileName, uploadContentType);
			for (ARMBean armBean : listArmBeans) {
				logger.info("\narm\n"+armBean.getContent());
				armBeanDao.saveOrUpdate(armBean);
			}
			for (ArchetypeBean archetypeBean : listArchetypeBeans) {
				logger.info("\narchetype\n"+archetypeBean.getContent());
				archetypeBeanDao.saveOrUpdate(archetypeBean);
			}
		} catch (Exception e) {
			result = "fail";
		} finally {
			HibernateUtil.closeSession();
		}
		return result;
	}

}
