package com.dxc.scb.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dxc.scb.model.BankAccount;


	@Repository
	public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

	    BankAccount findCurrentUserBankAccount();
	    BankAccount findByAccountNumber(String accountNumber);
	}


