package com.bankapi.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="bankaccounts")
public class BankAccounts {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "accountnumber")
	@SequenceGenerator(name="accountnumber",sequenceName = "accountseq",allocationSize = 12)
	@Column(name="acno",updatable = false,nullable = false)
	private Long acno;
	
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "cid")
	private Customerdetails customer;
	
	private Double balance;

	public Long getAcno() {
		return acno;
	}

	public void setAcno(Long acno) {
		this.acno = acno;
	}

	public Customerdetails getCustomer() {
		return customer;
	}

	public void setCustomer(Customerdetails customer) {
		this.customer = customer;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}
	
	
	
	
	
	

}
