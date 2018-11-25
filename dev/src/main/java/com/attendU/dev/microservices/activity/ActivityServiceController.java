package com.attendU.dev.microservices.activity;

import java.util.Date;
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
import com.attendU.dev.mybatis.MyBatisConnectionFactory;

@RestController
@RequestMapping("/activity")
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
	
	@RequestMapping(value = "/aid/{id}", method = RequestMethod.GET)
	public ResponseEntity<Activity> getUser(@PathVariable String id) {
		Activity activity = activityMapper.getActivityById(Long.parseLong(id));
		if (activity == null)
			return new ResponseEntity<Activity>(activity, HttpStatus.NOT_FOUND);
		return new ResponseEntity<Activity>(activity, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/activityname/{name}", method = RequestMethod.GET)
	public ResponseEntity<Object> getActivityByName(@PathVariable String name) {
		Activity activity = activityMapper.getActivitybyName(name);
		if (activity == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/findRoomByConfigId/{acid}", method = RequestMethod.GET)
	public List<Map<String, Object>> getActivityByConfigId(long acid) {
		return activityMapper.getActivityByConfigId(acid);
	}
	
	@RequestMapping(value = "/createActivity", method = RequestMethod.POST)
	public ResponseEntity<Object> createActivity(@RequestBody Activity reg) {
		// sanity check
				boolean check = true;
				if (reg != null) {	
					if (reg.getName() == null || reg.getDate() == null || reg.getAcid() == null)
						check = false;
				}
				else
					check = false;
				if (check) {
					try {
						int ret = activityMapper.createActivity(reg);
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
	
	@RequestMapping(value = "/removeActivity", method = RequestMethod.DELETE)
	public ResponseEntity<Object> removeActivity(long aid) {
		Activity activity = activityMapper.getActivityById(aid);
		if (activity != null) {
			activityMapper.removeActivity(aid);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/updateActivity", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateActivity(long aid, String name, Date date, Long acid) {
		Activity activity = activityMapper.getActivityById(aid);
		if (activity != null) {
			activityMapper.updateActivity(aid, name, date, acid);	
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
}
