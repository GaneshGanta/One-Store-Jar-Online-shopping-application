package com.onestore.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CurrentUserSession {
	
	@Id
	@Column(unique = true)
	private Integer userId;
	private LocalDateTime localDateTime;
	private String uuid;
	

}
