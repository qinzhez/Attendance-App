package com.attendU.dev.microservices.activity;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.attendU.dev.microservices.bean.Activity;
import com.attendU.dev.microservices.bean.Room;
import com.attendU.dev.mybatis.MyBatisConnectionFactory;

@RestController
public class ActivityServiceController {

	@Autowired
	private RestTemplate restTemplate;

	private ActivityMapper activityMapper;
	private SqlSession sqlSession;

	public ActivityServiceController() {
		sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		activityMapper = sqlSession.getMapper(ActivityMapper.class);
	}

	@RequestMapping(value = "/activity/{id}", method = RequestMethod.GET)
	public void getActivity(@PathVariable String id) {
		Activity cur = new Activity();
		List<Map<String, Object>> result = activityMapper.selectTest();
		result.forEach(row -> {
			System.out.println("---------------");
			row.forEach((columnName, value) -> {
				System.out.printf("columnName=%s, value=%s%n", columnName, value);
			});
		});
	}
	
	@RequestMapping(value = "/activity/{id}", method = RequestMethod.GET)
	public ResponseEntity<Activity> getActivitybyId(@PathVariable long id) {
		Activity activity = new Activity();
		activity = activityMapper.getActivitybyId(id);
		if (activity == null)
			return new ResponseEntity<Activity>(activity, HttpStatus.NOT_FOUND);
		return new ResponseEntity<Activity>(activity, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/activity/{name}", method = RequestMethod.GET)
	public ResponseEntity<Activity> getActivitybyName(@PathVariable String name) {
		Activity activity = new Activity();
		activity = activityMapper.getActivitybyName(name);
		if (activity == null)
			return new ResponseEntity<Activity>(activity, HttpStatus.NOT_FOUND);
		return new ResponseEntity<Activity>(activity, HttpStatus.OK);
		
	}
	
}
//check the valid information: null or not valid format
//use try catch
//check the edge of format