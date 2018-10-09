package com.attendU.dev.microservices.room;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.attendU.dev.microservices.bean.Room;
import com.attendU.dev.mybatis.MyBatisConnectionFactory;

@RestController
public class RoomServiceController {

	@Autowired
	private RestTemplate restTemplate;

	private RoomMapper roomMapper;
	private SqlSession sqlSession;

	public RoomServiceController() {
		sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		roomMapper = sqlSession.getMapper(RoomMapper.class);
	}

	@RequestMapping(value = "/room/{id}", method = RequestMethod.GET)
	public void getRoom(@PathVariable String id) {
		Room room = new Room();

	}
}
