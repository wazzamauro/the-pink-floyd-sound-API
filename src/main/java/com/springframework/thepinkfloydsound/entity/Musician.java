package com.springframework.thepinkfloydsound.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "musician")
@PrimaryKeyJoinColumn(name="people_id")
public class Musician extends Person {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "role", nullable = false)
	private String role;

	private enum Role {
		Guitar, Bass, Drum, Keyboard
	}

	public Musician() {
	}

	public Musician(String role) {
		super();
		this.role = role;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void inferRole() {
		switch (firstName) {
		case "David":
			setRole(Role.Guitar.toString());
			break;
		case "Roger":
			setRole(Role.Bass.toString());
			break;
		case "Richard":
			setRole(Role.Keyboard.toString());
			break;
		case "Nick":
			setRole(Role.Drum.toString());
			break;
		case "Syd":
			setRole(Role.Guitar.toString());
			break;
		default:
			setRole("null role");
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Musician [id=");
		builder.append(id);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", age=");
		builder.append(age);
		builder.append(", role=");
		builder.append(role);
		builder.append("]");
		return builder.toString();
	}
}
