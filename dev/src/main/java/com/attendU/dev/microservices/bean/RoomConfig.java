package com.attendU.dev.microservices.bean;

import lombok.Getter;
import lombok.Setter;

public class RoomConfig{
	@Getter@Setter
	private Long id;
	
	@Getter
	private RoomConfig sid;
	@Getter@Setter
	private Boolean anonymous;
}