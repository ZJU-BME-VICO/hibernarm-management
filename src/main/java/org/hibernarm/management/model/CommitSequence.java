package org.hibernarm.management.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class CommitSequence implements Serializable{
	private Integer id;
	private Date commitTime;
	private Integer commitValidation;
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getCommitTime() {
		return commitTime;
	}
	public void setCommitTime(Date commitTime) {
		this.commitTime = commitTime;
	}
	public Integer getCommitValidation() {
		return commitValidation;
	}
	public void setCommitValidation(Integer commitValidation) {
		this.commitValidation = commitValidation;
	}
	
	
}
