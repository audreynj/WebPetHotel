package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Audrey N. Johnston - ajohnston10
 * CIS175
 * Feb 12, 2024
 */
@Entity
@Table(name="pets")

public class Pet {

	@Id
	@GeneratedValue
	@Column(name="ID")
	private int id;
	
	@Column(name="TYPE")
	private String type;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="OWNER")
	private String owner;

	
	//Getter and Setter methods
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	

	//Constructors
	public Pet() {
		super();
	}
	
	public Pet (String type, String name, String owner) {
		super();
		this.type = type;
		this.name = name;
		this.owner = owner;
	}
	
	
	public	String returnPetDetails() {	
		return "Owner: " + this.owner + " - Pet Name: " + this.name + " - Type: " + this.type;
	}
	
	
	
}