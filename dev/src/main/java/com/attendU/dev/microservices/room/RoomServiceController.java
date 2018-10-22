package com.attendU.dev.microservices.room;

import java.util.List;
import java.util.Map;

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

	private static RoomMapper roomMapper;
	private static SqlSession sqlSession;

	public RoomServiceController() {
		sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		roomMapper = sqlSession.getMapper(RoomMapper.class);
	}
	
	@RequestMapping(value = "/room/{id}", method = RequestMethod.GET)
	public void getRoom(@PathVariable String id) {
		Room room = new Room();

	}
	
	public static void createRoom(Room room){
		try {
			roomMapper.createRoom(room);
            sqlSession.commit();  		
		} finally {
			sqlSession.close();
		}
	}
	
	public static void removeRoom(int id){
		try {
			sqlSession.delete("com.attendU.dev.microservices.room",id); 
			System.out.println("Room deleted."); 
			sqlSession.commit() ;  		
			} finally {
				sqlSession.close();
			}
	}
	
	public static void findRoomById(int rid) {
			try {
				Room room = roomMapper.findRoomById(rid);
				if (room == null)
					System.out.println("Required room not exists.");
				else
					System.out.println(room);
				} finally {
					sqlSession.close();
				}
			}
	
	public static void findRoomByAdmin(int adminId) {
		try {
			Room room = roomMapper.findRoomByAdmin(adminId);
			if (room == null)
				System.out.println("Required room not exists.");
			else
				System.out.println(room);
			} finally {
				sqlSession.close();
			}
		}
	
	public static void updateRoom(Room room){
		try {
			roomMapper.updateRoom(room);
            sqlSession.commit() ;  		
		} finally {
			sqlSession.close();
		}
		
	}
	
	}
	
	