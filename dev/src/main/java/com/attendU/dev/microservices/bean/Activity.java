package com.attendU.dev.microservices.bean;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;


public class Activity implements Serializable{

	@Getter@Setter
	private Long aid;
	@Getter@Setter
	private Long acid;
	@Getter@Setter
	private String name;
	@Getter@Setter
	private Date date;
}
