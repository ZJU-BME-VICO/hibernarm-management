package org.hibernarm.management.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
@Entity
public class HistoriedArchetypeBean implements Serializable{
	private Integer id;
	private String name;
	private String content;
	private Date historiedTime;
    private String description;
    private Integer commitSequence;
    
    public HistoriedArchetypeBean(){
    	
    }
    public HistoriedArchetypeBean(String name,String description){
    	this.name=name;
    	this.description=description;
    }
    @Lob
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
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
