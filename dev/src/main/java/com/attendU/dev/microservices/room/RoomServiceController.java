package com.attendU.dev.microservices.room;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.attendU.dev.microservices.bean.Room;
import com.attendU.dev.mybatis.MyBatisConnectionFactory;

@RestController
@RequestMapping("/room")
public class RoomServiceController {

	static private Logger log = Logger.getLogger(RoomServiceController.class.getName());


	@Autowired
	private RestTemplate restTemplate;

	private static RoomMapper roomMapper;
	private static SqlSession sqlSession;

	public RoomServiceController() {
		sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		roomMapper = sqlSession.getMapper(RoomMapper.class);
	}
	
	@RequestMapping(value = "/rid/{id}", method = RequestMethod.GET)
	public ResponseEntity<Room> getRoom(@PathVariable String id) {
		Room room = roomMapper.getRoomById(Long.parseLong(id));
		if (room == null)
			return new ResponseEntity<Room>(room, HttpStatus.NOT_FOUND);
		return new ResponseEntity<Room>(room, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/roomName/{name}", method = RequestMethod.GET)
	public ResponseEntity<Boolean> getRoomByName(@PathVariable String name) {
		Room room = roomMapper.getRoombyName(name);
		if (room == null)
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

	
	@RequestMapping(value = "/getRoomByAdmin/{adminId}", method = RequestMethod.GET)
	public List<Map<String, Object>> getRoomByAdmin(long adminId) {
		return roomMapper.getRoomByAdmin(adminId);
	}
	
	@RequestMapping(value = "/createRoom", method = RequestMethod.POST)
	public ResponseEntity<Boolean> createRoom(@RequestBody Room room) {
		// sanity check
		boolean check = true;
		if (room != null) {	
			if (room.getName() == null || room.getParticipationNum() <= 0 || room.getRcid()  == null)
				check = false;
		}
		else
			check = false;
		
		//update into db
		if (check) {
			try {
				int ret = roomMapper.createRoom(room);
				check = (ret == 1) ? true : false;
				sqlSession.commit();
			} catch (Exception e) {
				sqlSession.rollback();
				log.error(e);
			}
		}
		if (check) 
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		return new ResponseEntity<Boolean>(false, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/removeRoom", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> removeRoom(long rid) {
		Room room = roomMapper.getRoomById(rid);
		if (room != null) {
			roomMapper.removeRoom(rid);
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/updateRoom", method = RequestMethod.PUT)
	public ResponseEntity<Room> updateRoom(long rid, String name, long rcid, int participationNum) {
		Room room = roomMapper.getRoomById(rid);
		if (room != null) {
			roomMapper.updateRoom(rid, name, rcid, participationNum);	
			return new ResponseEntity<Room>(room, HttpStatus.OK);
		}
		return new ResponseEntity<Room>(room, HttpStatus.OK);
	}

}