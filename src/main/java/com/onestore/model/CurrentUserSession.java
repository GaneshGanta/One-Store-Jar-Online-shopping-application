package com.onestore.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class CurrentUserSession extends User {

	public CurrentUserSession(Integer customerId, String key, LocalDateTime now, String email, String password,
			String role) {
		// TODO Auto-generated constructor stub
		this.userId = customerId;
		this.uuid = key;
		this.localDateTime = now;
		this.setEmail(email);
		this.setPassword(password);
		this.setRole(role);
	}
	@Id
	@Column(unique = true)
	private Integer userId;
	private String uuid;
	private LocalDateTime localDateTime;
	
}
