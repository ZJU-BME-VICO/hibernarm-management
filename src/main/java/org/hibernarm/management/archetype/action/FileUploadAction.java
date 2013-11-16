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
import org.hibernarm.management.dao.impl.CommitSequenceDaoHibernateImpl;
import org.hibernarm.management.dao.virtual.ARMBeanDao;
import org.hibernarm.management.dao.virtual.ArchetypeBeanDao;
import org.hibernarm.management.dao.virtual.CommitSequenceDao;
import org.hibernarm.management.exception.CancleOneCommitException;
import org.hibernarm.management.exception.OneTimeSaveException;
import org.hibernarm.management.model.ARMBean;
import org.hibernarm.management.model.ArchetypeBean;
import org.hibernarm.management.model.CommitSequence;
import org.hibernarm.management.util.ARMUtil;
import org.hibernarm.management.util.ArchetypeUtil;
import org.hibernarm.management.util.CommitSequenceConstant;
import org.hibernarm.management.util.FileUtil;
import org.hibernarm.management.util.HibernateUtil;
import org.hibernarm.service.AQLExecute;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.support.DaoSupport;

public class FileUploadAction {
	private File[] upload;
	private String[] uploadFileName;
	private String[] uploadContentType;
	private String uploadResult;
	private String uploadResultDescription;
	private static ArchetypeBeanDao archetypeBeanDao = new ArchetypeBeanDaoHibernateImpl();
	private static ARMBeanDao armBeanDao = new ARMBeanDaoHibernateImpl();
	private static CommitSequenceDao commitSequenceDao=new CommitSequenceDaoHibernateImpl();
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

	public String getUploadResult() {
		return uploadResult;
	}

	public void setUploadResult(String uploadResult) {
		this.uploadResult = uploadResult;
	}

	public String getUploadResultDescription() {
		return uploadResultDescription;
	}

	public void setUploadResultDescription(String uploadResultDescription) {
		this.uploadResultDescription = uploadResultDescription;
	}

	private static List<ArchetypeBean> constructArchetypeBeans(File[] upload,
			String[] uploadFileName, String[] uploadContentType,
			Date modifyTime, int version) {
		List<ArchetypeBean> list = new ArrayList<ArchetypeBean>();
		for (int i = 0; i < upload.length; i++) {
			if (FileUtil.getFileType(upload[i]).compareToIgnoreCase("adl") == 0) {
				ArchetypeUtil archetypeUtil = new ArchetypeUtil(upload[i]);
				String archetypeId = archetypeUtil.getArchetypeId();
				String archetypeContent = archetypeUtil.getArchetypeContent();
				if (!archetypeId.isEmpty() && !archetypeContent.isEmpty()) {
					ArchetypeBean archetypeBean = new ArchetypeBean();
					archetypeBean.setModifyTime(modifyTime);
					archetypeBean.setContent(archetypeContent);
					archetypeBean.setName(archetypeId);
					archetypeBean.setCommitSequence(version);
					list.add(archetypeBean);
				}
			}
		}
		return list;
	}

	private static List<ARMBean> constructArmBeans(File[] upload,
			String[] uploadFileName, String[] uploadContentType,
			Date modifyTime, int version) {
		List<ARMBean> list = new ArrayList<ARMBean>();
		for (int i = 0; i < uploadFileName.length; i++) {
			if (FileUtil.getFileType(upload[i]).compareToIgnoreCase("xml") == 0) {
				ARMUtil armUtil = new ARMUtil(upload[i]);
				String archetypeId = armUtil.getArchetypeId();
				String armContent = armUtil.getARMContent();
				if (!archetypeId.isEmpty() && !armContent.isEmpty()) {
					ARMBean armBean = new ARMBean();
					armBean.setContent(armContent);
					armBean.setModifyTime(modifyTime);
					armBean.setName(archetypeId);
					armBean.setCommitSequence(version);
					list.add(armBean);
				}
			}
		}
		return list;
	}

	public String execute() {
		uploadResult = "success";
		Date modifyTime = new Date(System.currentTimeMillis());
		CommitSequence commitSequence = new CommitSequence();
		commitSequence
				.setCommitValidation(CommitSequenceConstant.VALIDATION_SUCCESS);
		commitSequence.setCommitTime(modifyTime);
		commitSequenceDao.saveCommitSequence(commitSequence);
		List<ArchetypeBean> listArchetypeBeans = null;
		List<ARMBean> listArmBeans = null;
		try {
			listArchetypeBeans = constructArchetypeBeans(upload,
					uploadFileName, uploadContentType, modifyTime,
					commitSequence.getId());
			listArmBeans = constructArmBeans(upload, uploadFileName,
					uploadContentType, modifyTime, commitSequence.getId());
			saveAction(listArmBeans, listArchetypeBeans);
			validateHibernarm();
		} catch (OneTimeSaveException e) {
			uploadResult = "fail";
			uploadResultDescription = e.getMessage();
			logger.error("upload file fail" + e.getMessage());
		} catch (Exception e) {
			commitSequence
					.setCommitValidation(CommitSequenceConstant.VALIDATION_FAIL);
			commitSequenceDao.saveCommitSequence(commitSequence);
			uploadResult = "fail";
			uploadResultDescription="there are problems with file uploaded";
			cancleAction(listArmBeans, listArchetypeBeans);

		} finally {
			HibernateUtil.closeSession();
		}
		return "success";
	}

	protected void validateHibernarm() {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml", HibernarmControlAction.class);
		AQLExecute client = (AQLExecute) context.getBean("wsclientvalidation");

		HibernarmControl control = new HibernarmControl();
		control.execute(client);
	}

	private void saveAction(List<ARMBean> listArmBeans,
			List<ArchetypeBean> listArchetypeBeans) {
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.getTransaction();
		try {
			tx.begin();
			for (ARMBean armBean : listArmBeans) {
				armBeanDao.saveOrUpdate(armBean, session);
			}
			for (ArchetypeBean archetypeBean : listArchetypeBeans) {
				archetypeBeanDao.saveOrUpdate(archetypeBean, session);
			}
			tx.commit();
		} catch (Throwable e) {
			tx.rollback();
			throw new OneTimeSaveException("save data uploaded this time error");
		}
	}

	private void cancleAction(List<ARMBean> listArmBeans,
			List<ArchetypeBean> listArchetypeBeans) {
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.getTransaction();
		try {
			tx.begin();
			for (ArchetypeBean archetypeBean : listArchetypeBeans) {
				archetypeBeanDao.deleteAndRestore(archetypeBean, session);
			}
			for (ARMBean armBean : listArmBeans) {
				armBeanDao.deleteAndRestore(armBean, session);

			}
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			logger.error("cancle action error" + e.getMessage());
		}
	}
}
