package com.bankapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankapi.exception.RecordsNotFoundException;
import com.bankapi.model.BankAccounts;
import com.bankapi.model.Customerdetails;
import com.bankapi.model.TransRequest;
import com.bankapi.service.BankAccountService;

@RestController
@RequestMapping("/customerbank")
public class BankController {

	@Autowired
	Environment env;
	
	@Autowired
	BankAccountService bservice;
	
	@GetMapping("/info")
	public String info() {
		String port=env.getProperty("local.server.port");
		return "BankController Running on "+port;
	}
	
	@GetMapping("/listofac")
	public ResponseEntity<List<BankAccounts>> getAllBankDetails(){
		List<BankAccounts> listOfacounts=bservice.getallBankDetails();
		return new ResponseEntity<List<BankAccounts>>(listOfacounts,new HttpHeaders(),HttpStatus.OK);
	}
	
	@GetMapping("/listofcustomers")
	public ResponseEntity<List<Customerdetails>> getAllAccounts(){
		List<Customerdetails> listOfcustomers=bservice.getallCustDetails();
		return new ResponseEntity<List<Customerdetails>>(listOfcustomers,new HttpHeaders(),HttpStatus.OK);
	}
	
	@PostMapping("/savecustomer")
	public ResponseEntity<Customerdetails> createOrUpdateCustomer(Customerdetails custdetail){//@RequestBody 
		Customerdetails updatecustomer=bservice.saveOrUpdatecustomer(custdetail);
		return new ResponseEntity<>(updatecustomer,new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/{mobileno}")
	public ResponseEntity<List<BankAccounts>> findaccountByMobile(@PathVariable("mobileno") long mobileno) throws RecordsNotFoundException {
		List<BankAccounts> acBymobile=bservice.findacBymobile(mobileno);
		return new ResponseEntity<List<BankAccounts>>(acBymobile,new HttpHeaders(),HttpStatus.OK);
	}
	
	@PostMapping("/checktransreq")//@RequestBody 
	public ResponseEntity<Customerdetails> checktransRequest(TransRequest transreq) throws RecordsNotFoundException {
		Customerdetails bankacc=bservice.findtransReq(transreq);
		return new ResponseEntity<Customerdetails>(bankacc,new HttpHeaders(),HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
}
