package com.attendU.dev.microservices.bean;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class User implements Serializable{

	@Getter
	@Setter
	private Long id;

	@Getter@Setter
	private String firstName;
	@Getter@Setter
	private String middleName;
	@Getter@Setter
	private String lastName;
	@Getter@Setter
	private String username;
	@Getter@Setter
	private String password;
	@Getter@Setter
	private String email;
	@Getter@Setter
	private String cell;
}
