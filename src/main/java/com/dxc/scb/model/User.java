package com.dxc.scb.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.dxc.scb.model.Address;
import com.dxc.scb.model.Enums.Role;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users",uniqueConstraints = { @UniqueConstraint(columnNames = { "email", "role" }) })
public class User implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String username;

   
  //  @Size(min=6,max = 18, message="password lenght should be between 6 to 18 characters")
	@Column(name="password")
	private String password;
	
	@Column(name="phonenumber")
//	@Size(min = 10,message = "Phone number should be 10 digits")
	private String phoneNumber;
	
	//@Emails
	@Column(name="Email",unique=true)
	private String email;
	
//	@Column
//	@Enumerated(EnumType.STRING)
//	private Role  role;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Enums.Role role;
	
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders;
    
    @OneToOne(mappedBy = "user")
    private Cart cart;


    
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return List.of(new SimpleGrantedAuthority(role.name()));
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public static User build(User user) {
		
		List<SimpleGrantedAuthority> authorities = Arrays.stream(Enums.Role.values())
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toList());

        return new User(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getPhoneNumber(),
                user.getEmail(),
                user.getRole(),
                user.getAddress(),
                user.getOrders(),
                user.getCart()
                
                
        );
	}


    
}
