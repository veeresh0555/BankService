package com.bankapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bankapi.model.Customerdetails;

@Repository
public interface CustomerRepository extends JpaRepository<Customerdetails, Long> {
	
	@Query("select ac from Customerdetails ac where ac.mobileno=:mobileno")
	public Optional<Customerdetails> findcustomerByMobileNo(@Param("mobileno") long mobileno); 
	
	
	
	
}
