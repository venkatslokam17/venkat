package com.slokam.sc.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity

public class SCCharecter {
	@Id
	@GeneratedValue
	private Long id;
	
//	@NotEmpty(message ="Name is required." )
	private String name;
	private String description;

	/*
	 * @Pattern(regexp = "^[A-Z]{5}[0-9]{4}[A-Z]$" ,message =
	 * "Pan card number is not correct" )
	 * 
	 * @NotEmpty private String panNumber;
	 * 
	 * 
	 * public String getPanNumber() { return panNumber; } public void
	 * setPanNumber(String panNumber) { this.panNumber = panNumber; }
	 */
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "SCCharecter [id=" + id + ", name=" + name + ", description=" + description + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(description, id, name);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SCCharecter other = (SCCharecter) obj;
		return Objects.equals(description, other.description) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name);
	}
	
	
	
}
