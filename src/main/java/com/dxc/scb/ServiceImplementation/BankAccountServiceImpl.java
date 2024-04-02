package com.dxc.scb.ServiceImplementation;

import org.springframework.stereotype.Service;

import com.dxc.scb.Repository.BankAccountRepository;
import com.dxc.scb.Service.BankAccountService;
import com.dxc.scb.model.BankAccount;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public double getBalance() {
        // Implementation to fetch bank account balance from the database
        // Fetching the bank account details from the database
        BankAccount bankAccount = bankAccountRepository.findCurrentUserBankAccount(); // Example method

        // Returning the balance from the fetched bank account entity
        return bankAccount.getBalance();
    }

    public boolean transferFunds(String recipientAccountNumber, double amount) {
        // Implementation to transfer funds to another account
        // This example assumes that you have access to a banking service or API
        // for transferring funds between accounts. Adjust the implementation as per your requirements.

        // Fetching sender's account details
        BankAccount senderAccount = bankAccountRepository.findCurrentUserBankAccount();

        // Fetching recipient's account details
        BankAccount recipientAccount = bankAccountRepository.findByAccountNumber(recipientAccountNumber);

        if (senderAccount != null && recipientAccount != null) {
            // Checking if the sender has sufficient balance for the transfer
            if (senderAccount.getBalance() >= amount) {
                // Performing the transfer
                senderAccount.setBalance(senderAccount.getBalance() - amount);
                recipientAccount.setBalance(recipientAccount.getBalance() + amount);

                // Saving the updated account balances
                bankAccountRepository.save(senderAccount);
                bankAccountRepository.save(recipientAccount);

                // Transfer successful
                return true;
            } else {
                // Insufficient balance
                return false;
            }
        } else {
            // Either sender or recipient account not found
            return false;
        }
    }
}
