package com.bankapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankapi.exception.RecordsNotFoundException;
import com.bankapi.model.BankAccounts;
import com.bankapi.model.Customerdetails;
import com.bankapi.model.TransRequest;
import com.bankapi.repository.BankAccountRepository;
import com.bankapi.repository.CustomerRepository;

@Service
public class BankAccountServiceImpl implements BankAccountService {

	@Autowired
	BankAccountRepository brepository;
	
	@Autowired
	CustomerRepository crepository;
	
	@Override
	public List<BankAccounts> getallBankDetails() {
		// TODO Auto-generated method stub
		return brepository.findAll();
	}

	@Override
	public List<Customerdetails> getallCustDetails() {
		// TODO Auto-generated method stub
		return crepository.findAll();
	}

	@Override
	public Customerdetails saveOrUpdatecustomer(Customerdetails custdetail) {
		Optional<Customerdetails> custdetails=crepository.findById(custdetail.getCid());
		if(custdetails.isPresent()) {
			Customerdetails custByid=custdetails.get();
			custByid.setCid(custdetail.getCid());
			custByid.setCname(custdetail.getCname());
			custByid.setMobileno(custdetail.getMobileno());
			List<BankAccounts> listofaccounts=custByid.getBaccounts();
			listofaccounts.stream().forEach(lst->lst.setCustomer(custByid));
			listofaccounts.forEach(System.out::println);
			custByid.setBaccounts(listofaccounts);
			return crepository.save(custByid);
		}else {
			List<BankAccounts> bankac=custdetail.getBaccounts();
			System.out.println("This is else block.....");
			bankac.stream().forEach(System.out::println);
			bankac.stream().forEach(lst->lst.setCustomer(custdetail));
			custdetail.setBaccounts(bankac);
			return crepository.save(custdetail);
		}
	}

	/*
	 * @Override public List<Customerdetails> findacBymobile(long mobileno) throws
	 * RecordsNotFoundException {
	 * 
	 * return null; }
	 */
	@Override
	public List<BankAccounts> findacBymobile(long mobileno) throws RecordsNotFoundException {
		Optional<Customerdetails> cd1=crepository.findcustomerByMobileNo(mobileno);
		if(cd1.isPresent()) {
			Customerdetails cd2=cd1.get();
			List<BankAccounts> bankac=cd2.getBaccounts();
			bankac.stream().forEach(lst->System.out.println("AccountNumber: "+lst.getAcno()+"\t Bank Balance: "+lst.getBalance()));
			return bankac;
		}else {
			System.out.println("No related Accounts found Your enter Mobile Number: "+mobileno);
			throw new RecordsNotFoundException("No related Accounts found Your enter Mobile Number");
		}
		
	}

	@Override
	public Customerdetails findtransReq(TransRequest transreq) throws RecordsNotFoundException  {
		System.out.println("Enter BankAccountServiceImpl: ***********");
		System.out.println("TransReq: "+transreq.getTransid()
		+"\t fromMobile: "+transreq.getFrmmobilenumber()
		+"\t toMobile: "+transreq.getTomobilenumber()
		+"\t amount: "+transreq.getAmount()
		+"\t comment: "+transreq.getComment()
		+"\t transDate: "+transreq.getTransdate()
				);
		
		Optional<Customerdetails> fromac=crepository.findcustomerByMobileNo(transreq.getFrmmobilenumber());
		if(fromac.isPresent()) {
			System.out.println("From Mobile Number/Cid prasent");
			Optional<Customerdetails> toacc=crepository.findcustomerByMobileNo(transreq.getTomobilenumber());
			Customerdetails fcdetails=fromac.get();//fromaccountcheck
			List<BankAccounts> bankac=fcdetails.getBaccounts();
			BankAccounts frba=new BankAccounts();
			bankac.stream().forEach(bclst->frba.setBalance(bclst.getBalance()));
			if(toacc.isPresent()) {
				if(frba.getBalance()>=transreq.getAmount()) {
				System.out.println("To Mobile Number/Cid prasent");
				Customerdetails tocdetails=toacc.get();//toaccountcheck
				List<BankAccounts> tobankac=tocdetails.getBaccounts();
				
				BankAccounts toba=new BankAccounts();
				tobankac.stream().forEach(toblst->toba.setBalance(toblst.getBalance()));//getbalance to mobile number
				//frba.setBalance(transreq.getAmount());
				bankac.stream().forEach(fromlist->fromlist.setBalance(frba.getBalance()-transreq.getAmount()));
				bankac.forEach(bac->System.out.println("\fromAc: "+bac.getAcno()+"\t Balance: "+bac.getBalance()));
				
				Customerdetails finalcdet1=new Customerdetails();
				finalcdet1.setBaccounts(bankac);
				crepository.save(finalcdet1);
				
				Customerdetails finalcdet2=new Customerdetails();
				tobankac.stream().forEach(toblist->toblist.setBalance(toba.getBalance()+transreq.getAmount()));
				tobankac.forEach(bc->System.out.println("\t toAc: "+bc.getAcno()+"\t Balance: "+bc.getBalance()));
				crepository.save(finalcdet2);
				
				
				
				
				//finalcdet2.setBaccounts(tobankac);
				
				return finalcdet2; 
			}else {
				throw new RecordsNotFoundException("Insufficiant Amount in from account");	
			}	
				
			}else {
				throw new RecordsNotFoundException("To MobileNumber/CID Not found");
			}
			
		}else {
			throw new RecordsNotFoundException("From MobileNumber/CID Not found");
		}
	}

}
