package com.dxc.scb.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.dxc.scb.model.Enums.Role;
import com.dxc.scb.model.User;

public interface UserRepository extends JpaRepository<User,Long>{

	Optional<User> findByEmail(String username);

	User findByRole(Role role);
	

}
