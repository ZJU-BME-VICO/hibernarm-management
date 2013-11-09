package org.hibernarm.management.archetype.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.hibernarm.management.dao.impl.ARMBeanDaoHibernateImpl;
import org.hibernarm.management.dao.impl.ArchetypeBeanDaoHibernateImpl;
import org.hibernarm.management.dao.virtual.ARMBeanDao;
import org.hibernarm.management.dao.virtual.ArchetypeBeanDao;
import org.hibernarm.management.model.ARMBean;
import org.hibernarm.management.model.ArchetypeBean;

import com.opensymphony.xwork2.ActionSupport;

public class FileDownLoad extends ActionSupport {
	private String fileType;
	private String keyName;
	private static ArchetypeBeanDao archetypeBeanDao=new ArchetypeBeanDaoHibernateImpl();
	private static ARMBeanDao armBeanDao=new ARMBeanDaoHibernateImpl();
	
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
	public InputStream getInputStream() throws UnsupportedEncodingException{
		InputStream input=null;
		if("adl".equalsIgnoreCase(fileType)){
			ArchetypeBean archetypeBean=archetypeBeanDao.selectByName(keyName);
			input=new ByteArrayInputStream(archetypeBean.getContent().getBytes("UTF-8"));			
		}else if("arm".equalsIgnoreCase(fileType)){
			ARMBean armBean=armBeanDao.findByName(keyName);
			input=new ByteArrayInputStream(armBean.getContent().getBytes("UTF-8"));						
		}
		return input;
	}
	public String execute(){
		return "success";
	}

}
