package com.bankapi.service;

import java.util.List;

import com.bankapi.exception.RecordsNotFoundException;
import com.bankapi.model.BankAccounts;
import com.bankapi.model.Customerdetails;
import com.bankapi.model.TransRequest;

public interface BankAccountService {

	public List<BankAccounts> getallBankDetails();

	public List<Customerdetails> getallCustDetails();

	public Customerdetails saveOrUpdatecustomer(Customerdetails custdetail);

	public List<BankAccounts> findacBymobile(long mobileno) throws RecordsNotFoundException;

	public Customerdetails findtransReq(TransRequest transreq) throws RecordsNotFoundException ;

}
