package com.attendU.dev.microservices.checkin;

import org.apache.ibatis.session.SqlSession;
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
import com.attendU.dev.microservices.bean.Participation;
import com.attendU.dev.microservices.bean.Room;
import com.attendU.dev.microservices.bean.User;
import com.attendU.dev.mybatis.MyBatisConnectionFactory;

@RestController
public class CheckinServiceController {

	@Autowired
	private RestTemplate restTemplate;

	private CheckinMapper checkinMapper;
	private SqlSession sqlSession;

	public CheckinServiceController() {
		sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		checkinMapper = sqlSession.getMapper(CheckinMapper.class);
	}

	@RequestMapping(value = "/checkin/{rid}/{aid}/{user}", method = RequestMethod.POST)
	public void checkin(@PathVariable String rid, @PathVariable String aid, @PathVariable User user) {
			
	}
	
	@RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
	public void updateRoom( @RequestBody Participation participation, @RequestBody String absentReason) {
		if (participation != null) {
			checkinMapper.updateStatus(1,absentReason);
		}
		return;
	}
}

