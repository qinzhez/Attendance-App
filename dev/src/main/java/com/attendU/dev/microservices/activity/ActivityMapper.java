package com.attendU.dev.microservices.activity;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.amazonaws.services.autoscaling.model.Activity;
import com.attendU.dev.microservices.bean.TokenBean;
import com.attendU.dev.microservices.bean.User;

@Mapper
public interface ActivityMapper {

	List<Map<String, Object>> selectTest();
	
	public int registerActivityByUser(User user);

	public List<Map<String, Object>> getActivity();
	
	public Activity getActivitybyId(long id);

	public Activity getActivitybyName(String username);
	
	public List<User> getUserByActivityId(long id);
}

