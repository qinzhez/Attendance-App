package com.attendU.dev.microservices.bean;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;


public class Activity implements Serializable{

	@Getter
	@Setter
	private Long id;

	public Activity() {

	}
}
