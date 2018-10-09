package com.attendU.dev;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class DevApplication {

	static Logger log = Logger.getLogger(DevApplication.class.getName());

	public static void main(String[] args) throws Exception {

		log.info("Start");
		log.debug("Debug info");

//		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession()) {
//			List<Map<String, Object>> result = session.selectList("sample.mybatis.selectTest");
//			result.forEach(row -> {
//				System.out.println("---------------");
//				row.forEach((columnName, value) -> {
//					System.out.printf("columnName=%s, value=%s%n", columnName, value);
//				});
//			});
//		}

		SpringApplication.run(DevApplication.class, args);
	}
}
