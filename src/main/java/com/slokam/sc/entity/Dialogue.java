package com.slokam.sc.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Dialogue {

	
	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	@JoinColumn(name="hid")
	private Header header;
	
	@ManyToOne ()
	@JoinColumn(name="cid" )
	private SCCharecter scCharecter;
	private Integer sequenceId;
	private String dialogue;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	
	public SCCharecter getScCharecter() {
		return scCharecter;
	}
	public void setScCharecter(SCCharecter scCharecter) {
		this.scCharecter = scCharecter;
	}
	public Integer getSequenceId() {
		return sequenceId;
	}
	public void setSequenceId(Integer sequenceId) {
		this.sequenceId = sequenceId;
	}
	public String getDialogue() {
		return dialogue;
	}
	public void setDialogue(String dialogue) {
		this.dialogue = dialogue;
	}
	@Override
	public String toString() {
		return "Dialogue [id=" + id + ", header=" + "::" + ", charecter=" + scCharecter + ", sequenceId=" + sequenceId
				+ ", dialogue=" + dialogue + "]";
	}
	
	
	
}
