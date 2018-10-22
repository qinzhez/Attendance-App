package com.attendU.dev.microservices.user;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.attendU.dev.microservices.bean.User;
import com.attendU.dev.mybatis.MyBatisConnectionFactory;
import com.netflix.discovery.converters.Auto;

@RestController
@CrossOrigin(origins = "*")
public class UserServiceController {

	static private Logger log = Logger.getLogger(UserServiceController.class.getName());

	@Autowired
	private RestTemplate restTemplate;

	private UserMapper userMapper;
	private SqlSession sqlSession;
	private AuthenticationService auth;

	public UserServiceController() {
		sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		userMapper = sqlSession.getMapper(UserMapper.class);
		auth = new AuthenticationService(userMapper, sqlSession);
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public void getUser(@PathVariable String id) {
		User user = new User();

	}

	@RequestMapping(value = "/user/registration", method = RequestMethod.POST)
	public ResponseEntity<Object> registration(@RequestBody User reg) {
		boolean check = true;
		if (reg != null) {
			if (reg.getFirstName() == null || reg.getFirstName().length() < 2)
				check = false;
			if (reg.getLastName() == null || reg.getLastName().length() < 2)
				check = false;
			if (reg.getEmail() == null || !validEmail(reg.getEmail()))
				check = false;
		} else
			check = false;

		// Update into db
		try {
			int ret = userMapper.registerUser(reg);
			check = ret == 1 ? true : false;
			sqlSession.commit();
		} catch (Exception e) {
			sqlSession.rollback();
			log.error(e);
		}

		if (check)
			return new ResponseEntity<>(HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	public ResponseEntity<Map<String, String>> login(@RequestParam("username") String username, @RequestParam("password") String password){
		Token token = auth.login(username, password);
		if(token == null)
			return null; // TODO:
		return null;
	}

	private boolean validEmail(String email) {
		if (email == null)
			return false;
		if (!email.contains("@"))
			return false;
		email = email.substring(email.indexOf('@'));
		if (!email.contains("."))
			return false;

		return true;
	}
}
