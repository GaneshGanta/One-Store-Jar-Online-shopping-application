package com.onestore.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Setter
@Getter

@NoArgsConstructor
@AllArgsConstructor
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer customerId;
	
	@NotNull(message = "First Name field is Mandatory!")
	private String firstName;
	
	@NotNull(message = "Last Name field is Mandatory!")
	private String lastName;
	
	@NotNull(message = "Mobile Numberfield is Mandatory!")
	private String mobileNumber;
	
	@NotNull(message = "email required to fill")
	private String email;
	
	@NotNull(message = "password required to fill")
	private String password;
	
//	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	private Address address;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL)
	private List<Order> order = new ArrayList<>();
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	private Cart cart;

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", mobileNumber=" + mobileNumber + ", email=" + email + ", password=" + password + "]";
	}
	
	
	
}
























