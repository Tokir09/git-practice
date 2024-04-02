package com.dxc.scb.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dxc.scb.model.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

    Wallet findCurrentUserWallet();
}
