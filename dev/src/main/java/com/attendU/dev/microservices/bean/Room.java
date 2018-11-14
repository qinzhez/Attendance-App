package com.attendU.dev.microservices.bean;

import lombok.Getter;
import lombok.Setter;

public class Room{

	@Getter@Setter
	private Long uid;
	
	@Getter@Setter
	private String name;
	@Getter@Setter
	private Long rcid; //RoomConfig id
	@Getter@Setter
	private Integer participationNum;
	@Getter@Setter
	private User adminId;
	
}