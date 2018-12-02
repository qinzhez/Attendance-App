package com.attendU.dev.microservices.room;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.attendU.dev.microservices.bean.Participation;
import com.attendU.dev.microservices.bean.Room;
import com.attendU.dev.microservices.bean.TokenBean;
import com.attendU.dev.microservices.bean.User;
import com.attendU.dev.microservices.user.Token;
import com.attendU.dev.mybatis.MyBatisConnectionFactory;

@RestController
@RequestMapping("/room")
@CrossOrigin(origins = "*")
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

	@RequestMapping(value = "/getRooms/{uid}", method = RequestMethod.GET)
	public ResponseEntity<List<Room>> getRooms(@PathVariable Long uid) {
		List<Room> rooms = roomMapper.getRoomByUid(uid);

		return new ResponseEntity<List<Room>>(rooms, HttpStatus.OK);
	}

	@RequestMapping(value = "/roomName/{name}", method = RequestMethod.GET)
	public ResponseEntity<Room> getRoomByName(@PathVariable String name) {
		Room room = roomMapper.getRoombyName(name);
		if (room == null)
			return new ResponseEntity<Room>(room, HttpStatus.NOT_FOUND);
		return new ResponseEntity<Room>(room, HttpStatus.OK);
	}

	@RequestMapping(value = "/getRoomByAdmin/{adminId}", method = RequestMethod.GET)
	public @ResponseBody List<Room> getRoomByAdmin(@PathVariable long adminId) {
		return roomMapper.getRoomByAdmin(adminId);
	}

	@RequestMapping(value = "/createRoom/{id}", method = RequestMethod.POST)
	public ResponseEntity<Boolean> createRoom(@PathVariable Long id, @RequestBody Room room) {
		// sanity check
		boolean check = true;
		if (room != null && id != null && id > 0) {
			if (room.getName() == null || room.getParticipationNum() <= 0)
				check = false;
		} else
			check = false;

		// update into db
		if (check) {
			try {
				check = false;
				int ret = roomMapper.createRoom(room);
				room.setRid(roomMapper.getCreatedRID().longValue());
				roomMapper.updateParticipation(id, room.getRid());
				roomMapper.updateAdmin(id, room.getRid());
				sqlSession.commit();
				check = (ret == 1) ? true : false;
			} catch (Exception e) {
				sqlSession.rollback();
				log.error(e);
				check = false;
			}
		}
		if (check)
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		return new ResponseEntity<Boolean>(false, HttpStatus.OK);
	}

	@RequestMapping(value = "/quitRoom", method = RequestMethod.POST)
	public ResponseEntity<Boolean> quitRoom(@RequestBody Participation pt) {
		int deleted = 0;
		if (pt.getUid() != null && pt.getRid() != null && pt.getUid() > 0 && pt.getRid() > 0) {
			try {
				deleted = roomMapper.quitRoom(pt.getUid(), pt.getRid());
				sqlSession.commit();
			} catch (Exception e) {
				sqlSession.rollback();
				log.error(e);
			}
		}
		if (deleted > 0)
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		return new ResponseEntity<Boolean>(false, HttpStatus.OK);
	}

	@RequestMapping(value = "/removeRoom", method = RequestMethod.POST)
	public ResponseEntity<Boolean> removeRoom(@RequestBody Room room) {
		int deleted = 0;
		Boolean pass = false;
		TokenBean tmp = new TokenBean();
		tmp.setUid(room.getAdminId());
		tmp.setToken(room.getName());
		try {
			RequestEntity<TokenBean> requestEntity = new RequestEntity<TokenBean>(tmp, HttpMethod.POST, new URI("http://localhost:8004//user/validToken"));
			ResponseEntity<Boolean> responseEntity = restTemplate
					.exchange("http://localhost:8004//user/validToken", HttpMethod.POST, requestEntity,
							new ParameterizedTypeReference<Boolean>() {});
			pass = responseEntity.getBody();
		}catch (Exception e) {
			pass = false;
		}

		if(room.getRid() != null && room.getRid()>0 && pass.booleanValue()) {
			try {
				// remove all activity
				List<Long> activities = roomMapper.getActivityByRid(room.getRid());
				roomMapper.removeActivities(activities);
				// remove other tables with rid
				roomMapper.removeRoomLinking(room.getRid());
				// remove room
				deleted = roomMapper.removeRoom(room.getRid());
				sqlSession.commit();
			} catch (Exception e) {
				sqlSession.rollback();
				log.error(e);
			}
		}
		if(deleted > 0 && !pass.booleanValue())
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		return new ResponseEntity<Boolean>(false, HttpStatus.OK);
	}

	@RequestMapping(value = "/updateRoom", method = RequestMethod.POST)
	public ResponseEntity<Room> updateRoom(@RequestBody Room room) {
		Room room_get = roomMapper.getRoomById(room.getRid());
		if (room_get != null) {
			roomMapper.updateRoom(room);
			return new ResponseEntity<Room>(room, HttpStatus.OK);
		}
		return new ResponseEntity<Room>(room, HttpStatus.OK);
	}

}