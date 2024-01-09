package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "owner")
public class Owner extends BaseEntity {
	@Column(name = "f_name", length = 30)
	private String fName;
	@Column(name = "l_name", length = 30)
	private String lName;
	@Column(length = 30)
	private String email;

	public Owner(String fName, String lName, String email) {
		this.fName = fName;
		this.lName = lName;
		this.email = email;
	}

	public Owner() {
		// TODO Auto-generated constructor stub
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Owner [fName=" + fName + ", lName=" + lName + ", email=" + email + "]";
	}

}
