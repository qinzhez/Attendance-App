package com.attendU.dev.microservices.user;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.attendU.dev.microservices.bean.User;
import com.attendU.dev.mybatis.MyBatisConnectionFactory;

@RestController
public class UserServiceController {

	@Autowired
	private RestTemplate restTemplate;

	private UserMapper userMapper;
	private SqlSession sqlSession;

	public UserServiceController() {
		sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		userMapper = sqlSession.getMapper(UserMapper.class);
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public void getUser(@PathVariable String id) {
		User user = new User();

	}
}
