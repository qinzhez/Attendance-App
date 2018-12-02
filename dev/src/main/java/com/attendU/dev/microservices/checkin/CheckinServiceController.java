package com.attendU.dev.microservices.checkin;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.attendU.dev.microservices.bean.Checkin;
import com.attendU.dev.mybatis.MyBatisConnectionFactory;

@RestController
@CrossOrigin(origins = "*")
public class CheckinServiceController {

	@Autowired
	private RestTemplate restTemplate;

	private CheckinMapper checkinMapper;
	private SqlSession sqlSession;

	public CheckinServiceController() {
		sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		checkinMapper = sqlSession.getMapper(CheckinMapper.class);
	}

	@RequestMapping(value = "/checkin/{rid}/{aid}/{uid}", method = RequestMethod.POST)
	public ResponseEntity<Boolean> checkin(@PathVariable long rid, @PathVariable long aid, @PathVariable long uid) {
		
		int check = checkinMapper.checkin(uid, rid, aid);
		if (check != 0)
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		return new ResponseEntity<Boolean>(false, HttpStatus.OK);
	}
	
	
}

