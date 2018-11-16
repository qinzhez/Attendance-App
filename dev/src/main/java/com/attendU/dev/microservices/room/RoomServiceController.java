package com.attendU.dev.microservices.room;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.attendU.dev.microservices.bean.Room;
import com.attendU.dev.mybatis.MyBatisConnectionFactory;

@RestController
@RequestMapping("/room")
public class RoomServiceController {

	@Autowired
	private RestTemplate restTemplate;

	private static RoomMapper roomMapper;
	private static SqlSession sqlSession;

	public RoomServiceController() {
		sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		roomMapper = sqlSession.getMapper(RoomMapper.class);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public void getRoom(@PathVariable String id) {
		Room room = new Room();
	}
	
	@RequestMapping(value = "/findRoomById", method = RequestMethod.GET)
	public Room findRoomById(int rid) {
		return roomMapper.findRoomById(rid);
	}
	
	@RequestMapping(value = "/findRoomByAdmin", method = RequestMethod.GET)
	public List<Room> findRoomByAdmin(int adminId) {
		return roomMapper.findRoomByAdmin(adminId);
	}
	
	@RequestMapping(value = "/createRoom", method = RequestMethod.POST)
	public @ResponseBody int createRoom(Room room) {
		return roomMapper.createRoom(room);	
	}
	
	@RequestMapping(value = "/createRoom", method = RequestMethod.DELETE)
	public Room removeRoom(int rid) {
		return roomMapper.removeRoom(rid);
	}
	
	@RequestMapping(value = "/createRoom", method = RequestMethod.PUT)
	public Room updateRoom(Room room) {
		return roomMapper.updateRoom(room);	
	}

}