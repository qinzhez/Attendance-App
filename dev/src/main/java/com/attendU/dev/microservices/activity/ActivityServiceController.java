package com.attendU.dev.microservices.activity;

import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.attendU.dev.microservices.bean.Activity;
import com.attendU.dev.mybatis.MyBatisConnectionFactory;

@RestController
@RequestMapping("/activity")
@CrossOrigin(origins = "*")
public class ActivityServiceController {

	static private Logger log = Logger.getLogger(ActivityServiceController.class.getName());
	
	@Autowired
	private RestTemplate restTemplate;

	private static ActivityMapper activityMapper;
	private static SqlSession sqlSession;

	public ActivityServiceController() {
		sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		activityMapper = sqlSession.getMapper(ActivityMapper.class);
	}
	
	@RequestMapping(value = "/aid/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Activity>> getActivityById(@PathVariable String id) {
		List<Activity> activity = activityMapper.getActivityById(Long.parseLong(id));
		if (activity == null)
			return new ResponseEntity<List<Activity>>(activity, HttpStatus.OK);
		return new ResponseEntity<List<Activity>>(activity, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/activityname/{name}", method = RequestMethod.GET)
	public @ResponseBody List<Activity> getActivityByName(@PathVariable String name) {
		return activityMapper.getActivitybyName(name);
	}
	
	@RequestMapping(value = "/getActivityList/{rid}", method = RequestMethod.GET)
	public @ResponseBody List<Activity> getActivityByRoom(@PathVariable long rid) {
		return activityMapper.getActivityByRoom(rid);
	}

	@RequestMapping(value = "/createActivity/{uid}/{rid}", method = RequestMethod.POST)
	public ResponseEntity<Boolean> createActivity(@PathVariable Long uid, @PathVariable Long rid, @RequestBody Activity reg) {
				boolean check = true;
				if (reg != null && uid != null && uid > 0 && rid != null && rid > 0) {	
					if (reg.getName() == null || reg.getDate() == null)
						check = false;
				} else
					check = false;
		
				if (check) {
					try {
						check = false;
						int ret = activityMapper.createActivity(uid, rid, reg);
						reg.setAid(activityMapper.getCreatedAID().longValue());
						activityMapper.updateParticipation_activity(uid, rid, reg.getAid());
						activityMapper.updateRALink(rid, reg.getAcid());
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
	
	@RequestMapping(value = "/removeActivity", method = RequestMethod.POST)
	public ResponseEntity<Boolean> removeActivity(long aid) {
		List<Activity> activity = activityMapper.getActivityById(aid);
		if (activity != null) {
			activityMapper.removeActivity(aid);
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(false, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/updateActivity", method = RequestMethod.POST)
	public ResponseEntity<Activity> updateActivity(@RequestBody Activity activity) {
		List<Activity> activity_get = activityMapper.getActivityById(activity.getAid());
		if (activity_get != null) {
			activityMapper.updateActivity(activity);	
			return new ResponseEntity<Activity>(activity, HttpStatus.OK);
		}
		return new ResponseEntity<Activity>(activity, HttpStatus.OK);
	}

	@RequestMapping(value = "/startActivity/{aid}", method = RequestMethod.POST)
	public ResponseEntity<Boolean> startActivity(@PathVariable String aid){
		List<Activity> activity = activityMapper.getActivityById(Long.parseLong(aid));
		if (activity != null){
			activityMapper.startActivity(aid);
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(false, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/endActivity/{aid}", method = RequestMethod.POST)
	public ResponseEntity<Boolean> endActivity(@PathVariable String aid){
		List<Activity> activity = activityMapper.getActivityById(Long.parseLong(aid));
		if (activity != null){
			activityMapper.endActivity(aid);
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(false, HttpStatus.OK);
	}
}
