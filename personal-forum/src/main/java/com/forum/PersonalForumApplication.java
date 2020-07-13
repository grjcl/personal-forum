package com.forum;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.forum.mapper")
public class PersonalForumApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonalForumApplication.class, args);
		
	}

}
