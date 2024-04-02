package com.dxc.scb.ServiceImplementation;

import org.springframework.stereotype.Service;

import com.dxc.scb.Repository.WalletRepository;
import com.dxc.scb.Service.WalletService;
import com.dxc.scb.model.Wallet;

@Service
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;

    public WalletServiceImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public double getBalance() {
        // Implementation to fetch wallet balance from the database
        // Fetching the wallet details from the database
        Wallet wallet = walletRepository.findCurrentUserWallet(); // Example method

        // Returning the balance from the fetched wallet entity
        return wallet.getBalance();
    }

    @Override
    public String getAccountNumber() {
        // Implementation to fetch wallet account number
        // Fetching the wallet details from the database
        Wallet wallet = walletRepository.findCurrentUserWallet(); // Example method

        // Returning the account number from the fetched wallet entity
        return wallet.getAccountNumber();
    }
}
