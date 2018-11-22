package com.attendU.dev.microservices.bean;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;


public class Activity implements Serializable{

	@Getter@Setter
	private Long aid;
    @Getter@Setter
    private String Name;
    @Getter@Setter
    private String Date;
    @Getter@Setter
	private Long acid;
    @Getter@Setter
    private String description;
}
