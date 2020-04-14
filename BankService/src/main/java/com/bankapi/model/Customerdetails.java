package com.bankapi.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author veeru
 *
 */
@Entity
@Table(name="customer")
public class Customerdetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long cid;
	

	private String cname;
	
	
	private String mobileno;
	
	@OneToMany(mappedBy = "customer",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JsonIgnore
	private List<BankAccounts> baccounts;

	public long getCid() {
		return cid;
	}

	public void setCid(long cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public List<BankAccounts> getBaccounts() {
		return baccounts;
	}

	public void setBaccounts(List<BankAccounts> baccounts) {
		this.baccounts = baccounts;
	}
	
	
	
}
