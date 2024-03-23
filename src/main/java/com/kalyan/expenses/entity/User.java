package com.kalyan.expenses.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "TBL_USER")
@Getter
@Setter
@ToString
public class User extends BaseEntity {

	@NotBlank(message = "Please enter user name")
	private String userName;

	@Column(unique = true)
	@Email(message = "Please enter valid user email")
	private String email;

	@JsonIgnore
	@NotNull(message = "please enter password")
	@Size(min = 5, message = "password should be atleaset 5 characters")
	private String password;

	private Integer age;

}
