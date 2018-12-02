package com.attendU.dev.microservices.checkin;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface CheckinMapper {
	
	public Void updateStatus(Integer attendance, String absentReason);

}
