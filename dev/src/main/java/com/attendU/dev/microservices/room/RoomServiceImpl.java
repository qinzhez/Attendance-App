package com.attendU.dev.microservices.room;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.attendU.dev.microservices.bean.Room;

@Service
public class RoomServiceImpl implements RoomService{
	
	@Autowired
	private RoomDao roomDao;
	
	@Override
	public Room findRoomById(int rid) {
		return roomDao.findRoomById(rid);
	}

	@Override
	public List<Room> findRoomByAdmin(int adminId) {
		return roomDao.findRoomByAdmin(adminId);
	}

	@Override
	public void createRoom(Room room) {
		roomDao.createRoom(room);
		
	}

	@Override
	public void removeRoom(int rid) {
		roomDao.removeRoom(rid);
		
	}

	@Override
	public void updateRoom(Room room) {
		roomDao.updateRoom(room);
		
	}

}
