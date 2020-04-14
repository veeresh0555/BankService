package com.bankapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankapi.model.BankAccounts;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccounts, Long> {

}
