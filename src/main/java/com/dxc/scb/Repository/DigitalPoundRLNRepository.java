package com.dxc.scb.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dxc.scb.model.DigitalPound3RLN;

@Repository
public interface DigitalPoundRLNRepository extends JpaRepository<DigitalPound3RLN, Long> {

    // Spring Data JPA will provide the findById method automatically
}
