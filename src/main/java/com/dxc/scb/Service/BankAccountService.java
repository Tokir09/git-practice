package com.dxc.scb.Service;

public interface BankAccountService {
    double getBalance();
    boolean transferFunds(String recipientAccountNumber, double amount);
}
