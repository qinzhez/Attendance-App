package com.attendU.dev.microservices.activity;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.attendU.dev.microservices.bean.Activity;

@Mapper
public interface ActivityMapper {
	
    public Integer createActivity(Long uid, Long rid, Activity activity);
	
	public Integer removeActivity(Long aid);
	
	public Void updateActivity(Activity activity);

	public List<Activity> getActivityById(Long aid);

	public List<Activity> getActivitybyName(String name);

	public List<Map<String, Object>> getActivityByConfigId(Long acid);

	public List<Activity> getActivityByRoom(Long rid);
	
	public Integer updateParticipation_activity(@Param("uid") Long uid, @Param("rid") Long rid, @Param("aid") Long aid);
	
	public Integer updateRALink(Long rid, Long aid);
	
	public Integer getCreatedAID();

	public Integer startActivity(String aid);
	
	public Integer endActivity(String aid);

}

