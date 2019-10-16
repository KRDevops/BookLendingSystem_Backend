package com.ing.bms.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table
@Getter
@Setter
@ToString
public class Transaction implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long transactionId;
	
	@Column(name = "transaction_type", nullable = false)
	private String transactionType;
	
	@Column(name = "transaction_date", nullable = false)
	private LocalDate transactionDate;
	
	@ManyToOne
	private User user_id;
	
	@ManyToOne
	private Book book_id;

}