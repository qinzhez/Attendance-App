package com.attendU.dev.microservices.room;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.attendU.dev.microservices.bean.Room;

@Mapper
public interface RoomMapper {
	
	public Room findRoomById(int rid);
	
	public List<Room> findRoomByAdmin(int adminId);
	
	public int createRoom(String name, int rcid, int participationNum);
	
	public int removeRoom(int rid);
	
	public int updateRoom(Room room);
	
}
