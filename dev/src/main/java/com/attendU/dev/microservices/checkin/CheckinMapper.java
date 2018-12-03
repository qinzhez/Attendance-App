package com.attendU.dev.microservices.checkin;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.attendU.dev.microservices.bean.Checkin;


@Mapper
public interface CheckinMapper {
	
	public Void updateStatus(Integer attendance, String absentReason);

	public Integer checkin(@Param("uid") Long uid, @Param("rid") Long rid, @Param("aid") Long aid);

	public List<Checkin> getCheckinInfo(@Param("uid") Long uid, @Param("rid") Long rid, @Param("aid") Long aid);

}
