package com.dxc.scb.model;


import java.util.Date;
import java.util.List;

import com.dxc.scb.model.Enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;


import java.util.Date;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @Column(nullable = false)
    private Date orderDate;
 
    // Other order properties, constructors, getters, and setters
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Enums.OrderStatus orderStatus;
    
    //added1042024
    private double totalAmount;
    
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    
    @OneToOne
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    private Payment payment;
 
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    //added1042024
   // @JoinColumn(name = "order_id", referencedColumnName = "id")
    private List<OrderProduct> orderProducts;
 

}
