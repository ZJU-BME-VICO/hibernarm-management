package org.hibernarm.management.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

public class HistoriedARMBean {
	private Integer id;
	private String name;
	private String content;
	private Date historiedTime;
	private int commitSequence;
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@Column(unique = true)
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

	public int getCommitSequence() {
		return commitSequence;
	}

	public void setCommitSequence(int commitSequence) {
		this.commitSequence = commitSequence;
	}
}
