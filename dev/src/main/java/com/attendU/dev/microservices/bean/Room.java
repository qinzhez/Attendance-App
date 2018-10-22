package com.attendU.dev.microservices.bean;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class Room{

	@Getter@Setter
	private Long id;
	
	@Getter@Setter
	private String name;
	@Getter@Setter
	private RoomConfig rcid;
	@Getter@Setter
	private int participationNum;
	@Getter@Setter
	private User adminId;
	
}