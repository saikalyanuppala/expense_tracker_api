package com.kalyan.expenses.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
	private String userName;
	private String email;
	private String password;
	private Integer age = 0;
}
