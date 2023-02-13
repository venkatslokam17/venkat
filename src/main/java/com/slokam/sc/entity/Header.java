package com.slokam.sc.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Header {

	 @Id
	 @GeneratedValue
	 private Long id;
	 private String intOrExt;
	 private String location;
	 private String dayTime;
	 private String description;
	 @OneToMany(mappedBy = "header", cascade = CascadeType.ALL )
	 private List<Action> actionList;
	 @OneToMany(mappedBy = "header", cascade = CascadeType.ALL)
	 private List<Dialogue> dialogueList;
	 @ManyToOne
	 @JoinColumn(name="sid")
	 private Script script;
	 
	 
	public Script getScript() {
		return script;
	}
	public void setScript(Script script) {
		this.script = script;
	}
	public List<Action> getActionList() {
		return actionList;
	}
	public void setActionList(List<Action> actionList) {
		this.actionList = actionList;
	}

	public List<Dialogue> getDialogueList() {
		return dialogueList;
	}
	public void setDialogueList(List<Dialogue> dialogueList) {
		this.dialogueList = dialogueList;
	}
	public String getIntOrExt() {
		return intOrExt;
	}
	public void setIntOrExt(String intOrExt) {
		this.intOrExt = intOrExt;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDayTime() {
		return dayTime;
	}
	public void setDayTime(String dayTime) {
		this.dayTime = dayTime;
	}
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
	@Override
	public String toString() {
		return "Header [id=" + id + ", intOrExt=" + intOrExt + ", location=" + location + ", dayTime=" + dayTime
				+ ", description=" + description + ", actionList=" + actionList + ", dialogueList=" + dialogueList
				+ "]";
	}
	
	
	
	
 
	 
 
}
