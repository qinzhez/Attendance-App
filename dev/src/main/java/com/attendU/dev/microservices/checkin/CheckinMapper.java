package com.attendU.dev.microservices.checkin;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.attendU.dev.microservices.bean.Checkin;

@Mapper
public interface CheckinMapper {

	public Integer checkin(@Param("rid") long l, @Param("aid") long m, @Param("uid") long n);

}
