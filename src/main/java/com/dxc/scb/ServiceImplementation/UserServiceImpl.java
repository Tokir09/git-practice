package com.dxc.scb.ServiceImplementation;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.dxc.scb.Repository.UserRepository;
import com.dxc.scb.model.User;
import com.dxc.scb.Service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    @Autowired
	private UserRepository userRepository;

	@Override
	public UserDetailsService userDetailsService() {
		
		return new UserDetailsService() {
			
			@Override
			public UserDetails loadUserByUsername(String username) {
				return userRepository.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
			}
		};
	}
	
	@Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
	@Override
	public User loadUserById(Long id) {
	    User user = userRepository.findById(id)
	            .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + id));
	    return user.build(user);
	}

    @Override
    public void updateUser(Long userId, User updatedUser) {
        User user = loadUserById(userId);
        // Update user properties based on the updatedUser object
        user.setUsername(updatedUser.getUsername());
        user.setPassword(updatedUser.getPassword());
        user.setPhoneNumber(updatedUser.getPhoneNumber());
        user.setEmail(updatedUser.getEmail());
        user.setRole(updatedUser.getRole());
        user.setAddress(updatedUser.getAddress());
        // Update other properties as needed
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UsernameNotFoundException("User not found with id: " + userId);
        }
        userRepository.deleteById(userId);
    }

	
}
