package com.attendU.dev.microservices.activity;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ActivityMapper {

	List<Map<String, Object>> selectTest();
}

