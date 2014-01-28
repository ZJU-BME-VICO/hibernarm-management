package edu.zju.bme.hibernarm.management.archetype.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import com.opensymphony.xwork2.ActionSupport;

import edu.zju.bme.hibernarm.management.dao.impl.ARMBeanDaoHibernateImpl;
import edu.zju.bme.hibernarm.management.dao.impl.ArchetypeBeanDaoHibernateImpl;
import edu.zju.bme.hibernarm.management.dao.virtual.ARMBeanDao;
import edu.zju.bme.hibernarm.management.dao.virtual.ArchetypeBeanDao;
import edu.zju.bme.hibernarm.management.model.ARMBean;
import edu.zju.bme.hibernarm.management.model.ArchetypeBean;

public class FileDownloadAction extends ActionSupport {
	private static final long serialVersionUID = -8185888950509500292L;

	private String fileType;
	private String keyName;
	private static ArchetypeBeanDao archetypeBeanDao = new ArchetypeBeanDaoHibernateImpl();
	private static ARMBeanDao armBeanDao = new ARMBeanDaoHibernateImpl();

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public InputStream getInputStream() throws UnsupportedEncodingException {
		InputStream input = null;
		if ("adl".equalsIgnoreCase(fileType)) {
			ArchetypeBean archetypeBean = archetypeBeanDao
					.selectByName(keyName);
			input = new ByteArrayInputStream(archetypeBean.getContent()
					.getBytes("UTF-8"));
		} else if ("arm".equalsIgnoreCase(fileType)) {
			ARMBean armBean = armBeanDao.findByName(keyName);
			input = new ByteArrayInputStream(armBean.getContent().getBytes(
					"UTF-8"));
		}
		return input;
	}

	public String execute() {
		return "success";
	}
}
