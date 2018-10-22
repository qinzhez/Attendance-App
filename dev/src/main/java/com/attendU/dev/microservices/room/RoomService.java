package com.attendU.dev.microservices.room;

import java.util.List;

import com.attendU.dev.microservices.bean.Room;

public interface RoomService {
	
	public Room findRoomById(int rid);
	
	public List<Room> findRoomByAdmin(int adminId);
	
	public void createRoom(Room room);
	
	public void removeRoom(int rid);
	
	public void updateRoom(Room room);
}
