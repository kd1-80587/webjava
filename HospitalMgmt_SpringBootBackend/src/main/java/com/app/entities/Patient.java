package com.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="patient")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Patient extends BaseEntity{
	@Column(length=30)
	private String name;
	@Column(name="mobile_no" ,length=10)
	private long mobileno;

}
