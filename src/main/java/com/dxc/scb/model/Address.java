package com.dxc.scb.model;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "addresses")
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
	private String House_No;
	@Column(nullable = false)
	private String Apartment_Name;
	@Column(nullable = false)
	private String Street_Name;
	@Column(nullable = false)
	private Integer PinCode;
	@Column(nullable = false)
	private String city;
//    @Column(nullable = false)
//    private String street;
//
//    @Column(nullable = false)
//    private String city;
//
//    
//    @Column(nullable = false)
//    private String zipCode;

    
    // Other address properties, constructors, getters, and setters

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
 
    // Getters and setters for user

    // Other methods
}
