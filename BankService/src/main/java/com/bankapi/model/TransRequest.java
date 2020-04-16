package com.bankapi.model;

public class TransRequest {
	
	private long transid;
	
	private long frmmobilenumber;
	
	private long tomobilenumber;
	
	private Double amount;

	private String transdate;
	
	private String comment;
	

	public long getFrmmobilenumber() {
		return frmmobilenumber;
	}

	public void setFrmmobilenumber(long frmmobilenumber) {
		this.frmmobilenumber = frmmobilenumber;
	}

	public long getTomobilenumber() {
		return tomobilenumber;
	}

	public long getTransid() {
		return transid;
	}

	public void setTransid(long transid) {
		this.transid = transid;
	}

	public String getTransdate() {
		return transdate;
	}

	public void setTransdate(String transdate) {
		this.transdate = transdate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setTomobilenumber(long tomobilenumber) {
		this.tomobilenumber = tomobilenumber;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	
	
	
	

		
	
	
}
