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

	@RequestMapping(value = "/activity/{id}", method = RequestMethod.GET)
	public void getActivity(@PathVariable String id) {
		Activity cur = new Activity();
		List<Map<String, Object>>result=activityMapper.getActivities();
		result.forEach(row -> { 
			System.out.println("---------------");
			row.forEach((columnName, value) -> {
				System.out.printf("columnName=%s, value=%s%n", columnName, value);
			});
		});
	}
	
	@RequestMapping(value = "/activity/{id}", method = RequestMethod.POST)
	public ResponseEntity<Object> registration(@RequestBody Activity act) {
		boolean check = true;
		if (act != null) {
			if (act.getAid() == null)
				check = false;
			if (act.getAcid() == null)
				check = false;
			if (act.getName() == null || act.getName().length() < 2)
				check = false;
			if(act.getDate()==null || act.getDate().length()<2)
				check=false;
		} else
			check = false;

		// Update into db
		try {
			int ret = activityMapper.updateActivity(act);
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
}
//check the valid information: null or not valid format
//use try catch
//check the edge of format