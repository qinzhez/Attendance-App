package com.attendU.dev.microservices.room;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.attendU.dev.microservices.bean.Room;

@Mapper
public interface RoomMapper {
	
	public Room findRoomById(int rid);
	
	public List<Room> findRoomByAdmin(int adminId);
	
	public Integer createRoom(String name, int rcid, int participationNum);
	
	public Room removeRoom(int rid);
	
	public Room updateRoom(Room room);
	
}
