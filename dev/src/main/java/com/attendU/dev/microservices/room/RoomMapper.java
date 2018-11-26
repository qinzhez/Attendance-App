package com.attendU.dev.microservices.room;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.attendU.dev.microservices.bean.Room;

@Mapper
public interface RoomMapper {
	
	public Integer createRoom(Room room);
	
	public Integer removeRoom(Long rid);
	
	public Integer updateRoom(Long rid, String name, Long rcid, Integer participationNum);

	public Room getRoomById(Long rid);
	
	public Room getRoombyName(String name);

	public List<Map<String, Room>> getRoomByAdmin(Long adminId);
	
}

