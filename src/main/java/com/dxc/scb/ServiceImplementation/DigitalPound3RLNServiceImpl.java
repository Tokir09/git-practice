package com.dxc.scb.ServiceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.scb.Repository.DigitalPoundRLNRepository;
import com.dxc.scb.Service.DigitalPound3RLNService;
import com.dxc.scb.model.DigitalPound3RLN;

@Service
public class DigitalPound3RLNServiceImpl implements DigitalPound3RLNService {

	@Autowired
	private DigitalPoundRLNRepository digitalPoundRLNRepository;

	public DigitalPound3RLNServiceImpl(DigitalPoundRLNRepository digitalPound3RLNRepository) {
		this.digitalPoundRLNRepository = digitalPound3RLNRepository;
	}

	@Override
	public double getBalance(long id) {
		// Fetching the DigitalPound3RLN details from the database
		DigitalPound3RLN digitalPound3RLN = digitalPoundRLNRepository.findById(id).orElse(null);

		// Returning the balance from the fetched DigitalPound3RLN entity
		return digitalPound3RLN != null ? digitalPound3RLN.getBalance() : 0.0;
	}

	@Override
	public boolean lock(long id) {
		// Fetching the DigitalPound3RLN details from the database
		DigitalPound3RLN digitalPound3RLN = digitalPoundRLNRepository.findById(id).orElse(null);

		if (digitalPound3RLN != null && !digitalPound3RLN.isLocked()) {
			// Locking the resource
			digitalPound3RLN.setLocked(true);
			digitalPoundRLNRepository.save(digitalPound3RLN);
			return true;
		}
		return false;
	}

	@Override
	public boolean unlock(long id) {
		// Fetching the DigitalPound3RLN details from the database
		DigitalPound3RLN digitalPound3RLN = digitalPoundRLNRepository.findById(id).orElse(null);

		if (digitalPound3RLN != null && digitalPound3RLN.isLocked()) {
			// Unlocking the resource
			digitalPound3RLN.setLocked(false);
			digitalPoundRLNRepository.save(digitalPound3RLN);
			return true;
		}
		return false;
	}
}
