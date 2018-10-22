package com.attendU.dev.microservices.room;

import org.apache.ibatis.annotations.Mapper;

import com.attendU.dev.microservices.bean.Room;

@Mapper
public interface RoomMapper {
	
	public Room findRoomById(int rid);
	
	public Room findRoomByAdmin(int adminId);
	
	public void createRoom(Room room);
	
	public void removeRoom(int rid);
	
	public void updateRoom(Room room);
	
}
