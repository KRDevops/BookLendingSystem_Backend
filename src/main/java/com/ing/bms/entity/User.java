package com.ing.bms.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table
@Getter
@Setter
@ToString
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;
	
	@Column(name = "user_name", nullable = false)
	private String userName;
	
	@Column(name = "email_id", nullable = false, unique = true)
	private String emailId;
	
	@Column(name = "phone_number", nullable = false)
	private Long phoneNumber;
	
	@Column(name = "password", nullable = false)
	private String password;
	

}
