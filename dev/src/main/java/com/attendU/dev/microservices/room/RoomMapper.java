package com.attendU.dev.microservices.room;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.attendU.dev.microservices.bean.Room;

@Mapper
public interface RoomMapper {

	public Integer createRoom(Room room);

	public Integer removeRoom(Long rid);

	public Void updateRoom(Room room);

	public Room getRoomById(Long rid);

	public Room getRoombyName(String name);

	public List<Map<String, Room>> getRoomByAdmin(Long adminId);

	public Integer getCreatedRID();

	public Integer updateParticipation(@Param("uid") Long uid, @Param("rid") Long rid);

}

