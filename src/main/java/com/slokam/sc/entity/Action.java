package com.slokam.sc.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Action {

	@Id
	@GeneratedValue
	private Long id;
	private String description;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="hid")
	private Header header;
	private Integer sequenceId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public Integer getSequenceId() {
		return sequenceId;
	}
	public void setSequenceId(Integer sequenceId) {
		this.sequenceId = sequenceId;
	}
	@Override
	public String toString() {
		return "Action [id=" + id + ", description=" + description + ", header=" + "::" + ", sequenceId=" + sequenceId
				+ "]";
	}
	
	
}
