package com.attendU.dev.microservices.activity;

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

import com.attendU.dev.microservices.bean.Activity;
import com.attendU.dev.microservices.bean.User;
import com.attendU.dev.microservices.user.UserServiceController;
import com.attendU.dev.mybatis.MyBatisConnectionFactory;

@RestController
public class ActivityServiceController {

	static private Logger log = Logger.getLogger(ActivityServiceController.class.getName());
	
	@Autowired
	private RestTemplate restTemplate;

	private ActivityMapper activityMapper;
	private SqlSession sqlSession;

	public ActivityServiceController() {
		sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		activityMapper = sqlSession.getMapper(ActivityMapper.class);
	}
	
	@RequestMapping(value = "/activity/id/{id}", method = RequestMethod.GET)
	public ResponseEntity<Activity> getUser(@PathVariable String id) {
		Activity activity = activityMapper.getActivitybyId(Long.parseLong(id));
		if (activity == null)
			return new ResponseEntity<Activity>(activity, HttpStatus.NOT_FOUND);
		return new ResponseEntity<Activity>(activity, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/activity/activityname/{activityname}", method = RequestMethod.GET)
	public ResponseEntity<Object> getActivityByName(@PathVariable String activityname) {
		Activity activity = activityMapper.getActivitybyName(activityname);
		if (activity == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/activity/registration_activity", method = RequestMethod.POST)
	public ResponseEntity<Object> registration(@RequestBody Activity reg) {
		boolean check = true;
		if (reg != null) {
			if (reg.getName() == null || reg.getName().length() < 2)
				check = false;
			if (reg.getDate()== null || reg.getDate().length() < 2)
				check = false;
		} else
			check = false;

		// Update into db
		if (check) {
			try {
				int ret = activityMapper.registerActivity(reg);
				check = (ret == 1) ? true : false;
				sqlSession.commit();
			} catch (Exception e) {
				sqlSession.rollback();
				log.error(e);
			}
		}

		if (check)
			return new ResponseEntity<>(HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
//check the valid information: null or not valid format
//use try catch
//check the edge of format