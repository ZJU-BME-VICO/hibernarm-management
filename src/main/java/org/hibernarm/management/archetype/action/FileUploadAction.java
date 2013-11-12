package org.hibernarm.management.archetype.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernarm.management.control.action.HibernarmControl;
import org.hibernarm.management.control.action.HibernarmControlAction;
import org.hibernarm.management.dao.impl.ARMBeanDaoHibernateImpl;
import org.hibernarm.management.dao.impl.ArchetypeBeanDaoHibernateImpl;
import org.hibernarm.management.dao.virtual.ARMBeanDao;
import org.hibernarm.management.dao.virtual.ArchetypeBeanDao;
import org.hibernarm.management.model.ARMBean;
import org.hibernarm.management.model.ArchetypeBean;
import org.hibernarm.management.util.FileUtil;
import org.hibernarm.management.util.HibernateUtil;
import org.hibernarm.service.AQLExecute;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionSupport;

public class FileUploadAction extends ActionSupport {
	private File[] upload;
	private String[] uploadFileName;
	private String[] uploadContentType;
	private static ArchetypeBeanDao archetypeBeanDao = new ArchetypeBeanDaoHibernateImpl();
	private static ARMBeanDao armBeanDao = new ARMBeanDaoHibernateImpl();
	private static Logger logger = Logger.getLogger(FileUploadAction.class
			.getName());

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
			String[] uploadFileName, String[] uploadContentType, Date modifyTime)
			throws Exception {
		List<ArchetypeBean> list = new ArrayList<ArchetypeBean>();
		for (int i = 0; i < upload.length; i++) {
			if (uploadFileName[i].endsWith("adl")) {
				ArchetypeBean archetypeBean = new ArchetypeBean();
				archetypeBean.setModifyTime(modifyTime);
				archetypeBean.setContent(FileUtil.extractContent(upload[i]));
				archetypeBean.setName(uploadFileName[i].substring(0,
						uploadFileName[i].lastIndexOf(".")));
				list.add(archetypeBean);
			}
		}
		return list;
	}

	private static List<ARMBean> constructArmBeans(File[] upload,
			String[] uploadFileName, String[] uploadContentType, Date modifyTime)
			throws Exception {
		List<ARMBean> list = new ArrayList<ARMBean>();
		for (int i = 0; i < uploadFileName.length; i++) {
			if (uploadFileName[i].endsWith("arm")) {
				ARMBean armBean = new ARMBean();
				armBean.setContent(FileUtil.extractContent(upload[i]));
				armBean.setModifyTime(modifyTime);
				armBean.setName(uploadFileName[i].substring(0,
						uploadFileName[i].lastIndexOf(".")));
				list.add(armBean);
			}
		}
		return list;
	}

	public String execute() {
		String result = "success";
		Date modifyTime = new Date(System.currentTimeMillis());
		try {
			List<ArchetypeBean> listArchetypeBeans = constructArchetypeBeans(
					upload, uploadFileName, uploadContentType, modifyTime);
			List<ARMBean> listArmBeans = constructArmBeans(upload,
					uploadFileName, uploadContentType, modifyTime);

			for (ARMBean armBean : listArmBeans) {
				armBeanDao.saveOrUpdate(armBean);
			}
			for (ArchetypeBean archetypeBean : listArchetypeBeans) {
				archetypeBeanDao.saveOrUpdate(archetypeBean);
			}
			
			validateHibernarm();
		} catch (Exception e) {
			result = "fail";
		} finally {
			HibernateUtil.closeSession();
		}
		return result;
	}
	
	protected void validateHibernarm() {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml", HibernarmControlAction.class);
		AQLExecute client = (AQLExecute) context.getBean("wsclientvalidation");
		
		HibernarmControl control = new HibernarmControl();
		control.execute(client);
	}

	private void saveAction(List<ARMBean>listArmBeans,List<ArchetypeBean>listArchetypeBeans) {
 		
		for (ARMBean armBean : listArmBeans) {
			armBeanDao.saveOrUpdate(armBean);
		}
		for (ArchetypeBean archetypeBean : listArchetypeBeans) {
			archetypeBeanDao.saveOrUpdate(archetypeBean);
		}
	}


}
