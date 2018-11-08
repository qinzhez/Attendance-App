package com.attendU.dev.microservices.activity;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.attendU.dev.microservices.bean.Activity;

@Mapper
public interface ActivityMapper {

	public int updateActivity(Activity act);
	
	public List<Map<String, Object>> getActivities();
	
	public Map<String, Object> getActivitiesbyName(String name);
}

