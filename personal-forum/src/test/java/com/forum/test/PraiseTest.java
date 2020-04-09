package com.forum.test;


import java.util.Date;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.forum.entity.Praise;
import com.forum.service.IPraiseService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PraiseTest {
	
	@Autowired
	private IPraiseService priseService;
	
	@org.junit.Test
	public void Test() {
		
		Praise p = new Praise();
		p.setArticleId(10);
		p.setUid(2);
		Date now = new Date();
		p.setCreatedUser("zs");
		p.setCreatedTime(now);
		p.setModifiedUser("zs");
		p.setModifiedTime(now);
		Integer toDealWithPraise = priseService.ToDealWithPraise(p);
		System.out.println("返回值=="+toDealWithPraise);
		
		
	}

	
}
