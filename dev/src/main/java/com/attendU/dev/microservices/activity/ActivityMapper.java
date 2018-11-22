package com.attendU.dev.microservices.activity;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.attendU.dev.microservices.bean.Activity;
import com.attendU.dev.microservices.bean.TokenBean;
import com.attendU.dev.microservices.bean.User;

@Mapper
public interface ActivityMapper {

	public int registerActivity(Activity act);
	
	public List<Map<String, Object>> getActivities();
	
	public Activity getActivitybyId(long id);

	public Activity getActivitybyName(String activityname);
	
}

