package com.dxc.scb.model;
import java.sql.Date;



import com.dxc.scb.model.Enums.PaymentMode;
import com.dxc.scb.model.Enums.PaymentStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@RequiredArgsConstructor
@Getter
@Setter
@Entity
public class PaymentModeModel {


		@Id
		@GeneratedValue
	    private Long id;
	    private String name; // e.g., Bank Account, E-Wallet, Digital Pound
	    private String description; // Optional



	    @Override
	    public String toString() {
	        return "PaymentMode{" +
	                "id=" + id +
	                ", name='" + name + '\'' +
	                ", description='" + description + '\'' +
	                '}';
	    }
	}


    

