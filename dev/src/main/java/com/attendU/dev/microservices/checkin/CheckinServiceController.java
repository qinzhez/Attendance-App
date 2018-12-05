package com.attendU.dev.microservices.checkin;

import java.util.Date;
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

import com.attendU.dev.microservices.bean.Checkin;
import com.attendU.dev.microservices.bean.Participation;
import com.attendU.dev.mybatis.MyBatisConnectionFactory;

@RestController
@RequestMapping("/checkin")
@CrossOrigin(origins = "*")
public class CheckinServiceController {

	static private Logger log = Logger.getLogger(CheckinServiceController.class.getName());

	@Autowired
	private RestTemplate restTemplate;

	private CheckinMapper checkinMapper;
	private SqlSession sqlSession;

	public CheckinServiceController() {
		sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		checkinMapper = sqlSession.getMapper(CheckinMapper.class);
	}


	@RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
	public void updateStatus( @RequestBody Participation participation, @RequestBody String absentReason) {
		if (participation != null) {
			checkinMapper.updateStatus(1,absentReason);
		}
		return;
	}

	@RequestMapping(value = "/{aid}/{rid}/checkin/{uid}", method = RequestMethod.POST)
	public ResponseEntity<Boolean> checkin(@PathVariable long uid, @PathVariable long rid, @PathVariable long aid) {
		boolean check = true;
		if (uid <= 0 || aid <= 0 || rid <= 0)
			check = false;

		if (check) {
			try {
				check = false;
				Checkin checkin = new Checkin();
				checkin.setAid(aid);
				checkin.setRid(rid);
				checkin.setAttendance(1);
				checkin.setUid(uid);
				checkin.setCreateTime(new Date());
				int checked = checkinMapper.checkin(checkin);
				sqlSession.commit();
				check = (checked == 1) ? true : false;
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

	@RequestMapping(value = "/getCheckinInfo/{rid}/{aid}/{uid}", method = RequestMethod.GET)
	public @ResponseBody List<Checkin> getCheckinInfo(@PathVariable long uid, @PathVariable long rid, @PathVariable long aid) {
		return checkinMapper.getCheckinInfo(uid, rid, aid);
	}
}
