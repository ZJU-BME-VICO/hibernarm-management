package org.hibernarm.management.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
@Entity
public class HistoriedARMBean {
	private Integer id;
	private String name;
	private String content;
	private Date historiedTime;
	private Integer commitSequence;
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Lob
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getHistoriedTime() {
		return historiedTime;
	}

	public void setHistoriedTime(Date historiedTime) {
		this.historiedTime = historiedTime;
	}

	public Integer getCommitSequence() {
		return commitSequence;
	}

	public void setCommitSequence(Integer commitSequence) {
		this.commitSequence = commitSequence;
	}
}
