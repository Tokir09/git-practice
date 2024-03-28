//package com.dxc.scb.Controller;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//import com.dxc.scb.Repository.AddressRepository;
//import com.dxc.scb.Repository.AdminRepository;
//import com.dxc.scb.Repository.UserRepository;
//import com.dxc.scb.model.Address;
//import com.dxc.scb.model.Enums;
//import com.dxc.scb.model.User;
//
//
//public class DatabaseLoader implements CommandLineRunner{
//	
//	private final UserRepository userRepo;
//	
//	private final AddressRepository addRepo;
//	
//    public DatabaseLoader(UserRepository userRepo,AddressRepository addRepo) {
//    	this.userRepo=userRepo;
//    	this.addRepo=addRepo;
//    }
//    
//    @Override
//    public void run(String... args){
//    	
//    	User adminAccount=userRepo.findByRole(Enums.Role.ADMIN);
//    	if(null==adminAccount) {
//    	User user1=new User();
//    	
//    	user1.setUsername("jaga");
//    	user1.setEmail("admin");
//    	user1.setRole(Enums.Role.ADMIN);
//    	user1.setPassword(new BCryptPasswordEncoder().encode("admin"));
//    	Address address = new Address();
//    	address.setApartment_Name("cloud valley");
//		address.setHouse_No("453");
//		address.setCity("Vizag");
//		address.setStreet_Name("ranga");
//		address.setPinCode(532142);
//    	user1.setAddress(address);
//    	userRepo.save(user1);
//    	addRepo.save(address);
//    	}
//    }
//}
